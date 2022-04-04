package com.aryan.stumps11.More;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aryan.stumps11.Adapters.RecentTransactionsAdapter;
import com.aryan.stumps11.Adapters.WinnersAdapter;
import com.aryan.stumps11.Model.ModelClass;
import com.aryan.stumps11.R;
import com.aryan.stumps11.Winners.WinnersActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecentTransactions extends AppCompatActivity {
RecyclerView rv;
ImageView i4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_transactions);

        i4=findViewById(R.id.imageView4);
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SetUpTransactions();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MoreActivity.class));
        overridePendingTransition(0,0);
        finish();
    }

    private void SetUpTransactions(){
        rv=findViewById(R.id.RecentView);
        rv.setLayoutManager(new LinearLayoutManager(RecentTransactions.this));
        rv.setHasFixedSize(true);
        List<ModelClass> list=new ArrayList<>();
        ModelClass mm=new ModelClass();
        mm.setTid("Hello");
        mm.setTdate("23/4/1981");
        mm.setTamt("+ 500");
        list.add(mm);
        RecentTransactionsAdapter rta=new RecentTransactionsAdapter(RecentTransactions.this,list);
        rv.setAdapter(rta);
    }
}