package com.example.swarupa.stressdetector;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import org.json.JSONArray;
import org.json.JSONException;
public class Graph extends Fragment {
    String email = null;
    View v;
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    Integer dates=0;
    Integer res=0;
    DataPoint[] values;
    JSONArray user_data;
    JSONArray root;
    Spinner spinner;
    Spinner spinner2;
    String month="";
    String year="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.graph_layout, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            email = bundle.getString("email", email);
        }
        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        SimpleDateFormat dateFormat = new SimpleDateFormat( "LLLL", Locale.getDefault() );
        Date date = new Date();
        month=dateFormat.format( date );
        spinner = (Spinner) v.findViewById(R.id.spinner);
        SimpleDateFormat dateFormat2 = new SimpleDateFormat( "yyyy", Locale.getDefault() );
        Date date2 = new Date();
        year=dateFormat2.format( date2 );
        spinner2 = (Spinner) v.findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                GraphView graphs = (GraphView) v.findViewById(R.id.graph);
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    month=item.toString();
                    graphs.removeAllSeries();
                    int tmp;
                    String data = "";
                    try {
                        URL url = new URL("http://192.168.43.197/graph.php");
                        String urlParams = "email=" + email+"&month="+month+"&year="+year;

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
                        values = new DataPoint[root.length()];
                        for(Integer i=0;i<root.length();i++) {
                            user_data = root.getJSONArray(i);
                            res=Integer.valueOf(user_data.getString(0));
                            dates= Integer.valueOf(user_data.getString(1).substring(8));
                            DataPoint v = new DataPoint(dates, res);
                            values[i] = v;
                        }
                        series = new LineGraphSeries<DataPoint>(values);
                        series.setColor(Color.BLACK);
                        series.setThickness(3);
                        series.setDrawDataPoints(true);
                        series.setDataPointsRadius(4);
                        graphs.addSeries(series);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        err = "Exception: " + e.getMessage();
                        Log.i("JSON ERROR:", "" + err);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                GraphView graphs = (GraphView) v.findViewById(R.id.graph);
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    year=item.toString();
                    graphs.removeAllSeries();
                    int tmp;
                    String data = "";
                    try {
                        URL url = new URL("http://192.168.43.197/graph.php");
                        String urlParams = "email=" + email+"&month="+month+"&year="+year;

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
                        values = new DataPoint[root.length()];
                        for(Integer i=0;i<root.length();i++) {
                            user_data = root.getJSONArray(i);
                            res=Integer.valueOf(user_data.getString(0));
                            dates= Integer.valueOf(user_data.getString(1).substring(8));
                            DataPoint v = new DataPoint(dates, res);
                            values[i] = v;
                        }
                        series = new LineGraphSeries<DataPoint>(values);
                        series.setColor(Color.BLACK);
                        series.setThickness(3);
                        series.setDrawDataPoints(true);
                        series.setDataPointsRadius(4);
                        graphs.addSeries(series);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        err = "Exception: " + e.getMessage();
                        Log.i("JSON ERROR:", "" + err);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });


        //String month= getMonth(Integer.valueOf(dateFormat.format(date)));
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(27);
        viewport.setMinX(1);
        viewport.setMaxX(31);
        viewport.setScrollable(false);
        graph.getGridLabelRenderer().setGridColor(Color.LTGRAY);
        graph.setTitle("Monthly Graph");
        graph.setTitleTextSize(40);
        graph.setTitleColor(Color.argb(100,222,36,67));

        int tmp;
        String data = "";
        try {
            URL url = new URL("http://192.168.43.197/graph.php");
            String urlParams = "email=" + email+"&month="+month;

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
            values = new DataPoint[root.length()];
            for(Integer i=0;i<root.length();i++) {
                user_data = root.getJSONArray(i);
                res=Integer.valueOf(user_data.getString(0));
                dates= Integer.valueOf(user_data.getString(1).substring(8));
                DataPoint v = new DataPoint(dates, res);
                values[i] = v;
            }
            series = new LineGraphSeries<DataPoint>(values);
            series.setColor(Color.BLACK);
            series.setThickness(3);
            series.setDrawDataPoints(true);
            series.setDataPointsRadius(4);
            graph.addSeries(series);
        } catch (JSONException e) {
            e.printStackTrace();
            err = "Exception: " + e.getMessage();
            Log.i("JSON ERROR:", "" + err);
        }

    return v;
    }
    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }



}

