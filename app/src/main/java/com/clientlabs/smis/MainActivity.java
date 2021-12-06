package com.clientlabs.smis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.clientlabs.smis.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView hum, notification;
    CardView results_card, assignment_card, calender_card;
    RelativeLayout fee_card;
    private AppBarConfiguration mAppBarConfiguration;
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigationfees:
                    Log.d(TAG, "onNavigationItemSelected: Clicked");
                    Intent fee_page = new Intent(MainActivity.this, Fees.class);
                    startActivity(fee_page);
                    Animatoo.animateSlideLeft(MainActivity.this);
                    return true;
                case R.id.navigationHome:
                    return true;
                case R.id.navigationResults:
                    return true;

            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_notification:
                Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                drawer.openDrawer(Gravity.LEFT);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // TODO: 11/27/2021 Enable below part when going into production, disable emulator bit
//        FirebaseApp.initializeApp(/*context=*/ this);
//        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
//        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance());

//        For Emulator runs
        FirebaseApp.initializeApp(/*context=*/ this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                DebugAppCheckProviderFactory.getInstance());
//        End Emulator bit


        toolbar = findViewById(R.id.toolbar);
        fee_card = findViewById(R.id.fees_card);
        results_card = findViewById(R.id.results_card);
        calender_card = findViewById(R.id.calender_card);
        assignment_card = findViewById(R.id.assignment_card);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        bottomNavigationView.setSelectedItemId(R.id.navigationHome);
        com.clientlabs.smis.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHome.toolbar);

        drawer = binding.drawer;
        navigationView = binding.navView;

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        fee_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fee_page = new Intent(MainActivity.this, Fees.class);
                startActivity(fee_page);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });

        toolbar.inflateMenu(R.menu.tool);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_fees) {

                    Intent fee_page = new Intent(MainActivity.this, Fees.class);
                    startActivity(fee_page);
                    Animatoo.animateSlideLeft(MainActivity.this);
                }
                return false;
            }
        });


    }
}