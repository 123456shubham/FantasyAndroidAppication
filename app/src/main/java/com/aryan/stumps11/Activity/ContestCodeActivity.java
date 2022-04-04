package com.aryan.stumps11.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aryan.stumps11.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ContestCodeActivity extends AppCompatActivity {

    private ImageView imgBackBtn;

    private TextInputEditText contextInviteCode;
    private MaterialButton materialButtonCreateContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_code);
        init();

        materialButtonCreateContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String coode=contextInviteCode.getText().toString();
                if (coode.isEmpty()){
                    contextInviteCode.setError("Please Enter Invite Code");
                    return;
                }

            }
        });
    }


    private void init(){
        contextInviteCode=findViewById(R.id.private_context_context_code_invite_code);
        imgBackBtn=findViewById(R.id.context_back_btn);
        materialButtonCreateContext=findViewById(R.id.private_context_code_invite);

    }

}