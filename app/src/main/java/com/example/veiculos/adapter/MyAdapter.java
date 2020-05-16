package com.example.veiculos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veiculos.R;
import com.example.veiculos.abastecimento.Abastecimento;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Abastecimento> lista = new ArrayList<>();

    public MyAdapter(List<Abastecimento> lista){
        this.lista = lista;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tipo;
        TextView posto;
        TextView data;
        TextView litros;
        TextView valor;
        TextView km;
        TextView total;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.tvTipo);
            posto = itemView.findViewById(R.id.tvPosto);
            data = itemView.findViewById(R.id.tvData);
            litros = itemView.findViewById(R.id.tvLitros);
            valor = itemView.findViewById(R.id.tvValor);
            km = itemView.findViewById(R.id.tvKm);
            total = itemView.findViewById(R.id.tvTotal);
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

        Abastecimento a = lista.get(position);
        holder.tipo.setText(a.getTipo());
        holder.posto.setText("Posto: "+a.getPosto());
        holder.data.setText(a.getData().toString());
        holder.litros.setText("Litros: "+a.getLitros());
        holder.valor.setText("Valor: R$"+a.getValor());
        holder.km.setText("Km: "+a.getKm());
        holder.total.setText("Total: R$"+a.getTotal());

    }

    @Override
    public int getItemCount() {
        return lista==null?0:lista.size();
    }






}
