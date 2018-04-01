package org.d3if.rememberactivities;

import android.content.ClipData.Item;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Yoga Wahyu Yuwono on 11/03/2018.
 */

public class simpankegiatan {
    private String namakegiatan,tgl_mulai,jam_mulai,tgl_berakhir,jam_berakhir,pengingat_sebelum,pengulangan,catatan;
    ArrayList<Item> items;

    public simpankegiatan(String namakegiatan, String tgl_mulai, String jam_mulai, String tgl_berakhir, String jam_berakhir, String pengingat_sebelum, String pengulangan, String catatan) {
        this.namakegiatan = namakegiatan;
        this.tgl_mulai = tgl_mulai;
        this.jam_mulai = jam_mulai;
        this.tgl_berakhir = tgl_berakhir;
        this.jam_berakhir = jam_berakhir;
        this.pengingat_sebelum = pengingat_sebelum;
        this.pengulangan = pengulangan;
        this.catatan = catatan;
    }

    public String getNamakegiatan() {
        return namakegiatan;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public String getTgl_berakhir() {
        return tgl_berakhir;
    }

    public String getJam_berakhir() {
        return jam_berakhir;
    }

    public String getPengingat_sebelum() {
        return pengingat_sebelum;
    }

    public String getPengulangan() {
        return pengulangan;
    }

    public String getCatatan() {
        return catatan;
    }
}
