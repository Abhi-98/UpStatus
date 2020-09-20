package i.apps.upstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class checkFare extends AppCompatActivity {

    String no,src,dest,age,prefclass,quota;
    TextView f;
    private ProgressBar pgsBar;


    List<Seats> seatsList;
    MyAdapter adapter1;
    String api_key = "r2s5mbrs5r";
    String train_no,stn_code,date;
    ListView listView;
    String base_url = "https://api.railwayapi.com/v2/live/train/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fare);

        getSupportActionBar().hide();
        pgsBar = (ProgressBar) findViewById(R.id.pBar);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        no = extras.getString("train_number");
        src = extras.getString("src");
        dest = extras.getString("train_number");
        age = extras.getString("train_number");
        date= extras.getString("date");
        prefclass = extras.getString("train_number");
        quota = extras.getString("train_number");

        Log.i("LLLLLLLL",no);

        seatsList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.list);




        String complete_url = base_url + no + "/station/" + src + "/date/" + date + "/apikey/" + api_key + "/";

        Log.i("URLLL",complete_url);

        pgsBar.setVisibility(VISIBLE);


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, complete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    pgsBar.setVisibility(INVISIBLE);

                    JSONObject obj = new JSONObject(response);

                    //we have the array named hero inside the object
                    //so here we are getting that json array
                    JSONObject clss = obj.getJSONObject("train");
                    JSONArray heroArray = clss.getJSONArray("classes");

                    //now looping through all the elements of the json array
                    for (int i = 0; i < heroArray.length(); i++) {
                        //getting the json object of the particular index inside the array
                        JSONObject heroObject = heroArray.getJSONObject(i);

                        //creating a hero object and giving them the values from json object
                        Seats seats = new Seats(heroObject.getString("name"), heroObject.getString("code"), heroObject.getString("available"));

                        //adding the hero to herolist
                        seatsList.add(seats);
                    }

                    //creating custom adapter object
                    adapter1 = new MyAdapter(seatsList,getApplicationContext(),R.layout.seat_availability);

                    //adding the adapter to listview
                    listView.setAdapter(adapter1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(checkFare.this, "Something went wrong.Please try again.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(checkFare.this,fare.class));
            }
        });

        requestQueue.add(stringRequest);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(checkFare.this,Home.class));
    }
}
