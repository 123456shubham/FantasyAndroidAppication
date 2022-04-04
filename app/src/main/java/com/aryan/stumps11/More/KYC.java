package com.aryan.stumps11.More;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Activity.BankAccountActivity;
import com.aryan.stumps11.ApiModel.profile.documents.DocumentsData;
import com.aryan.stumps11.ApiModel.profile.documents.UserDocumentsResponse;
import com.aryan.stumps11.ApiModel.profile.kyc.KycRequest;
import com.aryan.stumps11.ApiModel.profile.kyc.KycResponse;
import com.aryan.stumps11.ApiModel.profile.profilegetProfile.ProfileResponse;
import com.aryan.stumps11.Home.HomePage;
import com.aryan.stumps11.MainActivity;
import com.aryan.stumps11.R;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KYC extends AppCompatActivity {
    private TextView t57,t92;
    private TextInputEditText tt;
    private TextInputLayout tt1;
    private LinearLayout layout;
    private ImageView i20,i9;
    private CardView cc;
    private MaterialButton save,upload;
    private Uri uri;
    private Bitmap photo;
    private String imageString,encodedString,UPLOADPANCARD;
    private MultipartBody.Part profileFilePart;
    private ImageView imgPanCard;
    private CardView cardViewPanCard;
    public static final int RESULT_GALLERY = 0;
    public static final int CAMERA_REQUEST = 1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    public ImageLoader loader;
    Uri photoURI, finalUri;
    File photoFile;
    Bitmap resized;
    private TextView tvPhoneNumber;
    private String verify="",verifyStatus;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isDocuments=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_y_c);
        sharedPreferences=getSharedPreferences("MY_APP",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        cc=findViewById(R.id.cc);
        t57=findViewById(R.id.textView57);
        t92=findViewById(R.id.textView92);
        i20=findViewById(R.id.imageView20);
        tt=findViewById(R.id.Pan);
        tt1=findViewById(R.id.Pan1);
        save=findViewById(R.id.Save);
        upload=findViewById(R.id.upload);
        i9=findViewById(R.id.imageView9);
        layout=findViewById(R.id.Layout);
        imgPanCard=findViewById(R.id.pancardImage);
        cardViewPanCard=findViewById(R.id.kyc_pancadView);
        tvPhoneNumber=findViewById(R.id.verified_kyc_number);
        DisplayProfile();

        if (sharedPreferences.contains("UploadPanCard")){
            UPLOADPANCARD=sharedPreferences.getString("UploadPanCard","");
            getUserDocuments();
            cc.setClickable(false);
        }else{
            cc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(layout.getVisibility()==View.GONE){
                        layout.setVisibility(View.VISIBLE);
                        i20.setImageResource(R.drawable.ic_baseline_expand_less_24);
                        cardViewPanCard.setVisibility(View.VISIBLE);
                    }
                    else {
                        layout.setVisibility(View.GONE);
                        cardViewPanCard.setVisibility(View.GONE);
                        i20.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    }
                }
            });
        }
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPancardDetails();
                Dialog();
                 Notify();
            }
        });

        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layout.getVisibility()==View.GONE){
                    layout.setVisibility(View.VISIBLE);
                    i20.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
                else {
                    layout.setVisibility(View.GONE);
                    i20.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MoreActivity.class));
        overridePendingTransition(0,0);
        finish();
    }

    private void Dialog(){
        AlertDialog.Builder alert=new AlertDialog.Builder(KYC.this);
        alert.setMessage("Your Pan Card Verify Within 48 Hours.");
        alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                t57.setText("Verified");
                layout.setVisibility(View.GONE);
            }
        });
        AlertDialog alertDialog=alert.create();
        alertDialog.show();
    }

    public void Notify() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel ch=new NotificationChannel("Hello","Hello",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(ch);
        }
        Intent notificationIntent = new Intent(KYC.this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(KYC.this, 0, notificationIntent, 0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(KYC.this,"Hello");
        builder.setContentText("Your Pan Card Verify Successfully!");
        builder.setContentTitle("Pan Verification!");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentIntent(contentIntent);
        NotificationManagerCompat man= NotificationManagerCompat.from(KYC.this);
        man.notify(1,builder.build());
    }

    private void addPancardDetails(){
        SharedPreferences preferences = KYC.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        String pancardNumber=tt.getText().toString();
        imgPanCard.buildDrawingCache();
        Bitmap bmap = imgPanCard.getDrawingCache();
        String encodedImageData = "data:image/jpeg;base64,"+encodeImage(bmap);
        encodedImageData = encodedImageData.replaceAll("\n","");
        Log.d("myString",encodedImageData);
        KycRequest kycRequest=new KycRequest();
        kycRequest.setPanCardNumber(pancardNumber);
        kycRequest.setPanCardImage(encodedImageData);
        try{
            CheckConnection.api.addPancardDetails(tokenName,kycRequest).enqueue(new Callback<KycResponse>() {
                @Override
                public void onResponse(Call<KycResponse> call, Response<KycResponse> response) {
                    if (response.isSuccessful()){
                        if (response.code()==422){
                            Toast.makeText(KYC.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(KYC.this,"document upload"+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                            editor.putString("UploadPanCard","True");
                            editor.apply();
                            editor.commit();
                            cardViewPanCard.setVisibility(View.GONE);
                        }
                        Toast.makeText(KYC.this,"document upload"+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(KYC.this,"Error ",Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<KycResponse> call, Throwable t) {
                    Toast.makeText(KYC.this,"onFailure "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(KYC.this,"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }
    private void getUserDocuments(){

        SharedPreferences preferences = KYC.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        CheckConnection.api.getDocumentsDetails(tokenName).enqueue(new Callback<UserDocumentsResponse>() {
            @Override
            public void onResponse(Call<UserDocumentsResponse> call, Response<UserDocumentsResponse> response) {
                if (response.isSuccessful()){
                    try{
                        verify=response.body().getDocumentsData().getPanCardImageVerfiy();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(KYC.this, "  ", Toast.LENGTH_SHORT).show();
                    }

                    if (verify.isEmpty() || verify.length()==0 || verify==null || verify.equals(null)){
                        Toast.makeText(KYC.this, "", Toast.LENGTH_SHORT).show();
                    }
                    if(verify.equals("0")){

                        t57.setText("Pending");
                        i20.setVisibility(View.GONE);
                        t57.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Color6));
                        t92.setText("Please Wait We Check Your Information.");
                        cardViewPanCard.setVisibility(View.GONE);
                        cc.setClickable(false);
                    }else if(verify.equals("1")){
                        t57.setText("Verified");
                        i20.setVisibility(View.GONE);
                        String penCarNumber = response.body().getDocumentsData().getPanCardNumber();
                        String cardImage=response.body().getDocumentsData().getPanCardImage();
                        t92.setText(String.valueOf(penCarNumber));
                        String fullImage=CheckConnection.image+cardImage;
//                        Glide.with(KYC.this).load(fullImage).into(imgPanCard);
                        cardViewPanCard.setVisibility(View.GONE);
                        //cc.setVisibility(View.GONE);
                        cc.setClickable(false);
                        //     Toast.makeText(KYC.this, "nnx"+penCarNumber, Toast.LENGTH_SHORT).show();
                    }else if(verify.equals("2")){
                        t57.setText("Rejected");
                        cc.setEnabled(true);
                        i20.setVisibility(View.VISIBLE);
                        t57.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.Color1));
                        t92.setText("Please Verify Your Pan Card Again.");
                        cc.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(layout.getVisibility()==View.GONE){
                                    layout.setVisibility(View.VISIBLE);
                                    cardViewPanCard.setVisibility(View.VISIBLE);
                                    i20.setImageResource(R.drawable.ic_baseline_expand_less_24);
                                }
                                else {
                                    layout.setVisibility(View.GONE);
                                    cardViewPanCard.setVisibility(View.GONE);
                                    i20.setImageResource(R.drawable.ic_baseline_expand_more_24);
                                }
                            }
                        });
                    }else{
                        Toast.makeText(KYC.this,"Some Think went wrong   : ",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(KYC.this,"error  : ",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserDocumentsResponse> call, Throwable t) {
                Toast.makeText(KYC.this, "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectImage() {
       final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
//        final CharSequence[] options = {  "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(KYC.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent camera_intent
                            = new Intent(MediaStore
                            .ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera_intent, 100);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent=new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 200);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgPanCard.setImageBitmap(photo);
                Toast.makeText(KYC.this,";mkjhvg"+imgPanCard,Toast.LENGTH_SHORT).show();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                 encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);

            } else if (requestCode == 200) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imgPanCard.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private void DisplayProfile(){
        SharedPreferences preferences = KYC.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;
        CheckConnection.api.getProfile(tokenName).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()){
                    String userName=response.body().getData().getUsername();
                    String email=response.body().getData().getEmail();
                    String dob=response.body().getData().getDob();
                    String phone=response.body().getData().getPhone();
                    String state=response.body().getData().getState();
                    tvPhoneNumber.setText("91+ "+phone);
                }else {
                    Toast.makeText(KYC.this,"Token Expire",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(KYC.this,"onFailure",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override

    protected void onResume() {
        super.onResume();
        getUserDocuments();
    }
}