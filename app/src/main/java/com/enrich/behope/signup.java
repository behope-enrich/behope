package com.enrich.behope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

                Intent intent = new Intent(getApplicationContext(),VerifyPhoneNo.class);
                intent.putExtra( "phoneno",phoneno );
                startActivity( intent );
                finish();



                //UserHelperClass helperClass = new UserHelperClass(name,email,phoneno,password);
                //reference.child(phoneno).setValue(helperClass);
//
//                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//
//                startActivity( intent );

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