package com.example.charateristics.helper;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Shared preferences file name
    private static final String PREF_NAME = "KafwihiLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String KEY_IS_UserName = "username";
    private static final String KEY_IS_UserEmail = "useremail";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setParams(String userName, String userEmail) {

        editor.putString(KEY_IS_UserName, userName);
        editor.putString(KEY_IS_UserEmail, userEmail);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUsername() {
        return pref.getString(KEY_IS_UserName, null);
    }

    public String getUseremail() {
        return pref.getString(KEY_IS_UserEmail, null);
    }

    public void Logout() {
        editor.clear();
        editor.commit();
    }
}