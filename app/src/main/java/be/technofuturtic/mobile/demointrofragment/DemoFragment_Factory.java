package be.technofuturtic.mobile.demointrofragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemoFragment_Factory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemoFragment_Factory extends Fragment {

    // Fragment initialization parameters
    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_DATE = "ARG_DATE";

    // Data of fragment
    private String title;
    private LocalDate date;

    public DemoFragment_Factory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of DemoFragment.
     *
     * @param title Fragment title.
     * @param date Date to display.
     * @return A new instance of fragment DemoFragment_Factory.
     */
    public static DemoFragment_Factory newInstance(String title, LocalDate date) {
        DemoFragment_Factory fragment = new DemoFragment_Factory();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putSerializable(ARG_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            date = (LocalDate) getArguments().getSerializable(ARG_DATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_demo__factory, container, false);

        TextView tvTitle = v.findViewById(R.id.tv_frag_demofact_title);
        TextView tvDate = v.findViewById(R.id.tv_frag_demofact_date);

        tvTitle.setText(title);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getString(R.string.date_pattern));
        String display = String.format(getString(R.string.text_date_display), date.format(formatter));
        tvDate.setText(display);

        return v;
    }
}