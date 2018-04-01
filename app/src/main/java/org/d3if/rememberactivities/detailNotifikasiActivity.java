package org.d3if.rememberactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class detailNotifikasiActivity extends AppCompatActivity {
    Button ya,tidak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notifikasi);
         ya = (Button)findViewById(R.id.yaaaa);
        tidak=(Button)findViewById(R.id.tidakkk);
        ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailNotifikasiActivity.this,PencapaianActivity.class);
                startActivity(intent);
            }
        });
        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailNotifikasiActivity.this, PencapaianActivity.class);
                startActivity(intent);
            }
        });



    }
}
