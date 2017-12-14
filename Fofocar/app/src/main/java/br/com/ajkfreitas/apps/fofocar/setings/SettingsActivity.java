package br.com.ajkfreitas.apps.fofocar.setings;


import android.os.Bundle;

import br.com.ajkfreitas.apps.fofocar.view.settings.SettingsFragment;


public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content,fragment,fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
