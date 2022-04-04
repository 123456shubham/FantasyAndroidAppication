package com.aryan.stumps11.Home;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aryan.stumps11.Adapters.HomeAdapter;
import com.aryan.stumps11.Adapters.SliderAdapter;
import com.aryan.stumps11.ApiModel.profile.banner.BannerList;
import com.aryan.stumps11.ApiModel.profile.banner.BannerResponse;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.More.KYC;
import com.aryan.stumps11.More.MoreActivity;
import com.aryan.stumps11.More.Profile;
import com.aryan.stumps11.MyMatches.MyMatches;
import com.aryan.stumps11.NewUiData.Activity.MymatchActivity;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Signup.MobileNumber;
import com.aryan.stumps11.Wallet.Wallet;
import com.aryan.stumps11.Winners.WinnersActivity;
import com.aryan.stumps11.api_integration.CheckConnection;
import com.aryan.stumps11.dataPassing.DataPassringInterface;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;


public class HomePage extends AppCompatActivity implements DataPassringInterface {
    RequestQueue rq;
    RecyclerView HomeView;
    ShimmerFrameLayout sl;
    FusedLocationProviderClient fusedLocationProviderClient;
    SharedPreferences states;
    ImageView i14;
    private RecyclerView comingSoonRecyclerView;
    private SliderView sliderView;
    private List<BannerList> bannerListList;
    private SliderAdapter adapter;
    TextView tv_NewData;
    private ImageView  imageViewNoMatch;
    private ProgressDialog progressDialog;

//private List<Item> itemList;
//    private ComingSoonMatchAdapter comingSoonMatchAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bannerSlider();
         sliderView = findViewById(R.id.image_slider);
         imageViewNoMatch=findViewById(R.id.home_no_match);
         progressDialog=new ProgressDialog(HomePage.this);



        states=getSharedPreferences("state",MODE_PRIVATE);
//        comingSoonRecyclerView=findViewById(R.id.home_page_recyclerview);
//        comingSoonRecyclerView.setHasFixedSize(false);
//        comingSoonRecyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this,LinearLayoutManager.VERTICAL,false));

     //   itemList=new ArrayList<>();
        sl = findViewById(R.id.shimmer);
        Bottom();
        API();
        Permission();
//        slider();

