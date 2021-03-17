package com.okra.widget.utils.bank

import com.okra.widget.interfaces.BankServices
import com.okra.widget.models.Enums
import com.okra.widget.models.HoverStrategy

class FCMB : BaseBank(), BankServices {
    override fun getActionCount(): Int {
        return 3
    }

    override fun getIndex(): Int {
        return Companion.index
    }

    override fun setIndex(index: Int): Int {
        Companion.index = index
        return Companion.index
    }

    @Throws(Exception::class)
    override fun getActionByIndex(index: Int): HoverStrategy {
        return when (index) {
            1 -> accountBalance
            2 -> transactions
            3 -> bvn
            else -> accountBalance
        }
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
    override fun getBvn(): HoverStrategy {
        return this.getBvn("FCMB")
    }

    @Throws(Exception::class)
    override fun getAccounts(): HoverStrategy {
        throw Exception("Not implemented")
    }

    override fun getAccountBalance(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "39bd69bb",
                "FCMB",
                "Fetching account balance",
                5000
        )
        hoverStrategy.id = "balance"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.isFirstAction = true
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    override fun getTransactions(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "a755e3ec",
                "FCMB",
                "Fetching account transaction",
                10000
        )
        hoverStrategy.id = "transactions"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun confirmPayment(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "39bd69bb",
                "FCMB",
                "Verify-Payment",
                5000
        )
        hoverStrategy.id = "verify-payment"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun makePayment(isInternal: Boolean, hasMultipleAccounts: Boolean): HoverStrategy {
        val actionId = if(hasMultipleAccounts) "b8ab3ce5" else "9a143ca0"
        val hoverStrategy = HoverStrategy(
                actionId,
                "FCMB",
                "Processing Payment",
                0
        )
        hoverStrategy.id = "payment"
        hoverStrategy.requiresPin = true
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.differentActionForInternal = false
        return hoverStrategy
    }

    companion object {
      private  var index = 1
    }
}