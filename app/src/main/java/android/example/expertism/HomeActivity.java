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
        list.add(new HomeCategoryAdapter.HomeItem("Coding-Dev", R.mipmap.app_dev,ANDROID));
        list.add(new HomeCategoryAdapter.HomeItem("Academics", R.mipmap.web, WEB));
        list.add(new HomeCategoryAdapter.HomeItem("Placements", R.mipmap.ml, ML));
        list.add(new HomeCategoryAdapter.HomeItem("Hostel", R.mipmap.artificial_intelligence, AI));
        list.add(new HomeCategoryAdapter.HomeItem("Clubs", R.mipmap.content, CONTENT));
        list.add(new HomeCategoryAdapter.HomeItem("Personalised", R.mipmap.ui, UIUX));
        list.add(new HomeCategoryAdapter.HomeItem("Bookmarks", R.mipmap.video, VIDEO));
        list.add(new HomeCategoryAdapter.HomeItem("Miscellaneous", R.mipmap.misc, OTHERS));
//        list.add(new HomeCategoryAdapter.HomeItem("Personalized Blogs", R.mipmap.misc, OTHERS));
//        list.add(new HomeCategoryAdapter.HomeItem("Bookmarks", R.mipmap.misc, OTHERS));
        displayRecyclerView();
    }

    private void setUpToolbarNavigation() {
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.MyProfile:
                        Intent intent = new Intent(HomeActivity.this,MyProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.developers:
                        Intent intent1 = new Intent(HomeActivity.this,DevelopersActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.Ratings:
                        //Intent intent2 = new Intent(mContext, ProjectActivity.class);
                       // startActivity(intent2);
                        Toast.makeText(HomeActivity.this, "Rate Us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.LogOut:
                       // Intent intent3 = new Intent(mContext, AchievementActivity.class);
                       // startActivity(intent3);
                        Toast.makeText(HomeActivity.this, "Log Out", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.AboutUs:
                       // Intent intent4 = new Intent(mContext,ResumeActivity.class);
                       // startActivity(intent4);
                        Toast.makeText(HomeActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Share:
                        //Intent intent5 = new Intent(mContext,ContactActivity.class);
                        //startActivity(intent5);
                        Toast.makeText(HomeActivity.this, "Share", Toast.LENGTH_SHORT).show();
                        break;


                }
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
        if(list.get(position).itemName.equals("Personalized Blogs")){
            startActivity(new Intent(this, PersonalizedActivity.class));
            return;
        }
        if(list.get(position).itemName.equals("Bookmarks")){
            startActivity(new Intent(this, PersonalizedActivity.class));
            return;
        }
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", list.get(position).itemName);
        startActivity(intent);
    }
}