package org.d3if.rememberactivities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.d3if.rememberactivities.data.myDBHelper;

import java.util.Calendar;

public class detailingActivity extends AppCompatActivity {
    EditText namakegiatan,date,time,datedone,timedone,catatan;
    Spinner pengingatsebelum,pengulangan;
    DatePickerDialog datePickerDialog,datePickerDialogdone;
    final String message = "ini adalah contoh notifikasi";
    final String title = "ini adalah judul";
    final static int RQS_1 = 1;
    String namaAktivitas,pengingat,pengulanganwaktu;
    myDBHelper helper;
    int tampung=-1;
    int tampungwaktu=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailing);
        setupActionBar();
        namakegiatan = findViewById(R.id.activitiename);
        pengingatsebelum = findViewById(R.id.remindme);
        pengulangan = findViewById(R.id.repeat);
        catatan = findViewById(R.id.note);
        time=(EditText)findViewById(R.id.timee);
        datebeginshow();
        timebeginshow(false);
        datedoneshow();
        timedoneshow();
        helper=new myDBHelper(this);
        Button btn=(Button)findViewById(R.id.tambahdata);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addData();
               notifTemplate(message,title);
            }
        });
    }
    private void addData(){
        namaAktivitas = namakegiatan.getText().toString();
        pengingat = pengingatsebelum.getSelectedItem().toString();
        pengulanganwaktu = pengulangan.getSelectedItem().toString();
        String note=catatan.getText().toString();
        String tglmulai=date.getText().toString();
        String jammulai=time.getText().toString();
        String tglberakhir=datedone.getText().toString();
        String jamberakhir=timedone.getText().toString();
        if (namaAktivitas.isEmpty()||tglmulai.isEmpty()||jammulai.isEmpty()||tglberakhir.isEmpty()||jamberakhir.isEmpty()){
            Message.message(getApplicationContext(),"Tolong isi semua field");
        }else {
            try {
                if (pengingat.equalsIgnoreCase("5 menit")){
                    tampung=1;
                }else if (pengingat.equalsIgnoreCase("10 menit")){
                    tampung=2;
                }else if (pengingat.equalsIgnoreCase("15 menit")){
                    tampung=3;
                }else if (pengingat.equalsIgnoreCase("35 menit")){
                    tampung=4;
                }else if (pengingat.equalsIgnoreCase("1 jam")){
                    tampung=5;
                }
                if (pengulanganwaktu.equalsIgnoreCase("Tidak Ulangi")){
                    tampungwaktu=1;
                } else if (pengulanganwaktu.equalsIgnoreCase("1 kali")) {
                    tampungwaktu=2;
                } else if (pengulanganwaktu.equalsIgnoreCase("2 kali")) {
                    tampungwaktu=3;
                }
                long id = helper.insertData(namaAktivitas, tglmulai, jammulai, tglberakhir, jamberakhir,tampung,tampungwaktu,note);
                if (id<=0){
                    Message.message(getApplicationContext(),"Kegiatan gagal diinputkan");
                }else {
                    Message.message(getApplicationContext(),"Kegiatan Berhasil Diinputkan");
                    startActivity(new Intent(detailingActivity.this,seeActivity.class));
                }
            }catch (Exception e){
                Message.message(this,""+e);
            }
        }
    }
    private void notifTemplate(String tittle, String message) {
        final Intent intent = new Intent(detailingActivity.this,detailNotifikasiActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(detailingActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)new NotificationCompat.Builder(detailingActivity.this).
                setSmallIcon(R.mipmap.ic_launcher_foregrou).setContentTitle(tittle).setContentText(message).setContentIntent(pendingIntent)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.FLAG_AUTO_CANCEL);
        NotificationManager notificationManager =
                (NotificationManager)detailingActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,mBuilder.build());
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
            Intent in=new Intent(detailingActivity.this,firstActivity.class);
            startActivity(in);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void datebeginshow(){
        date =(EditText)findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c= Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMont=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(detailingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day+"/"+month+"/"+year);
                    }
                },mYear,mMont,mDay);
                datePickerDialog.show();
            }
        });
    }
    private void datedoneshow(){
        datedone =(EditText)findViewById(R.id.datedone);
        datedone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c= Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMont=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                datePickerDialogdone=new DatePickerDialog(detailingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        datedone.setText(day+"/"+month+"/"+year);
                    }
                },mYear,mMont,mDay);
                datePickerDialogdone.show();
            }
        });
    }
    private void timebeginshow(boolean is24){

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurentTime=Calendar.getInstance();
                final int hour=mCurentTime.get(Calendar.HOUR_OF_DAY);
                final int minute=mCurentTime.get(Calendar.MINUTE);
                TimePickerDialog mtimepicker;
                TimePickerDialog timePickerDialog;
               mtimepicker=new TimePickerDialog(detailingActivity.this,onTimeSetListener,hour,minute,true);
                mtimepicker.setTitle("Pilih waktu Mulai");
                mtimepicker.show();
            }
        });

        }
    TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();
            final int hour=calNow.get(Calendar.HOUR_OF_DAY);
            final int minute=calNow.get(Calendar.MINUTE);
            time.setText(hour+":"+minute);
            calSet.set(Calendar.HOUR_OF_DAY, i);
            calSet.set(Calendar.MINUTE, i1);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                // Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
                Log.i("hasil", " =<0");
            } else if (calSet.compareTo(calNow) > 0) {
                Log.i("hasil", " > 0");
            } else {
                Log.i("hasil", " else ");
            }

            setAlarm(calSet);
        }
    };
    private void setAlarm(Calendar target){

        Intent intent=new Intent(getBaseContext(),Alarmrecivier.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getBaseContext(),RQS_1,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,target.getTimeInMillis(),pendingIntent);
    }
    private void timedoneshow(){
        timedone=(EditText)findViewById(R.id.timedone);
        timedone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mCurentTime=Calendar.getInstance();
                int hour=mCurentTime.get(Calendar.HOUR_OF_DAY);
                int minute=mCurentTime.get(Calendar.MINUTE);
                TimePickerDialog mtimepickerdone;
                mtimepickerdone=new TimePickerDialog(detailingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        timedone.setText(hour+":"+minute);
                    }
                },hour,minute,true);
                mtimepickerdone.setTitle("Select Time");
                mtimepickerdone.show();
            }
        });
    }
}
