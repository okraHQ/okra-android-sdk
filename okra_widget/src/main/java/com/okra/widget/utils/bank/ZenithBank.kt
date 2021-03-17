package com.okra.widget.utils.bank

import com.okra.widget.interfaces.BankServices
import com.okra.widget.models.Enums
import com.okra.widget.models.HoverStrategy

class ZenithBank : BaseBank(), BankServices {
    override fun getActionCount(): Int {
        return 2
    }

    override fun getIndex(): Int {
        return Companion.index
    }

    override fun setIndex(index: Int): Int {
        Companion.index = index
        return Companion.index
    }

    @Throws(Exception::class)
    override fun getNextAction(): HoverStrategy {
        if (Companion.index >= actionCount) {
            index = 0
        }
        return getActionByIndex(Companion.index + 1)
    }

    override fun hasNext(): Boolean {
        return index < actionCount
    }

    @Throws(Exception::class)
    override fun getActionByIndex(index: Int): HoverStrategy {
        return when (index) {
            1 -> accountBalance
            2 -> bvn
            else -> accountBalance
        }
    }

    override fun confirmPayment(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "11229370",
                "Zenith Bank",
                "Confirming payment",
                10000
        )
        hoverStrategy.id = "verify-payment"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.sms
        hoverStrategy.requiresPin = true
        hoverStrategy.requiresAccountNumber = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun getBvn(): HoverStrategy {
        return this.getBvn("Zenith Bank")
    }

    @Throws(Exception::class)
    override fun getAccounts(): HoverStrategy {
        throw Exception("Not implemented")
    }

    override fun getAccountBalance(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "11229370",
                "Zenith Bank",
                "Fetching account balance",
                10000
        )
        hoverStrategy.id = "balance"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.isFirstAction = true
        hoverStrategy.requiresPin = true
        hoverStrategy.requiresAccountNumber = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun getTransactions(): HoverStrategy {
        throw Exception("Not implemented")
    }

    @Throws(Exception::class)
    override fun makePayment(isInternal: Boolean, hasMultipleAccounts: Boolean): HoverStrategy {
        val actionid = if(hasMultipleAccounts) "3f5c8b5e" else "eba74608"
        val hoverStrategy = HoverStrategy(
                actionid,
                "Zenith Bank",
                "Processing Payment",
                0
        )
        hoverStrategy.id = "payment"
        hoverStrategy.requiresPin = true
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.sms
        hoverStrategy.differentActionForInternal = false
        return hoverStrategy
    }

    companion object {
      private  var index = 1
    }
}