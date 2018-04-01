package org.d3if.rememberactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class firstActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView plus,jam,seegoals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        widgetCalendardanaktivitas();
    }
    private void widgetCalendardanaktivitas(){
        ArrayList<String> array=new ArrayList<>();
        array.add("Datang ke kantor");
        array.add("Makan siang");
        array.add("Rapat");
        array.add("Kumpul Keluarga");
        ListView list= findViewById(R.id.list_awal);
        ArrayAdapter<String> arr=new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        list.setAdapter(arr);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            Intent setting=new Intent(firstActivity.this,SettingsActivity.class);
            startActivity(setting);
        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_feedback) {

        }else if (id== R.id.nav_tambahKegiatan){
            startActivity(new Intent(firstActivity.this,detailingActivity.class));
        }else if(id== R.id.nav_lihatKegiatan){
            startActivity(new Intent(firstActivity.this,seeActivity.class));
        }else if(id==R.id.nav_lihatPencapaian){
            startActivity(new Intent(firstActivity.this,PencapaianActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
