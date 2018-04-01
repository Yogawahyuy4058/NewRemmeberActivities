package org.d3if.rememberactivities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.d3if.rememberactivities.data.myDBHelper;

import java.util.ArrayList;
import java.util.List;

public class detailviewActivity extends AppCompatActivity {
    myDBHelper helper;
    List<simpankegiatan> array;
    TextView nmkgt,tglml,jmml,tglbrk,jmbrk,catatn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);
        setupActionBar();
        nmkgt=(TextView)findViewById(R.id.textView_detailView);
        tglml=(TextView)findViewById(R.id.textView_tanggal_kegiatan);
        jmml=(TextView)findViewById(R.id.textView_jammulai);
        jmbrk=(TextView)findViewById(R.id.textView_jamberakhir);
        tglbrk=(TextView)findViewById(R.id.textView_tanggalakhir_kegiatan);
        catatn=(TextView)findViewById(R.id.textView_catatan);
        helper=new myDBHelper(this);
        array=new ArrayList<>();
        array=helper.getData();
       /* Intent intent=getIntent();
        Bundle extra=getIntent().getExtras();
        String id=extra.getString("id");
        int parid=Integer.parseInt(id);
        int hasil=helper.getId(parid);
        String terbaru=Integer.toString(hasil);
        getdata(terbaru);
        */
        Button btnedit=(Button)findViewById(R.id.btn_EditdetailingView);
        Button btnhapus=(Button)findViewById(R.id.btn_HapusdetailngView);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(detailviewActivity.this,detailingActivity.class));
            }
        });
        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"Kegiatan terhapus",Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(detailviewActivity.this,seeActivity.class));
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
            Intent in=new Intent(detailviewActivity.this,seeActivity.class);
            startActivity(in);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class viewAdapter extends BaseAdapter {
        LayoutInflater vi;

        public viewAdapter(Context context){
            vi=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return helper.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView==null){
                convertView=vi.inflate(R.layout.customlayoutlistview,null);
            }
            final TextView namakegiatan=(TextView)convertView.findViewById(R.id.custom_namaKegiatan);
            namakegiatan.setText(array.get(position).getNamakegiatan());
            final TextView tanggalkegiatan=(TextView)convertView.findViewById(R.id.custom_tanggalKegiatan);
            tanggalkegiatan.setText(array.get(position).getTgl_mulai());
            return convertView;
        }

    }
    /*
    private void getdata(String id){
       myDBHelper.myActionDbHelper myDBHelper=new myDBHelper.myActionDbHelper(this);
        SQLiteDatabase db=myDBHelper.getReadableDatabase();
        String[] allcolum={
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.UID,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.NAME,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.jam_mulai,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.tgl_mulai,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.jam_berakhir,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.tgl_berakhir,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.wkt_pengingat,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.ulangi,
                org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.catatan
        };
        String  whereClause= org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.UID + " = ?";
        String [] args={
                id
        };
        Cursor c=db.query(org.d3if.rememberactivities.data.myDBHelper.myActionDbHelper.Table_Name,allcolum,whereClause,args,null,null,null);
        while (c.moveToNext()){
            nmkgt.setText(c.getString(1));
            tglml.setText(c.getString(2));
            jmml.setText(c.getString(3));
            tglbrk.setText(c.getString(4));
            jmbrk.setText(c.getString(5));
            catatn.setText(c.getString(6));
        }
    }
    */
}
