package android.example.expertism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import static android.example.expertism.HomeCategoryAdapter.AI;
import static android.example.expertism.HomeCategoryAdapter.ANDROID;
import static android.example.expertism.HomeCategoryAdapter.CONTENT;
import static android.example.expertism.HomeCategoryAdapter.ML;
import static android.example.expertism.HomeCategoryAdapter.OTHERS;
import static android.example.expertism.HomeCategoryAdapter.UIUX;
import static android.example.expertism.HomeCategoryAdapter.VIDEO;
import static android.example.expertism.HomeCategoryAdapter.WEB;

public class HomeActivity extends AppCompatActivity implements HomeCategoryAdapter.HomeListener {

    ArrayList<HomeCategoryAdapter.HomeItem> list;
    RecyclerView recyclerView;
    HomeCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        list.add(new HomeCategoryAdapter.HomeItem("App Development", R.mipmap.app_dev,ANDROID));
        list.add(new HomeCategoryAdapter.HomeItem("Web Development", R.mipmap.web, WEB));
        list.add(new HomeCategoryAdapter.HomeItem("Machine Learning", R.mipmap.ml, ML));
        list.add(new HomeCategoryAdapter.HomeItem("Artificial Intelligence", R.mipmap.artificial_intelligence, AI));
        list.add(new HomeCategoryAdapter.HomeItem("Content Writing", R.mipmap.content, CONTENT));
        list.add(new HomeCategoryAdapter.HomeItem("UI/UX Design", R.mipmap.ui, UIUX));
        list.add(new HomeCategoryAdapter.HomeItem("Video Editing", R.mipmap.video, VIDEO));
        list.add(new HomeCategoryAdapter.HomeItem("Others", R.mipmap.misc, OTHERS));
        adapter = new HomeCategoryAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
    }

    @Override
    public void onDomainClick(int position) {
        Toast.makeText(this, list.get(position).itemName, Toast.LENGTH_SHORT).show();
    }
}