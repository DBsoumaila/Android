package com.example.bricole;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class ClientListAdapter extends ArrayAdapter<ClientViewTrInfos> {

    private Context context;
    private int resource;


    public ClientListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ClientViewTrInfos> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(resource, parent, false);

        ImageView imagePic = convertView.findViewById(R.id.clt_tr_pic);
        TextView txtName = convertView.findViewById(R.id.clt_tr_name);
        TextView txtProfession = convertView.findViewById(R.id.clt_tr_profession);

        Button btnTrProfile = convertView.findViewById(R.id.voir_profil_tr);
        Button btnCallTr = convertView.findViewById(R.id.appeler_tr);

        //Show Worker Profile
        btnTrProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getContext(),
                        "View Worker Profile", Toast.LENGTH_SHORT).show();*/

                ClientTrProfileView.trId = getItem(position).getId();
                context.startActivity(new Intent(getContext(), ClientTrProfileView.class));

            }
        });


        // Call Worker
        btnCallTr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        "Call Worker", Toast.LENGTH_SHORT).show();

                String phoneNum = getItem(position).getNum();

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ phoneNum));
                context.startActivity(callIntent);

                Log.i("NUM", phoneNum);
            }
        });

        imagePic.setImageBitmap(byteArrayToBitmap(getItem(position).getImage()));
        txtName.setText(getItem(position).getName());
        txtProfession.setText(getItem(position).getProfession());

        return convertView;
    }

    private Bitmap byteArrayToBitmap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return  bitmap;
    }
}
