package com.webakruti.kamgarchowk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


/**
 * Manages the shared preferences all over the application
 */
public class SharedPreferenceManager {
    private static Context applicationContext;
    private static SharedPreferences kamgarPreferences;
    public static void setApplicationContext(Context context) {
        applicationContext = context;
        setPreferences();
    }

    private static void setPreferences() {
        if (kamgarPreferences == null) {
            kamgarPreferences = applicationContext.getSharedPreferences("kamgarchowk",
                    Context.MODE_PRIVATE);
        }
    }

    public static void clearPreferences() {
        kamgarPreferences.edit().clear().commit();
    }


/*

    public static void storeUserResponseObjectInSharedPreference(UserResponse user) {
        SharedPreferences.Editor prefsEditor = kamgarPreferences.edit();
        //  prefsEditor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("UserResponseObject", json);
        prefsEditor.commit();
    }

    public static UserResponse getUserObjectFromSharedPreference() {
        Gson gson1 = new Gson();
        String json1 = kamgarPreferences.getString("UserResponseObject", "");
        UserResponse obj = gson1.fromJson(json1, UserResponse.class);
//		Log.e("RetrivedName:", obj.getFirstName());
        return obj;
    }


    public static void storeAdminResponseObjectInSharedPreference(AdminLoginSuccess adminLoginSuccess) {
        SharedPreferences.Editor prefsEditor = kamgarPreferences.edit();
        //  prefsEditor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(adminLoginSuccess);
        prefsEditor.putString("AdminResponseObject", json);
        prefsEditor.commit();
    }

    public static AdminLoginSuccess getAdminObjectFromSharedPreference() {
        Gson gson1 = new Gson();
        String json1 = kamgarPreferences.getString("AdminResponseObject", "");
        AdminLoginSuccess obj = gson1.fromJson(json1, AdminLoginSuccess.class);
//		Log.e("RetrivedName:", obj.getFirstName());
        return obj;
    }
*/

}
