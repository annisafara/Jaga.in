package com.nyoba.jagain;

import android.content.Intent;
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


public class profile extends Fragment {
    TextView pnama;
    sessionConfig sessionConfig;
    TextView ptmpt;
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
        View profiles = inflater.inflate(R.layout.profile_fragment, container, false);

        pnama = (TextView) profiles.findViewById(R.id.profnama);
        palamat = (TextView) profiles.findViewById(R.id.profalamat);
        pemail = (TextView) profiles.findViewById(R.id.profemail);
        ptgl = (TextView) profiles.findViewById(R.id.proftgl);
        ptmpt = (TextView) profiles.findViewById(R.id.proftmpt);
        pjenkel = (TextView) profiles.findViewById(R.id.profjenkel);
        pgoldar = (TextView) profiles.findViewById(R.id.profgoldar);

        sessionConfig = new sessionConfig(getContext());
        HashMap<String, String> user = sessionConfig.getUserDetail();
        String id = user.get(sessionConfig.ID);
        String name = user.get(sessionConfig.NAME);
        String emaili = user.get(sessionConfig.EMAIL);
        String tgllah = user.get(sessionConfig.TGLTLAHIR);
        String tmpt = user.get(sessionConfig.TMPTLAHIR);
        String jenkel = user.get(sessionConfig.JENKEL);
        String goldar = user.get(sessionConfig.GOLDAR);
        String alamat = user.get(sessionConfig.ALAMAT);
        pnama.setText(Html.fromHtml("<b>" + name + "</b>"));
        palamat.setText(alamat);
        pemail.setText(emaili);
        ptgl.setText(tgllah);
        ptmpt.setText(tmpt);
        pjenkel.setText(jenkel);
        pgoldar.setText(goldar);

        btnlogout = (Button) profiles.findViewById(R.id.logout);
        btnupdate = (Button) profiles.findViewById(R.id.update);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UpdateActivity.class);
                i.putExtra("id",id);
                i.putExtra("nama",name);
                i.putExtra("alamat",alamat);
                i.putExtra("tmptlhr",tmpt);
                i.putExtra("tgllhr",tgllah);
                i.putExtra("jenkel",jenkel);
                i.putExtra("goldar",goldar);
                startActivity(i);
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionConfig.logout();
            }
        });

        return profiles;
    }
}
