package com.example.swarupa.stressdetector;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Results extends Fragment{
    private Integer Result=0;
    private Integer sum=0;
    private TextView res;
    String Date;
    String email=null;
    View v;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.results_layout, container, false);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
            res=(TextView)v.findViewById(R.id.text);
        int tmp;
        String data="";
        try
        {
        if(sum<=4)
        {
            res.setText("Stress Level: Minimal or None"+" \n "+"Score out of 27:"+sum);
        }
        else if(sum<=9)
        {
            res.setText("Stress Level: Mild"+" \n "+"Score out of 27:"+sum);
        }
        else if(sum<=14)
        {
            res.setText("Stress Level: Moderate"+" \n "+"Score out of 27:"+sum);
        }
        else if(sum<=19)
        {
            res.setText("Stress Level: Moderately Severe"+" \n "+"Score out of 27:"+sum);
        }
        else if(sum<=27)
        {
            res.setText("Stress Level: Severe"+" \n "+"Score out of 27:"+sum);
        }
            Result=sum;
            Date= new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                URL url = new URL("http://192.168.43.197/question.php");
                String urlParams = "Result="+Result+"&Date="+Date+"&email="+email;

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(urlParams.getBytes());
            os.flush();
            os.close();
            InputStream is = httpURLConnection.getInputStream();
            while((tmp=is.read())!=-1){
                data+= (char)tmp;
            }
            is.close();
            httpURLConnection.disconnect();
            if(data.equals("")){
                data="Result was saved successfully.";
            }
            Toast.makeText(getContext(), data, Toast.LENGTH_LONG).show();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return v;

    }
    public Results()
    {

    }

    public Results(String Email,Integer gr1,Integer gr2,Integer gr3,Integer gr4,Integer gr5,Integer gr6,Integer gr7,Integer gr8,Integer gr9)

    {
        email=Email;
        if(gr1==1)
        {
            sum=sum+0;
        }
        else if(gr1==2)
        {
            sum=sum+1;
        }
        else if(gr1==3)
        {
            sum=sum+2;
        }
        else if(gr1==4)
        {
            sum=sum+3;
        }

        if(gr2==5)
        {
            sum=sum+0;
        }
        else if(gr2==6)
        {
            sum=sum+1;
        }
        else if(gr2==7)
        {
            sum=sum+2;
        }
        else if(gr2==8)
        {
            sum=sum+3;
        }

        if(gr3==9)
        {
            sum=sum+0;
        }
        else if(gr3==10)
        {
            sum=sum+1;
        }
        else if(gr3==11)
        {
            sum=sum+2;
        }
        else if(gr3==12)
        {
            sum=sum+3;
        }

        if(gr4==13)
        {
            sum=sum+0;
        }
        else if(gr4==14)
        {
            sum=sum+1;
        }
        else if(gr4==15)
        {
            sum=sum+2;
        }
        else if(gr4==16)
        {
            sum=sum+3;
        }

        if(gr5==17)
        {
            sum=sum+0;
        }
        else if(gr5==18)
        {
            sum=sum+1;
        }
        else if(gr5==19)
        {
            sum=sum+2;
        }
        else if(gr5==20)
        {
            sum=sum+3;
        }

        if(gr6==21)
        {
            sum=sum+0;
        }
        else if(gr6==22)
        {
            sum=sum+1;
        }
        else if(gr6==23)
        {
            sum=sum+2;
        }
        else if(gr6==24)
        {
            sum=sum+3;
        }

        if(gr7==25)
        {
            sum=sum+0;
        }
        else if(gr7==26)
        {
            sum=sum+1;
        }
        else if(gr7==27)
        {
            sum=sum+2;
        }
        else if(gr7==28)
        {
            sum=sum+3;
        }

        if(gr8==29)
        {
            sum=sum+0;
        }
        else if(gr8==30)
        {
            sum=sum+1;
        }
        else if(gr8==31)
        {
            sum=sum+2;
        }
        else if(gr8==32)
        {
            sum=sum+3;
        }

        if(gr9==33)
        {
            sum=sum+0;
        }
        else if(gr9==34)
        {
            sum=sum+1;
        }
        else if(gr9==35)
        {
            sum=sum+2;
        }
        else if(gr9==36)
        {
            sum=sum+3;
        }

    }

}
