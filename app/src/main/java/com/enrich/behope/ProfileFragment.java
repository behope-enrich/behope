package com.enrich.behope;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.enrich.behope.ui.login.DonorHelperClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView txtBloodGroup,txtBDT,txtAge,txtGender,txtLastDonateDate,txtSelectedDate,txtView;

    EditText edtxtAge,edttxtUName,edtxtUPphoneno,edtxtUEmail;

    Button btnBecomeDonor,btnSubmitBecomeDonorDetail,logout,btnUpdate;

    ScrollView scrollView;

    Boolean donor=false;

    String Gender,SelectedBloodGroup,GenderRequire;

    DatePicker picker;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    private Spinner spinner;
    private static final String[] paths = {"Male", "Female"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View v =  inflater.inflate( R.layout.fragment_profile, container, false );

        btnBecomeDonor = v.findViewById( R.id.btnBecomeDonor );
        txtBloodGroup = v.findViewById( R.id.txtBloodGroup );
        txtBDT = v.findViewById( R.id.txtBDT );
        scrollView = v.findViewById( R.id.scrollView );
        txtSelectedDate = (TextView)v.findViewById(R.id.txtSelectedDate);
        picker= (DatePicker)v.findViewById(R.id.datePicker);
        txtAge=v.findViewById( R.id.txtAge );
        txtGender=v.findViewById( R.id.txtGender );
        txtLastDonateDate=v.findViewById( R.id.txtLastDonateDate );
        edtxtAge=v.findViewById( R.id.edtxtAge );
        edttxtUName=v.findViewById( R.id.edttxtUName );
        edtxtUPphoneno=v.findViewById( R.id.edtxtUPphoneno );
        edtxtUEmail=v.findViewById( R.id.edtxtUEmail );
        btnSubmitBecomeDonorDetail=v.findViewById( R.id.btnSubmitBecomeDonorDetail );
        logout = v.findViewById( R.id.logout );
        btnUpdate = v.findViewById( R.id.btnUpdate );

        ///Blood Group Dorpdown

        SessionManager sessionManager = new SessionManager( v.getContext(),SessionManager.SESSION_USERSESSION );
        HashMap<String,String> userDetails = sessionManager.getUserDetailFromSession();

        String name = userDetails.get( SessionManager.KEY_USERNAME );
        String password = userDetails.get( SessionManager.KEY_PASSWORD );
        String phoneno = userDetails.get( SessionManager.KEY_PHONENUMBER );
        String email = userDetails.get( SessionManager.KEY_EMAIL );

        String donate = userDetails.get( SessionManager.KEY_DONATE );
        String blood_group = userDetails.get( SessionManager.KEY_BLOOD_GROUP );
        String age = userDetails.get( SessionManager.KEY_AGE );
        String gender = userDetails.get( SessionManager.KEY_GENDER );
        String last_donated_date = userDetails.get( SessionManager.KEY_LAST_DONATED_DATE );


        // Spinner element
        Spinner bloodGroupDropDown = (Spinner) v.findViewById(R.id.bloodGroupDropDown);

        // Spinner click listener
        bloodGroupDropDown.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> bloodGroup = new ArrayList<String>();
        bloodGroup.add("Not Sure");
        bloodGroup.add("A+");
        bloodGroup.add("A-");
        bloodGroup.add("B+");
        bloodGroup.add("B-");
        bloodGroup.add("O+");
        bloodGroup.add("O-");
        bloodGroup.add("AB+");
        bloodGroup.add("AB-");

        // Creating adapter for spinner
        ArrayAdapter<String> bloodGroupdataAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, bloodGroup);

        // Drop down layout style - list view with radio button
        bloodGroupdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         // attaching data adapter to spinner
        bloodGroupDropDown.setAdapter(bloodGroupdataAdapter);


        String selectbloodGroupVariable;

        switch(blood_group){
            case "Not Sure":
                selectbloodGroupVariable = "Not Sure";
                break;

            case "A+":
                selectbloodGroupVariable = "A+";
                break;

            case "A-":
                selectbloodGroupVariable = "A-";
                break;

            case "B+":
                selectbloodGroupVariable = "B+";
                break;

            case "B-":
                selectbloodGroupVariable = "B-";
                break;

            case "O+":
                selectbloodGroupVariable = "O+";
                break;

            case "O-":
                selectbloodGroupVariable = "O-";
                break;

            case "AB+":
                selectbloodGroupVariable = "AB+";
                break;

            case "AB-":
                selectbloodGroupVariable = "AB-";
                break;

            default:
                selectbloodGroupVariable = "Not Sure";
                break;
        }

        int bloodGroupSpinnerPosition = bloodGroupdataAdapter.getPosition(selectbloodGroupVariable);

        bloodGroupDropDown.setSelection(bloodGroupSpinnerPosition);

        //////




        ///Gender Dorpdown

        // Spinner element
        Spinner genderDropDown = (Spinner) v.findViewById(R.id.genderDropDown);

        // Spinner click listener
        genderDropDown.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> genderList = new ArrayList<String>();
        genderList.add( "--Select Gender--" );
        genderList.add("Male");
        genderList.add("Female");

        // Creating adapter for spinner
        ArrayAdapter<String> genderDataAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, genderList);

        // Drop down layout style - list view with radio button
        genderDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        genderDropDown.setAdapter(genderDataAdapter);


        //genderDropDown.setSelected( true );

        String genderSelecteVariable;

        switch (gender){
            case "Male":
                genderSelecteVariable = "Male";
                break;

            case "Female":
                genderSelecteVariable = "Female";
                break;

            default:
                genderSelecteVariable = "--Select Gender--";
                break;
        }

        int genderSpinnerPosition = genderDataAdapter.getPosition(genderSelecteVariable);

        genderDropDown.setSelection(genderSpinnerPosition);


        if(donate.equals( "false" )) {
            txtBDT.setVisibility( View.GONE );
            txtBloodGroup.setVisibility( View.GONE );
            bloodGroupDropDown.setVisibility( View.GONE );
            txtAge.setVisibility( View.GONE );
            edtxtAge.setVisibility( View.GONE );
            txtGender.setVisibility( View.GONE );
            genderDropDown.setVisibility( View.GONE );
            txtLastDonateDate.setVisibility( View.GONE );
            txtSelectedDate.setVisibility( View.GONE );
            btnSubmitBecomeDonorDetail.setVisibility( View.GONE );
        }else{
            btnBecomeDonor.setVisibility( View.GONE );
            txtBDT.setVisibility( View.VISIBLE );
            txtBloodGroup.setVisibility( View.VISIBLE );
            bloodGroupDropDown.setVisibility( View.VISIBLE );
            txtAge.setVisibility( View.VISIBLE );
            edtxtAge.setVisibility( View.VISIBLE );
            txtGender.setVisibility( View.VISIBLE );
            genderDropDown.setVisibility( View.VISIBLE );
            txtLastDonateDate.setVisibility( View.VISIBLE );
            txtSelectedDate.setVisibility( View.VISIBLE );
            btnSubmitBecomeDonorDetail.setVisibility( View.VISIBLE );
        }

        btnBecomeDonor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btnBecomeDonor.setVisibility( View.GONE );
                    txtBDT.setVisibility( View.VISIBLE );
                    txtBloodGroup.setVisibility( View.VISIBLE );
                    bloodGroupDropDown.setVisibility( View.VISIBLE );
                    txtAge.setVisibility( View.VISIBLE );
                    edtxtAge.setVisibility( View.VISIBLE );
                    txtGender.setVisibility( View.VISIBLE );
                    genderDropDown.setVisibility( View.VISIBLE );
                    txtLastDonateDate.setVisibility( View.VISIBLE );
                    txtSelectedDate.setVisibility( View.VISIBLE );
                    btnSubmitBecomeDonorDetail.setVisibility( View.VISIBLE );
            }
        } );

        ////Date Picker

        picker.setVisibility( View.GONE );

        txtSelectedDate.setText(last_donated_date);

        txtSelectedDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker.setVisibility( View.VISIBLE );
                txtSelectedDate.setText("Selected Date: "+getCurrentDate());
             }
        } );

        ///Set Edit Text Value

        edttxtUName.setText( name );
        edtxtUPphoneno.setText( phoneno );
        edtxtUEmail.setText( email );
        edtxtAge.setText( age );
        txtSelectedDate.setText( last_donated_date );

        if(age.equals( "undefined" )){
            edtxtAge.setText( "" );
        }

