package com.aryan.stumps11.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aryan.stumps11.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PrivateContestActivity extends AppCompatActivity {
    private ImageView imgBackBtn;

    private TextInputEditText contextName;
    private TextInputEditText contextSize;
    private TextInputEditText contextEntry;
    private TextInputEditText contextPoolSize;
    private MaterialButton materialButtonCreateContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_contest);
        imgBackBtn=findViewById(R.id.private_context_back_btn);

        init();

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        materialButtonCreateContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cName=contextName.getText().toString();
                String cSize=contextSize.getText().toString();
                String cEntry=contextEntry.getText().toString();
                String cPool=contextPoolSize.getText().toString();

                if (cName.isEmpty()){
                    contextName.setError("Please Enter Context Name");
                    return;
                }

                if (cSize.isEmpty()){
                    contextSize.setError("Please Enter Context Size");
                    return;

                }

                if (cEntry.isEmpty()){
                    contextEntry.setError("Please Enter Context Entry");
                    return;
                }

                if (cPool.isEmpty()){
                    contextEntry.setError("Please Enter Context Context Pool");
                    return;
                }


            }
        });
    }


    private void init(){
        contextName=findViewById(R.id.private_context_context_name);
        contextEntry=findViewById(R.id.private_context_entry);
        contextSize=findViewById(R.id.private_context_context_size);
        contextPoolSize=findViewById(R.id.private_context_max_prize_pool);
        materialButtonCreateContext=findViewById(R.id.private_context_continue);

    }



}