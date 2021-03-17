package com.okra.widget.utils.bank

import com.okra.widget.interfaces.BankServices
import com.okra.widget.models.Enums
import com.okra.widget.models.HoverStrategy

class SterlingBank : BaseBank(), BankServices {
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
            1 -> accounts
            2 -> accountBalance
            3 -> bvn
            else -> accounts
        }
    }

    @Throws(Exception::class)
    override fun getNextAction(): HoverStrategy {
        if (Companion.index >= actionCount) {
            Companion.index = 0
        }
        return getActionByIndex(Companion.index + 1)
    }

    override fun hasNext(): Boolean {
        return Companion.index < actionCount
    }

    @Throws(Exception::class)
    override fun getBvn(): HoverStrategy {
        return this.getBvn("Sterling Bank")
    }

    override fun getAccounts(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "b46ceac3",
                "Sterling Bank",
                "Fetching Accounts",
                0
        )
        hoverStrategy.id = "accounts"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        hoverStrategy.isFirstAction = true
        return hoverStrategy
    }

    override fun getAccountBalance(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "79a457c7",
                "Sterling Bank",
                "Fetching Account balance",
                0
        )
        hoverStrategy.id = "balance"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun getTransactions(): HoverStrategy {
        throw Exception("Not implemented")
    }

    @Throws(Exception::class)
    override fun confirmPayment(): HoverStrategy {
        val hoverStrategy = HoverStrategy(
                "79a457c7",
                "Sterling Bank",
                "Verify Payment",
                0
        )
        hoverStrategy.id = "verify-payment"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        return hoverStrategy
    }

    @Throws(Exception::class)
    override fun makePayment(isInternal: Boolean, hasMultipleAccounts: Boolean): HoverStrategy? {
        val actionid = if(hasMultipleAccounts) "52885640" else "9c658ade"
        val hoverStrategy = HoverStrategy(
                actionid,
                "Sterling Bank",
                "Processing Payment",
                0
        )
        hoverStrategy.id = "payment"
        hoverStrategy.bankResponseMethod = Enums.BankResponseMethod.ussd
        return hoverStrategy
    }

    companion object {
        private var index = 1
    }
}