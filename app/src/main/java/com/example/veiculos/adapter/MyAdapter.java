package com.example.veiculos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veiculos.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tipo;
        TextView posto;
        TextView data;
        TextView litros;
        TextView valor;
        TextView km;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.tvTipo);
            posto = itemView.findViewById(R.id.tvPosto);
            data = itemView.findViewById(R.id.tvData);
            litros = itemView.findViewById(R.id.tvLitros);
            valor = itemView.findViewById(R.id.tvValor);
            km = itemView.findViewById(R.id.tvKm);
        }
        // each data item is just a string in this case

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_combustivel, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tipo.setText("Gasolina");
        holder.posto.setText("Extra");
        holder.data.setText("12/12/2020");
        holder.litros.setText("50 lts");
        holder.valor.setText("R$ 200,00");
        holder.km.setText("999 km");

    }

    @Override
    public int getItemCount() {
        return 5;
    }






}
