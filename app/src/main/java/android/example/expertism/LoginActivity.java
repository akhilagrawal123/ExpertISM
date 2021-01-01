package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.expertism.modelClasses.LoginResult;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.example.expertism.SplashActivity.MyPREFERENCES;
import static android.example.expertism.SplashActivity.TOKEN;
import static android.example.expertism.SplashActivity.USER_ID;

public class LoginActivity extends AppCompatActivity {

      TextView loginEmailId , txtSignUp;
      TextView loginPass;
      FloatingActionButton nextButton;
      private Retrofit retrofit;
      private RetrofitInterface retrofitInterface;
      private String BASE_URL = "http://192.168.0.103:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

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

//               Toast.makeText(LoginActivity.this, "All Okk", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

             HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("password", password);

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if(!response.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Login Failure Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            Log.i("Login: ", "Login Failure");
                            return;
                        }

                        String message = response.message();

                        Boolean profileCompleted = response.body().getProfileCompleted();
                        String profile = Boolean.toString(profileCompleted);
                        Log.i("Login Profile Status : " , profile);

                        editor.putString(USER_ID, response.body().id);
                        editor.putString(TOKEN, response.body().token);
                        editor.commit();

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        Log.i("Login: ",message);

                        if(profileCompleted)
                        {
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(LoginActivity.this,CreateProfile.class);
                            startActivity(intent);
                        }



                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("Login: ", t.getMessage());
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