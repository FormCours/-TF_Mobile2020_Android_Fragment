package be.technofuturtic.mobile.demointrofragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DemoFragment_Singleton extends Fragment {

    //region Singleton
    private static DemoFragment_Singleton instance;

    public static DemoFragment_Singleton getInstance() {
        if(instance == null) {
            instance = new DemoFragment_Singleton();
        }
        return instance;
    }
    //endregion


    public DemoFragment_Singleton() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo__singleton, container, false);
    }
}