package com.samsung.fragmentexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.samsung.fragmentexample.activities.SettingsActivity;
import com.samsung.fragmentexample.fragment.CounterFragment;
import com.samsung.fragmentexample.fragment.HomeFragment;
import com.samsung.fragmentexample.fragment.NotificationFragment;

public class MainActivity extends AppCompatActivity {
    Fragment homeFragment = new HomeFragment();
    Fragment counterFragment = new CounterFragment();
    Fragment notificationFragment = new NotificationFragment();

    Fragment current = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_content, notificationFragment, "notification")
                .add(R.id.frame_content, homeFragment, "home")
                .add(R.id.frame_content, counterFragment, "counter")
                .hide(notificationFragment)
                .hide(counterFragment)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        changeFragment(homeFragment);
                        return true;
                    case R.id.navigation_counter:
                        changeFragment(counterFragment);
                        return true;
                    case R.id.navigation_notification:
                        changeFragment(notificationFragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void changeFragment(Fragment newFragment) {
//      FragmentTransaction transaction = manager.beginTransaction();
//      transaction.hide(current);
//      transaction.show(homeFragment);
//      transaction.commit();
        getSupportFragmentManager()
                .beginTransaction()
                .hide(current)
                .show(newFragment)
                .commit();
        current = newFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            intent.putExtra("my_key", 123);
            startActivityForResult(intent, 404);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(
                this,
                "requestCode:"+requestCode+"|resultCode:"+resultCode,
                Toast.LENGTH_LONG
        ).show();

        super.onActivityResult(requestCode, resultCode, data);
    }
}
