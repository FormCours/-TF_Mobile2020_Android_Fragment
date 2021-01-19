package be.technofuturtic.mobile.demointrofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
                CallBackFragment_Auto.OnFragmentClickListener {

    Button btnFrag1, btnFrag2, btnFrag3, btnFrag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFrag1 = findViewById(R.id.btn_main_frag1);
        btnFrag2 = findViewById(R.id.btn_main_frag2);
        btnFrag3 = findViewById(R.id.btn_main_frag3);
        btnFrag4 = findViewById(R.id.btn_main_frag4);

        btnFrag1.setOnClickListener(this);
        btnFrag2.setOnClickListener(this);
        btnFrag3.setOnClickListener(this);
        btnFrag4.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        disabledNavButton(1);

        // Obtenir l'intance du fragement
        DemoFragment_Singleton frag1 = DemoFragment_Singleton.getInstance();

        // Utilisation d'un gestionnaire de fragment
        FragmentManager fm = getSupportFragmentManager();

        // (Netoyage des fragments)
        for (Fragment f : fm.getFragments()) {
            fm.beginTransaction()
                    .remove(f)
                    .commit();
        }

        // - Initialisation d'une serie de changement (Transaction)
        FragmentTransaction transaction = fm.beginTransaction();

        // - Ajout des modifications a effectuer durant la transaction
        transaction.add(R.id.frag_main_container, frag1);

        // - Valider le changmeent
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_main_frag1:
                displayFrag1();
                disabledNavButton(1);
                break;
            case R.id.btn_main_frag2:
                displayFrag2();
                disabledNavButton(2);;
                break;
            case R.id.btn_main_frag3:
                displayFrag3();
                disabledNavButton(3);
                break;
            case R.id.btn_main_frag4:
                displayFrag4();
                disabledNavButton(4);
                break;
        }
    }

    private void disabledNavButton(int current) {
        btnFrag1.setEnabled(true);
        btnFrag2.setEnabled(true);
        btnFrag3.setEnabled(true);
        btnFrag4.setEnabled(true);

        switch (current) {
            case 1:
                btnFrag1.setEnabled(false);
                break;
            case 2:
                btnFrag2.setEnabled(false);
                break;
            case 3:
                btnFrag3.setEnabled(false);
                break;
            case 4:
                btnFrag4.setEnabled(false);
                break;
        }
    }

    private void displayFrag1() {
        displayFragment(DemoFragment_Singleton.getInstance());
    }

    private void displayFrag2() {
        Random random = new Random();
        int day = random.nextInt(28) + 1;
        int month = random.nextInt(11) + 1;

        LocalDate date = LocalDate.of(2020, month, day);
        DemoFragment_Factory f = DemoFragment_Factory.newInstance("Génération de date", date);
        displayFragment(f);
    }

    private void displayFrag3() {
        CallBackFragment_Manuel cb = new CallBackFragment_Manuel();
        cb.setClickListener(() -> {
            Toast.makeText(getApplicationContext(), "Vous avez cliqué", Toast.LENGTH_LONG).show();
        });

        displayFragment(cb);
    }

    private void displayFrag4() {
        CallBackFragment_Auto cb = new CallBackFragment_Auto();

        displayFragment(cb);
    }

    @Override
    public void onFragmentClick() {
        Toast.makeText(getApplicationContext(), "Action !", Toast.LENGTH_LONG).show();
    }

    private void displayFragment(Fragment fragment) {
        // Obtenir le fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // - Debut de la transaction
        FragmentTransaction transaction = fm.beginTransaction();

        // - (BONUS) Ajout d'une animation (Avant les operations)
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );

        // - Les operations a effectuer
        transaction.replace(R.id.frag_main_container, fragment);
//        Fragment old = fm.findFragmentById(R.id.frag_main_container);
//        transaction.remove(old);
//        transaction.add(R.id.frag_main_container, fragment);


        // Ajout la transaction à l'historique de navigation de l'app
        transaction.addToBackStack(null);

        // - Validation de la transaction
        transaction.commit();
    }
}