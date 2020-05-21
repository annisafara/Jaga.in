package com.nyoba.jagain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nyoba.jagain.network.config.Config;

import com.nyoba.jagain.network.config.UpdateConfig;
import com.nyoba.jagain.network.config.sessionConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    String pid;
    sessionConfig sessionConfig;
    String pemail;
    String pnama;
    String ptmptlhr;
    String ptgllhr;
    String palamat;
    String pjk;
    String pgoldar;
    String id,nama,alamat,tmptlhr,tgllhr,jenkel,goldar;
    EditText enama,ealamat,etmpt,etgl,ejenkel,egoldar;
    Button updatebtn;
    ProgressDialog prd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        nama = extras.getString("nama");
        alamat = extras.getString("alamat");
        tmptlhr = extras.getString("tmptlhr");
        tgllhr =  extras.getString("tgllhr");
        jenkel = extras.getString("jenkel");
        goldar = extras.getString("goldar");

        enama = (EditText) findViewById(R.id.unama);
        ealamat = (EditText) findViewById(R.id.ualamat);
        etmpt = (EditText) findViewById(R.id.utmpt);
        etgl = (EditText) findViewById(R.id.utgl);
        ejenkel = (EditText) findViewById(R.id.ujenkel);
        egoldar = (EditText) findViewById(R.id.ugoldar);
        updatebtn = (Button) findViewById(R.id.updatebtn);

        prd = new ProgressDialog(UpdateActivity.this);
        sessionConfig = new sessionConfig(getApplicationContext());

        enama.setText(nama);
        ealamat.setText(alamat);
        etmpt.setText(tmptlhr);
        etgl.setText(tgllhr);
        ejenkel.setText(jenkel);
        egoldar.setText(goldar);

        UpdateConfig update = Config.getClient().create(UpdateConfig.class);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prd.setMessage("Updating..");
                prd.setCancelable(false);
                prd.show();

                String sid = id;
                String sname = enama.getText().toString();
                String salamat = ealamat.getText().toString();
                String stmpt  = etmpt.getText().toString();
                String stgl = etgl.getText().toString();
                String sjenkel = ejenkel.getText().toString();
                String sgoldar = egoldar.getText().toString();

                Call<DataClass> updater = update.update(sid,sname,salamat,stmpt,stgl,sjenkel,sgoldar);
                updater.enqueue(new Callback<DataClass>() {
                    @Override
                    public void onResponse(Call<DataClass> call, Response<DataClass> response) {
                        Log.d("cek","Response :" + response.body().getEmail());


                        pid = response.body().getId();
                        pemail = response.body().getEmail();
                        pnama = response.body().getNama();
                        ptmptlhr = response.body().getTmptlhr();
                        ptgllhr = response.body().getTgllhr();
                        palamat = response.body().getAlamat();
                        pjk = response.body().getJk();
                        pgoldar = response.body().getGoldar();


                        sessionConfig.createSession(pnama,pemail,palamat,ptmptlhr,ptgllhr,pjk,pgoldar,pid);

                        prd.hide();
                        int success = response.body().getSuccess();

                        if ( success == 1) {
                            Log.d("cek","Response :" + response.body().getSuccess());
                            Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                            startActivity(i);
                        } else if( success == 0){
                            Toast.makeText(UpdateActivity.this, "Gagal UpdateActivity, Data tidak sesuai format", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataClass> call, Throwable t) {

                    }
                });

            }
        });
    }

}