        i14=findViewById(R.id.imageView14);
        i14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, Wallet.class));
            }
        });





    }

    private void API() {

        try {

            progressDialog.setTitle("Stumps11");
            progressDialog.setMessage("Loading...");
            progressDialog.show();


//
            comingSoonRecyclerView = findViewById(R.id.home_page_recyclerview);
            comingSoonRecyclerView.setHasFixedSize(true);
            comingSoonRecyclerView.setLayoutManager(new LinearLayoutManager(HomePage.this));

        List<ModelClass> list = new ArrayList<>();
        rq = Volley.newRequestQueue(HomePage.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://rest.entitysport.com/v2/matches/?status=1&pre_squad=true&per_page=176&token=de7cddfd6309f89136ca5c4f68aaff99", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String status = jsonObject.getString("status");
                    if (status.equals("ok")) {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("response");
                        JSONArray jsonArray = jsonObject1.getJSONArray("items");

                        if (jsonArray.length()==0){
                            imageViewNoMatch.setVisibility(View.VISIBLE);
                        }else{
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                String matchid=jsonObject2.getString("match_id");
                                String timer=jsonObject2.getString("date_start_ist");


                                //   Toast.makeText(HomePage.this,"Match ID "+matchid,Toast.LENGTH_SHORT).show();


                                JSONObject jsonObject3 = jsonObject2.getJSONObject("competition");
                                String seriesname = jsonObject3.getString("title");
                                String cid=jsonObject3.getString("cid");

                                JSONObject jsonObject4 = jsonObject2.getJSONObject("teama");
                                String teamname1 = jsonObject4.getString("name");
                                String teamimage1 = jsonObject4.getString("logo_url");
                                JSONObject jsonObject5 = jsonObject2.getJSONObject("teamb");
                                String teamname2 = jsonObject5.getString("name");
                                String teamimage2 = jsonObject5.getString("logo_url");

                                ModelClass mm = new ModelClass();
                                mm.setSname(seriesname);
                                mm.setMatchid(matchid);
                                mm.setCid(cid);
                                mm.setTname1(teamname1);
                                mm.setTname2(teamname2);
                                mm.setTimage1(teamimage1);
                                mm.setTimage2(teamimage2);
                                mm.setMatchTiming(timer);
                                list.add(mm);

                                HomeAdapter ha = new HomeAdapter(HomePage.this, list,HomePage.this::sendData);
                                comingSoonRecyclerView.setAdapter(ha);
                                progressDialog.dismiss();

                            }

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomePage.this, "Error :- "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
       rq.add(stringRequest);
    } catch (Exception e) {
            e.printStackTrace();
        }

//    private void slider(){
//        SliderView sliderView;
//        List<ModelClass>list=new ArrayList<>();
//        sliderView = findViewById(R.id.image_slider);
//
//        RequestQueue rq=Volley.newRequestQueue(HomePage.this);
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://www.stumps11.com/APIS/Apis.asmx/ImageSlider", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONArray jsonArray=new JSONArray(response);
//                    for(int i=0;i<jsonArray.length();i++){
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        String images="http://www.stumps11.com/SliderImages/"+jsonObject.getString("Sliderimage");
//                        ModelClass mm=new ModelClass();
//                        mm.setImages(images);
//                        list.add(mm);
//                        SliderAdapter sliderAdapter = new SliderAdapter(list,HomePage.this);
//                        sliderView.setSliderAdapter(sliderAdapter);
//                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//                        sliderView.startAutoCycle();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        rq.add(stringRequest);
    }

    private void Bottom() {
        BottomNavigationView bbn = findViewById(R.id.bottomnav);
        bbn.setSelectedItemId(R.id.HomePage);
        bbn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.HomePage:
                        return true;

                    case R.id.MyMatches:
                        startActivity(new Intent(getApplicationContext(), MyMatches.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.Winners:
                        startActivity(new Intent(getApplicationContext(), WinnersActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.More:
                        startActivity(new Intent(getApplicationContext(), MoreActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    public void exit() {
        AlertDialog.Builder alert = new AlertDialog.Builder(HomePage.this);
        alert.setMessage("Do You Want To Close Application?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    private void Location() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(HomePage.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(HomePage.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        String state = addresses.get(0).getAdminArea();

                        if (state.equals("Telangana") || state.equals("Assam") || state.equals("Karnataka") || state.equals("Odisha") || state.equals("Andhra Pradesh") || state.equals("Sikkim") || state.equals("Nagaland")) {
                            DialogforLocations();
                        }
                        SharedPreferences.Editor editor1=states.edit();
                        editor1.putString("sKey",state);
                        editor1.apply();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void DialogforLocations(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HomePage.this);
        bottomSheetDialog.setContentView(R.layout.location);
        bottomSheetDialog.show();
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setCancelable(false);
        MaterialButton button=bottomSheetDialog.findViewById(R.id.Close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    private void DialogforPermission(){
        AlertDialog.Builder alert=new AlertDialog.Builder(HomePage.this);
        alert.setCancelable(false);
        alert.setMessage("Sorry You are Not Able to Use Our App Without Granted Permissions.");
        alert.setNegativeButton("Go To Settings.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in=new Intent();
                in.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri= Uri.fromParts("package",getPackageName(),null);
                in.setData(uri);
                startActivity(in);
              }
        });
        AlertDialog alertDialog=alert.create();
        alertDialog.show();
    }

    private void Permission(){
        Dexter.withContext(HomePage.this).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if(multiplePermissionsReport.areAllPermissionsGranted()){
                    Location();
                }
                else {
                DialogforPermission();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    private void bannerSlider(){
        SharedPreferences preferences = HomePage.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String tokenName="Bearer "+retrivedToken;


        try{

            CheckConnection.api.getBannerList(tokenName).enqueue(new Callback<BannerResponse>() {
                @Override
                public void onResponse(Call<BannerResponse> call, retrofit2.Response<BannerResponse> response) {

                    if (response.isSuccessful()){
//                        for (int i=0; i<bannerListList.size(); i++){
                            bannerListList=response.body().getBannerListList();
                            adapter = new SliderAdapter(bannerListList,HomePage.this);
                            adapter.notifyDataSetChanged();
                            sliderView.setSliderAdapter(adapter);
                            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                            sliderView.startAutoCycle();
              //              Toast.makeText(HomePage.this, "Success :  "+response.body().getBannerListList(), Toast.LENGTH_SHORT).show();

//                        }

                    }else
                    {
                        Toast.makeText(HomePage.this,"Error "+response.errorBody(),Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<BannerResponse> call, Throwable t) {
                    Toast.makeText(HomePage.this, "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){

            Toast.makeText(this, "Exception :  "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void sendData(String data) {
        Toast.makeText(HomePage.this,"Sending Data "+data,Toast.LENGTH_SHORT).show();
    }
}