//        edtxtAge.setText( age );
//        txtSelectedDate.setText( last_donated_date );

        picker.setOnDateChangedListener( new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtSelectedDate.setText(getCurrentDate());
                picker.setVisibility( View.GONE );

            }
        } );


        //Update Details

        rootNode = FirebaseDatabase.getInstance();

        reference = rootNode.getReference( "users" ).child( phoneno );


        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uEmail = edtxtUEmail.getText().toString();
                String uUname = edttxtUName.getText().toString();
                String uPhone = edtxtUPphoneno.getText().toString();


                rootNode.getReference( "users" ).child( phoneno ).child("name").setValue( uUname );
                rootNode.getReference( "users" ).child( phoneno ).child("email").setValue( uEmail );
                rootNode.getReference( "users" ).child( phoneno ).child("phoneno").setValue( uPhone );


                rootNode.getReference( "blood_donors" ).child( phoneno ).child("donor_name").setValue( uUname );
                rootNode.getReference( "blood_donors" ).child( phoneno ).child("donor_phone").setValue( uPhone );



                SessionManager sessionManager = new SessionManager( v.getContext(),SessionManager.SESSION_USERSESSION );
                sessionManager.updateSharePre( uUname,uEmail,uPhone );

                Toast.makeText( v.getContext(),"Data Updated",Toast.LENGTH_SHORT ).show();
            }
        } );


        btnSubmitBecomeDonorDetail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int bg = bloodGroupdataAdapter.getPosition(selectbloodGroupVariable);

                String uAge = edtxtAge.getText().toString();
                String uLastDdate = txtSelectedDate.getText().toString();
                String uDonate = "true";

                if(!uAge.equals( "" )){

                rootNode.getReference( "users" ).child( phoneno ).child("gender").setValue( Gender );
                rootNode.getReference( "users" ).child( phoneno ).child("age").setValue( uAge );
                rootNode.getReference( "users" ).child( phoneno ).child("blood_group").setValue( SelectedBloodGroup );
                rootNode.getReference( "users" ).child( phoneno ).child("last_donated_date").setValue( uLastDdate );
                rootNode.getReference( "users" ).child( phoneno ).child("donate").setValue( uDonate );

                SessionManager sessionManager = new SessionManager( v.getContext(),SessionManager.SESSION_USERSESSION );
                sessionManager.donorData( Gender,SelectedBloodGroup,uAge,uLastDdate,uDonate );


                rootNode.getReference( "blood_donors" ).child( phoneno ).child("donor_blood_group").setValue( SelectedBloodGroup );
                rootNode.getReference( "blood_donors" ).child( phoneno ).child("donor_gender").setValue( Gender );
                rootNode.getReference( "blood_donors" ).child( phoneno ).child("donor_lst_dnt_dt").setValue( uLastDdate );

                String uEmail = edtxtUEmail.getText().toString();
                String uUname = edttxtUName.getText().toString();
                String uPhone = edtxtUPphoneno.getText().toString();

                rootNode.getReference( "blood_donors" ).child( phoneno ).child("donor_name").setValue( uUname );
                rootNode.getReference( "blood_donors" ).child( phoneno ).child("donor_phone").setValue( uPhone );


                sessionManager.updateSharePre( uUname,uEmail,uPhone );

                Toast.makeText( v.getContext(),"Data Updated",Toast.LENGTH_SHORT ).show();

                    //reference.child(phoneno).setValue(donorClass);



                }

                else{
                    Toast.makeText(v.getContext(), "Age Required", Toast.LENGTH_LONG).show();
                }

            }
        } );



        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager( v.getContext(),SessionManager.SESSION_USERSESSION );
                sessionManager.logoutUserFromSession();
                Intent intent = new Intent( v.getContext(), login.class );
                startActivity( intent );
                //finish();
            }
        } );


        return v;

    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append(picker.getDayOfMonth()+"/");
        builder.append((picker.getMonth() + 1)+"/");//month is 0 based
        builder.append(picker.getYear());
        return builder.toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        if(item.equals( "Male" ) || item.equals( "Female" )){
            Gender = item;
        }


        if(item.equals( "A+" ) || item.equals( "A-" ) || item.equals( "B+" ) || item.equals( "B-" ) || item.equals( "O+" ) || item.equals( "O-" ) || item.equals( "AB+" ) || item.equals( "AB-" )){

            SelectedBloodGroup = item;
        }


        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}