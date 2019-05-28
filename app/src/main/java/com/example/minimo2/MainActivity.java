package com.example.minimo2;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class MainActivity extends AppCompatActivity {
    public List<Element> listaelementos;

    private DibaApi dibaApi;
    public Recycler recyler;
    public RecyclerView recyclerview;
    TextView textViewadreca;
    ImageView ivImageFromUrl;


    protected void onCreate(Bundle savedInstanceState) {
        recyclerview.setHasFixedSize(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.RecyclerView);
        recyler = new Recycler(this);
        recyclerview.setAdapter(recyler);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        textViewadreca = findViewById(R.id.adreca_nom);
        ivImageFromUrl = findViewById(R.id.imatge);
        dibaApi = DibaApi.retrofit.create(DibaApi.class);
        getData();
    }


    private void getData() {

        Call<Museums> elementCall = dibaApi.getData(1, 11);
        elementCall.enqueue(new Callback<Museums>() {
            @Override


            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if (response.isSuccessful()) {
                    Museums museums = response.body();
                    listaelementos = museums.getElements();
                    recyler.rellenarLista(listaelementos);

                } else {

                   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();



                }
            }
            //aa
            @Override
            public void onFailure(Call<Museums> call, Throwable t) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }
}
