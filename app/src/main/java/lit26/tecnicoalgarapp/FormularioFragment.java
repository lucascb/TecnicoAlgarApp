package lit26.tecnicoalgarapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lucas on 04/03/17.
 */

public class FormularioFragment extends Fragment {
    private Button buttonCriar;
    private Button buttonCancelar;
    private EditText editTextRua;
    private EditText editTextNumero;
    private EditText editTextBairro;
    private EditText editTextCidade;

    public View onCreateView (LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.fragment_formulario, viewGroup, false);

        buttonCriar = (Button) view.findViewById(R.id.buttonCriar);
        buttonCancelar = (Button) view.findViewById(R.id.buttonCancelar);
        editTextRua = (EditText) view.findViewById(R.id.editTextRua);
        editTextNumero = (EditText) view.findViewById(R.id.editTextNumero);
        editTextBairro = (EditText) view.findViewById(R.id.editTextBairro);
        editTextCidade = (EditText) view.findViewById(R.id.editTextCidade);

        buttonCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                LatLng location = getLocationFromAddress(getContext(), editTextRua.getText().toString() +
                    " " + editTextNumero.getText().toString() + " " + editTextCidade.getText().toString());
                Log.i("TESTE", location.latitude + " " + location.longitude);

                Demand demand = new Demand(4, 1, "Lucas", location.latitude, location.longitude);
                Call<JsonObject> callPost = ArtecApplication.getApi().postDemand(demand);
                callPost.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            Log.i("TESTE", "Foi");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> addresses;
        LatLng location = null;

        try {
            addresses = coder.getFromLocationName(strAddress, 5);
            if (addresses == null) {
                return null;
            }
            Address address = addresses.get(0);
            location = new LatLng(address.getLatitude(), address.getLongitude());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return location;
    }

}
