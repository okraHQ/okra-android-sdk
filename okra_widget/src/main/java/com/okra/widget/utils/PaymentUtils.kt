package com.okra.widget.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import com.google.gson.Gson
import com.hover.sdk.api.HoverParameters
import com.okra.widget.interfaces.BankServices
import com.okra.widget.models.HoverStrategy
import com.okra.widget.models.IntentData
import com.okra.widget.models.payment.PaymentModel
import com.okra.widget.utils.BankSlugs.*

object PaymentUtils {

    var  currentBankService:BankServices? = null
    var bankSlug = ""
    var recordId = ""
    var pin = ""
    var nuban = ""
    var json = ""
    var bgColor = ""
    var accentColor = ""
    var buttonColor = ""
    var lastPaymentAction = false
    var paymentConfirmed = true
    var bankMiscellaneous = ""

    @JvmStatic
    fun startPayment(json: String, mContext: Context){
        val paymentModel:PaymentModel? = try {
            Gson().fromJson(json,PaymentModel::class.java)
        }catch (ex:Exception){
            null
        }
        if(paymentModel == null) {
            println("ERROR")
            return
        }

        paymentConfirmed = false
        val isSameBank = paymentModel.sameBank ?: false
        val userHasMultipleAccounts = paymentModel.multipleAccounts ?: false
        val creditAccountNumber = paymentModel.clientAccount?.nuban ?: ""
        val amount = paymentModel.amount.toString()
        val creditBankName = paymentModel.creditBankName ?: ""
        val debitAccountNumber = getDebitAccountNumber(userHasMultipleAccounts,paymentModel.customerAccount?.nuban ?: "",bankSlug)
        currentBankService?.makePayment(isSameBank,userHasMultipleAccounts)?.let { fireIntent(mContext, it, IntentData(bankSlug, recordId, pin, nuban, json, bgColor, accentColor, buttonColor, "true", creditAccountNumber, amount, creditBankName, isSameBank.toString(),debitAccountNumber)) }
    }

    private fun getDebitAccountNumber(userHasMultipleAccounts: Boolean, customerAccountNumber: String,customerBankSlug:String): String {
        val bank = customerBankSlug.toBankSlug() ?: return ""
        if(!userHasMultipleAccounts) return ""
        if(customerAccountNumber.length != 10) return ""
       return when(bank){
           EcoBank -> TODO()
           FidelityBank -> TODO()
           FirstBankOfNigeria -> customerAccountNumber.replaceRange(3,7,"XXXX")
           FirstCityMonumentBank -> TODO()
           GTB -> TODO()
           PolarisBank -> TODO()
           StanbicIBTC -> TODO()
           StandardCharteredBank -> TODO()
           SterlingBank -> TODO()
           UnionBank -> TODO()
           UBA -> TODO()
           WemaBank -> TODO()
           UnityBank -> TODO()
           Alat -> TODO()
           AccessBank -> customerAccountNumber.replaceRange(2,6,"XXXX")
       }

    }

    fun confirmPayment( mContext: Context){
        paymentConfirmed = true
        Log.i("partyneverstops", "ABOUT TO FIRE CONFIRMATION")
        val strategy =   currentBankService?.confirmPayment()
        strategy?.let {
            Log.i("partyneverstops", "ABOUT TO FIRE")
            try {
                object : CountDownTimer(5000, 5000) {
                    override fun onFinish() {
                        Log.i("partyneverstops", "COUNT DOWN TIMER FIRING")
                        fireIntent(mContext, it, IntentData(bankSlug, recordId, pin, nuban, json, bgColor, accentColor, buttonColor, "true"))
                    }
                    override fun onTick(millisUntilFinished: Long) {

                    }
                }.start()

            }catch (ex:Exception){
                Log.i("partyneverstops", ex.message)
            }

        }
    }

