package com.okra.widget.example;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.okra.widget.Starter;
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
        OkraOptions okraOptions = new OkraOptions(true, "","", new ArrayList<String>(), Enums.Environment.dev,"Bassey");
        Starter starter = new Starter(MainActivity.this, okraOptions);
        starter.OpenOkraWidget();
    }
}
