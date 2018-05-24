package in.fusionbit.shreejeepricelist.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedPreferences {

    private android.content.SharedPreferences sp;


    public SharedPreferences(final Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean saveUsernameAndPassword(final String username, final String password) {
        return sp.edit()
                .putString("username", username)
                .putString("password", password)
                .commit();
    }

    public Map<String, String> getSavedUsernameAndPassword() {

        final String username = sp.getString("username", null);
        final String password = sp.getString("password", null);

        if (username == null || password == null) {
            return null;
        } else {
            return new HashMap<String, String>() {{
                put("username", username);
                put("password", password);
            }};
        }
    }

}
