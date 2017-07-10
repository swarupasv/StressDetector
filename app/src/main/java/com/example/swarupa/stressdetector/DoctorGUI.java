package com.example.swarupa.stressdetector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
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

public class DoctorGUI extends AppCompatActivity {
    String email = "";
    String data = "";
    int tmp;
    JSONArray root, user_data;
    String Name="";
    String Phone="";
    String Email="";
    private ListView List;
    private ArrayAdapter<String> listAdapter ;
    String[] patients;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        email = getIntent().getExtras().getString("email");

        List=(ListView)findViewById(R.id.list);
        try {
            URL url = new URL("http://192.168.43.197/doctor.php");
            String urlParams = "email=" + email;

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
            //Toast.makeText(getApplication(), data, Toast.LENGTH_LONG).show();
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
            patients= new String[root.length()];
            for (Integer i = 0; i < root.length(); i++) {
                user_data = root.getJSONArray(i);
                Name = user_data.getString(1);
                patients[i]=Name;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            err = "Exception: " + e.getMessage();
            Log.i("JSON ERROR:", "" + err);
        }
        ArrayList<String> patientList = new ArrayList<String>();
        patientList.addAll( Arrays.asList(patients) );
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, patientList);
        List.setAdapter( listAdapter );
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view).getText().toString();

                Intent myIntent = new Intent(DoctorGUI.this, Doctor_view.class);
                myIntent.putExtra("Name", item);
                startActivity(myIntent);
            }
        });    }
    }


