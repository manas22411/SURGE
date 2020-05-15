package com.hfad.innvocon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    EditText txtfname, txtstore, txtpass, txtnumber, txtemail;
    Button btnreg;
    FirebaseAuth Auth;
    ProgressBar pb;
    TextView log;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtfname = (EditText) findViewById(R.id.txtfname);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtstore = (EditText) findViewById(R.id.txtstore);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtpass = (EditText) findViewById(R.id.txtpass);
        txtnumber = (EditText) findViewById(R.id.txtnumber);
        log=(TextView) findViewById(R.id.tv);
        Auth = FirebaseAuth.getInstance();
        pb = (ProgressBar) findViewById(R.id.prob);
        btnreg = (Button) findViewById(R.id.btnreg);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(register.this,Login.class);
                startActivity(intent);
            }
        });
        if (Auth.getCurrentUser() != null) {
            Intent intent = new Intent(register.this, home.class);
            startActivity(intent);
            finish();
        }

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = txtemail.getText().toString().trim();
                String pass = txtpass.getText().toString().trim();

                if (TextUtils.isEmpty(em)) {
                    txtemail.setText("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    txtpass.setText("Paassword is invalid");
                    return;
                }
                if(pass.length()<6){
                    txtpass.setText("password should be minimun 6 letter");
                    return;
                }
                pb.setVisibility(View.VISIBLE);
                Auth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this,"User Created",Toast.LENGTH_SHORT).show();
                            Intent obj=new Intent(register.this,Login.class);
                            startActivity(obj);
                        }
                        else{
                            Toast.makeText(register.this,"Error",Toast.LENGTH_SHORT).show();

                        }


                    }
                });
            }

        });


    }

    }
