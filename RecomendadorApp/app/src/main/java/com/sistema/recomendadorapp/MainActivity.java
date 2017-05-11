package com.sistema.recomendadorapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String urlRequest;
    String result = "";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = (SearchView) findViewById(R.id.search);
        searchView.setQueryHint("Buscar canciones..");
        textView = (TextView) findViewById(R.id.test);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String busqueda = query;
                String urlBase = Variables.getURLBase();
                urlRequest = urlBase + "action=Buscar&valor=" + busqueda;
//                Toast.makeText(MainActivity.this, urlRequest, Toast.LENGTH_LONG).show();
                textView.setText("Buscando...");
                new BuscarTask().execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



//        ***********************************************************
//        Prueba de conexion con Backend
//        urlRequest = urlBase + "action=Testing";
//        ***********************************************************

    }

    public class BuscarTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {

            if(result == null){
                Toast.makeText(MainActivity.this, "No hay canciones :(", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Disfrutalas ;)!!", Toast.LENGTH_SHORT).show();
                try{
                    JSONArray data = new JSONArray(result);
                    textView.setText(data.toString());
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }

//                    ***********************************************************
//                    Prueba de conexion con Backend
//                    String test = result;
//                    TextView textView = (TextView) findViewById(R.id.test);
//                    textView.setText(result);
//                    ***********************************************************
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(urlRequest);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String valueResult = bf.readLine();
                System.out.println("Result: " + valueResult);

//                result = valueResult;
                return valueResult;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
