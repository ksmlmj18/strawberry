package com.example.main_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class myPage extends AppCompatActivity {

    private Button btn_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);



        btn_move = findViewById(R.id.bt_myPage_back);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_move = findViewById(R.id.bt_myPage_edit);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myPage.this, userinfoEdit.class);
                startActivity(intent); // 액티비티 이동
            }
        });

        btn_move = findViewById(R.id.bt_myPage_auctionCheck);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myPage.this, MyAuction.class);
                startActivity(intent); // 액티비티 이동
            }
        });

        btn_move = findViewById(R.id.bt_myPage_commentCheck);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myPage.this, MyComment.class);
                startActivity(intent); // 액티비티 이동
            }
        });

        btn_move = findViewById(R.id.bt_myPage_interCheck);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myPage.this, MyScrap.class);
                startActivity(intent); // 액티비티 이동
            }
        });

//        btn_move = findViewById(R.id.bt_home)



    }
}