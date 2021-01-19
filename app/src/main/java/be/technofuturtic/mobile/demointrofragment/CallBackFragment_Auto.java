package be.technofuturtic.mobile.demointrofragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CallBackFragment_Auto extends Fragment {

    //region Callback
    @FunctionalInterface
    public interface OnFragmentClickListener {
        void onFragmentClick();
    }

    private OnFragmentClickListener clickListener;
    //endregion

    //region Recuperation du listener de l'activité
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentClickListener) {
            clickListener = (OnFragmentClickListener) context;
        }
        else {
            String msgError = String.format("%s must implement OnFragmentClickListener", context.toString());
            throw new RuntimeException(msgError);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        clickListener = null;
    }
    //endregion


    public CallBackFragment_Auto() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_call_back__auto, container, false);

        Button btn = v.findViewById(R.id.btn_frag_cbauto_action);
        btn.setOnClickListener((view) -> {
            this.clickListener.onFragmentClick();
        });

        return v;
    }
}