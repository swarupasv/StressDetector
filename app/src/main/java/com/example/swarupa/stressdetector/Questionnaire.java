package com.example.swarupa.stressdetector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Questionnaire extends Fragment {
    private Button Submit;
    private RadioGroup group1;
    private RadioGroup group2;
    private RadioGroup group3;
    private RadioGroup group4;
    private RadioGroup group5;
    private RadioGroup group6;
    private RadioGroup group7;
    private RadioGroup group8;
    private RadioGroup group9;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;
    private RadioButton rb7;
    private RadioButton rb8;
    private RadioButton rb9;
    private RadioButton rb10;
    private RadioButton rb11;
    private RadioButton rb12;
    private RadioButton rb13;
    private RadioButton rb14;
    private RadioButton rb15;
    private RadioButton rb16;
    private RadioButton rb17;
    private RadioButton rb18;
    private RadioButton rb19;
    private RadioButton rb20;
    private RadioButton rb21;
    private RadioButton rb22;
    private RadioButton rb23;
    private RadioButton rb24;
    private RadioButton rb25;
    private RadioButton rb26;
    private RadioButton rb27;
    private RadioButton rb28;
    private RadioButton rb29;
    private RadioButton rb30;
    private RadioButton rb31;
    private RadioButton rb32;
    private RadioButton rb33;
    private RadioButton rb34;
    private RadioButton rb35;
    private RadioButton rb36;
    String email=null;
    ScrollView scrollView;
    boolean flag=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.questions_layout, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             email = bundle.getString("email",email);
        }
        scrollView=(ScrollView)v.findViewById(R.id.scroll);
        group1 = (RadioGroup) v.findViewById(R.id.group1);
        group2 = (RadioGroup) v.findViewById(R.id.group2);
        group3 = (RadioGroup) v.findViewById(R.id.group3);
        group4 = (RadioGroup) v.findViewById(R.id.group4);
        group5 = (RadioGroup) v.findViewById(R.id.group5);
        group6 = (RadioGroup) v.findViewById(R.id.group6);
        group7 = (RadioGroup) v.findViewById(R.id.group7);
        group8 = (RadioGroup) v.findViewById(R.id.group8);
        group9 = (RadioGroup) v.findViewById(R.id.group9);
        rb1=(RadioButton) v.findViewById(R.id.ans1_1);
        rb1.setId(1);
        rb2=(RadioButton) v.findViewById(R.id.ans1_2);
        rb2.setId(2);
        rb3=(RadioButton) v.findViewById(R.id.ans1_3);
        rb3.setId(3);
        rb4=(RadioButton) v.findViewById(R.id.ans1_4);
        rb4.setId(4);
        rb5=(RadioButton) v.findViewById(R.id.ans2_1);
        rb5.setId(5);
        rb6=(RadioButton) v.findViewById(R.id.ans2_2);
        rb6.setId(6);
        rb7=(RadioButton) v.findViewById(R.id.ans2_3);
        rb7.setId(7);
        rb8=(RadioButton) v.findViewById(R.id.ans2_4);
        rb8.setId(8);
        rb9=(RadioButton) v.findViewById(R.id.ans3_1);
        rb9.setId(9);
        rb10=(RadioButton) v.findViewById(R.id.ans3_2);
        rb10.setId(10);
        rb11=(RadioButton) v.findViewById(R.id.ans3_3);
        rb11.setId(11);
        rb12=(RadioButton) v.findViewById(R.id.ans3_4);
        rb12.setId(12);
        rb13=(RadioButton) v.findViewById(R.id.ans4_1);
        rb13.setId(13);
        rb14=(RadioButton) v.findViewById(R.id.ans4_2);
        rb14.setId(14);
        rb15=(RadioButton) v.findViewById(R.id.ans4_3);
        rb15.setId(15);
        rb16=(RadioButton) v.findViewById(R.id.ans4_4);
        rb16.setId(16);
        rb17=(RadioButton) v.findViewById(R.id.ans5_1);
        rb17.setId(17);
        rb18=(RadioButton) v.findViewById(R.id.ans5_2);
        rb18.setId(18);
        rb19=(RadioButton) v.findViewById(R.id.ans5_3);
        rb19.setId(19);
        rb20=(RadioButton) v.findViewById(R.id.ans5_4);
        rb20.setId(20);
        rb21=(RadioButton) v.findViewById(R.id.ans6_1);
        rb21.setId(21);
        rb22=(RadioButton) v.findViewById(R.id.ans6_2);
        rb22.setId(22);
        rb23=(RadioButton) v.findViewById(R.id.ans6_3);
        rb23.setId(23);
        rb24=(RadioButton) v.findViewById(R.id.ans6_4);
        rb24.setId(24);
        rb25=(RadioButton) v.findViewById(R.id.ans7_1);
        rb25.setId(25);
        rb26=(RadioButton) v.findViewById(R.id.ans7_2);
        rb26.setId(26);
        rb27=(RadioButton) v.findViewById(R.id.ans7_3);
        rb27.setId(27);
        rb28=(RadioButton) v.findViewById(R.id.ans7_4);
        rb28.setId(28);
        rb29=(RadioButton) v.findViewById(R.id.ans8_1);
        rb29.setId(29);
        rb30=(RadioButton) v.findViewById(R.id.ans8_2);
        rb30.setId(30);
        rb31=(RadioButton) v.findViewById(R.id.ans8_3);
        rb31.setId(31);
        rb32=(RadioButton) v.findViewById(R.id.ans8_4);
        rb32.setId(32);
        rb33=(RadioButton) v.findViewById(R.id.ans9_1);
        rb33.setId(33);
        rb34=(RadioButton) v.findViewById(R.id.ans9_2);
        rb34.setId(34);
        rb35=(RadioButton) v.findViewById(R.id.ans9_3);
        rb35.setId(35);
        rb36=(RadioButton) v.findViewById(R.id.ans9_4);
        rb36.setId(36);
        Submit = (Button) v.findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (group1.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else if (group2.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else if (group3.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                }// one of the radio buttons is checked
                else if (group4.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else if (group5.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else if (group6.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else if (group7.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else if (group8.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else if (group9.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity().getApplicationContext(), "Please select answers for all questions", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "You submitted successfully", Toast.LENGTH_SHORT).show();

                    Fragment fragmentA = new Results(email, group1.getCheckedRadioButtonId(), group2.getCheckedRadioButtonId(), group3.getCheckedRadioButtonId(), group4.getCheckedRadioButtonId(), group5.getCheckedRadioButtonId(), group6.getCheckedRadioButtonId(), group7.getCheckedRadioButtonId(), group8.getCheckedRadioButtonId(), group9.getCheckedRadioButtonId());
                    getFragmentManager().beginTransaction()
                            .replace(R.id.content, fragmentA, "YOUR_TARGET_FRAGMENT_TAG")
                            .commit();
                            scrollView.fullScroll(View.FOCUS_DOWN);

                    takeScreenshot(v);


                }
            }
        });


        return  v ;  }

    public void sendMail(String path) {
        scrollView.setBackgroundColor(Color.argb(255,200,226,215));
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                new String[] { "vaishampayan4@gmail.com" });
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                "Result");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                "This is an autogenerated mail from StressDetector app");
        emailIntent.setType("image/png");
        Uri myUri = Uri.parse("file://" + path);
        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(emailIntent, "Send mail to Doctor..."));
    }
    public void takeScreenshot(View v) {
        ScrollView scrollView=(ScrollView)getView().findViewById(R.id.scroll);
        int h = 0;
        Bitmap bitmap = null;
        //get the actual height of scrollview
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.WHITE);
        }
        // create bitmap with target size
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/sdcard/screen_test.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
            sendMail("/sdcard/screen_test.png");
        } catch (IOException e) {
            // TODO: handle exception
        }
        }
    }







