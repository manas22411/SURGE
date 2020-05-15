package com.hfad.innvocon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;

public class home extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<youtubev> youtubevVector=new Vector<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        youtubevVector.add(new youtubev("<iframe width=\"100%\" height=\"100%\" src=\"https://drive.google.com/file/d/19dEJslef9hy9nRF5LlgD0Xv4B0OV8M2_/view?usp=drivesdk\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubevVector.add(new youtubev("<iframe width=\"100%\" height=\"100%\" src=\"https://youtu.be/bSMZknDI6bg\" frameborder=\"0\" allowfullscreen></iframe>"));
        youtubevVector.add(new youtubev("<iframe width=\"100%\" height=\"100%\" src=\"https://youtu.be/SoAp7sX65TQ\" frameborder=\"0\" allowfullscreen></iframe>"));
        VideoAdapter videoAdapter=new VideoAdapter(youtubevVector);
        recyclerView.setAdapter(videoAdapter);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectfrag=null;
                    switch (menuItem.getItemId()){
                        case R.id.profile:
                            selectfrag = new profile_fragment();
                            break;
                        case R.id.learn_book:
                            selectfrag = new learn_fragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectfrag).commit();
                    return true;
                }
            };
}
