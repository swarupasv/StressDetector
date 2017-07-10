package com.example.swarupa.stressdetector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class Doctor_view extends AppCompatActivity {
    String name = "";
    String email="";
    String data = "";
    int tmp;
    JSONArray root;
    //private ListView List;
    //private ArrayAdapter<String> listAdapter;
    Button viewgraph;
    Button call;
    String phone="";
    EditText Pres;
    String pres="";
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorview_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = getIntent().getExtras().getString("Name");
        Pres=(EditText)findViewById(R.id.prescription);
        Button change=(Button)findViewById(R.id.changepre);


        try {
            URL url = new URL("http://192.168.43.197/doctorview.php");
            String urlParams = "Name=" + name;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(urlParams.getBytes());
            os.flush();
            os.close();

            InputStream is = httpURLConnection.getInputStream();
            while ((tmp = is.read()) != -1) {
                data += (char) tmp;
            }
            is.close();
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String err = null;
        try {
            root = new JSONArray(data);
            phone="";
            for (Integer i = 0; i < root.length(); i++) {
                email=root.getString(0);
                phone=root.getString(1);
                pres=root.getString(2);
            }
            Pres.setText(pres);
        } catch (JSONException e) {
            e.printStackTrace();
            err = "Exception: " + e.getMessage();
            Log.i("JSON ERROR:", "" + err);
        }

        viewgraph=(Button)findViewById(R.id.button1);
        call=(Button)findViewById(R.id.button2);
        call.setVisibility(View.VISIBLE);
        viewgraph.setVisibility(View.VISIBLE);

        viewgraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentA = new Graph();
                Bundle bundle1 = new Bundle();
                bundle1.putString("email", email);
                fragmentA.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, fragmentA, "YOUR_TARGET_FRAGMENT_TAG")
                        .addToBackStack("YOUR_SOURCE_FRAGMENT_TAG").commit();


            }


        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
            }


        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pres=Pres.getText().toString();
                String data = "";
                int tmp;
                try {
                    URL url = new URL("http://192.168.43.197/doctorviewpost.php");
                    String urlParams = "Name=" + name+"&pres="+pres;

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();
                    InputStream is = httpURLConnection.getInputStream();
                    while ((tmp = is.read()) != -1) {
                        data += (char) tmp;
                    }
                    is.close();
                    httpURLConnection.disconnect();
                    if (data.equals("")&& pres!="") {
                        data = "Prescription was updated successfully.";
                    }
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        });

    }
}

