package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignUpActivity extends AppCompatActivity {

    TextView signUpEmailId, signUpPass, signUpConfirmPass;
    FloatingActionButton nextSignUPBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        signUpEmailId = (TextView) findViewById(R.id.signUpEmailId);
        signUpPass = (TextView) findViewById(R.id.signUpPassword);
        signUpConfirmPass = (TextView) findViewById(R.id.signUpCnfrmPassword);
        nextSignUPBtn = (FloatingActionButton) findViewById(R.id.nextSignUp);

        //setting up onclick listener to the next floating btn
        nextSignUPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = signUpEmailId.getText().toString();
                String password = signUpPass.getText().toString();
                String cnfrmPassword = signUpConfirmPass.getText().toString();

                if(email.isEmpty())
                {
                    signUpEmailId.setError("Enter a valid email address");
                    signUpEmailId.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    signUpEmailId.setError("Enter a valid email address");
                }

                if(password.isEmpty())
                {
                    signUpPass.setError("Password is required");
                    signUpPass.requestFocus();
                    return;
                }

                if(password.length() < 6 )
                {
                    signUpPass.setError("Minimum length is 6");
                }

                if(!cnfrmPassword.equals(password))
                {
                    signUpConfirmPass.setError("Passwords do not match");
                    return;
                }

                Toast.makeText(SignUpActivity.this, "All Okk", Toast.LENGTH_SHORT).show();
            }
        });


    }
}