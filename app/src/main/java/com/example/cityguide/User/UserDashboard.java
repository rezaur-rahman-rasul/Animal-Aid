package com.example.cityguide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cityguide.Common.LoginSignup.RetailerStartUpScreen;
import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.cityguide.HelperClasses.HomeAdapter.MostViewAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.MostViewHelperClass;
import com.example.cityguide.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Variables
    static final float END_SCALE = 0.7f;
    RecyclerView featureRecycler, mostViewRecycler, categoriesRecycler;
    FeaturedAdapter adapter;
    MostViewAdapter adapter1;
    CategoriesAdapter adapter2;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);
        //Hooks
        featureRecycler = findViewById(R.id.featured_recycler);
        mostViewRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content_view);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        //Navigation Drawer
        navigationDrawer();

        //Recycler Views Function Calls
        featureRecycler();
        mostViewRecycler();
        categoriesRecycler();
    }

    //Navigation Drawer Functions
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
//        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    public void callRetailerScreens(View view) {
        Intent intent = new Intent(getApplicationContext(), RetailerStartUpScreen.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_all_categories:
                Intent intent = new Intent(getApplicationContext(), AllCategories.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    //Recycler Views Functions
    private void featureRecycler() {
        featureRecycler.setHasFixedSize(true);
        featureRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.kfc_image, "KFC", "Shelled with a giant piece of Hot and Crispy chicken fillet, layered with freshly chopped"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.kfc_image, "KFC", "Shelled with a giant piece of Hot and Crispy chicken fillet, layered with freshly chopped"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.kfc_image, "KFC", "Shelled with a giant piece of Hot and Crispy chicken fillet, layered with freshly chopped"));

        adapter = new FeaturedAdapter(featuredLocations);
        featureRecycler.setAdapter(adapter);

        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }

    private void mostViewRecycler() {
        mostViewRecycler.setHasFixedSize(true);
        mostViewRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewHelperClass> mostViewLocations = new ArrayList<>();
        mostViewLocations.add(new MostViewHelperClass(R.drawable.kfc_image, "KFC", "Shelled with a giant piece of Hot and Crispy chicken fillet, layered with freshly chopped"));
        mostViewLocations.add(new MostViewHelperClass(R.drawable.kfc_image, "KFC", "Shelled with a giant piece of Hot and Crispy chicken fillet, layered with freshly chopped"));
        mostViewLocations.add(new MostViewHelperClass(R.drawable.kfc_image, "KFC", "Shelled with a giant piece of Hot and Crispy chicken fillet, layered with freshly chopped"));

        adapter1 = new MostViewAdapter(mostViewLocations);
        mostViewRecycler.setAdapter(adapter1);

        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});

    }

    private void categoriesRecycler() {
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> categoriesLocations = new ArrayList<>();
        categoriesLocations.add(new CategoriesHelperClass( "Restaurant", R.drawable.restaurant_image));
        categoriesLocations.add(new CategoriesHelperClass("Restaurant", R.drawable.restaurant_image));
        categoriesLocations.add(new CategoriesHelperClass("Restaurant", R.drawable.restaurant_image));

        adapter2 = new CategoriesAdapter(categoriesLocations);
        categoriesRecycler.setAdapter(adapter2);

        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
        //All Gradients
//        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
//        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
//        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
//        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
//        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
//        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, "Education", R.drawable.school_image));
//        categoriesHelperClasses.add(new CategoriesHelperClass(gradient2, "HOSPITAL", R.drawable.hospital_image));
//        categoriesHelperClasses.add(new CategoriesHelperClass(gradient3, "Restaurant", R.drawable.restaurant_image));
//        categoriesHelperClasses.add(new CategoriesHelperClass(gradient4, "Shopping", R.drawable.shopping_image));
//        categoriesHelperClasses.add(new CategoriesHelperClass(gradient1, "Transport", R.drawable.transport_image));
//        categoriesRecycler.setHasFixedSize(true);
//        adapter2 = new CategoriesAdapter(categoriesHelperClasses);
//        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        categoriesRecycler.setAdapter(adapter);
//    }
    }

}