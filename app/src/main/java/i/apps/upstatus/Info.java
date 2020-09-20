package i.apps.upstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Info extends AppCompatActivity {

    String train_no,stn_code,date;
    String api_key = "ae76mytwec";
    String base_url = "https://api.railwayapi.com/v2/live/train/";

    TextView stn,pos,name,dep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        stn = findViewById(R.id.stn);
        pos = findViewById(R.id.pos);
        name = findViewById(R.id.name);
        dep = findViewById(R.id.dep);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        train_no = extras.getString("train_number");
        stn_code = extras.getString("stn_code");
        date = extras.getString("date");


        String complete_url = base_url + train_no + "/station/" + stn_code + "/date/" + date + "/apikey/" + api_key + "/";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, complete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject json2 = jsonObject.getJSONObject("station");

                    JSONObject trainName = jsonObject.getJSONObject("train");
                    JSONObject status = jsonObject.getJSONObject("status");

                    String name1 = (String)trainName.get("name");
                    String stn1 = (String) json2.get("name");
                    String status1 = (String)status.get("schdep");

                    stn.setText(stn1);
                    pos.setText((String)jsonObject.get("position"));
                    name.setText(name1);
                    dep.setText(status1);



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
    }
}
