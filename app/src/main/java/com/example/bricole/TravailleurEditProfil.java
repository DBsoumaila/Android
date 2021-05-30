package com.example.bricole;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bricole.trFragments.FragmentAdditionalInfos;
import com.example.bricole.trFragments.FragmentPersonalInfos;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TravailleurEditProfil extends AppCompatActivity {

    //Part containing the 2 fragments
    private ViewPager trViewPager;
    private PagerAdapter trPagerAdapter;

    public static ImageView profil_pic, cin_pic, ce_pic;
    public static TextView name_text;

    public static TextView profession_text;

    //Used while choosing an image from gallery
    static final int PICK_IMAGE = 1;
    Uri imageUri;
    String activeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travailleur_edit_profil);

        //Initialisation
        profil_pic = findViewById(R.id.travailleur_edit_pic);
        name_text = findViewById(R.id.travailleur_ed_name);

        profession_text = findViewById(R.id.travailleur_ed_profession);

        cin_pic = findViewById(R.id.travailleur_edit_cin);
        ce_pic = findViewById(R.id.travailleur_edit_ce);

        //Update profil picture
        profil_pic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                activeImage = "profil_pic";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });

        //Update CIN
        cin_pic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                activeImage = "cin_pic";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select CIN"), PICK_IMAGE);

            }
        });

        //Update CIN
        ce_pic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                activeImage = "ce_pic";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select XXXX"), PICK_IMAGE);

            }
        });

        //Getting data from database
        byte[] profil = TravailleurLogin.currentWorker.getProfil_pic();
        String name = TravailleurLogin.currentWorker.getFullName();

       // String profession = TravailleurLogin.currentWorker.getProffesion();
        String profession = "";

        byte[] cin = TravailleurLogin.currentWorker.getCin();
        byte[] ce = TravailleurLogin.currentWorker.getCe();

        //Insert Data from DB to the view
        profil_pic.setImageBitmap(byteArrayToBitmap(profil));
        name_text.setText(name);
        profession_text.setText(profession);
        cin_pic.setImageBitmap(byteArrayToBitmap(cin));
        ce_pic.setImageBitmap(byteArrayToBitmap(ce));
        
        //List of both fragments personal infos and additional ones
        List<Fragment> list = new ArrayList<>();
        list.add(new FragmentPersonalInfos());
        list.add(new FragmentAdditionalInfos());

        //Adapt the current fragment to the view
        trViewPager = findViewById(R.id.tr_view_pager);


        trPagerAdapter = new TravailleurSlidePagerAdapter(getSupportFragmentManager(), list);

        trViewPager.setAdapter(trPagerAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try {

                if(activeImage.equals("profil_pic")){

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    profil_pic.setImageBitmap(bitmap);

                }
                if(activeImage.equals("cin_pic")){

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    cin_pic.setImageBitmap(bitmap);

                }
                if(activeImage.equals("ce_pic")){

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    ce_pic.setImageBitmap(bitmap);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap byteArrayToBitmap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return  bitmap;
    }

}
