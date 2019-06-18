package com.uas.uas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class InvoicepulsaActivity extends AppCompatActivity {

    TextView txtHello2, txtHello3;
    private String no_hp, denom;
    private String KEY_NAME2 = "NO_HP";
    private String KEY_NAME3 = "DENOM";




    private TextView name, email;  // Mendeklarasikan variabel name dan email bertipe data object TextView dan bersifat private
    private Button btn_logout; // Mendeklarasikan variabel btn_logout bertipe data object Button dan bersifat private
    SessionManager sessionManager; // Mendeklarasikan variabel sessionManager dari object SessionManager

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Membuat pembbentukan Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoicepulsa);  // Menggunakan view dari layout activity home

        txtHello2 = (TextView) findViewById(R.id.txtHello2);
        txtHello3 = (TextView) findViewById(R.id.txtHello3);

        Bundle extras = getIntent().getExtras();
        no_hp = extras.getString(KEY_NAME2);
        denom = extras.getString(KEY_NAME3);
        txtHello2.setText(no_hp);
        txtHello3.setText(denom);




        sessionManager = new SessionManager(this); // Menginisialisasi object baru bernama sessionManager dari object SessionManager
        sessionManager.checkLogin(); // SessionManager mengecek kondisi login

        name = (TextView) findViewById(R.id.name); // Inisialisasi variabel name sebagai object TextView berdasarkan id bernama "name"
        email = (TextView) findViewById(R.id.email); // Inisialisasi variabel email sebagai object TextView berdasarkan id bernama "email"
        btn_logout = (Button) findViewById(R.id.btn_logout); // Inisialisasi variabel btn_logout sebagai Button berdasarkan id bernama "btn_logout"

        HashMap<String, String> user = sessionManager.getUserDetail();  //Membuat object HashMap bernama user yang digunakan untuk menyimpan data dengan tipe data String dan value String
        String mName = user.get(sessionManager.NAME);  // Variabel mName bertipe String memuat  nilai yang diambil dari object user dengan parameter sesssionManager bagian NAME
        String mEmail = user.get(sessionManager.EMAIL); // Variabel mEmail bertipe String memuat nilai yang diambil dari object user dengan parameter sessionManager bagian EMAIL

        name.setText(mName); // MensetText pada object name yang diambil dari variabel mName
        email.setText(mEmail); //  MensetText pada object email yang diambil dari variable mEmail

        btn_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        }); // Proses yang terjadi ketika Variabel btn_logout terakses oleh tindakan seperti di Klik oleh User
    }
}
