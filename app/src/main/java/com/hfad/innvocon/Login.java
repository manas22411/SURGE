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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText em,pass;
    TextView reg;
    Button log;
    ProgressBar progressBar;
    FirebaseAuth Auth;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        em = (EditText) findViewById(R.id.logemail);
        pass = (EditText) findViewById(R.id.logpass);
        log=(Button)findViewById(R.id.btnlog);
        fauth=FirebaseAuth.getInstance();
        reg=(TextView)findViewById(R.id.text);
        Auth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,register.class);
                startActivity(intent);
            }
        });
        

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=em.getText().toString().trim();
                String password=pass.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    em.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Password is Empty");
                    return;
                }
                if(password.length()<6){
                    pass.setError("Password must be greater than or equal to 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //authentication
                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent (Login.this,home.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }
}
