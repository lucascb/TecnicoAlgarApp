package lit26.tecnicoalgarapp;

import android.app.Dialog;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.LogRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private GoogleMap mMap;

    private FrameLayout container;
    private List<Demand> demandas;
    private int atual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        atual = 0;
        carregarDemandas();

        container = (FrameLayout) findViewById(R.id.mainContainer);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Demand prox = proximaDemanda();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(prox.getLatitude(), prox.getLongitude())));
                Call<JsonObject> callGet = ArtecApplication.getApi().notificaProximo(prox.getCliente_id());
                callGet.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Entrando em contato com o próximo cliente", Toast.LENGTH_SHORT).show();
                            //Snackbar.make(view, "Entrando em contato com o próximo cliente", Snackbar.LENGTH_LONG)
                            //       .setAction("Action", null).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onResume() {
        super.onResume();


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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            container.setVisibility(View.GONE);
            carregarDemandas();
        } else if (id == R.id.nav_gallery) {
            container.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new FormularioFragment())
            .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the came
        LatLng centro = new LatLng(-18.910549447195713, -48.2623815536499);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Algar Telecom"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centro));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));

    }

    public Demand proximaDemanda() {
        atual += (atual + 1) % demandas.size();
        if (demandas.get(0) == null) {
            atual += (atual + 1) % demandas.size();
        }
        return demandas.get(atual);
    }

    public void carregarDemandas() {
        Call<List<Demand>> getCall = ArtecApplication.getApi().getDemands();
        getCall.enqueue(new Callback<List<Demand>>() {
            @Override
            public void onResponse(Call<List<Demand>> call, Response<List<Demand>> response) {
                if (response.isSuccessful()) {
                    Log.i("HEADER", response.headers().toString());
                    Log.i("BODY", response.body().toString());

                    demandas = response.body();
                    mMap.clear();
                    for (Demand d : response.body()) {
                        if (d != null)  {
                            Log.i("Demanda: ", d.toString());
                            LatLng l = new LatLng(d.getLatitude(), d.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(l).title(d.getDescricao()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Demand>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
