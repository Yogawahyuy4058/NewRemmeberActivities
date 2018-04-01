package org.d3if.rememberactivities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class detailingViewpencapaianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailing_viewpencapaian);
        setupActionBar();
        Button btnhapus=(Button)findViewById(R.id.btn_HapusdetailngViewPencapaian);
        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast toast=Toast.makeText(getApplicationContext(),"Kegiatan terhapus",Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(detailingViewpencapaianActivity.this,PencapaianActivity.class));
            }
        });

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
            Intent in=new Intent(detailingViewpencapaianActivity.this,PencapaianActivity.class);
            startActivity(in);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
