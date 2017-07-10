package com.example.swarupa.stressdetector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String email;
    public PagerAdapter(FragmentManager fm, int NumOfTabs,String Email) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        email=Email;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Questionnaire tab1 = new Questionnaire();
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                Graph tab2 = new Graph();
                Bundle bundle1 = new Bundle();
                bundle1.putString("email", email);
                tab2.setArguments(bundle1);
                return tab2;
            case 2:
                Diary tab3 = new Diary();
                Bundle bundle2 = new Bundle();
                bundle2.putString("email", email);
                tab3.setArguments(bundle2);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}