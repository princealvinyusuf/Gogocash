package com.uas.uas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText name, email, password, c_password;  // Mendeklarasikan variable bernama name, email, password, dan c_password yang bertipe data object EditText yang bersifat private
    private Button btn_regist; // Mendeklarasikan variable bernama btn_regist yang bertipe data object Button dan bersifat private
    private ProgressBar loading; // Mendekalrasikan variable bernama loading yang bertipe data object ProgressBar dan bersifat private
    private static String URL_REGIST = "http://192.168.43.209/android/register.php"; // Mendeklarasikan variabel URL_REGIST yang dipointkan ke "http://192.168.43.209/android/register.php" yang bertipe data String dan bersifat private dimana nilai data tidak dapat dirubah (static)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loading = findViewById(R.id.loading); // Menginisialisasi variable loading dimana ia bernilai object yang bernama id "loading" yang didapatkan dari layout main
        name = findViewById(R.id.name); // Menginisialisasi variable name dimana ia bernilai object yang bernama id "name" yang didapatkan dari layout main
        email = findViewById(R.id.email); // Menginisialisasi variable email dimana ia bernilai object yang bernama id "email" yang didapatkan dari layout main
        password = findViewById(R.id.password); // Menginisialisasi varible password dimana ia bernilai object yang bernama id "password" yang didapatkan dari layout main
        c_password = findViewById(R.id.c_password); // Menginisialisasi variable c_password dimana ia bernilai object yang bernama id "c_password" yang didapatkan dari layout main
        btn_regist = findViewById(R.id.btn_regist); // Menginisialisasi variable btn_regist dimana ia bernilai object yang bernama id "btn_regist" yang didapatkan dari layout main


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button


        btn_regist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Regist();
            }
        }); // Proses yang terjadi ketika Variabel btn_regist terakses oleh tindakan seperti di Klik oleh User
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void Regist(){ // Method ini digunakan untuk melakukan serangkaian tindakan registrasi user
        loading.setVisibility(View.VISIBLE); // Menampilkan Progress Loading
        btn_regist.setVisibility(View.GONE); // Menghilangkan Buton REGIS

        final String name = this.name.getText().toString().trim(); // Mendeklarasikan variable bernama name yang bertipe data String dan bersifat final dimana variable ini digunakan untuk mengambil nilai teks dari object yang ber id "name" dan mengkonversinya ke dalam bentuk string dan menghapus semua spasi dari teks kecuali spasi tunggal di antara kata yang diinputkan user
        final String email = this.email.getText().toString().trim(); // Mendeklarasikan variable bernama email yang bertipe data string dan bersifat final dimana variable ini digunakan untuk mengambil nilai teks dari object yang ber id "email" dan mengkonversikannya kedalam bentuk string dan menghapus semua spasi dari teks kecuali spasi tunggal di antara kata yang diinputkan user
        final String password = this.password.getText().toString().trim(); // Mendelkarasikan variable bernama password bertipe data string dan bersifat final dimana variable digunakan untuk mengambil nilai teks dari object yang ber id "password" dan mengkonversikannya kedalam bentuk string dan menghapus semua spasi dari teks kecuali spasi tunggal di antara kata yang diinputkan user

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, // Membuat variable stringRequest untuk mengirimkan data ke variable URL_REGIST yang terpoint ke "http://192.168.43.209"
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){ // Jika Sukses mengirimkan data maka
                                Toast.makeText(MainActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                        }
                        catch (JSONException e){ // Jika gagal mengirimkan data maka
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Register Error! " +e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    }
                },

                new Response.ErrorListener() { //Jika gagal mengirimkan data karena kesalahan
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Register Error! " +error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>(); //Membuat object HashMap bernama params yang digunakan untuk menyimpan data dengan tipe data String dan value String
                params.put("name", name); // Variabel params untuk meletakkan nilai name dari variable name
                params.put("email", email); // Variable params untuk meletakkan nilai email dari variable email
                params.put("password", password); // Variable params untuk meletakkan nilai password dari variable password
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
