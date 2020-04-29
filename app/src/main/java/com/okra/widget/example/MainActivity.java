package com.okra.widget.example;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.okra.widget.Okra;
import com.okra.widget.handlers.OkraHandler;
import com.okra.widget.models.Enums;
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
        ArrayList  products = new ArrayList<Enums.Product>();
        products.add(Enums.Product.auth);
        products.add(Enums.Product.balance);
        products.add(Enums.Product.transactions);
        OkraOptions okraOptions = new OkraOptions(true, "key","token", products, Enums.Environment.production,"Bassey");
        okraOptions.setColor("#3AB795");
        okraOptions.setLimit("24");
        okraOptions.setCorporate(false);
        okraOptions.setConnectMessage("Which account do you want to connect with?");
        okraOptions.setGuarantors(new Guarantor(true, "this is me", 3));
        okraOptions.setCallback_url("");
        okraOptions.setRedirect_url("redirect");
        okraOptions.setLogo("https://cdn.okra.ng/images/icon.svg");
               // filter: {"industry_type":"all","banks":["alat","access-bank"]},
        okraOptions.setWidget_success("Your account was successfully linked to Okra, Inc");
        okraOptions.setCurrency("USD");
        okraOptions.setExp("2020-08-06");
        okraOptions.setSuccess_title("it has entered success");
        okraOptions.setSuccess_message("this is the success message");
        Okra.create(MainActivity.this, okraOptions);
    }
}
