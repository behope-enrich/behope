package com.enrich.behope;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMBERME = "rememberMe";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final  String KEY_USERNAME = "userName";
    public static final String KEY_EMAIL ="email";
    public static final String KEY_PHONENUMBER ="phoneNmber";
    public static final String KEY_PASSWORD ="password";

    private static final String IS_REMEMBERME = "IsRememberMe";
    public static final String KEY_SESSIONPHONENUMBER ="phoneNmber";
    public static final String KEY_SESSIONPASSWORD ="password";

    public SessionManager(Context _context,String sessionName){

        context=_context;
        usersSession = context.getSharedPreferences(sessionName,Context.MODE_PRIVATE );
        editor = usersSession.edit();

    }

    public void createLoginSession(String userName,String email,String phoneNumber,String password){

        editor.putBoolean( IS_LOGIN,true );

        editor.putString( KEY_USERNAME,userName);
        editor.putString( KEY_EMAIL,email );
        editor.putString( KEY_PHONENUMBER,phoneNumber );
        editor.putString( KEY_PASSWORD,password );

        editor.commit();

    }

    public HashMap<String, String> getUserDetailFromSession(){

        HashMap<String,String> userData = new HashMap<>();

        userData.put( KEY_USERNAME, usersSession.getString( KEY_USERNAME, null ) );
        userData.put( KEY_EMAIL, usersSession.getString( KEY_EMAIL, null ) );
        userData.put( KEY_PHONENUMBER, usersSession.getString( KEY_PHONENUMBER, null ) );
        userData.put( KEY_PASSWORD, usersSession.getString( KEY_PASSWORD, null ) );

        return userData;

    }

    public boolean checkLogin(){

        if (usersSession.getBoolean( IS_LOGIN,false ))
        {
            return true;
        }
        else {
            return false;
        }

    }

    public void logoutUserFromSession(){

        editor.clear();
        editor.commit();

    }

    public void createRememberMeSession(String phoneNumber,String password){

        editor.putBoolean( IS_REMEMBERME,true );

        editor.putString( KEY_SESSIONPHONENUMBER,phoneNumber );
        editor.putString( KEY_SESSIONPASSWORD,password );

        editor.commit();

    }

    public HashMap<String, String> getRememberMeDetailsFromSession(){

        HashMap<String,String> userData = new HashMap<>();

        userData.put( KEY_SESSIONPHONENUMBER, usersSession.getString( KEY_SESSIONPHONENUMBER, null ) );
        userData.put( KEY_SESSIONPASSWORD, usersSession.getString( KEY_SESSIONPASSWORD, null ) );

        return userData;

    }

    public boolean checkRememberMe(){

        if (usersSession.getBoolean( IS_REMEMBERME,false ))
        {
            return true;
        }
        else {
            return false;
        }

    }


}
