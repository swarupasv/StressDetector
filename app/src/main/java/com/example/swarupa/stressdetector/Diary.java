package com.example.swarupa.stressdetector;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Diary extends Fragment {
    private Button submit;
    String email = null;
    View v;
    String diary_data;
    TextView diary;
    EditText Date;
    String date = "";
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.diary_layout, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            email = bundle.getString("email", email);
        }

        diary = (EditText) v.findViewById(R.id.textView2);
        diary_data = diary.getText().toString();
        Date = (EditText) v.findViewById(R.id.datePicker);
        DateDialogFragment datepicker = new DateDialogFragment();
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        datepicker.showSetDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        date = Date.getText().toString();
        id = (int) R.id.datePicker;

        Date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DateDialogFragment datepicker = new DateDialogFragment();
                datepicker.show(getActivity().getSupportFragmentManager(), "showDate");
            }
        });

        submit = (Button) v.findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String data = "";
                int tmp;

                    try {
                        URL url = new URL("http://192.168.43.197/diary.php");
                        diary_data = diary.getText().toString();
                        date = Date.getText().toString();
                        String urlParams = "diary_data=" + diary_data + "&email=" + email + "&date=" + date;
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
                        if (data.equals("")&& diary_data!="") {
                            data = "Your diary was saved successfully.";
                        }
                        Toast.makeText(getActivity().getApplicationContext(), data, Toast.LENGTH_LONG).show();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        });
        return v;

    }

    public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public DateDialogFragment() {
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            showSetDate(year, monthOfYear, dayOfMonth);
        }


        public void showSetDate(int year, int month, int day) {
            Date.setText(year + "/" + (month + 1) + "/" + day);
            date = Date.getText().toString();
            diary.setText("");
            diary.setHint("So What'sup Today");
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://192.168.43.197/diary_fetch.php");
                String urlParams = "diary_data=" + diary_data + "&email=" + email + "&date=" + date;

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
            if (data == "")
            {
                diary.setText("");
                diary.setHint("So What'sup Today");

            }
            else {
                try {
                    JSONObject root = new JSONObject(data);
                    JSONObject user_data = root.getJSONObject("user_data");
                    diary_data = user_data.getString("diary_data");
                    diary.setText(diary_data);
                    String Em = user_data.getString("email");
                } catch (JSONException e) {
                    e.printStackTrace();
                    err = "Exception: " + e.getMessage();
                    Log.i("JSON ERROR:", "" + err);
                }

            }
        }
    }
}