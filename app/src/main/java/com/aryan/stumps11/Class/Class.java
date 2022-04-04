package com.aryan.stumps11.Class;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;

import com.aryan.stumps11.More.AboutUs;
import com.aryan.stumps11.R;

public class Class {

    public void DisplayprogressforTexts(Activity activity){
        Dialog dialog=new Dialog(activity);
        dialog.setContentView(R.layout.progressbar);
        dialog.show();
        dialog.setCancelable(false);
        dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.hide();
            }
        },1000);
    }

    public void Share(Context activity, String msg){
        Intent in=new Intent(Intent.ACTION_SEND);
        in.setType("text/plain");
        String sharebody=msg;

        String sharesub="Stumps11!";
        in.putExtra(Intent.EXTRA_SUBJECT,sharesub);
        in.putExtra(Intent.EXTRA_TEXT,sharebody);
        activity.startActivity(Intent.createChooser(in,"Share Via"));
    }
}
