package com.uas.uas.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uas.uas.InvoicegamesActivity;
import com.uas.uas.R;
import com.uas.uas.RequestHandler;
import com.uas.uas.SessionManager;
import com.uas.uas.konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamesActivity extends AppCompatActivity {

    private String KEY_NAME2 = "GAME";
    private String KEY_NAME3 = "VOUCHER";



    private static final String TAG = GamesActivity.class.getSimpleName();
    private TextView saldo;
    SessionManager sessionManager; // Mendeklarasikan variabel sessionManager dari object SessionManager
    String getId;
    private static String URL_READ = "http://192.168.43.209/android/barang/saldo.php";

    private ListView listView; //*
    private String JSON_STRING; //*

    private EditText games, voucher;
    private Button btn_bayar; // Mendeklarasikan variable bernama btn_regist yang bertipe data object Button dan bersifat private
    private ProgressBar loading; // Mendekalrasikan variable bernama loading yang bertipe data object ProgressBar dan bersifat private
    private static String URL_BAYAR = "http://192.168.43.209/android/transaksi/games/bayar.php"; // Mendeklarasikan variabel URL_REGIST yang dipointkan ke "http://192.168.43.209/android/transaksi/register.php" yang bertipe data String dan bersifat private dimana nilai data tidak dapat dirubah (static)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        sessionManager = new SessionManager(this); // Menginisialisasi object baru bernama sessionManager dari object SessionManager
        sessionManager.checkLogin(); // SessionManager mengecek kondisi login

        saldo = (TextView) findViewById(R.id.saldo) ;

        HashMap<String, String> user = sessionManager.getUserDetail();  //Membuat object HashMap bernama user yang digunakan untuk menyimpan data dengan tipe data String dan value String
        getId = user.get(sessionManager.ID);


        listView = (ListView) findViewById(R.id.listView);
        getJSON();

        loading = findViewById(R.id.loading); // Menginisialisasi variable loading dimana ia bernilai object yang bernama id "loading" yang didapatkan dari layout main
        games = (EditText)findViewById(R.id.games);
        voucher = findViewById(R.id.voucher);
        btn_bayar = findViewById(R.id.btn_bayar); // Menginisialisasi variable btn_regist dimana ia bernilai object yang bernama id "btn_regist" yang didapatkan dari layout main


        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button


        btn_bayar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String mGames = games.getText().toString().trim();
                String mVoucher = voucher.getText().toString().trim();


                if (!mGames.isEmpty() || !mVoucher.isEmpty()){
                    Regist();
                }

                else {
                    games.setError("Harap Masukkan Nama Games");
                    voucher.setError("Harap Masukkan Nominal Voucher");
                }
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
        btn_bayar.setVisibility(View.GONE); // Menghilangkan Buton REGIS

        final String games = this.games.getText().toString().trim();
        final String voucher = this.voucher.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_BAYAR, // Membuat variable stringRequest untuk mengirimkan data ke variable URL_REGIST yang terpoint ke "http://192.168.43.209"
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){ // Jika Sukses mengirimkan data maka
                                Toast.makeText(GamesActivity.this, "Permintaan Sedang Diproses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(GamesActivity.this, InvoicegamesActivity.class);
                                intent.putExtra(KEY_NAME2, games);
                                intent.putExtra(KEY_NAME3, voucher);
                                startActivity(intent);
                                loading.setVisibility(View.GONE);
                                btn_bayar.setVisibility(View.VISIBLE);
                            }

                        }
                        catch (JSONException e){ // Jika gagal mengirimkan data maka
                            e.printStackTrace();
                            Toast.makeText(GamesActivity.this, "Permintaan Gagal Diproses " +e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_bayar.setVisibility(View.VISIBLE);
                        }
                    }
                },

                new Response.ErrorListener() { //Jika gagal mengirimkan data karena kesalahan
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GamesActivity.this, "Permintaan Gagal Diproses " +error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_bayar.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>(); //Membuat object HashMap bernama params yang digunakan untuk menyimpan data dengan tipe data String dan value String
                params.put("games", games);
                params.put("voucher", voucher);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID);
                String name = jo.getString(konfigurasi.TAG_NAMA);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_ID,id);
                employees.put(konfigurasi.TAG_NAMA,name);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                GamesActivity.this, list, R.layout.list_item2,
                new String[]{konfigurasi.TAG_ID,konfigurasi.TAG_NAMA},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GamesActivity.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    //getUserDetail
    private void getUserDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strSaldo = object.getString("saldo").trim();

                                    saldo.setText(strSaldo);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(GamesActivity.this, "Error Reading Detail "+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(GamesActivity.this, "Error Reading Detail "+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })

        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }


}
