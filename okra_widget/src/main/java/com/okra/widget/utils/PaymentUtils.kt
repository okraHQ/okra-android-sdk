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
import com.okra.widget.utils.BankUtils.getBankLayout

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
    var lastPaymentModel:PaymentModel? = null
    var lastJsonString:String = ""
    var options = ""

    fun retryPaymentWithMultipleAccounts(context: Context){
        lastPaymentModel?.multipleAccounts = true
        lastPaymentModel?.let { startPaymentAction(it,context, lastJsonString) }
    }

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
        lastPaymentModel = paymentModel
        lastJsonString = json
        paymentConfirmed = false
        startPaymentAction(paymentModel, mContext, json)
    }

    private fun startPaymentAction(paymentModel: PaymentModel, mContext: Context, json: String) {
        val isSameBank = paymentModel.sameBank ?: false
        val userHasMultipleAccounts = paymentModel.multipleAccounts ?: false
        val creditAccountNumber = paymentModel.clientAccount?.nuban ?: ""
        val amount = paymentModel.amount.toString()
        val creditBankName = paymentModel.creditBankName ?: ""
        val debitAccountNumber = getDebitAccountNumber(userHasMultipleAccounts, paymentModel.customerAccount?.nuban
                ?: "", bankSlug)
        Log.i("partyneverstops", "PAYMENT MODEL ${paymentModel}")
        currentBankService?.makePayment(isSameBank, userHasMultipleAccounts)?.let { fireIntent(mContext, it, IntentData(bankSlug, recordId, pin, nuban, json, bgColor, accentColor, buttonColor, "true", creditAccountNumber, amount, creditBankName, isSameBank.toString(), debitAccountNumber, options)) }
    }

    private fun getDebitAccountNumber(userHasMultipleAccounts: Boolean, customerAccountNumber: String,customerBankSlug:String): String {
        val bank = customerBankSlug.toBankSlug() ?: return ""
        if(!userHasMultipleAccounts) return ""
        if(customerAccountNumber.length != 10) return ""
       return when(bank){
           EcoBank -> ""
           FidelityBank -> ""
           FirstBankOfNigeria -> customerAccountNumber.replaceRange(3,7,"XXXX")
           FirstCityMonumentBank ->  ""
           GTB -> ""
           PolarisBank -> ""
           StanbicIBTC -> ""
           StandardCharteredBank -> ""
           SterlingBank -> ""
           UnionBank -> ""
           UBA -> ""
           WemaBank -> ""
           UnityBank -> ""
           Alat -> ""
           AccessBank -> customerAccountNumber.replaceRange(2,6,"XXXX")
       }

    }

    fun confirmPayment( mContext: Context){
        paymentConfirmed = true
        val strategy =   currentBankService?.confirmPayment()
        strategy?.let {
            try {
                object : CountDownTimer(5000, 5000) {
                    override fun onFinish() {
                        fireIntent(mContext, it, IntentData(bankSlug, recordId, pin, nuban, json, bgColor, accentColor, buttonColor, "true", options))
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
                    .private_extra("options",intentData.getOptions())
                    .private_extra("buttonColor", intentData.buttonColor)
                    .private_extra("payment", intentData.payment)
                    .private_extra("authPin", intentData.pin)
                    .private_extra("nuban", intentData.nuban)
                    .setHeader(hoverStrategy.header).initialProcessingMessage(hoverStrategy.processingMessage)
                    .setHeader(String.format("Connecting to %s...", intentData.bankSlug.replace("-", " ")))
                    .initialProcessingMessage("Verifying your credentials")
                    .sessionOverlayLayout(getBankLayout(mContext, intentData.getBankSlug()))
                    .request(hoverStrategy.actionId)
            if ((!intentData.pin.isEmpty() || !intentData.pin.trim { it <= ' ' }.isEmpty()) && hoverStrategy.requiresPin) {
                Log.i("partyneverstops", "PIN ADDED")
                hoverBuilder.extra("pin", intentData.pin)
            }
            if (!hoverStrategy.isFirstAction) {
                hoverBuilder.setSim(BankUtils.selectedSim.osReportedHni)
            }
            if(intentData.paymentAmount.isNotEmpty()){
                if(intentData.bankSlug == "access-bank"|| intentData.bankSlug == "polaris-bank" ||intentData.bankSlug  == "fidelity-bank" || intentData.bankSlug == "guaranty-trust-bank") {
                    var amount = intentData.paymentAmount.toDoubleOrNull()?.toInt()
                    amount = amount ?: 50
                    intentData.paymentAmount = amount.toString()
                }
                Log.i("partyneverstops", "AMOUNT ADDED ${intentData.paymentAmount}")
                hoverBuilder.extra("amount",intentData.paymentAmount)
            }

            if ((!intentData.nuban.isEmpty() || !intentData.nuban.trim { it <= ' ' }.isEmpty()) && hoverStrategy.requiresAccountNumber) {
                Log.i("partyneverstops", "ACCOUNT NUMBER ADDED ${intentData.nuban}")
                hoverBuilder.extra("accountNumber", intentData.nuban)
            }
            if(intentData.paymentCreditAccount.isNotEmpty()){
                Log.i("partyneverstops", "CREDIT ACCOUNT NUMBER ADDED ${intentData.paymentCreditAccount}")
                hoverBuilder.extra("accountNumber",intentData.paymentCreditAccount)
            }
            if(intentData.debitAccountNumber.isNotEmpty()){
                Log.i("partyneverstops", "DEBIT ACCOUNT NUMBER ADDED ${intentData.debitAccountNumber}")
                hoverBuilder.extra("debitAccountNumber",intentData.debitAccountNumber)
            }

            var creditBankName =  intentData.paymentCreditBankName
            if(hoverStrategy.differentActionForInternal && intentData.isSameBank == "true") creditBankName = ""
            if(creditBankName.isNotEmpty()){
                Log.i("partyneverstops", "BANK ADDED ${intentData.paymentCreditBankName}")
                hoverBuilder.extra("bank",intentData.paymentCreditBankName)
            }
            Log.i("partyneverstops", "BUILDING REQUEST")
            hoverBuilder.finalMsgDisplayTime(0)
            intent = hoverBuilder.buildIntent()
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


