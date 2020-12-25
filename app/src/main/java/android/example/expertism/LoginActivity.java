package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

      TextView loginEmailId , txtSignUp;
      TextView loginPass;
      FloatingActionButton nextButton;
      private Retrofit retrofit;
      private RetrofitInterface retrofitInterface;
      private String BASE_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         retrofitInterface = retrofit.create(RetrofitInterface.class);

        loginEmailId = (TextView) findViewById(R.id.loginEmailId);
        loginPass = (TextView) findViewById(R.id.loginPassword);
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        nextButton = (FloatingActionButton) findViewById(R.id.nextLogin);

        //setting the text "signup" underline.........
        SpannableString content = new SpannableString("Sign Up");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txtSignUp.setText(content);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpeningSignUpActivity();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmailId.getText().toString();
                String password = loginPass.getText().toString();

                if(email.isEmpty())
                {
                    loginEmailId.setError("Enter a valid email address");
                    loginEmailId.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    loginEmailId.setError("Enter a valid email address");
                    return;
                }

                if(password.isEmpty())
                {
                    loginPass.setError("Password is required");
                    loginPass.requestFocus();
                    return;
                }

                if(password.length() < 6 )
                {
                    loginPass.setError("Minimum length is 6");
                    return;
                }

//                Toast.makeText(LoginActivity.this, "All Okk", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("password", password);

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //method to open sign up activity.......
    public void OpeningSignUpActivity()
    {
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}