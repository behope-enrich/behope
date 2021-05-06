package com.enrich.behope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {

    TextView txtloginbtn;
    EditText rg_email,rg_name,rg_phoneno,rg_password;
    Button rg_signup;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        txtloginbtn = findViewById( R.id.txtloginbtn );
        rg_name = findViewById( R.id.edtxtname );
        rg_email = findViewById( R.id.edtxtemail );
        rg_phoneno = findViewById( R.id.edtphoneno );
        rg_password = findViewById( R.id.edtxtpassword );
        rg_signup =findViewById( R.id.btnsignup );

        rg_signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = rg_name.getText().toString();
                String email = rg_email.getText().toString();
                String phoneno = rg_phoneno.getText().toString();
                String password = rg_password.getText().toString();
                String donate = "false";
                String blood_group = "Not Sure";
                String age = "undefined";
                String gender = "undefined";
                String last_donated_date = "Clicke here to Select Date";

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (TextUtils.isEmpty( name )){
                    rg_name.setError( "Fill Your Name" );
                    rg_name.requestFocus();
                }

                else if(!name.matches("[a-zA-Z ]+")){
                    rg_name.setError( "Enter Only Alphabetical Character" );
                    rg_name.requestFocus();
                }

                else if (TextUtils.isEmpty( email )){
                    rg_email.setError( "Fill Your E-mail" );
                    rg_email.requestFocus();
                }

                else if(!email.matches(emailPattern)){
                    rg_email.setError( "Enter Correct Your E-mail" );
                    rg_email.requestFocus();
                }

                else if (TextUtils.isEmpty( phoneno )){
                    rg_phoneno.setError( "Fill Your Phone Number" );
                    rg_phoneno.requestFocus();

                }
                else if(phoneno.length()<10){
                        rg_phoneno.setError( "Your Phone Number Is Incorrect" );
                        rg_phoneno.requestFocus();

//                    reference.addValueEventListener( new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            for (DataSnapshot d : snapshot.getChildren()) {
//
//                                if (d.child( "phoneno" ).getValue().toString().equals( rg_phoneno.getText().toString() )) {
//
//
//                                    rg_phoneno.setError( "This phone number is already in use with another account" );
//                                    rg_phoneno.requestFocus();
//
//                                    break;
//
//                                    }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    } );


                }

//                else if(!phoneno.isEmpty()){
//
//
//                    reference.addValueEventListener( new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                            for (DataSnapshot d : snapshot.getChildren()) {
//
//                                if (d.child( "phoneno" ).getValue().toString().equals( rg_phoneno.getText().toString() )) {
//
//
//                                    rg_phoneno.setError( "This phone number is already in use with another account" );
//                                    rg_phoneno.requestFocus();
//
//                                    break;
//
//                                    }
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    } );
//
//                }


                
                else if(TextUtils.isEmpty( password )){
                    rg_password.setError( "Fill Your Password" );
                    rg_password.requestFocus();
                }

                else{
                    Intent intent = new Intent(getApplicationContext(),VerifyPhoneNo.class);
                    intent.putExtra( "phoneno",phoneno );
                    intent.putExtra( "name",name );
                    intent.putExtra( "email",email );
                    intent.putExtra( "password",password );
                    intent.putExtra( "donate",donate );
                    intent.putExtra( "blood_group",blood_group );
                    intent.putExtra( "age",age );
                    intent.putExtra( "gender",gender );
                    intent.putExtra( "last_donated_date",last_donated_date );
                    startActivity( intent );
                    finish();
                }
            }
        } );


        txtloginbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,login.class);
                startActivity( intent );
            }
        } );

    }
}