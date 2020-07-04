package com.example.bonvoyaide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bonvoyaide.adapters.CountryAdapter;
import com.example.bonvoyaide.models.Country;
import com.example.bonvoyaide.services.GetCOVData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Covid extends AppCompatActivity {

    private Spinner spinner;
    private TextView confirmed;
    private TextView recovered;
    private TextView deaths;
    private TextView active;
    private List<Country> countriesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);


        spinner = findViewById(R.id.select_country);
        confirmed = findViewById(R.id.confirmed);
        recovered = findViewById(R.id.recovered);
        deaths = findViewById(R.id.death);
        active = findViewById(R.id.active);


        try {
            String response = new GetCOVData().execute("https://api.covid19api.com/countries").get();

            JSONObject json = new JSONObject(response);
            JSONArray array = json.getJSONArray("data");

            for (int i = 0; i < array.length(); i++) {
                JSONObject countryJSON = new JSONObject(array.get(i).toString());
                Country country = new Country();
                country.setCountry(countryJSON.getString("Country"));
                country.setSlug(countryJSON.getString("Slug"));
                countriesList.add(country);
            }


            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                    this, R.layout.country, R.id.country, countriesList.stream().map(x -> x.getCountry()).sorted().collect(Collectors.toList()));

            spinnerArrayAdapter.setDropDownViewResource(R.layout.country);
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    getSelectedCountryData();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getSelectedCountryData() {
        Optional<Country> selectedCountryOptional = countriesList.stream().filter(country -> country.getCountry().equalsIgnoreCase(spinner.getSelectedItem().toString())).findFirst();
        Country selectedCountry = selectedCountryOptional.get();
        Log.e("selected",selectedCountry.getSlug());

        try{
            String response = new GetCOVData().execute("https://api.covid19api.com/country/" + selectedCountry.getSlug()).get();
            JSONObject json = new JSONObject(response);
            JSONArray array = json.getJSONArray("data");

            for (int i = 0; i < array.length(); i++) {
                JSONObject cases = new JSONObject(array.get(i).toString());
                Log.d("date", cases.getString("Date").substring(0,11));
                if(cases.getString("Date").substring(0,10).equalsIgnoreCase("2020-07-03")){
                    Log.d("Confirmed", cases.getString("Confirmed"));
                    Log.d("Deaths", cases.getString("Deaths"));
                    Log.d("Recovered", cases.getString("Recovered"));
                    confirmed.setText(cases.getString("Confirmed"));
                    recovered.setText(cases.getString("Recovered"));
                    deaths.setText(cases.getString("Deaths"));
                    active.setText(cases.getString("Active"));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
