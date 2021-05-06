package com.enrich.behope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class login extends AppCompatActivity {

    TextView txtsignup,txtremoverqklogin;
    Button btnlogin;
    EditText phone,password;
    CheckBox chkrememberme;

    ProgressBar progressLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        txtsignup = findViewById( R.id.txtsignup );
        btnlogin = findViewById( R.id.btnlogin);
        phone = findViewById( R.id.edtxtphoneno );
        password = findViewById( R.id.edtxtpassword );
        chkrememberme = findViewById( R.id.chkrember );
        txtremoverqklogin = findViewById( R.id.txtremoverqklogin );
        progressLogin = findViewById( R.id.progressLogin );

        SessionManager rmsessionManager = new SessionManager( login.this,SessionManager.SESSION_REMEMBERME );
        if (rmsessionManager.checkRememberMe()){
            HashMap<String,String> rememberMeDetails = rmsessionManager.getRememberMeDetailsFromSession();
            phone.setText( rememberMeDetails.get( SessionManager.KEY_SESSIONPHONENUMBER ) );
            password.setText( rememberMeDetails.get( SessionManager.KEY_SESSIONPASSWORD ) );
        }

        SessionManager sessionManager = new SessionManager( login.this,SessionManager.SESSION_USERSESSION );
        if(sessionManager.checkLogin()){

            Intent intent = new Intent(login.this,HomeActivity.class);
            startActivity( intent );

        }



        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressLogin.setVisibility( View.VISIBLE );

                String chkphoneno = phone.getText().toString();
                String chkpassword = password.getText().toString();

                if (TextUtils.isEmpty( chkphoneno )){
                    phone.setError( "Fill Your Phone Number" );
                    phone.requestFocus();

                }
                else if(phone.length()<10){
                    phone.setError( "Your Phone Number Is Incorrect" );
                    phone.requestFocus();
                }


                else if(TextUtils.isEmpty( chkpassword )){
                    password.setError( "Fill Your Password" );
                    password.requestFocus();
                }

                else {


                    rootNode = FirebaseDatabase.getInstance();

                    reference = rootNode.getReference( "users" );//.child( pn )

                    reference.addValueEventListener( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot d : snapshot.getChildren()) {

                                if (d.child( "phoneno" ).getValue().toString().equals( phone.getText().toString() )) {


                                    if (d.child( "password" ).getValue().toString().equals( password.getText().toString() )) {

                                        String pn = d.child( "phoneno" ).getValue().toString();
                                        String pw = d.child( "password" ).getValue().toString();
                                        String un = d.child( "name" ).getValue().toString();
                                        String em = d.child( "email" ).getValue().toString();
                                        String donate = d.child( "donate" ).getValue().toString();
                                        String blood_group = d.child( "blood_group" ).getValue().toString();
                                        String age = d.child( "age" ).getValue().toString();
                                        String gender = d.child( "gender" ).getValue().toString();
                                        String last_donated_date = d.child( "last_donated_date" ).getValue().toString();

                                        SessionManager sessionManager = new SessionManager( login.this,SessionManager.SESSION_USERSESSION );
                                        sessionManager.createLoginSession( un,em,pn,pw,donate,blood_group,age,gender,last_donated_date );


                                        if (chkrememberme.isChecked()){
                                            SessionManager rmsessionManager = new SessionManager( login.this,SessionManager.SESSION_REMEMBERME );
                                            rmsessionManager.createRememberMeSession( pn,pw );
                                        }

                                        Intent intent = new Intent( getApplicationContext(), HomeActivity.class );
                                        intent.putExtra( "pn", pn );
                                        intent.putExtra( "pw", pw );
                                        startActivity( intent );
                                        finish();

                                        progressLogin.setVisibility( View.GONE );

                                        break;

                                    } else {

                                        //Toast.makeText( login.this, "Phone No And Password Is Incorrect", Toast.LENGTH_SHORT ).show();
                                        //phone.setError( "Fill Your Phone Number" );
                                        phone.requestFocus();
                                        password.requestFocus();
                                        password.setError( "Your Phone Number And Password are Incorrect" );

                                    }

                                }


                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    } );
                }
            }
        } );

        txtsignup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity( intent );
            }
        } );

        txtremoverqklogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager rmsessionManager = new SessionManager( login.this,SessionManager.SESSION_REMEMBERME );
                rmsessionManager.logoutUserFromSession();
                phone.setText( null );
                password.setText( null );
            }
        } );

    }

}