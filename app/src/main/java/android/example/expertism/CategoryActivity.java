package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.expertism.modelClasses.GetBlogsByCategoryResult;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static android.example.expertism.SplashActivity.MyPREFERENCES;
import static android.example.expertism.SplashActivity.TOKEN;
import static android.example.expertism.SplashActivity.USER_ID;

public class CategoryActivity extends AppCompatActivity implements PreviewAdapter.PreviewListener {

    private static final String TAG = "CategoryActivity";
    ArrayList<PreviewAdapter.PreviewItem> previewList = new ArrayList<>();
    ArrayList<String> subCategoryList;

    RecyclerView recyclerView;
    ChipGroup chipGroupSubCategory;
    PreviewAdapter adapter;
    ChipGroup.OnCheckedChangeListener chipCheckChangeListener;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.103:3000";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.recyclerView);
        chipGroupSubCategory = findViewById(R.id.chipGroupSubCategory);

        String category = getIntent().getStringExtra("category");

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String userId = sharedPreferences.getString(USER_ID, "");
        String token = "Bearer " + sharedPreferences.getString(TOKEN, "");

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<GetBlogsByCategoryResult> call = retrofitInterface.executeGetBlogByCategory(token,category);

        call.enqueue(new Callback<GetBlogsByCategoryResult>() {
            @Override
            public void onResponse(Call<GetBlogsByCategoryResult> call, Response<GetBlogsByCategoryResult> response) {

                if(response.isSuccessful())
                {
                    Log.i("SubCategoryList: ", response.body().subCategories.size() + "");
                    subCategoryList = response.body().subCategories;
                    prepareSubCategoryChips();
                }
            }

            @Override
            public void onFailure(Call<GetBlogsByCategoryResult> call, Throwable t) {

                Log.i("SubCategoryList: ", "Failure " + t.getLocalizedMessage());
            }
        });

       //preparePreviewList();

        adapter = new PreviewAdapter(previewList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

      //  prepareSubCategoryList();

        chipCheckChangeListener = new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Log.d(TAG, "onCheckedChanged: checked changed "+ checkedId);
              //  String s = (String) group.findViewById(checkedId).getTag();
                filterByCategory(checkedId);
            }
        };

        chipGroupSubCategory.setOnCheckedChangeListener(chipCheckChangeListener);

     //   prepareChips();

        FloatingActionButton fab = findViewById(R.id.fabCreateBlog);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, CreateBlogActivity.class));
            }
        });
    }

    private void prepareSubCategoryChips(){
        Chip chipAll = new Chip(this);
        chipAll.setTag("All");
        chipAll.setId(0);
        chipAll.setText("All");
        chipAll.setCheckable(true);
        chipAll.setClickable(true);
        chipAll.setChecked(true);
        chipGroupSubCategory.addView(chipAll);
        int id = 1;
        for(String subCategory: subCategoryList){
            Chip chip = new Chip(this);
            chip.setTag(subCategory);
            chip.setId(id);
            chip.setText(subCategory);
            chip.setCheckable(true);
            chip.setClickable(true);
            chipGroupSubCategory.addView(chip);
            id++;
        }
    }
    //hey android studio devta humne khuch shabd kotlin bhasa mai likhe hai krapya unhe accpet kare

