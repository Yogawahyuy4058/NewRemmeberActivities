package org.d3if.rememberactivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.d3if.rememberactivities.data.myDBHelper;

import java.util.ArrayList;
import java.util.List;

public class seeActivity extends AppCompatActivity {
    ArrayList<simpankegiatan> arr;
    List<simpankegiatan> array;
    myDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);
        helper=new myDBHelper(this);
        final ListView lis=(ListView)findViewById(R.id.myactivites);
        array=new ArrayList<>();
        array=helper.getData();

            lis.setAdapter(new viewAdapter(getApplicationContext()));

        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(seeActivity.this,detailviewActivity.class);
                int id=helper.getId(i);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        registerForContextMenu(lis);
        setupActionBar();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId()==R.id.myactivites){
           AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;

            String [] menuitem=getResources().getStringArray(R.array.menu);
            for (int i = 0; i <menuitem.length ; i++) {
                menu.add(Menu.NONE,i,i,menuitem[i]);
            }
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       int menuItemIndex=item.getItemId();
        String [] menuItem=getResources().getStringArray(R.array.menu);
        String  menuItemName=menuItem[menuItemIndex];
        simpankegiatan ListitemName=arr.get(info.position);
        for (int i = 0; i <menuItem.length ; i++) {
            if(menuItem[i].equalsIgnoreCase("Edit")){
                startActivity(new Intent(seeActivity.this,detailingActivity.class));
            }else if(menuItem[i].equalsIgnoreCase("Hapus")) {
                /*to do*/
                final AlertDialog.Builder alert= new android.support.v7.app.AlertDialog.Builder(this);
                alert.setTitle("Hapus kegiatan");
                alert.setMessage("Yakin hapus kegiatan ini?");
                alert.setCancelable(true);
                alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast=Toast.makeText(getApplicationContext(),"Kegiatan terhapus",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                //AlertDialog alert1=alert.create();
                alert.show();
            }
        }
       return true;
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
            Intent in=new Intent(seeActivity.this,firstActivity.class);
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


}
