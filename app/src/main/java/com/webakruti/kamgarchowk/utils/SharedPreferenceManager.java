package com.webakruti.kamgarchowk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.webakruti.kamgarchowk.model.KamgarLoginResponse;
import com.webakruti.kamgarchowk.model.SearchLocationList;
import com.webakruti.kamgarchowk.model.User;
import com.webakruti.kamgarchowk.model.UserLoginResponse;


/**
 * Manages the shared preferences all over the application
 */
public class SharedPreferenceManager {
    private static Context applicationContext;
    private static SharedPreferences tuitionPlusPreferences;
    public static void setApplicationContext(Context context) {
        applicationContext = context;
        setPreferences();
    }

    private static void setPreferences() {
        if (tuitionPlusPreferences == null) {
            tuitionPlusPreferences = applicationContext.getSharedPreferences("niramlrail",
                    Context.MODE_PRIVATE);
        }
    }

    public static void clearPreferences() {
        tuitionPlusPreferences.edit().clear().commit();
    }



    public static void storeUserResponseObjectInSharedPreference(UserLoginResponse user) {
        SharedPreferences.Editor prefsEditor = tuitionPlusPreferences.edit();
        //  prefsEditor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("UserResponseObject", json);
        prefsEditor.commit();
    }

    public static UserLoginResponse getUserObjectFromSharedPreference() {
        Gson gson1 = new Gson();
        String json1 = tuitionPlusPreferences.getString("UserResponseObject", "");
        UserLoginResponse obj = gson1.fromJson(json1, UserLoginResponse.class);
//		Log.e("RetrivedName:", obj.getFirstName());
        return obj;
    }


    public static void storeUserLocationResponseInSharedPreference(SearchLocationList.Citylist location) {
        SharedPreferences.Editor prefsEditor = tuitionPlusPreferences.edit();
        //  prefsEditor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(location);
        prefsEditor.putString("CityResponseObject", json);
        prefsEditor.commit();
    }

    public static SearchLocationList.Citylist getUserLocationObjectFromSharedPreference() {
        Gson gson1 = new Gson();
        String json1 = tuitionPlusPreferences.getString("CityResponseObject", "");
        SearchLocationList.Citylist obj = gson1.fromJson(json1, SearchLocationList.Citylist.class);
//		Log.e("RetrivedName:", obj.getFirstName());
        return obj;
    }


   //kamgar references-------------------------------------------------------------------------------

    public static void storeKamgarObject(KamgarLoginResponse kamgar) {
        SharedPreferences.Editor prefsEditor = tuitionPlusPreferences.edit();
        //  prefsEditor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(kamgar);
        prefsEditor.putString("KamgarResponseObject", json);
        prefsEditor.commit();
    }

    public static KamgarLoginResponse getKamgarObject() {
        Gson gson1 = new Gson();
        String json1 = tuitionPlusPreferences.getString("KamgarResponseObject", "");
        KamgarLoginResponse obj = gson1.fromJson(json1, KamgarLoginResponse.class);
//		Log.e("RetrivedName:", obj.getFirstName());
        return obj;
    }

}
