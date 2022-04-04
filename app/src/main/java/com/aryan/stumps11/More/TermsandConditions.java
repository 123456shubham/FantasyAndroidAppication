package com.aryan.stumps11.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.aryan.stumps11.Class.Class;
import com.aryan.stumps11.R;

public class TermsandConditions extends AppCompatActivity {
ImageView i4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsand_conditions);
        //For ProgressBar.
        Class clas=new Class();
        clas.DisplayprogressforTexts(TermsandConditions.this);
        i4=findViewById(R.id.imageView4);
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}