package com.example.anubh.pnrstatus;

import android.app.DownloadManager;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    EditText pnr;
    Button button;
    ArrayList<HashMap<String,String>> list;
    String address,apikey,url;
    TextView trainno,trainname,doj,from,to,Class,chartstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  this.getActionBar().hide();

        pnr = (EditText) findViewById(R.id.pnrtext);
        button = (Button) findViewById(R.id.button);
        trainno = (TextView) findViewById(R.id.trainno);
        trainname = (TextView) findViewById(R.id.trainname);
        doj = (TextView) findViewById(R.id.DOJ);
        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        Class = (TextView) findViewById(R.id.Class);
        chartstatus = (TextView) findViewById(R.id.chartstatus);

      //  list = new ArrayList<HashMap<String,String>>();

        address = "http://api.railwayapi.com/pnr_status/pnr/";
        apikey  = "/apikey/n7guqmzi";


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pnr.getText().toString().trim() == null || pnr.getText().toString().trim() == "") {
                    Toast.makeText(MainActivity.this, "Enter the PNR", Toast.LENGTH_SHORT).show();
                } else {

                    final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                    url = address + pnr.getText().toString().trim() + apikey;
                    //JSONObject jsonObject = new JSONObject();
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,

                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String temp;
                                        //response = response.getJSONObject("response");
                                        temp = response.get("response_code").toString();
                                        if ((temp.equals("200"))) {
                                            System.out.println("error: " + response.get("error").toString());
                                            if (!Boolean.parseBoolean(response.get("error").toString())) {
                                                System.out.println("trainno: " + response.get("train_num").toString());
                                                trainno.setText(response.get("train_num").toString());
                                                trainname.setText(response.get("train_name").toString());
                                                doj.setText(response.get("doj").toString());
                                                from.setText((response.getJSONObject("boarding_point")).get("code").toString());
                                                to.setText((response.getJSONObject("to_station")).get("code").toString());
                                                Class.setText(response.get("class").toString());
                                                if (response.get("chart_prepared").toString().equals("N"))
                                                    chartstatus.setText("Not Prep.");
                                                else
                                                    chartstatus.setText("Prepared");
                                            }
                                        } else
                                            trainno.setText("error:204");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //       .setText("Something went wrong...");
                                    requestQueue.stop();
                                }
                            }

                    );
                    requestQueue.add(jsonObjectRequest);
                    // }
                }
            }
        });

    }
}
