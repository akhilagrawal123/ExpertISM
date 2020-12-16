package android.example.expertism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

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
    HomeCategoryAdapter adapter;

    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        setUpToolbarNavigation();

        list = new ArrayList<>();
        list.add(new HomeCategoryAdapter.HomeItem("App Development", R.mipmap.app_dev,ANDROID));
        list.add(new HomeCategoryAdapter.HomeItem("Web Development", R.mipmap.web, WEB));
        list.add(new HomeCategoryAdapter.HomeItem("Machine Learning", R.mipmap.ml, ML));
        list.add(new HomeCategoryAdapter.HomeItem("Artificial Intelligence", R.mipmap.artificial_intelligence, AI));
        list.add(new HomeCategoryAdapter.HomeItem("Content Writing", R.mipmap.content, CONTENT));
        list.add(new HomeCategoryAdapter.HomeItem("UI/UX Design", R.mipmap.ui, UIUX));
        list.add(new HomeCategoryAdapter.HomeItem("Video Editing", R.mipmap.video, VIDEO));
        list.add(new HomeCategoryAdapter.HomeItem("Others", R.mipmap.misc, OTHERS));

        displayRecyclerView();
    }

    private void setUpToolbarNavigation() {
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_open);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void displayRecyclerView() {
        adapter = new HomeCategoryAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
    }

    @Override
    public void onDomainClick(int position) {
        Toast.makeText(this, list.get(position).itemName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}