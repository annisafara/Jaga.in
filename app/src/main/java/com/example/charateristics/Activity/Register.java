
package com.example.charateristics.Activity;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.charateristics.R;
import com.example.charateristics.App.AppConfig;
import com.example.charateristics.App.AppController;
import com.example.charateristics.helper.SQLiteHandler;
import com.example.charateristics.helper.SessionManager;
import com.example.charateristics.MainActivity;

import com.example.charateristics.Activity.LoginActivity;

public class Register extends Activity{
    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private String name,email,password,response;
    private RequestQueue queue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoinScreen);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        //db = new SQLiteHandler(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(Register.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                 name = inputFullName.getText().toString().trim();
                 email = inputEmail.getText().toString().trim();
                 password = inputPassword.getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    pDialog.setMessage("Registering ...");
                    showDialog();
                    String url = "https://192.168.43.195/android_login_api/register.php";
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,

                    new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    Log.d(TAG,"Response "+response.toString());
                                    hideDialog();
                                    try{
                                    JSONObject jObj = new JSONObject(response);
                                    boolean error = jObj.getBoolean("error");
                                    if(!error){
                                        //user successfull stored in mysql
                                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                                        // Launch login activity
                                        try{
                                            Thread.sleep(Toast.LENGTH_LONG);
                                            Register.this.finish();
                                            Intent intent = new Intent(
                                                    Register.this,
                                                    LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        catch (InterruptedException ex){
                                            ex.printStackTrace();
                                        }
                                    }else
                                    {
                                        // Error occurred in registration. Get the error
                                        // message
                                        String errorMsg = jObj.getString("error_msg");
                                        Toast.makeText(getApplicationContext(),
                                                errorMsg, Toast.LENGTH_LONG).show();
                                    }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    Log.e(TAG, "Registration Error: " + error.getMessage());
                                    Toast.makeText(getApplicationContext(),
                                            error.getMessage(), Toast.LENGTH_LONG).show();
                                    hideDialog();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("name", name);
                            params.put("email", email);
                            params.put("password", password);
                            return params;
                        }
                    };

                    queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(postRequest);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(i);
                finish();
            }
        });

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String email,
                              final String password)
    {
        pDialog.setMessage("Registering ...");
        showDialog();
        String url = "https://192.168.43.195/android_login_api/register.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,

        new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,"Response "+response.toString());
                hideDialog();
                try{
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if(!error){
                        //user successfull stored in mysql
                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        try{
                            Thread.sleep(Toast.LENGTH_LONG);
                            Register.this.finish();
                            Intent intent = new Intent(
                                    Register.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        catch (InterruptedException ex){
                            ex.printStackTrace();
                        }


                    }else
                    {
                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }
                    ) {
        @Override
        protected Map<String, String> getParams()
        {
            Map<String, String>  params = new HashMap<String, String>();
            params.put("name", name);
            params.put("email", email);
            params.put("password", password);
            return params;
        }
    };

        queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(postRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
