package i.apps.upstatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TrainNumberRoute extends AppCompatActivity {

    ImageView route;
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_number_route);
        getSupportActionBar().hide();

        route = findViewById(R.id.btn_route);
        number = findViewById(R.id.et_number);

        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(number.getText().toString().isEmpty() || number.getText().toString().length() < 5){
                    number.setError("Invalid Number");
                }else{
                    Intent intent = new Intent(TrainNumberRoute.this, Route.class);
                    Bundle extras = new Bundle();
                    extras.putString("train_number",number.getText().toString());

                    intent.putExtras(extras);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TrainNumberRoute.this,Home.class));
    }
}
