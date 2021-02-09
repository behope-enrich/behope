package com.enrich.behope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    TextView txtsignup;
    Button btnlogin;
    EditText phone,password;

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


        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //signIn();

                rootNode = FirebaseDatabase.getInstance();

                    reference = rootNode.getReference( "users" );//.child( pn )

                    reference.addValueEventListener( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for(DataSnapshot d: snapshot.getChildren()){

                                if (d.child("phoneno").getValue().toString().equals( phone.getText().toString() ) && d.child("password").getValue().toString().equals( password.getText().toString())){
                                    String pn = d.child("phoneno").getValue().toString();
                                    String pw = d.child( "password" ).getValue().toString();

                                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                    intent.putExtra( "pn",pn );
                                    intent.putExtra( "pw",pw );
                                    startActivity( intent );
                                    finish();

                                    break;
                                }
                                else{

                                    Toast.makeText( login.this,"Phone No And Password Is Incorrect",Toast.LENGTH_LONG ).show();

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    } );

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

}