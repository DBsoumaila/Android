package com.example.bricole;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminAdapter  extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    // Declarations
    Context context;
    List<AdminModel> maliste;

    public AdminAdapter(Context context, List<AdminModel> maliste) {
        this.context = context;
        this.maliste = maliste;
    }

    @NonNull
    @Override
    public AdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.ViewHolder holder, int position) {
        if(maliste !=null && maliste.size()>0)
        {
            AdminModel admin= maliste.get(position);


            holder.idView.setText(String.valueOf(admin.getId()));
            holder.nomView.setText(admin.getNom());
            holder.prenomView.setText(admin.getPrenom());
            holder.passwordView.setText(admin.getPassword());
            holder.emailVew.setText(admin.getEmail());

        }else
        {

        }

    }

    @Override
    public int getItemCount() {
        return maliste.size();
    }
    public class  ViewHolder extends  RecyclerView.ViewHolder{
        TextView idView, nomView, prenomView, passwordView, emailVew;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idView= itemView.findViewById(R.id.admin_id);
            nomView= itemView.findViewById(R.id.admin_nom);
            prenomView= itemView.findViewById(R.id.admin_prenom);
            passwordView= itemView.findViewById(R.id.admin_password);
            emailVew= itemView.findViewById(R.id.admin_email);
        }
    }
}
