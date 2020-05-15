package com.hfad.innvocon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    CountryCodePicker codePicker;
    FirebaseAuth auth;
    EditText otpcode,phone;
    TextView resend;
    Button nextbtn;
    String verify;
    LinearLayout linear1;
    Animation uptodown,leftin;
    boolean flag=false;
    PhoneAuthProvider.ForceResendingToken token;
    private static final String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth=FirebaseAuth.getInstance();
        phone=findViewById(R.id.phone);
        otpcode=findViewById(R.id.otpcode);
        linear1=findViewById(R.id.linear1);
        nextbtn=findViewById(R.id.nextbtn);

        resend=findViewById(R.id.resend);
        codePicker=findViewById(R.id.ccp);
        leftin=AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        nextbtn.setAnimation(leftin);
        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        linear1.setAnimation(uptodown);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag){
                    if(!phone.getText().toString().isEmpty() && phone.getText().toString().length()==10){
                        String phonenumber="+"+codePicker.getSelectedCountryCode()+phone.getText().toString();
                        Log.d(TAG,"onClick: phone number "+phonenumber);
                        requestotp(phonenumber);

                    }
                    else{
                        phone.setError("Enter Valid Number");
                    }
                }
                else{
                    String otp= otpcode.getText().toString();
                    if(otp.length()==6 && !otp.isEmpty()){
                        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verify,otp);
                        verifyAuth(credential);

                    }
                    else{
                        otpcode.setError("InValid OTP");
                    }
                }

            }
        });


    }

    private void verifyAuth(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Successfully verified",Toast.LENGTH_SHORT).show();
                    Intent obj=new Intent(MainActivity.this,Dashboard1.class);
                    startActivity(obj);


                }
                else{
                    Toast.makeText(MainActivity.this,"Can't Verify you...SORRY",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void requestotp(String phonenumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phonenumber, 60L, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verify=s;
                token=forceResendingToken;
                otpcode.setVisibility(View.VISIBLE);
                nextbtn.setText("Verify");
                flag=true;

            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }
        });

    }

}
