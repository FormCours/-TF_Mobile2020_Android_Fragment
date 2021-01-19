package be.technofuturtic.mobile.demointrofragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CallBackFragment_Manuel extends Fragment {

    //region Callback
    @FunctionalInterface
    public interface OnFragmentClickListener {
        void onFragmentClick();
    }

    private OnFragmentClickListener clickListener;

    // Alternative => Ajouter le listenner dans la factory
    public void setClickListener(OnFragmentClickListener listener) {
        this.clickListener = listener;
    }
    //endregion

    public CallBackFragment_Manuel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_call_back__manuel, container, false);

        Button btn = v.findViewById(R.id.btn_frag_cbmanu_action);
        btn.setOnClickListener((view) -> {
            Log.d("CallBackFragment_Manuel", "Click");

            if(this.clickListener != null) {
                this.clickListener.onFragmentClick();
            }
        });

        return v;
    }
}