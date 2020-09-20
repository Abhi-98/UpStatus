package i.apps.upstatus;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;

    EditText et_phone;
    ImageView send_otp_btn;
    ProgressBar pb_bar;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, Home.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        et_phone = findViewById(R.id.et_phone);
        send_otp_btn = findViewById(R.id.send_otp_btn);
        pb_bar = findViewById(R.id.pb_bar);

        pb_bar.setVisibility(View.GONE);

        send_otp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = et_phone.getText().toString().trim();
                if(phoneNumber.isEmpty()){
                    et_phone.setError("Invalid Phone Number");
                }else {
                    pb_bar.setVisibility(View.VISIBLE);
                    sendVerificationCode(phoneNumber);
                }
            }
        });

    }

    private void sendVerificationCode(String phoneNumber){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCall
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCall = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            pb_bar.setVisibility(View.GONE);
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

            pb_bar.setVisibility(View.GONE);
            String mVerificationId = verificationId;
            Intent i = new Intent(Login.this , OtpActivity.class);
            i.putExtra("verificationId" , mVerificationId);
            startActivity(i);
            finish();
        }
    };


}


 
        
        

   
