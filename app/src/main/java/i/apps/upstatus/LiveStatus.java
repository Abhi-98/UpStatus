package i.apps.upstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class LiveStatus extends AppCompatActivity {

    String train_no, stn_code, date;
    TextView from,status,tname,sch,act,reach;
    String api_key = "r2s5mbrs5r";
    String base_url = "https://api.railwayapi.com/v2/live/train/";
    private ProgressBar pgsBar;


    TextView stn, pos, name, dep, actDep, actArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_status);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        train_no = extras.getString("train_number");
        stn_code = extras.getString("stn_code");
        date = extras.getString("date");

        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        stn = findViewById(R.id.stn);
        pos = findViewById(R.id.pos);
        name = findViewById(R.id.name);
        dep = findViewById(R.id.dep);
        actDep = findViewById(R.id.actDep);
        actArr = findViewById(R.id.actArrival);

        from = findViewById(R.id.txtStn);
        status = findViewById(R.id.txtPos);
        tname = findViewById(R.id.txtName);
        sch = findViewById(R.id.txtDep);
        act = findViewById(R.id.txtActDep);
        reach = findViewById(R.id.txtActArrival);






        String complete_url = base_url + train_no + "/station/" + stn_code + "/date/" + date + "/apikey/" + api_key + "/";

        Log.i("URLLL",complete_url);

        hide();

        pgsBar.setVisibility(VISIBLE);
        pgsBar.setTag("Loading..");


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, complete_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                pgsBar.setVisibility(INVISIBLE);
                unhide();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject json2 = jsonObject.getJSONObject("station");

                    JSONObject trainName = jsonObject.getJSONObject("train");
                    JSONObject status = jsonObject.getJSONObject("status");

                    String name1 = (String) trainName.get("name");
                    String stn1 = (String) json2.get("name");
                    String status1 = (String) status.get("schdep");
                    String accDep = (String)status.get("actdep");
                    Boolean hasArr = status.getBoolean("has_arrived");

                    stn.setText(stn1);
                    pos.setText((String) jsonObject.get("position"));
                    name.setText(name1);
                    dep.setText(status1);
                    actDep.setText(accDep);
                    actArr.setText(hasArr.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(LiveStatus.this, "Something went wrong. Try again !", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LiveStatus.this,Home.class));
    }

    public void hide(){
        from.setVisibility(INVISIBLE);
        status.setVisibility(INVISIBLE);
        tname.setVisibility(INVISIBLE);
        sch.setVisibility(INVISIBLE);
        act.setVisibility(INVISIBLE);
        reach.setVisibility(INVISIBLE);

        stn.setVisibility(INVISIBLE);
        pos.setVisibility(INVISIBLE);
        name.setVisibility(INVISIBLE);
        dep.setVisibility(INVISIBLE);
        actDep.setVisibility(INVISIBLE);
        actArr.setVisibility(INVISIBLE);

    }

    public void unhide(){
        from.setVisibility(VISIBLE);
        status.setVisibility(VISIBLE);
        tname.setVisibility(VISIBLE);
        sch.setVisibility(VISIBLE);
        act.setVisibility(VISIBLE);
        reach.setVisibility(VISIBLE);

        stn.setVisibility(VISIBLE);
        pos.setVisibility(VISIBLE);
        name.setVisibility(VISIBLE);
        dep.setVisibility(VISIBLE);
        actDep.setVisibility(VISIBLE);
        actArr.setVisibility(VISIBLE);

    }
}
