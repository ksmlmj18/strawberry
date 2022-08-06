package com.example.main_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;

public class auctionActivity extends AppCompatActivity {

    //뷰의 주소값을 담을 참조 변수
    private Button btn_move;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);

        btn_move = findViewById(R.id.bt_auction_back);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        btn_move = findViewById(R.id.bt_main_post);
//        btn_move.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(auctionActivity.this, AuctionCategory.class);
//                startActivity(intent); // 액티비티 이동
//            }
//        });
    }
}