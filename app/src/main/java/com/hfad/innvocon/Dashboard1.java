package com.hfad.innvocon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Dashboard1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);
        ImageView bgapp,clover;
        LinearLayout textsplash;
        Animation frombutton;




            bgapp=(ImageView)findViewById(R.id.bgapp);

            textsplash=(LinearLayout)findViewById(R.id.textsplash);

            bgapp.animate().translationY(-1700).setDuration(800).setStartDelay(500);

            textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);



        }

        public void passon(View view){

            Intent obj=new Intent(Dashboard1.this,Login.class);
            startActivity(obj);

        }
}
