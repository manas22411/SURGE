package com.hfad.innvocon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {
    Button adminbtn,mentoorbtn,studentbtn;
    Dialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        adminbtn = findViewById(R.id.adminbtn);
        mentoorbtn = findViewById(R.id.mentorbtn);
        studentbtn = findViewById(R.id.studentbtn);
        super.onStart();
        TextView skip;
        mydialog=new Dialog(this);
        mydialog.setContentView(R.layout.popup);
        skip=(TextView) mydialog.findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });
        mydialog.show();
    }


    public void homeid1(View view){
        Intent obj=new Intent(Dashboard.this,Login.class);
        startActivity(obj);
    }
}
