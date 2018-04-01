package org.d3if.rememberactivities.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Pc on 01/04/2018.
 */

public class DBProvider extends ContentProvider {
   myDBHelper myDBHelper;
    private static final int RA = 100;
    private static final int RA_ID =101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(myContract.CONTENT_AUTHORITY,myContract.MY_PATH,RA);
        sUriMatcher.addURI(myContract.CONTENT_AUTHORITY,myContract.MY_PATH+" /#", RA_ID);
    }
    public static final String LOG_TAG=DBProvider.class.getSimpleName();
    @Override
    public boolean onCreate() {
        myDBHelper=new myDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = myDBHelper.myaction.getReadableDatabase();

                Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case RA:

                cursor = db.query(myContract.myContractEntry.Table_Name,projection,selection,selectionArgs,null,null,null);
                break;
            case RA_ID:
                selection =myContract.myContractEntry.UID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor=db.query(myContract.myContractEntry.Table_Name,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
