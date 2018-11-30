package com.webakruti.kamgarchowk.kamgarUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.fragments.CategoryKamgarFragment;
import com.webakruti.kamgarchowk.kamgarUI.fragments.DocumentsFragment;
import com.webakruti.kamgarchowk.kamgarUI.fragments.HomeOrProfileFragment;
import com.webakruti.kamgarchowk.kamgarUI.fragments.SubscriptionPlansFragment;
import com.webakruti.kamgarchowk.userUI.fragments.CategoryFragment;
import com.webakruti.kamgarchowk.userUI.fragments.SupportFragment;

public class HomeOrProfileActivity extends AppCompatActivity {
    private Toolbar toolbarKamgar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationViewKamgar;
    private FragmentManager fragManager;
    private TextView toolbarKamgarDetailsHomeTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_or_profile);

        toolbarKamgar = (Toolbar) findViewById(R.id.toolbarKamgar);
        setSupportActionBar(toolbarKamgar);

        toolbarKamgarDetailsHomeTitle = (TextView) findViewById(R.id.toolbarKamgarDetailsHomeTitle);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_kamgar);
        navigationViewKamgar = (NavigationView) findViewById(R.id.navigationViewKamgar);

        View headerLayout = navigationViewKamgar.getHeaderView(0);

        Menu menu = navigationViewKamgar.getMenu();

        MenuItem navigationLogout = menu.findItem(R.id.navigationLogout);

        navigationViewKamgar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {

                    case R.id.navigationHomeOrProfile:
                        toolbarKamgarDetailsHomeTitle.setText("Kamgar Chowk");
                        // toolbarStudentDetailsHomeTitle.setText("My details");
                        // SwachhataKendraFragment fragment = new SwachhataKendraFragment();
                        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new HomeOrProfileFragment()).commit();
                        break;


                    case R.id.navigationCategory:
                        toolbarKamgarDetailsHomeTitle.setText("Kamgar Category");
                        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new CategoryKamgarFragment()).commit();
                        break;

                    case R.id.navigationMyOrders:
                        toolbarKamgarDetailsHomeTitle.setText("My Orders");
                        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new HomeOrProfileFragment()).commit();
                        break;

                   /* case R.id.navigationMyProfile:
                        toolbarKamgarDetailsHomeTitle.setText("My Profile");
                        // toolbarStudentDetailsHomeTitle.setText("My details");
                        // SwachhataKendraFragment fragment = new SwachhataKendraFragment();
                        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new HomeOrProfileFragment()).commit();
                        break;*/

                    case R.id.navigationDocuments:
                        toolbarKamgarDetailsHomeTitle.setText("Documents");
                        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new DocumentsFragment()).commit();
                        break;


                    case R.id.navigationSubcsriptionPlans:
                        toolbarKamgarDetailsHomeTitle.setText("Subcsription Plans");
                        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new SubscriptionPlansFragment()).commit();
                        break;

                    case R.id.navSupport:
                        toolbarKamgarDetailsHomeTitle.setText("Support");
                        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new SupportFragment()).commit();
                        break;



                    case R.id.navigationLogout:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeOrProfileActivity.this);
                        // Setting Dialog Title
                        alertDialog.setTitle("Logout");
                        // Setting Dialog Message
                        alertDialog.setMessage("Are you sure to logout?");
                        // Setting Icon to Dialog
                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // SharedPreferenceManager.clearPreferences();
                                Intent intent = new Intent(HomeOrProfileActivity.this, KamgarLoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        });
                        // Setting Negative "NO" Button
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        // Showing Alert Message
                        alertDialog.show();


                        break;
                }
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbarKamgar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.home_container_kamgar, new HomeOrProfileFragment()).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

}
