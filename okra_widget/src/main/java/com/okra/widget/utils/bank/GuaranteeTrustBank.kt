package com.okra.widget.utils.bank

import com.okra.widget.interfaces.BankServices
import com.okra.widget.models.Enums
import com.okra.widget.models.HoverStrategy

class GuaranteeTrustBank : BankServices {
    override fun getActionCount(): Int {
        return 1
    }

    override fun getIndex(): Int {
        return Companion.index
    }

    override fun setIndex(index: Int): Int {
        this.index = index
        return this.index
    }

    override fun getActionByIndex(index: Int): HoverStrategy {
        return when (index) {
            1 -> accountBalance
            else -> accountBalance
        }
    }

    override fun confirmPayment(): HoverStrategy {
        TODO("Not yet implemented")
    }

    @Throws(Exception::class)
    override fun getNextAction(): HoverStrategy {
        if (Companion.index >= actionCount) {
            index = 0
        }
        return getActionByIndex(Companion.index + 1)
    }

    override fun hasNext(): Boolean {
        return Companion.index < actionCount
    }

    @Throws(Exception::class)
    override fun getBvn(): HoverStrategy {
        throw Exception("Not implemented")
    }

    @Throws(Exception::class)
    override fun getAccounts(): HoverStrategy {
        throw Exception("Not implemented")
    }

    override fun getAccountBalance(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "ad6c3e9c",
                "balance",
                "Guarantee Trust Bank",
                "Fetching account balance",
                10000
        )
        hoverStrategy.id = "balance"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.isFirstAction = true
        hoverStrategy.isLastAction = true
        hoverStrategy.requiresPin = true
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun getTransactions(): HoverStrategy {
        throw Exception("Not implemented")
    }

    @Throws(Exception::class)
    override fun makePayment(isInternal: Boolean, hasMultipleAccounts: Boolean): HoverStrategy {
        val actionid = if(isInternal) "d5f503e2" else "b85c8f85"
        val hoverStrategy = HoverStrategy(
                actionid,
                "Guarantee Trust Bank",
                "Processing Payment",
                0
        )
        hoverStrategy.id = "payment"
        hoverStrategy.requiresPin = true
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.differentActionForInternal = true
        return hoverStrategy
    }

    companion object {
        private const val index = 1
    }
}