//    private void prepareSubCategoryList() {
//        subCategoryList = new ArrayList<>();
//        subCategoryList.add(new SubCategory(0, "All"));
//        subCategoryList.add(new SubCategory(1, "Mess Refund"));
//        subCategoryList.add(new SubCategory(2, "Mess Food"));
//        subCategoryList.add(new SubCategory(3, "Movies"));
//        subCategoryList.add(new SubCategory(4, "Shopping"));
//        subCategoryList.add(new SubCategory(5, "Rooms"));
//        subCategoryList.add(new SubCategory(6, "Clubs"));
//        subCategoryList.add(new SubCategory(7, "Campus Life"));
//    }

    /*private void preparePreviewList() {
        previewList = new ArrayList<>();
        HashSet<Integer> set1 = new HashSet<>(), set2 = new HashSet<>(), set3 = new HashSet<>(), set4 = new HashSet<>();
        set1.add(1);set1.add(5);
        set2.add(2);set2.add(6);
        set3.add(3);set3.add(7);
        set4.add(4);set4.add(8);
        previewList.add(new PreviewAdapter.PreviewItem("ISM The Greatest", "Akash Mahapatra", "I love this institute, and I explain how...", set1));
        previewList.add(new PreviewAdapter.PreviewItem("The Best Club Of ISM", "Akhil Agrawal", "I absolutely love ADC, and I explain why...", set2));
        previewList.add(new PreviewAdapter.PreviewItem("ISM Culture", "Aditya Sinha", "This great institute going by the name I.S.M encompasses...", set3));
        previewList.add(new PreviewAdapter.PreviewItem("Tips for a great GPA", "Abhishek Kumar Jain", "Read more to find out what it needs to...", set4));
        previewList.add(new PreviewAdapter.PreviewItem("ISM The Greatest", "Akash Mahapatra", "I love this institute, and I explain how...", set1));
        previewList.add(new PreviewAdapter.PreviewItem("The Best Club Of ISM", "Akhil Agrawal", "I absolutely love ADC, and I explain why...", set2));
        previewList.add(new PreviewAdapter.PreviewItem("ISM Culture", "Aditya Sinha", "This great institute going by the name I.S.M encompasses...", set3));
        previewList.add(new PreviewAdapter.PreviewItem("Tips for a great GPA", "Abhishek Kumar Jain", "Read more to find out what it needs to...", set4));
        previewList.add(new PreviewAdapter.PreviewItem("ISM The Greatest", "Akash Mahapatra", "I love this institute, and I explain how...", set1));
        previewList.add(new PreviewAdapter.PreviewItem("The Best Club Of ISM", "Akhil Agrawal", "I absolutely love ADC, and I explain why...", set2));
        previewList.add(new PreviewAdapter.PreviewItem("ISM Culture", "Aditya Sinha", "This great institute going by the name I.S.M encompasses...", set3));
        previewList.add(new PreviewAdapter.PreviewItem("Tips for a great GPA", "Abhishek Kumar Jain", "Read more to find out what it needs to...", set4));
        previewList.add(new PreviewAdapter.PreviewItem("ISM The Greatest", "Akash Mahapatra", "I love this institute, and I explain how...", set1));
        previewList.add(new PreviewAdapter.PreviewItem("The Best Club Of ISM", "Akhil Agrawal", "I absolutely love ADC, and I explain why...", set2));
        previewList.add(new PreviewAdapter.PreviewItem("ISM Culture", "Aditya Sinha", "This great institute going by the name I.S.M encompasses...", set3));
        previewList.add(new PreviewAdapter.PreviewItem("Tips for a great GPA", "Abhishek Kumar Jain", "Read more to find out what it needs to...", set4));
        previewList.add(new PreviewAdapter.PreviewItem("ISM The Greatest", "Akash Mahapatra", "I love this institute, and I explain how...", set1));
        previewList.add(new PreviewAdapter.PreviewItem("The Best Club Of ISM", "Akhil Agrawal", "I absolutely love ADC, and I explain why...", set2));
        previewList.add(new PreviewAdapter.PreviewItem("ISM Culture", "Aditya Sinha", "This great institute going by the name I.S.M encompasses...", set3));
        previewList.add(new PreviewAdapter.PreviewItem("Tips for a great GPA", "Abhishek Kumar Jain", "Read more to find out what it needs to...", set4));
    }*/

    @Override
    public void onDomainClick(int position) {
        Toast.makeText(this, "Written by " + previewList.get(position).writerName, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ViewBlogActivity.class));
    }

//    void prepareChips(){
//        for(SubCategory sub: subCategoryList){
//            Chip chip = new Chip(this);
//            chip.setId(sub.ID);
//            chip.setText(sub.subCategoryName);
//            chip.setClickable(true);
//            chip.setCheckable(true);
//            if(sub.ID==0) chip.setChecked(true);
//            chipGroupSubCategory.addView(chip);
//        }
//    }

    void filterByCategory(int subCategory){
        Log.d(TAG, "filterByCategory: Filtering by "+subCategory);
        Toast.makeText(this, subCategory + "", Toast.LENGTH_SHORT).show();
        ArrayList<PreviewAdapter.PreviewItem> newList = new ArrayList<>();
        if(subCategory==-1 || subCategory==0) {
            adapter.updateData(previewList);
            return;
        }
        String subCategoryString = subCategoryList.get(subCategory);
        for(PreviewAdapter.PreviewItem previewItem: previewList){
            if(previewItem.subCategorySet.contains(subCategoryString)) {
                newList.add(previewItem);
                Log.d(TAG, "filterByCategory: adding "+previewItem.heading);
            }
        }
        adapter.updateData(newList);
    }
}