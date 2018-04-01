package org.d3if.rememberactivities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.d3if.rememberactivities.data.myDBHelper;

import java.util.List;

/**
 * Created by Yoga Wahyu Yuwono on 11/03/2018.
 */

public class custom_adapter extends BaseAdapter {
   LayoutInflater vi;
   myDBHelper helper;
   private List<simpankegiatan> list;
   public custom_adapter(Context context,List<simpankegiatan> lis){
       vi=LayoutInflater.from(context);
       this.list=lis;
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
        namakegiatan.setText(helper.getData().get(position).getNamakegiatan());
        final TextView tanggalkegiatan=(TextView)convertView.findViewById(R.id.custom_tanggalKegiatan);
        tanggalkegiatan.setText(helper.getData().get(position).getTgl_mulai());
        return convertView;
    }

}
