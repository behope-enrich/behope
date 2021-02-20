package com.enrich.behope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

//    TextView uname,session;
//    Button logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

//        First Login
//
//        uname = findViewById( R.id.uname );
//        session = findViewById(R.id.txtsessiondata  );
//        logout = findViewById( R.id.btnlogout );
//
//        String name = getIntent().getStringExtra( "name" );
//        String pn = getIntent().getStringExtra( "pn" );
//        String pw = getIntent().getStringExtra( "pw" );
//
//
//        //uname.setText( "Hey \n"+ pn +" \n"+pw );
//
//        SessionManager sessionManager = new SessionManager( this,SessionManager.SESSION_USERSESSION );
//        HashMap<String,String> userDetails = sessionManager.getUserDetailFromSession();
//
//        String uname = userDetails.get( SessionManager.KEY_USERNAME );
//        String upassword = userDetails.get( SessionManager.KEY_PASSWORD );
//        String uphoneno = userDetails.get( SessionManager.KEY_PHONENUMBER );
//        String uemail = userDetails.get( SessionManager.KEY_EMAIL );
//
//        session.setText( uname+ "\n"+upassword+"\n"+uphoneno+"\n"+uemail );
//
//        logout.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SessionManager sessionManager = new SessionManager( HomeActivity.this,SessionManager.SESSION_USERSESSION );
//                sessionManager.logoutUserFromSession();
//                Intent intent = new Intent( getApplicationContext(), login.class );
//                startActivity( intent );
//                finish();
//            }
//        } );
//




    }

}