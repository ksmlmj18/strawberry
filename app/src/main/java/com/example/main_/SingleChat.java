package com.example.main_;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

// < 채팅방 >
public class SingleChat extends AppCompatActivity implements Serializable {
    private Button btn_move;
    private int chat_length;
    private String inputStr;
    public static boolean check2 = true;    // 에디트 텍스트 true or false check
    public static Timer timer2;
    boolean finish2 = true;
    private final String TAG = this.getClass().getSimpleName();


    ArrayList<String> chat_contents = new ArrayList<>();

    private ListView listview ;
    private ListViewAdapter5 adapter5;
    private int msgCount=0;
    public static Boolean intentCheck = false;
    EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        //에딧텍스트에 변화가 있다면 finish값을 false로 주어 새로고침 중지
        editText2 = findViewById(R.id.et_single_chat_input);
        editText2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                finish2 = false;
                return false;
            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "beforeTextChanged() - charSequence : " + charSequence);
                finish2 = false;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "onTextChanged() - charSequence : " + charSequence);
                finish2 = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e(TAG, "afterTextChanged() - editable : " + editable.toString());
                finish2 = true;
            }
        });




        System.out.println("--------- singleChat.java에 onCreate에 들어옴 ---------");
        // [ 누군가가 메시지를 보낼 때마다 새로고침을 해준다. ]
        // 만약 특정 값에 변화가 생겼다면..
        // 한번만 새로고침해라..
//        if(Login.change_check == true){
//            try {
//                finish();//인텐트 종료
//                overridePendingTransition(0, 0);//인텐트 효과 없애기
//                Intent intent2 = getIntent(); //인텐트
//                startActivity(intent2); //액티비티 열기
//                overridePendingTransition(0, 0);//인텐트 효과 없애기
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        // --------------------------- 각 자리에 item 값을 넣기 위한 과정 ---------------------------
        // 초기화
        ListViewAdapter5.items.clear();

        // Adapter 생성
        adapter5 = new ListViewAdapter5();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listView_single_chat);
        listview.setAdapter(adapter5);

        //id 속성이 text id값의 뷰의 주소 값을 얻어온다.
        TextView chat_name = (TextView)findViewById(R.id.tv_Single_chat_name);
//        TextView getter_content = (TextView)findViewById(R.id.tv_item_getterContent);

        // 보내온 Intent를 얻는다.
        Intent intent = getIntent();

        // 프론트
        //새로운 문자열을 설정한다.
        chat_name.setText(intent.getStringExtra("ch_getter"));

        // 백엔드
        // Chat.java에서 intent로 받아온 값들
        String sender_nick = intent.getStringExtra("ch_sender");
        String getter_nick = intent.getStringExtra("ch_getter");
        String ch_id = intent.getStringExtra("chat_id");
        ArrayList<String> data_contents = (ArrayList<String>) getIntent().getSerializableExtra("ch_content");


//        System.out.println("-----------sender_nick, getter_nick");
//        System.out.println(sender_nick);
//        System.out.println(getter_nick);
//        System.out.println("-----------sender_nick, getter_nick");


//        System.out.println("-----------나 가져온 list 인덱스 0의 내용이야");
//        System.out.println(Chat.chat_contentList.get(0).get("chat_content"));
//        System.out.println("-----------나 가져온 list 인덱스 0의 내용이야");


        chat_length = 0;    // 그 해당 방의 채팅 갯수
        for(int i=0; i<Chat.chat_contentList.size(); i++){
            if(Chat.chat_contentList.get(i).get("chat_id").equals(ch_id)){
                chat_length++;
            }
        }

        // 채팅방에 들어왔을 때 갯수 체크
