package com.example.roomdatabaselivedata.navigation_da;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.roomdatabaselivedata.R;
import com.example.roomdatabaselivedata.navigation_da.fragment.AddFragment;
import com.example.roomdatabaselivedata.navigation_da.fragment.ProductFragment;
import com.example.roomdatabaselivedata.navigation_da.fragment.UpdateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_navigation);
        NavController navController = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.botton_navigation);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);


    }


}