package com.skyworth.inputtest.tabhost;

import com.skyworth.inputtest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class FirFragment extends Fragment implements OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View mView = inflater.inflate(R.layout.firstfrag_lay, null);
        return mView;
    }    
    
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        
    }
    
}
