package br.com.ajkfreitas.apps.fofocar.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.ajkfreitas.apps.fofocar.R;
import br.com.ajkfreitas.apps.fofocar.view.settings.SettingsActivity;
import br.com.ajkfreitas.apps.fofocar.view.settings.SettingsFragment;
import br.com.ajkfreitas.apps.fofocar.view.status.StatusActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_tweet:
                startActivity(new Intent(this, StatusActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
