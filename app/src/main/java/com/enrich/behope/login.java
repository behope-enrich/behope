package com.enrich.behope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    TextView txtsignup;
    Button btnlogin;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        txtsignup = findViewById( R.id.txtsignup );
        btnlogin = findViewById( R.id.btnlogin);
        email = findViewById( R.id.edtxtemail );
        password = findViewById( R.id.edtxtpassword );

        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        } );



        txtsignup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity( intent );
            }
        } );

    }

    private void signIn() {

        String userEnterEmail = email.getText().toString().trim();
        String userEnterPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUSer = reference.orderByChild( "name" ).equalTo( userEnterEmail );

        checkUSer.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if(snapshot.exists()){

                    //email.setErrorEnabled(false);
                    email.setError( null );


                    String passwordFromDB = snapshot.child( userEnterEmail).child( "password" ).getValue(String.class);

                    if(passwordFromDB.equals( userEnterPassword )){

                        email.setError( null );
                        //email.setErrorEnabled(false);

                        String nameFromDB = snapshot.child( userEnterEmail).child( "name" ).getValue(String.class);
                        String emailFromDB = snapshot.child( userEnterEmail).child( "email" ).getValue(String.class);
                        String phonenoFromDB = snapshot.child( userEnterEmail).child( "phoneno" ).getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);

                    intent.putExtra( "name",nameFromDB );
                    intent.putExtra( "email",emailFromDB );
                    intent.putExtra( "phoneno",phonenoFromDB );

                    startActivity( intent );


                    }

                    else{
                        password.setError( "Wrong Password" );
                        password.requestFocus();
                    }


                }
                else{
                    email.setError( "No Such Email Exist" );
                    email.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

    }
}