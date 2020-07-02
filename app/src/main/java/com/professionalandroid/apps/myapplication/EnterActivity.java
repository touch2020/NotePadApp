package com.professionalandroid.apps.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnterActivity extends AppCompatActivity {

    EditText editText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        editText = findViewById(R.id.editNotepad);

        findViewById(R.id.buttondone).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();

                if(str.length() > 0){
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    String substr = sdf.format(date);


                    Intent intent = new Intent();
                    intent.putExtra("main", str);
                    intent.putExtra("sub", substr);
                    setResult(RESULT_OK,intent);

                    finish();



                }
            }
        });

    }
}