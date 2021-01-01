package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.example.expertism.SplashActivity.MyPREFERENCES;
import static android.example.expertism.SplashActivity.TOKEN;
import static android.example.expertism.SplashActivity.USER_ID;

public class CreateProfile extends AppCompatActivity {

    Toolbar toolbar;
    EditText name, branch, yearOfGraduation;
    EditText bio;
    Button create;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.103:3000";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_profile);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        name = (EditText) findViewById(R.id.createProfileNameEditText);
        branch = (EditText) findViewById(R.id.createProfileBranchEditText);
        yearOfGraduation = (EditText) findViewById(R.id.createProfileYearEditText);
        bio = (EditText) findViewById(R.id.editTextBio);

        create = (Button) findViewById(R.id.createProfileButton);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName;
                String mBranch;
                String mYog;
                String mBio;

                mName = name.getText().toString();
                mBranch = branch.getText().toString();
                mYog = yearOfGraduation.getText().toString();
                mBio = bio.getText().toString();


                if(mBranch.isEmpty())
                {
                    branch.setError("Enter branch");
                    return;
                }
                if(mName.isEmpty())
                {
                    name.setError("Enter name");
                    return;
                }
                if(mYog.isEmpty())
                {
                    yearOfGraduation.setError("Enter year of graduation");
                    return;
                }

                if(mBio.isEmpty())
                {
                    mBio = "0_0";
                }
                proceedToUpdateProfile(mName,mBranch,mBio,mYog);

            }
        });

    }

    void proceedToUpdateProfile(String mName, String mBranch, String mBio, String mYog)
    {
        Log.i("UserProfileUpdated: ", mBranch + " " + mName + " " + mBio + " " + mYog);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name" , mName);
        map.put("bio", mBio);
        map.put("branch", mBranch);
        map.put("yearOfGraduation", mYog);
        map.put("profileCompleted", true);

        String userId = sharedPreferences.getString(USER_ID, "");
        String token = "Bearer " + sharedPreferences.getString(TOKEN, "");

        Call<HashMap<String, String>> call = retrofitInterface.executeUpdateProfile(token,userId,map);

        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                if(response.isSuccessful())
                {
                    Log.i("UpdateCreateProfile : ", response.body().get("message"));
                    Toast.makeText(CreateProfile.this, "Profile Created Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.i("UpdateCreateProfile: ", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                Log.i("UserCreatedProfileF: ", t.getLocalizedMessage());

            }
        });
    }
}