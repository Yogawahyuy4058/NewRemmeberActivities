package org.d3if.rememberactivities.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Yoga Wahyu Yuwono on 01/04/2018.
 */

public class myContract {
    private myContract(){

    }
    public static final String CONTENT_AUTHORITY="org.d3if.rememberactivities";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://" +CONTENT_AUTHORITY);
    public static final String MY_PATH="RA";
    public static final class myContractEntry implements BaseColumns{
        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,MY_PATH);
        public static final String DB_NAME = "rememberactivities";
        public static final String Table_Name = "kegiatan";
        public static final int version = 1;
        public static final String UID = "_id";
        public static final String NAME = "nama_kgt";
        public static final String tgl_mulai = "tgl_mulai";
        public static final String jam_mulai = "jam_mulai";
        public static final String tgl_berakhir = "tgl_berakhir";
        public static final String jam_berakhir = "jam_berakhir";
        public static final String wkt_pengingat = "wkt_pengingat";
        public static final String ulangi = "ulangi";
        public static final String catatan = "catatan";
        public static final String create_table = "CREATE TABLE " + Table_Name + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(50), " +
                tgl_mulai + " VARCHAR(10), " + jam_mulai + " VARCHAR(5), " + tgl_berakhir + " VARCHAR(10), " + jam_berakhir + " VARCHAR(5), " + wkt_pengingat + " INTEGER, " + ulangi + " INTEGER," +
                catatan + " VARCHAR(255));";
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS" + Table_Name;
    }
}
