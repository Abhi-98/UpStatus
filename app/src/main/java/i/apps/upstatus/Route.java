package i.apps.upstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class Route extends AppCompatActivity {

    List<trains> trainList;
    Adapter adapter1;
    String api_key = "r2s5mbrs5r";
    String train_no;
    private static final String TAG = "CardListActivity";
    private ListView listView;
    CardArrayAdapter cardArrayAdapter;
    private ProgressBar pgsBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        getSupportActionBar().hide();


        trainList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);

        listView.addHeaderView(new View(this));
        listView.addFooterView(new View(this));
        pgsBar = (ProgressBar) findViewById(R.id.pBar);


        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.train_route);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        train_no = extras.getString("train_number");




        String curl = "https://api.railwayapi.com/v2/route/train/" + train_no + "/apikey/" + api_key + "/";

        Log.i("UUUUUU",curl);
        Log.i("NNNNNNNNN",train_no);

        pgsBar.setVisibility(VISIBLE);
        pgsBar.setTag("Loading..");

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,curl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pgsBar.setVisibility(INVISIBLE);


                try {
                    JSONObject obj = new JSONObject(response);

                    //we have the array named hero inside the object
                    //so here we are getting that json array
                    JSONArray heroArray = obj.getJSONArray("route");

                    //now looping through all the elements of the json array
                    for (int i = 0; i < heroArray.length(); i++) {
                        //getting the json object of the particular index inside the array
                        JSONObject heroObject = heroArray.getJSONObject(i);

                        //creating a hero object and giving them the values from json object
//                        trains train = new trains(heroObject.getJSONObject("station").getString("name"), heroObject.getJSONObject("station").getString("code"), heroObject.getString("distance"),heroObject.getString("schdep"),heroObject.getString("scharr"));
//
//                        //adding the hero to herolist
//                        trainList.add(train);

                            trains trains = new trains(heroObject.getJSONObject("station").getString("name"), heroObject.getJSONObject("station").getString("code"), heroObject.getString("distance"),heroObject.getString("schdep"),heroObject.getString("scharr"));
                            cardArrayAdapter.add(trains);

                        listView.setAdapter(cardArrayAdapter);
                    }

                    //creating custom adapter object
//                    adapter1 = new Adapter(trainList, getApplicationContext(),R.layout.train_route);

                    //adding the adapter to listview
                    listView.setAdapter(cardArrayAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(Route.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Route.this,TrainNumberRoute.class));
            }
        });

        requestQueue.add(stringRequest);


    }
}
