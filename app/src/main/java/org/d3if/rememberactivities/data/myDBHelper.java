package org.d3if.rememberactivities.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.widget.TextView;

import org.d3if.rememberactivities.Message;
import org.d3if.rememberactivities.simpankegiatan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yoga Wahyu Yuwono on 18/03/2018.
 */

public class myDBHelper {

   myActionDbHelper myaction;

    public myDBHelper(Context context) {
        myaction = new myActionDbHelper(context);
    }


        public static class myActionDbHelper extends SQLiteOpenHelper {

            private Context context;

            public myActionDbHelper(Context context) {
                super(context, myContract.myContractEntry.DB_NAME, null, myContract.myContractEntry.version);
                this.context = context;
            }

            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                try {
                    sqLiteDatabase.execSQL(myContract.myContractEntry.create_table);
                } catch (Exception e) {
                    Message.message(context, "" + e);
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
                try {
                    Message.message(context, "Onuprgade");
                    sqLiteDatabase.execSQL(myContract.myContractEntry.DROP_TABLE);
                    onCreate(sqLiteDatabase);
                } catch (Exception e) {
                    Message.message(context, "" + e);
                }
            }
        }

        public long insertData(String namakgt, String tglmulai, String jammulai, String tglberakhir, String jamberakhir, int ingatkan, int ulangi, String catatan) {
            SQLiteDatabase db =myaction.getWritableDatabase() ;
            ContentValues contentValues = new ContentValues();
            contentValues.put(myContract.myContractEntry.NAME, namakgt);
            contentValues.put(myContract.myContractEntry.tgl_mulai, tglmulai);
            contentValues.put(myContract.myContractEntry.jam_mulai, jammulai);
            contentValues.put(myContract.myContractEntry.tgl_berakhir, tglberakhir);
            contentValues.put(myContract.myContractEntry.jam_berakhir, jamberakhir);
            contentValues.put(myContract.myContractEntry.wkt_pengingat, ingatkan);
            contentValues.put(myContract.myContractEntry.ulangi, ulangi);
            contentValues.put(myContract.myContractEntry.catatan, catatan);
            long id = db.insert(myContract.myContractEntry.Table_Name, null, contentValues);
            return id;
        }

        public List<simpankegiatan> getData() {
            List<simpankegiatan> lis = new ArrayList<>();
            simpankegiatan child;
            Cursor c = null;
            SQLiteDatabase db = myaction.getWritableDatabase();
            try {
                String query = "select * from " + myContract.myContractEntry.Table_Name;
                c = db.rawQuery(query, null);
                while (c.moveToNext()) {
                    child = new simpankegiatan(c.getString(c.getColumnIndex(myContract.myContractEntry.NAME)),
                            c.getString(c.getColumnIndex(myContract.myContractEntry.tgl_mulai)),
                            c.getString(c.getColumnIndex(myContract.myContractEntry.jam_mulai)),
                            c.getString(c.getColumnIndex(myContract.myContractEntry.tgl_berakhir)),
                            c.getString(c.getColumnIndex(myContract.myContractEntry.jam_berakhir)),
                            c.getString(c.getColumnIndex(myContract.myContractEntry.wkt_pengingat)),
                            c.getString(c.getColumnIndex(myContract.myContractEntry.ulangi)),
                            c.getString(c.getColumnIndex(myContract.myContractEntry.catatan)));
                    lis.add(child);
                }
                db.close();
                return lis;
            } finally {
                if (c != null) {
                    c.close();
                }
                if (db != null) {
                    db.close();
                }
            }

        }

        public Integer getId(int id1) {
            int id = 0;
            SQLiteDatabase db = myaction.getWritableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + myContract.myContractEntry.Table_Name, null);
            c.moveToPosition(id1);
            id = c.getInt(1);
            c.close();
            return id;
        }

        public void getDatawhere(String data) {
            SQLiteDatabase db = myaction.getWritableDatabase();
            String namakgt, tglml, wktml, tglakhr, wktakhir, catat;
            String[] allcolum = {
                    myContract.myContractEntry.UID,
                    myContract.myContractEntry.NAME,
                    myContract.myContractEntry.jam_mulai,
                    myContract.myContractEntry.tgl_mulai,
                    myContract.myContractEntry.jam_berakhir,
                    myContract.myContractEntry.tgl_berakhir,
                    myContract.myContractEntry.wkt_pengingat,
                    myContract.myContractEntry.ulangi,
                    myContract.myContractEntry.catatan
            };
            String whereClause = myContract.myContractEntry.UID + " = ?";
            String[] args = {
                    data
            };
            Cursor c = db.query(myContract.myContractEntry.Table_Name, allcolum, whereClause, args, null, null, null);
            while (c.moveToNext()) {
                namakgt = c.getString(1);
                tglml = c.getString(2);
                wktml = c.getString(3);
                tglakhr = c.getString(4);
                wktakhir = c.getString(5);
                catat = c.getString(6);

            }
        }

        public Cursor fetch() {
            SQLiteDatabase db = myaction.getWritableDatabase();
            String[] allcolum = {
                    myContract.myContractEntry.UID,
                    myContract.myContractEntry.NAME,
                    myContract.myContractEntry.jam_mulai,
                    myContract.myContractEntry.tgl_mulai,
                    myContract.myContractEntry.jam_berakhir,
                    myContract.myContractEntry.tgl_berakhir,
                    myContract.myContractEntry.wkt_pengingat,
                    myContract.myContractEntry.ulangi,
                    myContract.myContractEntry.catatan
            };
            Cursor cursor = db.query(myContract.myContractEntry.Table_Name, allcolum, null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }


}