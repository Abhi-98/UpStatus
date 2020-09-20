package i.apps.upstatus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    EditText et_checkStatus,et_stn_code,et_date;
    ImageView bt_checkStatus;
    String api_key = "YOUR_API_KEY";
    EditText edittext;
    Calendar myCalendar;
    String base_url = "https://api.railwayapi.com/v2/live/train/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        et_checkStatus = findViewById(R.id.et_pnr);
        et_stn_code = findViewById(R.id.et_stn_code);
        et_date = findViewById(R.id.et_date);
        bt_checkStatus = findViewById(R.id.bt_checkPnr);
        edittext= (EditText) findViewById(R.id.et_date);
        myCalendar = Calendar.getInstance();




        bt_checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(et_checkStatus.getText().toString().isEmpty() && et_stn_code.getText().toString().isEmpty() && et_date.getText().toString().isEmpty()){
                    et_checkStatus.setError("Invalid Number");
                    et_stn_code.setError("Invalid Code");
                    et_date.setError("Invalid Date");


                }

                else{

                    Intent i = new Intent(MainActivity.this,LiveStatus.class);
                    startActivity(i);

                    Intent intent = new Intent(MainActivity.this, LiveStatus.class);
                    Bundle extras = new Bundle();
                    extras.putString("train_number",et_checkStatus.getText().toString());
                    extras.putString("stn_code",et_stn_code.getText().toString());
                    extras.putString("date",et_date.getText().toString());

                    intent.putExtras(extras);
                    startActivity(intent);
                }





            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this,Home.class));
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, Login.class));
        finish();
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
}
