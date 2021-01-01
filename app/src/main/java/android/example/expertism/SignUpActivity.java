package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.expertism.modelClasses.LoginResult;
import android.example.expertism.modelClasses.SignupResult;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    TextView signUpEmailId, signUpPass, signUpConfirmPass;
    FloatingActionButton nextSignUPBtn;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.103:3000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

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

//                Toast.makeText(SignUpActivity.this, "All Okk", Toast.LENGTH_SHORT).show();
                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("password", password);

                Call<SignupResult> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<SignupResult>() {
                    @Override
                    public void onResponse(Call<SignupResult> call, Response<SignupResult> response) {
                       if(!response.isSuccessful())
                       {
                           Toast.makeText(SignUpActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                           Log.i("Signup: ", response.message());
                           return;
                       }

                       String message = response.message();

                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this,HomeActivity.class);
                        startActivity(intent);
                        Log.i("SignUp: ",message);
                    }

                    @Override
                    public void onFailure(Call<SignupResult> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("SignupF", t.getMessage());
                    }
                });
            }
        });


    }
}