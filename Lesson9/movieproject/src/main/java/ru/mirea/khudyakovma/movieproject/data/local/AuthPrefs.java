package ru.mirea.khudyakovma.movieproject.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthPrefs {
    private static final String PREFS_NAME = "auth_prefs";
    private static final String KEY_AUTH = "is_auth";
    private static final String KEY_USERNAME = "username";

    private final SharedPreferences prefs;

    public AuthPrefs(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean isAuthorized() {
        return prefs.getBoolean(KEY_AUTH, false);
    }

    public String getUserName() {
        return prefs.getString(KEY_USERNAME, "");
    }

    public void login(String userName) {
        prefs.edit().putBoolean(KEY_AUTH, true).putString(KEY_USERNAME, userName).apply();
    }

    public void logout() {
        prefs.edit().putBoolean(KEY_AUTH, false).putString(KEY_USERNAME, "").apply();
    }
}
