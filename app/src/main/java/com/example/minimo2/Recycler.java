package com.example.minimo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {

    public Recycler(Context context) {
        this.context = context;
        this.listaelementos = new ArrayList<>();
    }

    private Context context;
    private List<Element> listaelementos;

    public Recycler(List<Element> listaelementos) {
        this.listaelementos = listaelementos;
    }


    public void rellenarLista (List<Element> elementList) {
        listaelementos.addAll(elementList);
        notifyDataSetChanged();

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.museum_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Element element =listaelementos.get(i);
        Picasso.with(context).load(element.getImatge().get(0)).into(viewHolder.imatgeView);
        viewHolder.adreca_nomView.setText(element.getAdrecaNom());


    }

    @Override
    public int getItemCount() {
        return listaelementos.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout constraintLayout;
        private TextView adreca_nomView;
        private ImageView imatgeView;

        public ViewHolder(View v){
            super(v);

            constraintLayout = v.findViewById(R.id.constraintLayout);
            adreca_nomView = v.findViewById(R.id.adreca_nom);
            imatgeView = v.findViewById(R.id.imatge);


        }

    }
}