package com.example.bricole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ClientTrProfileView extends AppCompatActivity {

     TextView workerName, workerProfession, workerNum, workerAbout;

     ImageView workerPic, work1, work2, work3, work4, workerCin, workerCe;

    Bitmap addImgIcon;

    static Integer trId;
    static List<TravailleurModel> workers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_tr_profile_view);

        addImgIcon = BitmapFactory.decodeResource(getResources(), R.drawable.add_img_icon);

        workerName = findViewById(R.id.worker_name);
        workerProfession = findViewById(R.id.worker_profession);
        workerNum = findViewById(R.id.worker_phone);
        workerAbout = findViewById(R.id.worker_about);

        workerPic = findViewById(R.id.worker_image);
        work1 = findViewById(R.id.work1);
        work2 = findViewById(R.id.work2);
        work3 = findViewById(R.id.work3);
        work4 = findViewById(R.id.work4);
        workerCin = findViewById(R.id.worker_cin);
        workerCe = findViewById(R.id.worker_ce);

        for(int i = 0; i<workers.size(); i++){

            if(trId == workers.get(i).getId()){

                workerName.setText(workers.get(i).getFullName());
                workerProfession.setText(workers.get(i).getProffesion());
                workerNum .setText(workers.get(i).getNum());
                workerAbout.setText(workers.get(i).getDescription());

                workerPic.setImageBitmap(byteArrayToBitmap(workers.get(i).getProfil_pic()));
                workerCin.setImageBitmap(byteArrayToBitmap(workers.get(i).getCin()));
                workerCe.setImageBitmap(byteArrayToBitmap(workers.get(i).getCe()));

                if(!(addImgIcon.sameAs(byteArrayToBitmap(workers.get(i).getWorkImg1())))){
                    work1.setImageBitmap(byteArrayToBitmap(workers.get(i).getWorkImg1()));
                }

                if(!(addImgIcon.sameAs(byteArrayToBitmap(workers.get(i).getWorkImg2())))){
                    work2.setImageBitmap(byteArrayToBitmap(workers.get(i).getWorkImg2()));
                }

                if(!(addImgIcon.sameAs(byteArrayToBitmap(workers.get(i).getWorkImg3())))){
                    work3.setImageBitmap(byteArrayToBitmap(workers.get(i).getWorkImg3()));
                }

                if(!(addImgIcon.sameAs(byteArrayToBitmap(workers.get(i).getWorkImg4())))){
                    work4.setImageBitmap(byteArrayToBitmap(workers.get(i).getWorkImg4()));
                }
            }
        }
    }
    private Bitmap byteArrayToBitmap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return  bitmap;
    }
}