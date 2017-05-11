package com.sistema.recomendadorapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;

import Model.Cancion;

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

//        ListView list = (ListView) findViewById(R.id.lista_canciones);
//        final ListViewAdapter listAdapter = new ListViewAdapter(this, arrayList);
//        list.setAdapter(listAdapter);

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

    class ListViewAdapter  extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        private ArrayList<Cancion> cancioneslist;

        public ListViewAdapter(Context context, ArrayList<Cancion> cancionesList) {
            this.context = context;
            this.cancioneslist = cancionesList;
            inflater = LayoutInflater.from(context);
            this.cancioneslist = new ArrayList<Cancion>();
        }

        class ViewHolder {
            TextView nombre;
        }

        @Override
        public int getCount() {
            return this.cancioneslist.size();
        }

        @Override
        public Object getItem(int position) {
            return this.cancioneslist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_item, null);
                // Locate the TextViews in list_items.xml
                holder.nombre = (TextView) convertView.findViewById(R.id.nombre_cancion);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // Set the results into TextViews
            holder.nombre.setText(cancioneslist.get(position).getNombre());
            return convertView;
        }
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
