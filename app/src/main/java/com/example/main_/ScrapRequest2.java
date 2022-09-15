package com.example.main_;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
// 내가 스크랩한 정보 받아오는 리퀘스트
public class ScrapRequest2 extends StringRequest {

    final static private String URL = "http://strawberry.dothome.co.kr/get_scrap.php";
    private Map<String, String> map;

    public ScrapRequest2(String user_nickName, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_nickName",user_nickName);

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
