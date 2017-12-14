package br.com.ajkfreitas.apps.fofocar.util;

import android.content.Context;

import java.util.Locale;

/**
 * Created by Angelo Freitas on 14/12/2017.
 */

public class LocaleProvider {
    public static Locale get(Context context) {
        final Locale current;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            current = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            current = context.getResources().getConfiguration().locale;
        }
        return current;
    }
}
