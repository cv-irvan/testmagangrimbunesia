package com.magang.testrimbunesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailPlayerActivity extends AppCompatActivity {
    public static String EXTRA_PLAYER = "extra_player";
    TextView tvNo, tvNama, tvCategory;

    private static final String JSON_URL = "http://www.json-generator.com/api/json/get/bVSknCbJSG?indent=2";


    ListView listView;
    private List<PlayerItem> playerItemList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);

        listView =  findViewById(R.id.listView);
        playerItemList = new ArrayList<>();

        tvNo = findViewById(R.id.tvNo);
        tvNama = findViewById(R.id.tvNama);
        tvCategory = findViewById(R.id.tvCategory);

        PlayerItem playerItem =  getIntent().getParcelableExtra(EXTRA_PLAYER);

        String club = playerItem.getClub();
        String name = playerItem.getName();
        String category = playerItem.getCategory();

        tvNo.setText(club);
        tvNama.setText(name);
        tvCategory.setText(category);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                PlayerItem playerItem = playerItemList.get(position);

                Intent i = new Intent(getApplicationContext(), DetailPlayerActivity.class);
                i.putExtra(DetailPlayerActivity.EXTRA_PLAYER, playerItem);
                startActivity(i);


            }
        });
        loadPlayer();


    }

    private void loadPlayer() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray playerArray = obj.getJSONArray("result");

                            for (int i = 0; i < playerArray.length(); i++) {

                                JSONObject playerObject = playerArray.getJSONObject(i);


                                PlayerItem playerItem = new PlayerItem(playerObject.getString("club"),
                                        playerObject.getString("name"),
                                        playerObject.getString("category"));

                                playerItemList.add(playerItem);
                            }

                            ListViewAdapter adapter = new ListViewAdapter(playerItemList, getApplicationContext());

                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
