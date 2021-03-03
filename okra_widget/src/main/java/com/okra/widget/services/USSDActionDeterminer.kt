package com.okra.widget.services

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.hover.sdk.api.Hover
import com.hover.sdk.sims.SimInfo
import com.okra.widget.interfaces.BankServices
import com.okra.widget.models.IntentData
import com.okra.widget.utils.BankUtils
import com.okra.widget.utils.PaymentUtils
import com.okra.widget.utils.bank.*


interface USSDActionDeterminer {
    fun onUSDDResultReceived(data: Intent, okraOptions: MutableMap<String, Any>)
}

class USSDActionDeterminerImpl(private val context: Context):USSDActionDeterminer{

    override fun onUSDDResultReceived(data: Intent, okraOptions: MutableMap<String, Any>) {
        val ussd_messages:Array<String>? = data.extras?.get("ussd_messages") as? Array<String>
        val session_messages:Array<String>? = data.extras?.get("session_messages") as? Array<String>
        val actionID = data.extras?.get("action_id") as String?
        session_messages?.forEach {
            println("USSD DATA SESSION $it")
        }
        println("USSD SESSION MESASAGE ${session_messages.toString()}")
        ussd_messages?.forEach {
            println("USSD DATA USSD MESSAGES $it")
        }

        println("USSD DATA $actionID")
        println("USSD DATA ${bundle2string(data.extras)}")
        val map = BankUtils.getInputExtras(data)
        val bankServices = BankUtils.getBankImplementation(map["bank"])
        if (BankUtils.isFirstAction(map)) {
            BankUtils.selectedSim = getSelectedSim(context, data)
            try {
                Log.i("partyneverstops", BankUtils.selectedSim.countryIso)
                Log.i("partyneverstops", BankUtils.selectedSim.imsi)
                Log.i("partyneverstops", BankUtils.selectedSim.networkCountryIso)
                Log.i("partyneverstops", BankUtils.selectedSim.networkOperator)
                Log.i("partyneverstops", BankUtils.selectedSim.networkOperatorName)
                Log.i("partyneverstops", BankUtils.selectedSim.operatorName)
                Log.i("partyneverstops", BankUtils.selectedSim.osReportedHni)
                Log.i("partyneverstops", BankUtils.selectedSim.iccId)
                Log.i("partyneverstops", BankUtils.selectedSim.isRoaming.toString())
            }catch (ex:Exception){

            }

        }
        val payment:Boolean = okraOptions["payment"] as Boolean? ?: false
        val charge = okraOptions["charge"]
        val isPayment = payment && (charge != null)
        Log.i("partyneverstops", "This is payment $isPayment")
        Log.i("partyneverstops", "payment $payment")
        Log.i("partyneverstops", "charge $charge")
        if(isPayment){
            runPaymentNextActions(bankServices,map)
            return
        }
        Log.i("partyneverstops", "action count is " + bankServices.actionCount)
        Log.i("partyneverstops", "and present index is " + bankServices.index)
        Log.i("partyneverstops", if (bankServices.hasNext()) "this bank has next" else "this bank doesnt have next")

        if (bankServices.hasNext()) {
            runAnotherAction(bankServices, map,isPayment.toString())
        } else {
            Log.i("partyneverstops", "-------It has finished and reset to 1--------")
            bankServices.index = 1
        }
    }

    private fun runPaymentNextActions(bankServices: BankServices, map: Map<String, String>) {
        PaymentUtils.bankMiscellaneous =  map["miscellaneous"] ?: ""
        when{
            PaymentUtils.lastPaymentAction -> {
                if(!PaymentUtils.paymentConfirmed){
                    if(bankServices is FirstBank){
                        return
                    }
                    PaymentUtils.confirmPayment(context);
                }
                return
            }

            bankServices is WemaBank || bankServices is GuaranteeTrustBank ||bankServices is ZenithBank
                    ||bankServices is UBA ||bankServices is FCMB||bankServices is PolarisBank  -> {
                PaymentUtils.lastPaymentAction = true
                return
            }
        }

        try {
            object : CountDownTimer(5000, 5000) {
                override fun onFinish() {
                    BankUtils.fireIntent(
                            context,
                            bankServices.getActionByIndex(2),
                            IntentData(
                                    map["bank"],
                                    map["recordId"],
                                    if (map.containsKey("authPin")) map["authPin"] else "",
                                    if (map.containsKey("nuban")) map["nuban"] else "",
                                    map["miscellaneous"],
                                    map["bgColor"],
                                    map["accentColor"],
                                    map["buttonColor"],
                                    "true"
                            ))
                    Log.i("partyneverstops", "-------It has finished and reset to 1--------")
                    bankServices.index = 1
                    PaymentUtils.lastPaymentAction = true
                }

                override fun onTick(millisUntilFinished: Long) {
                    // millisUntilFinished    The amount of time until finished.
                }
            }.start()
        } catch (ignored: Exception) {
            val c = ignored.message
            val v = ignored.cause!!.message
            Log.i("partyneverstops", "-------ERROR $c")
        }
    }

    private fun runAnotherAction(bankServices: BankServices, map: MutableMap<String, String>,isPayment:String) {
        try {
            object : CountDownTimer(5000, 5000) {
                override fun onFinish() {
                    try {
                        BankUtils.fireIntent(
                                context,
                                bankServices.nextAction,
                                IntentData(
                                        map["bank"],
                                        map["recordId"],
                                        if (map.containsKey("authPin")) map["authPin"] else "",
                                        if (map.containsKey("nuban")) map["nuban"] else "",
                                        map["miscellaneous"],
                                        map["bgColor"],
                                        map["accentColor"],
                                        map["buttonColor"],
                                        isPayment
                                ))
                        bankServices.index = bankServices.index + 1
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onTick(millisUntilFinished: Long) {
                    // millisUntilFinished    The amount of time until finished.
                }
            }.start()
        } catch (ignored: Exception) {
            val c = ignored.message
            val v = ignored.cause!!.message
        }
    }

    private fun getSelectedSim(context: Context?, intent: Intent): SimInfo? {
        return try {
            BankUtils.simSlot = intent.getIntExtra("slot_idx", -1)
            Hover.getPresentSims(context)[BankUtils.simSlot]
        } catch (exception: java.lang.Exception) {
            null
        }
    }

}

fun bundle2string(bundle: Bundle?): String? {
    if (bundle == null) {
        return null
    }
    var string = "Bundle{"
    for (key in bundle.keySet()) {
        if(key != "input_extras"){
            string += " " + key + " => " + bundle[key] + ";"
        }

    }
    string += " }Bundle"
    return string
}