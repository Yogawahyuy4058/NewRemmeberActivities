package org.d3if.rememberactivities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PencapaianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencapaian);
        ListView lis=(ListView)findViewById(R.id.list_pencapaian);
        ArrayList arr=new ArrayList<>();
        arr.add(new simpankegiatan("Memancing","12/07/2018","15.30","12/07/2018","17.00","5 menit","2 kali","harus pulang jam 17.00"));
        arr.add(new simpankegiatan("Datang Rapat jam 3","12/07/2018","15.30","12/07/2018","17.00","5 menit","2 kali","harus pulang jam 17.00"));
       // custom_adapter adapter=new custom_adapter(getApplicationContext(),R.layout.customlayoutlistview,arr);
       // lis.setAdapter(adapter);
        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(PencapaianActivity.this,detailingViewpencapaianActivity.class));
            }
        });
        setupActionBar();
    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent in=new Intent(PencapaianActivity.this,firstActivity.class);
            startActivity(in);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
