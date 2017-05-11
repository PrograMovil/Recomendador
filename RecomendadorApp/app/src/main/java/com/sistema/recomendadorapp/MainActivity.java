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
    ListView mListV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListV=(ListView) findViewById(R.id.lista_canciones);
        SearchView searchView = (SearchView) findViewById(R.id.search);
        searchView.setQueryHint("Buscar canciones..");
        textView = (TextView) findViewById(R.id.test);
        urlRequest = Variables.getURLBase() + "action=Buscar&valor=";
        new BuscarTask(mListV,getApplicationContext()).execute();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String busqueda = query;
                String urlBase = Variables.getURLBase();
                urlRequest = urlBase + "action=Buscar&valor=" + busqueda;
//                Toast.makeText(MainActivity.this, urlRequest, Toast.LENGTH_LONG).show();
                textView.setText("Buscando...");
                new BuscarTask(mListV,getApplicationContext()).execute();
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
        }

        class ViewHolder {
            TextView nombre;
            TextView artista;
            TextView genero;
            TextView album;
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
                holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
                holder.artista = (TextView) convertView.findViewById(R.id.artista);
                holder.album = (TextView) convertView.findViewById(R.id.album);
                holder.genero = (TextView) convertView.findViewById(R.id.genero);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // Set the results into TextViews
            holder.nombre.setText(cancioneslist.get(position).getNombre());
            holder.artista.setText(cancioneslist.get(position).getArtista());
            holder.album.setText(cancioneslist.get(position).getAlbum());
            holder.genero.setText(cancioneslist.get(position).getGenero());
            return convertView;
        }
    }

    public class BuscarTask extends AsyncTask<String, Void, String> {

        ListView mListV;
        Context mContext;

        public BuscarTask(ListView lv, Context c){
            mListV=lv;
            mContext=c;
        }

        @Override
        protected void onPostExecute(String result) {

            ArrayList<Cancion> canciones=new ArrayList<>();


            if(result == null){
                Toast.makeText(MainActivity.this, "No hay canciones :(", Toast.LENGTH_SHORT).show();
            }else{


                Toast.makeText(MainActivity.this, "Disfrutalas ;)!!", Toast.LENGTH_SHORT).show();
                try{

                    JSONArray data = new JSONArray(result);
                    Cancion c;
                    for(int i=0;i<data.length();i++){
                        c=new Cancion();
                        c.setNombre(data.getJSONObject(i).getString("nombre"));
                        c.setArtista(data.getJSONObject(i).getString("artista"));
                        c.setAlbum(data.getJSONObject(i).getString("album"));
                        c.setGenero(data.getJSONObject(i).getString("genero"));
                        canciones.add(c);
                    }
                    ListViewAdapter adapter=new ListViewAdapter(mContext,canciones);
                    mListV.setAdapter(adapter);


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
