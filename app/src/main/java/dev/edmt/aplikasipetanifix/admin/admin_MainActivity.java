package dev.edmt.aplikasipetanifix.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import dev.edmt.aplikasipetanifix.R;

public class admin_MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private admin_SectionsPagerAdapter mSectionsPagerAdapter;

    private TabLayout mTabLayout;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mViewPager = (ViewPager) findViewById(R.id.main_tabPager);



        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, admin_LoginActivity.class));
        }
        //Tabs
        mSectionsPagerAdapter = new admin_SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);



       /* mViewPager = (ViewPager) findViewById(R.id.main_tabPager);
        mSectionsPagerAdapter = new admin_SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);*/



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TabLayout.Tab Facebook=mTabLayout.newTab();
        final TabLayout.Tab Youtube=mTabLayout.newTab();
        final TabLayout.Tab Twitter=mTabLayout.newTab();

        Facebook.setText("Beranda");
        Youtube.setText("Chat");
        Twitter.setText("Tentang");

        View FaceView = getLayoutInflater().inflate(R.layout.custom_tabs,null);
        ImageView face = (ImageView) FaceView.findViewById(R.id.image);
        TextView textface = (TextView) FaceView.findViewById(R.id.text_view);
        face.setImageResource(R.drawable.homeputih);
        textface.setText("Beranda");

        View YtbView = getLayoutInflater().inflate(R.layout.custom_tabs,null);
        ImageView Ytb = (ImageView) YtbView.findViewById(R.id.image);
        TextView textYtb = (TextView) YtbView.findViewById(R.id.text_view);
        Ytb.setImageResource(R.drawable.chat);
        textYtb.setText("Chat");

        View TwtView = getLayoutInflater().inflate(R.layout.custom_tabs,null);
        ImageView Twt = (ImageView) TwtView.findViewById(R.id.image);
        TextView textTwt = (TextView) TwtView.findViewById(R.id.text_view);
        Twt.setImageResource(R.drawable.aboutputih);
        textTwt.setText("Tentang");

        Facebook.setCustomView(FaceView);
        Youtube.setCustomView(YtbView);
        Twitter.setCustomView(TwtView);

        mTabLayout.addTab(Facebook,0);
        mTabLayout.addTab(Youtube,1);
        mTabLayout.addTab(Twitter,2);

        mTabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicate));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){

                    case 0:{
                        Facebook.setIcon(R.drawable.homeputih);
                        Youtube.setIcon(R.drawable.chat);
                        Twitter.setIcon(R.drawable.aboutputih);
                        break;
                    }

                    case 1:{
                        Facebook.setIcon(R.drawable.homeputih);
                        Youtube.setIcon(R.drawable.chat);
                        Twitter.setIcon(R.drawable.aboutputih);
                        break;
                    }

                    case 2:{
                        Facebook.setIcon(R.drawable.homeputih);
                        Youtube.setIcon(R.drawable.chat);
                        Twitter.setIcon(R.drawable.aboutputih);
                        break;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(admin_MainActivity.this, nav_profile.class));
            // Handle the camera action
        } else if (id == R.id.nav_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(admin_MainActivity.this, admin_LoginActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
