package com.uas.uas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.uas.uas.activity.AboutActivity;
import com.uas.uas.activity.AirActivity;
import com.uas.uas.activity.DataActivity;
import com.uas.uas.activity.GamesActivity;
import com.uas.uas.activity.IndihomeActivity;
import com.uas.uas.activity.ListrikActivity;
import com.uas.uas.activity.PulsaActivity;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);


        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent (mainGrid);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu1:
                Intent x = new Intent(this, NotesActivity.class);
                startActivity(x);
                return true;

            case R.id.menu2:
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
                return true;

            case R.id.logout:
                final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setTitle("Keluar");
                builder.setMessage("Apa anda yakin ingin keluar ??");
                builder.setPositiveButton("Ya. Keluar Sekarang!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();

                    }
                });
                builder.setNegativeButton("Tidak Sekarang", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                return true;
        }
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item for main grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Merubah warna background
                        cardView.setBackgroundColor(Color.parseColor("#1dce6c"));
                        Toast.makeText(DashboardActivity.this, "State: True", Toast.LENGTH_SHORT).show();
                    } else {
                        cardView.setBackgroundColor(Color.parseColor("#ffffff"));
                        Toast.makeText(DashboardActivity.this, "State: False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item for main grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //All child item is cardView, just cast object to cardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0 && cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        Intent intent = new Intent(DashboardActivity.this, PulsaActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 1 && cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        Intent intent = new Intent(DashboardActivity.this, DataActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 2 && cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        Intent intent = new Intent(DashboardActivity.this, GamesActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 3 && cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        Intent intent = new Intent(DashboardActivity.this, ListrikActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 4 && cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        Intent intent = new Intent(DashboardActivity.this, AirActivity.class);
                        startActivity(intent);
                    }
                    else if (finalI == 5 && cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        Intent intent = new Intent(DashboardActivity.this, IndihomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(DashboardActivity.this, "Please set activity for this card item", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.pulsa) {
            // Handle the camera action
            Intent intent = new Intent(DashboardActivity.this, PulsaActivity.class);
            startActivity(intent);

        } else if (id == R.id.data) {
            Intent intent = new Intent(DashboardActivity.this, DataActivity.class);
            startActivity(intent);

        } else if (id == R.id.games) {
            Intent intent = new Intent(DashboardActivity.this, GamesActivity.class);
            startActivity(intent);

        } else if (id == R.id.listrik) {
            Intent intent = new Intent(DashboardActivity.this, ListrikActivity.class);
            startActivity(intent);

        } else if (id == R.id.air) {
            Intent intent = new Intent(DashboardActivity.this, AirActivity.class);
            startActivity(intent);

        } else if (id == R.id.indihome) {
            Intent intent = new Intent(DashboardActivity.this, IndihomeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(DashboardActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(DashboardActivity.this, MenuActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
            builder.setTitle("Keluar");
            builder.setMessage("Apa anda yakin ingin keluar ??");
            builder.setPositiveButton("Ya. Keluar Sekarang!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    finish();
                }
            });
            builder.setNegativeButton("Tidak Sekarang", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
