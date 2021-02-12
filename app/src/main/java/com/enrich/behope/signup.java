package com.enrich.behope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                if (TextUtils.isEmpty( name )){
                    rg_name.setError( "Fill Your Name" );
                }

                if (TextUtils.isEmpty( email )){
                    rg_email.setError( "Fill Your E-mail" );
                }

                if (TextUtils.isEmpty( phoneno )){
                    rg_phoneno.setError( "Fill Your Phone Number" );
                }

                if(TextUtils.isEmpty( password )){
                    rg_password.setError( "Fill Your Password" );
                }

                if (rg_name != null && rg_email != null && rg_phoneno != null && rg_password != null){

                    Intent intent = new Intent(getApplicationContext(),VerifyPhoneNo.class);
                    intent.putExtra( "phoneno",phoneno );
                    intent.putExtra( "name",name );
                    intent.putExtra( "email",email );
                    intent.putExtra( "password",password );

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