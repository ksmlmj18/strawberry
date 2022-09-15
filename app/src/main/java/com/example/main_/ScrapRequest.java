package com.example.main_;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
// 스크랩을 눌렀을 때 정보 전달
public class ScrapRequest extends StringRequest {

    final static private String URL = "http://strawberry.dothome.co.kr/add_scrap.php";
    private Map<String, String> map;

    public ScrapRequest(String user_nickName, String poster_id, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_nickName",user_nickName);
        map.put("poster_id", poster_id);

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
