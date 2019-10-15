package com.okra.widget.example;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.okra.widget.Okra;
import com.okra.widget.models.Enums;
import com.okra.widget.utils.OkraOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        products.add(Enums.Product.transactions);
        OkraOptions okraOptions = new OkraOptions(true, "c81f3e05-7a5c-5727-8d33-1113a3c7a5e4","5d8a35224d8113507c7521ac", products, Enums.Environment.sandbox,"Bassey");
        Okra.create(MainActivity.this, okraOptions);
    }
}
