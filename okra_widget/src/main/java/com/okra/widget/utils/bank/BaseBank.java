package com.okra.widget.utils.bank;

import com.okra.widget.models.Enums;
import com.okra.widget.models.HoverStrategy;

public class BaseBank {

    public HoverStrategy getBvn(String bankAlias) {
        HoverStrategy hoverStrategy =  new HoverStrategy(
                "8ac6e2d8",
                "bvn",
                bankAlias,
                "Fetching BVN",
                0
        );
        hoverStrategy.setId("bvn");
        hoverStrategy.setBankResponseMethod(Enums.BankResponseMethod.ussd);
        hoverStrategy.setFirstAction(true);
        return hoverStrategy;
    }
}
