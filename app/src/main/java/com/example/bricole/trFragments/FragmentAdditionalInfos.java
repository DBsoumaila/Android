package com.example.bricole.trFragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bricole.R;
import com.example.bricole.TravailleurDatabase;
import com.example.bricole.TravailleurEditProfil;
import com.example.bricole.TravailleurLogin;
import com.example.bricole.TravailleurProfil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.view.View.*;

public class FragmentAdditionalInfos extends Fragment {

    public static EditText description;
    public static ImageView workImg1, workImg2, workImg3, workImg4;

    Button aUpdate, aToHome;

    //TravailleurDB travailleurDB;
    TravailleurDatabase travailleurDB;
    Integer id;

    //Used while choosing an image from gallery
    static final int PICK_IMAGE = 1;
    Uri imageUri;
    String activeImage;

    //Stores the add image icon appearing in additional infos page
    Bitmap addImgIcon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.travailleur_additional_infos, container,
                        false);

        addImgIcon = BitmapFactory.decodeResource(getResources(), R.drawable.add_img_icon);

        description = rootView.findViewById(R.id.description);
        workImg1 = rootView.findViewById(R.id.add_img_icon1);
        workImg2 = rootView.findViewById(R.id.add_img_icon2);
        workImg3 = rootView.findViewById(R.id.add_img_icon3);
        workImg4 = rootView.findViewById(R.id.add_img_icon4);
        aUpdate = rootView.findViewById(R.id.update_add_infos);
        aToHome = rootView.findViewById(R.id.a_to_home);

        //Get current user (worker) additional infos from DB
        String currentDescription = TravailleurLogin.currentWorker.getDescription();
        byte[] currentWorkImg1 = TravailleurLogin.currentWorker.getWorkImg1();
        byte[]currentWorkImg2 = TravailleurLogin.currentWorker.getWorkImg2();
        byte[] currentWorkImg3 = TravailleurLogin.currentWorker.getWorkImg3();
        byte[] currentWorkImg4 = TravailleurLogin.currentWorker.getWorkImg4();

        id = TravailleurLogin.currentWorker.getId();
        //travailleurDB = new TravailleurDB(getContext());
        travailleurDB = new TravailleurDatabase(getContext());

        //Set view fields to current worker infos
        description.setText(currentDescription);
        workImg1.setImageBitmap(byteArrayToBitmap(currentWorkImg1));
        workImg2.setImageBitmap(byteArrayToBitmap(currentWorkImg2));
        workImg3.setImageBitmap(byteArrayToBitmap(currentWorkImg3));
        workImg4.setImageBitmap(byteArrayToBitmap(currentWorkImg4));

        //When I click on the first ImageView to update it
        workImg1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "workImg1";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Choisissez une image"), PICK_IMAGE);

            }
        });

        //Delete the image
        workImg1.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //Show delete message confirm dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Supprimer");
                builder.setMessage("êtes vous sur ?");

                //Delete Button
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Delete image from the view
                        workImg1.setImageBitmap(addImgIcon);

                        //Delete image from database
                        Boolean result = updateDb();
                        if(result){
                            Toast.makeText(getContext(),
                                    "Image suprimée", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //Cancel delete button
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Dismis dialog
                        dialog.dismiss();
                    }
                });

                //Create and show dialog
                builder.create().show();

                return false;
            }
        });


        //When I click on the second ImageView to update it
        workImg2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "workImg2";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Image"), PICK_IMAGE);

            }
        });

        //Delete the image
        workImg2.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //Show delete message confirm dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are you sure to delete this image ?");

                //Delete Button
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Delete image from the view
                        workImg2.setImageBitmap(addImgIcon);

                        //Delete image from database
                        Boolean result = updateDb();
                        if(result){
                            Toast.makeText(getContext(),
                                    "Image Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //Cancel delete button
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Dismis dialog
                        dialog.dismiss();
                    }
                });

                //Create and show dialog
                builder.create().show();

                return false;
            }
        });

        //When I click on the second ImageView to update it
        workImg3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "workImg3";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Image"), PICK_IMAGE);

            }
        });

        //Delete the image
        workImg3.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //Show delete message confirm dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are you sure to delete this image ?");

                //Delete Button
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Delete image from the view
                        workImg3.setImageBitmap(addImgIcon);

                        //Delete image from database
                        Boolean result = updateDb();
                        if(result){
                            Toast.makeText(getContext(),
                                    "Image Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //Cancel delete button
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Dismis dialog
                        dialog.dismiss();
                    }
                });

                //Create and show dialog
                builder.create().show();

                return false;
            }
        });

        //When I click on the second ImageView to update it
        workImg4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                activeImage = "workImg4";

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Select Image"), PICK_IMAGE);

            }
        });

        //Delete the image
        workImg4.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //Show delete message confirm dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are you sure to delete this image ?");

                //Delete Button
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Delete image from the view
                        workImg4.setImageBitmap(addImgIcon);

                        //Delete image from database
                        Boolean result = updateDb();
                        if(result){
                            Toast.makeText(getContext(),
                                    "Image Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //Cancel delete button
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Dismis dialog
                        dialog.dismiss();
                    }
                });

                //Create and show dialog
                builder.create().show();

                return false;
            }
        });

        aUpdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Boolean result = updateDb();

                if(result){
                    Toast.makeText(getContext(),
                            "Mise à jour avec succès", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Go back to profile page
        aToHome.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TravailleurProfil.class));
            }
        });

        return rootView;
    }

    public boolean updateDb(){

        //Parts appearing in this fragment
        String currentDescription = description.getText().toString();
        byte[] currentWorkImg1 =  imageViewToByte(workImg1);
        byte[] currentWorkImg2 =  imageViewToByte(workImg2);
        byte[] currentWorkImg3 =  imageViewToByte(workImg3);
        byte[] currentWorkImg4=  imageViewToByte(workImg4);

        //Parts appearing in personal infos fragment must stay the same in the database
        String currentFullName = FragmentPersonalInfos.fullName.getText().toString();
        String currentProfession = FragmentPersonalInfos.profession.getText().toString();
        String currentNum = FragmentPersonalInfos.num.getText().toString();

        //String currentVille = FragmentPersonalInfos.ville.getText().toString();
        String currentVille = " ";

        String currentEmail = FragmentPersonalInfos.email.getText().toString();
        String currentPassword = FragmentPersonalInfos.password.getText().toString();


        //Parts appearing in activity travailleur edit profil must stay the same in the database
        byte[] currentProfilPic =  imageViewToByte(TravailleurEditProfil.profil_pic);
        byte[] currentCin =  imageViewToByte(TravailleurEditProfil.cin_pic);
        byte[] currentCe =  imageViewToByte(TravailleurEditProfil.ce_pic);


        Boolean result = travailleurDB.update(id, currentProfilPic, currentFullName,
                currentProfession, currentNum, currentVille, currentEmail, currentPassword, currentCin, currentCe,
                currentDescription, currentWorkImg1, currentWorkImg2, currentWorkImg3, currentWorkImg4);

        return result;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){

            imageUri = data.getData();
            try {

                if(activeImage.equals("workImg1")){

                    Context applicationContext = getActivity().getApplicationContext();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), imageUri);
                    workImg1.setImageBitmap(bitmap);
                }

                if(activeImage.equals("workImg2")){

                    Context applicationContext = getActivity().getApplicationContext();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), imageUri);
                    workImg2.setImageBitmap(bitmap);
                }

                if(activeImage.equals("workImg3")){

                    Context applicationContext = getActivity().getApplicationContext();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), imageUri);
                    workImg3.setImageBitmap(bitmap);
                }

                if(activeImage.equals("workImg4")){

                    Context applicationContext = getActivity().getApplicationContext();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), imageUri);
                    workImg4.setImageBitmap(bitmap);
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

    //Convert from image view to byte[]
    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Log.i("Converting Bitmap Add","Success");
        return byteArray;
    }


}
