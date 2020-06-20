package com.nyoba.jagain;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.nyoba.jagain.network.config.sessionConfig;
import java.util.HashMap;

public class home extends Fragment
{
    TextView pnama;
    sessionConfig sessionConfig;
    TextView kota;
    TextView pgoldar;
    TextView pjenkel;
    TextView ptgl;
    TextView pemail;
    TextView palamat;
    Button btnupdate,btnlogout;

    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View profiles = inflater.inflate(R.layout.home_fragment, container, false);

        pnama = (TextView) profiles.findViewById(R.id.profnama);
        pemail = (TextView) profiles.findViewById(R.id.profemail);

        sessionConfig = new sessionConfig(getContext());
        HashMap<String, String> user = sessionConfig.getUserDetail();
        String id = user.get(sessionConfig.ID);
        String name = user.get(sessionConfig.NAME);
        String emaili = user.get(sessionConfig.EMAIL);
        String tgllah = user.get(sessionConfig.TGLTLAHIR);
        String kota = user.get(sessionConfig.KOTA);
        String jenkel = user.get(sessionConfig.JENKEL);
        String goldar = user.get(sessionConfig.GOLDAR);
        String alamat = user.get(sessionConfig.ALAMAT);
        pnama.setText(Html.fromHtml("<b>" + name + "</b>"));
        pemail.setText(emaili);

        btnlogout = (Button) profiles.findViewById(R.id.logout);
        btnupdate = (Button) profiles.findViewById(R.id.update);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionConfig.logout();
            }
        });
    return profiles;
    }
}