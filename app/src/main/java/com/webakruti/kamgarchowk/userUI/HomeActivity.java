package com.webakruti.kamgarchowk.userUI;

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
import com.webakruti.kamgarchowk.userUI.fragments.CategoryFragment;
import com.webakruti.kamgarchowk.userUI.fragments.HomeFragment;
import com.webakruti.kamgarchowk.userUI.fragments.MyEnquiryFragment;
import com.webakruti.kamgarchowk.userUI.fragments.MyProfileFragment;
import com.webakruti.kamgarchowk.userUI.fragments.SupportFragment;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

public class HomeActivity extends AppCompatActivity {
//    private ImageView imageViewBack;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FragmentManager fragManager;
    private TextView toolbarUserDetailsHomeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarUserDetailsHomeTitle = (TextView) findViewById(R.id.toolbarUserDetailsHomeTitle);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        SharedPreferenceManager.setApplicationContext(HomeActivity.this);

        View headerLayout = navigationView.getHeaderView(0);

        Menu menu = navigationView.getMenu();

        MenuItem navigationLogout = menu.findItem(R.id.navigationLogout);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {

                    case R.id.navigationHome:
                        toolbarUserDetailsHomeTitle.setText("Kamgar Chowk");
                        // toolbarStudentDetailsHomeTitle.setText("My details");
                        // SwachhataKendraFragment fragment = new SwachhataKendraFragment();
                        fragManager.beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();
                        break;


                    case R.id.navigationCategory:
                        toolbarUserDetailsHomeTitle.setText("Kamgar Category");
                        fragManager.beginTransaction().replace(R.id.home_container, new CategoryFragment()).commit();
                        break;

                    case R.id.navigationMyProfile:
                        toolbarUserDetailsHomeTitle.setText("My Profile");
                        // toolbarStudentDetailsHomeTitle.setText("My details");
                        // SwachhataKendraFragment fragment = new SwachhataKendraFragment();
                        fragManager.beginTransaction().replace(R.id.home_container, new MyProfileFragment()).commit();
                        break;

                    /*case R.id.navigationDocuments:
                        toolbarUserDetailsHomeTitle.setText("Documents");
                        fragManager.beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();
                        break;
*/
                    case R.id.navigationMyEnquiry:
                        toolbarUserDetailsHomeTitle.setText("My Enquiry");
                        fragManager.beginTransaction().replace(R.id.home_container, new MyEnquiryFragment()).commit();
                        break;

                    /*case R.id.navigationSubcsriptionPlans:
                        toolbarUserDetailsHomeTitle.setText("Subcsription Plans");
                        fragManager.beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();
                        break;*/

                    case R.id.navSupport:
                        toolbarUserDetailsHomeTitle.setText("Support");
                        fragManager.beginTransaction().replace(R.id.home_container, new SupportFragment()).commit();
                        break;



                    case R.id.navigationLogout:
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
                        // Setting Dialog Title
                        alertDialog.setTitle("Logout");
                        // Setting Dialog Message
                        alertDialog.setMessage("Are you sure to logout?");
                        // Setting Icon to Dialog
                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceManager.clearPreferences();
                                Intent intent = new Intent(HomeActivity.this, UserLoginActivity.class);
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

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
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
        fragManager.beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();

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
