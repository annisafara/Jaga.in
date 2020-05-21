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
import com.nyoba.jagain.network.config.LoginIConfig;
import com.nyoba.jagain.network.config.sessionConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    String pid;
    sessionConfig sessionConfig;
    String pemail;
    String pnama;
    String ptmptlhr;
    String ptgllhr;
    String palamat;
    String pjk;
    String pgoldar;
    EditText email,password;
    Button btn_login;
    ProgressDialog pd;

    public void pdh_regis(View view) {
        Intent i = new Intent(this, RegistrasiActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionConfig = new sessionConfig(getApplicationContext());

        pd = new ProgressDialog(LoginActivity.this);
        email = (EditText) findViewById(R.id.email_lgn);
        password = (EditText) findViewById(R.id.password_lgn);
        btn_login = (Button) findViewById(R.id.btn_login);

        LoginIConfig lgin = Config.getClient().create(LoginIConfig.class);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail= email.getText().toString();
                String spassword = password.getText().toString();

                    pd.setMessage("Login...");
                    pd.setCancelable(false);
                    pd.show();


                    Call<DataClass> login = lgin.login(semail,spassword);
                    login.enqueue(new Callback<DataClass>() {
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

                            pd.hide();
                            int success = response.body().getSuccess();

                            if ( success == 1) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.putExtra("pid", pid);
                                String message = pid;
                                startActivity(i);
                            } else if( success == 2) {
                                Toast.makeText(LoginActivity.this, "password salah", Toast.LENGTH_SHORT).show();
                                password.setText("");
                            } else if( success == 404){
                                Toast.makeText(LoginActivity.this, "Nomor belum terdaftar", Toast.LENGTH_SHORT).show();
                                password.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<DataClass> call, Throwable t) {

                            Log.d("cek","Fail : Gagal mengirim data");
                            System.out.println("onFailure"+call);
                            t.printStackTrace();
                            pd.hide();

                            Toast.makeText(LoginActivity.this, "Error mengirim!", Toast.LENGTH_SHORT).show();

                        }
                    });
            }
        });
    }

}
