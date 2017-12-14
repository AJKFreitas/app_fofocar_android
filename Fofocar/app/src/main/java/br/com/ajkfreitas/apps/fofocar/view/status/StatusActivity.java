package br.com.ajkfreitas.apps.fofocar.view.status;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.ajkfreitas.apps.fofocar.R;
import br.com.ajkfreitas.apps.fofocar.domain.model.Message;
import br.com.ajkfreitas.apps.fofocar.domain.services.MessageService;
import br.com.ajkfreitas.apps.fofocar.view.main.MainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends AppCompatActivity {

    private static final String TAG = "StatusActivity";
    private static final int TEXT_SIZE_LIMIT = 140;

    private EditText editStatus;
    private Button buttonTweet;
    private TextView textCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        editStatus = (EditText) findViewById(R.id.editStatus);
        buttonTweet = (Button) findViewById(R.id.buttonTweet);
        textCount = (TextView) findViewById(R.id.textCount);

        buttonTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1 - Carregando dados de usuário.
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(StatusActivity.this);
                final String username = prefs.getString("username", "Jhon Doe");

                String status = editStatus.getText().toString();

                new PostTask().execute(new Message(username, status));
            }
        });

        editStatus.addTextChangedListener(new TextLimitTextWatcher(editStatus, textCount, TEXT_SIZE_LIMIT));
    }

    @SuppressLint("NewApi")
    private final class PostTask extends AsyncTask<Message, Void, String> {
        // 1 - Instanciando serviço.
        private final MessageService service = new MessageService();

        @Override
        protected String doInBackground(Message... messages) {
            String message = getResources().getString(R.string.publish_message_succes);

            try {
                // 2 - Para cada mensagem enviada para essa task, vamos publicá-la utilizando o
                // serviço.
                for (Message m: messages) {
                    service.publish(m);
                }
            } catch (Exception e) {
                // 3 - Se algo falhar (REDE, disponibilidade do serviço, etc precisamos dar o retorno)
                Log.e(TAG, "Error posting status! " + e.getMessage() ,e);
                message = getResources().getString(R.string.publish_message_failed);
            }

            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // 1 - Exibir mensagem sobre a tela com o resultado da operação.
            Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();

            // 2 - Levar o usuário para a tela inicial
            startActivity(new Intent(StatusActivity.this, MainActivity.class));
            // 3 - Finalizar essa activity para liberar recursos
            StatusActivity.this.finish();
        }
    }
}
