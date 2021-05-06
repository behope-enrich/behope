package com.enrich.behope.ui.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.enrich.behope.HomeActivity;
import com.enrich.behope.HomeFragment;
import com.enrich.behope.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class dmyadapter extends FirebaseRecyclerAdapter<dmodel, dmyadapter.dviewholder> {


    public dmyadapter(@NonNull FirebaseRecyclerOptions<dmodel> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder(@NonNull dviewholder holder, int position, @NonNull dmodel model) {

        holder.name.setText( model.getDonor_name() );
        holder.bloodgroup.setText( model.getDonor_blood_group() );
        holder.donatelastdate.setText( model.getDonor_lst_dnt_dt() );
        holder.gender.setText( model.getDonor_gender() );
        //holder.phone.setText( model.getDonor_phone() );
        //String callPhone = model.getDonor_phone() ;



        holder.btncall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent( Intent.ACTION_CALL );
                String hyyy = "tel:+91"+model.getDonor_phone();
                callIntent.setData( Uri.parse( hyyy ) );
                if (ActivityCompat.checkSelfPermission( v.getContext(), Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                v.getContext().startActivity( callIntent );
            }
        } );



    }

    @NonNull
    @Override
    public dviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.donordatadisplay ,parent,false);
        return new dviewholder( view );
    }

    class dviewholder extends RecyclerView.ViewHolder{

        TextView name,bloodgroup,gender,phone,donatelastdate;
        Button btncall;

        public dviewholder(@NonNull View itemView) {
            super( itemView );
            name = itemView.findViewById( R.id.txtDname );
            bloodgroup = itemView.findViewById( R.id.txtDBDGroop );
            gender = itemView.findViewById( R.id.txtDGender );
            //phone=itemView.findViewById( R.id.txtDphone );
            btncall = itemView.findViewById( R.id.btncall);
            donatelastdate = itemView.findViewById( R.id.txtDLstDt );
        }
    }


}
