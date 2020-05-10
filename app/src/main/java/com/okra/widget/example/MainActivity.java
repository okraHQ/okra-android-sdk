package com.okra.widget.example;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.models.Enums;
import com.okra.widget.models.Filter;
import com.okra.widget.models.Guarantor;
import com.okra.widget.utils.OkraOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkraHandler okraHandler = (OkraHandler) getIntent().getSerializableExtra("okraHandler");
        String okraData = "";
        if(okraHandler != null){
            if(okraHandler.getIsDone() && (okraHandler.getIsSuccessful() || okraHandler.getHasError()) ) {
                okraData = okraHandler.getData();
            }
        }
        button = findViewById(R.id.okra_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOkraWidget();
            }
        });
    }

    public void openOkraWidget(){

        ArrayList banks = new ArrayList<String>();
        banks.add(Enums.Banks.AccessBank.toString());
        banks.add(Enums.Banks.GTB.toString());

        ArrayList  products = new ArrayList<Enums.Product>();
        products.add(Enums.Product.auth);
        products.add(Enums.Product.balance);
        products.add(Enums.Product.transactions);
        OkraOptions okraOptions = new OkraOptions(true, "101ee499-beed-53ef-b9e4-1846790792a5","5da6358130a943486f33dced", products, Enums.Environment.production.toString(),"Chris");
        okraOptions.setColor("#953ab7")
        .setLimit("24")
        .setCorporate(false)
        .setConnectMessage("Which account do you want to connect with?")
        .setGuarantors(new Guarantor(true, "this is me", 3))
        .setCallback_url("")
        .setRedirect_url("redirect")
        .setLogo("https://cdn.okra.ng/images/icon.svg")
        .setFilter(new Filter(Enums.IndustryType.all.toString(), banks))
        .setWidget_success("Your account was successfully linked to Okra, Inc")
        .setCurrency("USD")
        .setExp("2020-08-06")
        .setSuccess_title("it has entered success")
        .setSuccess_message("this is the success message");
        Okra.create(MainActivity.this, okraOptions);
    }
}
