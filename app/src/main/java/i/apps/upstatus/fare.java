package i.apps.upstatus;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class fare extends AppCompatActivity {

    EditText no,src,dest,age,date,prefclass,quota;
    ImageView fare;
    EditText edittext;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);

        getSupportActionBar().hide();

        no = findViewById(R.id.et_number);
        src = findViewById(R.id.src);
        date = findViewById(R.id.date);
        edittext= (EditText) findViewById(R.id.date);
        myCalendar = Calendar.getInstance();

        fare = findViewById(R.id.btn_fare);

        fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(no.getText().toString().isEmpty() && src.getText().toString().isEmpty() && date.getText().toString().isEmpty()){
                    no.setError("Invalid Number");
                    src.setError("Invalid Code");
                    date.setError("Invalid Date");


                }

                else{

                    Intent intent = new Intent(fare.this, checkFare.class);
                    Bundle extras = new Bundle();
                    extras.putString("train_number",no.getText().toString());
                    extras.putString("src",src.getText().toString());
                    extras.putString("date",date.getText().toString());

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
                new DatePickerDialog(fare.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
}