    @JvmStatic
    fun fireIntent(mContext: Context, hoverStrategy: HoverStrategy, intentData: IntentData) {
        try {
            Log.i("partyneverstops", "-------About to start an intent--------")
            Log.i("partyneverstops", hoverStrategy.actionId)
            Log.i("partyneverstops", hoverStrategy.actionId)
            val intent: Intent
            val hoverBuilder = HoverParameters.Builder(mContext)
                    .private_extra("id", hoverStrategy.id)
                    .private_extra("bankResponseMethod", hoverStrategy.bankResponseMethod.toString())
                    .private_extra("isFirstAction", hoverStrategy.isFirstAction.toString())
                    .private_extra("isLastAction", hoverStrategy.isLastAction.toString())
                    .private_extra("bank", intentData.bankSlug)
                    .private_extra("recordId", intentData.recordId)
                    .private_extra("miscellaneous", bankMiscellaneous)
                    .private_extra("bgColor", intentData.bgColor)
                    .private_extra("accentColor", intentData.accentColor)
                    .private_extra("buttonColor", intentData.buttonColor)
                    .private_extra("payment", intentData.payment)
                    .private_extra("authPin", intentData.pin)
                    .private_extra("nuban", intentData.nuban)
                    .private_extra("apiKey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZDkyODhlYTE4MmQzZDAwMGNiN2M0ODYiLCJpYXQiOjE2MDE5ODIwODV9.R59jXuebkEPSrBjSSyo0rIveiw07-YrioEtP-YxcXWc")
                    .setHeader(hoverStrategy.header).initialProcessingMessage(hoverStrategy.processingMessage)
                    .setHeader(String.format("Connecting to %s...", intentData.bankSlug.replace("-", " ")))
                    .initialProcessingMessage("Verifying your credentials")
                    .request(hoverStrategy.actionId)
            Log.i("the start", "as I suspected")
            if ((!intentData.pin.isEmpty() || !intentData.pin.trim { it <= ' ' }.isEmpty()) && hoverStrategy.requiresPin) {
                hoverBuilder.extra("pin", intentData.pin)
            }
            if (!hoverStrategy.isFirstAction) {
                hoverBuilder.setSim(BankUtils.selectedSim.osReportedHni)
            }
            if(intentData.paymentAmount.isNotEmpty()){
                if(intentData.bankSlug == "access-bank"|| intentData.bankSlug == "polaris-bank" ||intentData.bankSlug  == "fidelity-bank" || intentData.bankSlug == "guaranty-trust-bank") {
                    var amount = intentData.paymentAmount.toDoubleOrNull()?.toInt()
                    Log.i("partyneverstops", "This is the amount: $amount")
                    amount = amount ?: 50
                    Log.i("partyneverstops", "This is the FORMATTED amount: $amount")
                    intentData.paymentAmount = amount.toString()
                }
                hoverBuilder.extra("amount",intentData.paymentAmount)
            }

            if ((!intentData.nuban.isEmpty() || !intentData.nuban.trim { it <= ' ' }.isEmpty()) && hoverStrategy.requiresAccountNumber) {
                hoverBuilder.extra("accountNumber", intentData.nuban)
            }
            if(intentData.paymentCreditAccount.isNotEmpty()){
                hoverBuilder.extra("accountNumber",intentData.paymentCreditAccount)
            }
            if(intentData.debitAccountNumber.isNotEmpty()){
                hoverBuilder.extra("debitAccountNumber",intentData.debitAccountNumber)
            }

            var creditBankName =  intentData.paymentCreditBankName
            if(hoverStrategy.differentActionForInternal && intentData.isSameBank == "true") creditBankName = ""
            if(creditBankName.isNotEmpty()){
                hoverBuilder.extra("bank",intentData.paymentCreditBankName)
            }

            Log.i("the start", "of good things")
            hoverBuilder.finalMsgDisplayTime(0)
            intent = hoverBuilder.buildIntent()
            Log.i("partyneverstops", hoverStrategy.actionId)
            (mContext as Activity).startActivityForResult(intent, 0)
        } catch (ex: java.lang.Exception) {
            Log.i("partyneverstops", "-------an error occured--------")
            Log.i("partyneverstops", ex.cause!!.message)
            Log.i("partyneverstops", ex.message)
        }
    }
}

fun delayFor(millseconds:Long,action:() -> Unit ){
    Handler().postDelayed({
        action()
    }, millseconds)
}


