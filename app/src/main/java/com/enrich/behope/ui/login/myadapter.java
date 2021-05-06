//package com.enrich.behope.ui.login;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.enrich.behope.R;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.donorviewholder> {
//
//    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
//        super( options );
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull donorviewholder holder, int position, @NonNull model model) {
//        holder.name.setText( model.getdName() );
//        holder.bloodgroup.setText( model.getdBloodGroup() );
//        holder.gender.setText( model.getdGender() );
//        holder.phone.setText( model.getdPhone() );
//        holder.donatelastdate.setText( model.getdBDLastDate() );
//
//
//    }
//
//    @NonNull
//    @Override
//    public donorviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.donordisplayview,parent,false );
//        return new donorviewholder(view);
//    }
//
//    class donorviewholder extends RecyclerView.ViewHolder{
//
//        TextView name,bloodgroup,gender,phone,donatelastdate;
//
//        public donorviewholder(@NonNull View itemView) {
//            super( itemView );
//            name=itemView.findViewById( R.id.txtDname );
//            bloodgroup=itemView.findViewById( R.id.txtDBDGroop );
//            gender=itemView.findViewById( R.id.txtDGender );
//            phone=itemView.findViewById( R.id.txtDphone );
//            donatelastdate=itemView.findViewById( R.id.txtDLstDt );
//        }
//    }
//
//}