//        System.out.println("=====================================");
//        System.out.println("해당 방의 총 채팅 갯수: " + chat_length);
//        System.out.println("=====================================");
//
//
//        // [ 아이템 추가 ]
//        System.out.println("--------------첫 대화의 chat_id");
//        System.out.println(Chat.chat_contentList.get(0).get("chat_id"));
//        System.out.println(ch_id);
//        System.out.println("--------------첫 대화의 chat_id");
//
//        System.out.println("--------------item arrayList의 크기");
//        System.out.println(adapter5.getCount());
//        System.out.println("--------------item arrayList의 크기");
//
        System.out.println("--------------채팅한 목록");
        System.out.println(chat_contents);
        System.out.println("--------------채팅한 목록");






        System.out.println("--------------3");
        System.out.println(adapter5.getCount());
        System.out.println("--------------3");
        // ------------------------------------------------------------------------
        TimerTask TT_chat = new TimerTask(){

            Handler thisHandler = null;

            // 일정시간 반복한다.
            @Override
            public void run() {

                System.out.println("--------- 채팅 연동 중 --------");
                Chat.chat_contentList.clear();

                // < 대화 목록 정보 리스폰 >
                Response.Listener<String> response_chatContent = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 리스폰 값 확인
                        System.out.println("=================================2 채팅 내역 목록");
                        System.out.println(response);
                        System.out.println("=================================2 채팅 내역 목록");

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            // DB 연동 성공 후
                            if(success){

                                // 2. [ "채팅방 안에 대화 내역" 관련 정보를 담는다. ]
                                Chat.chat_contentInfoList = jsonObject.getJSONArray("chat_contentList");
                                Chat.chat_contentInfoList_length = Chat.chat_contentInfoList.length();  // 총 채팅방 내역 갯수

                                System.out.println("=================================채팅 내역 갯수");
                                System.out.println(Chat.chat_contentInfoList_length);
                                System.out.println("=================================채팅 내역 갯수");



                                // [ 채팅방 내역의 데이터 정제 과정 ]
                                // 총 본인 채팅 내역 갯수만큼 반복
                                for(int i=0; i<Chat.chat_contentInfoList_length; i++){
                                    // 다른 맵 객체 생성
                                    Chat.chat_contentMap = new HashMap<>();

                                    // 채팅방 내역 정보(chat_id, sender, getter, chat_content, chat_day, chat_time)
                                    JSONObject json_chContentInfo = Chat.chat_contentInfoList.getJSONObject(i);
                                    Chat.ch_c_id = json_chContentInfo.getString("chat_id");
                                    Chat.ch_c_sender = json_chContentInfo.getString("sender");
                                    Chat.ch_c_getter = json_chContentInfo.getString("getter");
                                    Chat.ch_c_content = json_chContentInfo.getString("chat_content");
                                    Chat.ch_c_day = json_chContentInfo.getString("chat_day");
                                    Chat.ch_c_time = json_chContentInfo.getString("chat_time");


                                    Chat.chat_contentMap.put("chat_id", Chat.ch_c_id);
                                    Chat.chat_contentMap.put("sender", Chat.ch_c_sender);
                                    Chat.chat_contentMap.put("getter", Chat.ch_c_getter);
                                    Chat.chat_contentMap.put("chat_content", Chat.ch_c_content);
                                    Chat.chat_contentMap.put("chat_day", Chat.ch_c_day);
                                    Chat.chat_contentMap.put("chat_time", Chat.ch_c_time);

                                    Chat.chat_contentList.add(Chat.chat_contentMap);
                                }




                                // ----------------- 아이템 추가 ----------------------
                                for (int i = 0; i < Chat.chat_contentList.size(); i++) {
                                    // 만약에, 데이터값에서의 chat_id가 해당 방의 chat_id와 같다면..
                                    if (Chat.chat_contentList.get(i).get("chat_id").equals(ch_id)) {
                                        if (sender_nick.equals(Chat.chat_contentList.get(i).get("sender"))) {  // sender_nick == 로그인한 사람
                                            System.out.println("첫 번째 if문 들어왔따.");
                                            System.out.println(Chat.chat_contentList.get(i).get("chat_content"));
                                            chat_contents.add(msgCount,Chat.chat_contentList.get(i).get("chat_content"));
                                            adapter5.addItem_sender(sender_nick, chat_contents.get(msgCount));
                                            msgCount++;
                                            // 상대가 보내는거라면..
                                        } else if (getter_nick.equals(Chat.chat_contentList.get(i).get("sender"))) {
                                            System.out.println("두 번째 if문 들어왔따.");
                                            System.out.println(Chat.chat_contentList.get(i).get("chat_content"));
                                            chat_contents.add(msgCount,Chat.chat_contentList.get(i).get("chat_content"));
                                            // getter 관련된 item 추가
                                            adapter5.addItem_getter(getter_nick, chat_contents.get(msgCount));
                                            msgCount++;
                                        }
                                    }
                                }



                                // item 갱신
                                adapter5.notifyDataSetChanged();


                                if(Looper.myLooper() == null){
                                    Looper.prepare();
                                }
                                thisHandler = new Handler();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        timer2.cancel();
                                        if(finish2 == true) { // finsih가 true라면 새로고침 진행
                                            Intent intent_single2 = new Intent(SingleChat.this, SingleChat.class);
                                            intent_single2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                            intent_single2.putExtra("ch_sender", sender_nick);
                                            intent_single2.putExtra("ch_getter", getter_nick);
                                            intent_single2.putExtra("chat_id", ch_id);
                                            intent_single2.putExtra("ch_content", chat_contents);
                                            intent_single2.putExtra("ch_name", getter_nick);

                                            overridePendingTransition(0, 0);//인텐트 효과 없애기
                                            startActivity(intent_single2);
                                            overridePendingTransition(0, 0);//인텐트 효과 없애기
                                        }
                                    }
                                }, 2000);
                                Looper.loop();

                            }else{
                                Toast.makeText(getApplicationContext(), "서버 연동에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };



                // < 채팅방 내역 관련 요청 >
                SingleChatRequest singleChatRequest = new SingleChatRequest(Login.userL2, response_chatContent);
                RequestQueue queue3 = Volley.newRequestQueue(SingleChat.this);
                queue3.add(singleChatRequest);


            }
        };

        // 반복 타이머
        timer2 = new Timer();
        // 반복 타이머 설정
        timer2.schedule(TT_chat, 0, 3000);




        // < "전송" 버튼을 눌렀을 시 >
        Button btn = findViewById(R.id.bt_single_chat_send);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                check2 = true;


                // 만약에 메시지 입력 상태이면,
                // 타이머를 잠시 멈춘다.

                // [ 메시지 입력 및 메시지 저장 ]
                EditText editText = (EditText) findViewById(R.id.et_single_chat_input);
                inputStr = editText.getText().toString();    // 메시지 값 저장.
                System.out.println("==============1");
                System.out.println(inputStr);
                System.out.println("==============1");

                // 채팅 갯수 증가
                chat_length++;

                Toast.makeText(SingleChat.this, "보내기 버튼 눌러짐", Toast.LENGTH_SHORT).show();


                // [ 현재 날짜, 시간 계산 ]
                long mNow;
                Date mDate;
                SimpleDateFormat mf_date = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat mf_time = new SimpleDateFormat("HH:mm:ss");

                mNow = System.currentTimeMillis();
                mDate = new Date(mNow);
                String current_date = mf_date.format(mDate);    // 현재 날짜
                String current_time = mf_time.format(mDate);    // 현재 시간


                // < 대화 목록 정보 리스폰 >
                Response.Listener<String> response_server = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 리스폰 값 확인
                        System.out.println("=================================1");
                        System.out.println(response);
                        System.out.println("=================================1");

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            // DB 연동 성공 후
                            if(success){
                                Toast.makeText(getApplicationContext(), "메세지가 전송되었습니다.", Toast.LENGTH_SHORT).show();
                                finish2 = true;
                                // 1초간 쉬고, 채팅방 목록에 갔다 온다.
//                                try {
//                                    Thread.sleep(1000);
//                                    SingleChat.intentCheck = true;
//                                    Intent intent2 = new Intent(SingleChat.this, Chat.class);
//                                    finish(); //현재 액티비티 종료 실시/
//                                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
//                                    startActivity(intent2); // chat.java 액티비티로 이동
//                                    overridePendingTransition(0, 0); //인텐트 애니메이션 없애기
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }


                            }else{
                                Toast.makeText(getApplicationContext(), "소켓 서버 연동에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };



                // [[ 서버에 요청 ]]
                // [ 보낼 데이터 ]
                // - sender_nick : 보낸 사람 => sender 키 값으로 보내줘야함
                // - getter_nick : 받는 사람 => getter 키 값으로 보내줘야함
                // - inputStr : 메시지 입력 값 => chat_content 키 값으로 보내줘야함
                // - current_date : 현재 날짜 => chat_day 키 값으로 보내줘야함
                // - current_time : 현재 시간 => chat_time 키 값으로 보내줘야함
                ServerChatRequest serverChatRequest = new ServerChatRequest(sender_nick, getter_nick, inputStr, current_date, current_time, response_server);
                RequestQueue queue = Volley.newRequestQueue(SingleChat.this);
                queue.add(serverChatRequest);
            }
        });




        // -------------------- 하단바 및 뒤로가기 --------------------
        btn_move = findViewById(R.id.bt_singlechat_back);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_move = findViewById(R.id.bt_home);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); finish2 = false;
                if(Login.userL == 0) {
                    Intent intent2 = new Intent(SingleChat.this, Main.class);
                    startActivity(intent2); // 액티비티 이동
                    finish();
                }else if(Login.userL == 1) {
                    Intent intent2 = new Intent(SingleChat.this, MainLogin.class);
                    startActivity(intent2); // 액티비티 이동
                    finish();
                }
            }
        });

        btn_move = findViewById(R.id.bt_auction);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); finish2 = false;
                Intent intent2 = new Intent(SingleChat.this, Auction.class);
                startActivity(intent2); // 액티비티 이동
                finish();
            }
        });

        btn_move = findViewById(R.id.bt_scrap);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); finish2 = false;
                Intent intent2 = new Intent(SingleChat.this, MyScrap.class);
                startActivity(intent2); // 액티비티 이동
                finish();
            }
        });

        btn_move = findViewById(R.id.bt_chat);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); finish2 = false;
                Intent intent2 = new Intent(SingleChat.this, Chat.class);
                startActivity(intent2); // 액티비티 이동
                finish();
            }
        });

        btn_move = findViewById(R.id.bt_myPage);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); finish2 = false;
                Intent intent2 = new Intent(SingleChat.this, MyPage.class);
                startActivity(intent2); // 액티비티 이동
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish(); finish2 = false;
        super.onBackPressed();
    }
}

