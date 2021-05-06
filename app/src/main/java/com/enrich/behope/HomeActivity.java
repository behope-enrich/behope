package com.enrich.behope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.enrich.behope.ui.login.LoginFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.HashMap;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeActivity extends AppCompatActivity {

//    TextView uname,session;
//    Button logout;
    TextView HomeUserName;

    MeowBottomNavigation bottomNavigation;

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
        SessionManager sessionManager = new SessionManager( this,SessionManager.SESSION_USERSESSION );
        HashMap<String,String> userDetails = sessionManager.getUserDetailFromSession();
//
        String uname = userDetails.get( SessionManager.KEY_USERNAME );
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

        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.frame_layout, new HomeFragment() )
                .commit();

        HomeUserName = findViewById( R.id.txtHomeUserName );
        String name = getIntent().getStringExtra( "name" );

        HomeUserName.setText( uname.toUpperCase() );

        bottomNavigation = findViewById( R.id.bottom_navigation );

        bottomNavigation.add( new MeowBottomNavigation.Model( 1,R.drawable.ic_baseline_home ) );
        bottomNavigation.add( new MeowBottomNavigation.Model( 2,R.drawable.ic_baseline_person ) );

        bottomNavigation.setOnShowListener( new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {



            }
        } );



        bottomNavigation.show( 1,true );

        bottomNavigation.setOnClickMenuListener( new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

                Fragment fragment=null;

                switch (item.getId()){

                    case 1:
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        //fragment = new ProfileFragment();
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);

            }
        } );



        bottomNavigation.setOnReselectListener( new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
//                Toast.makeText( getApplicationContext()
//                ,"You Reslected" + item.getId()
//                ,Toast.LENGTH_SHORT).show();

            }
        } );


    }



    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.frame_layout,fragment )
                .commit();
    }






}