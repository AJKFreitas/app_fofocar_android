package br.com.ajkfreitas.apps.fofocar.view.status;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Angelo Freitas on 14/12/2017.
 */

public class TextLimitTextWatcher implements TextWatcher {

    private final EditText text;
    private final TextView limitView;
    private final int limit;
    private final int defaultColor;

    public TextLimitTextWatcher(EditText text, TextView limitView, int limit) {
        this.text = text;
        this.limitView = limitView;
        this.limit = limit;
        this.defaultColor = limitView.getTextColors().getDefaultColor();
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        int count = 140 - text.length();
        limitView.setText(Integer.toString(count));
        limitView.setTextColor(Color.GREEN);
        if (count < 10) {
            limitView.setTextColor(Color.RED);
        } else if (count < 25) {
            limitView.setTextColor(Color.YELLOW);
        } else {
            limitView.setTextColor(defaultColor);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
