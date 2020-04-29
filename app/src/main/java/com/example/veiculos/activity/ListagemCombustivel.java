package com.example.veiculos.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.veiculos.R;
import com.example.veiculos.adapter.MyAdapter;
import com.example.veiculos.model.Abastecimento;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class ListagemCombustivel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    List<Abastecimento> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fuel);

        //recycleview----------------------------------------------------

        //listar(); função para testar se a lista estava funcionando

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        lista = (List<Abastecimento>) bundle.getSerializable("lista");

        recyclerView = findViewById(R.id.rvabasteci);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(this,LinearLayout.VERTICAL));

        //CONFIGURAR O ADAPTER
        mAdapter = new MyAdapter(lista);
        recyclerView.setAdapter(mAdapter);

        //CONFIGURAR O RECYCLEVIEW
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recycleview----------------------------------------------------

    }

    /* Função para testar se a lista estava funcionando
    public void listar(){
        Abastecimento a = new Abastecimento();
        a.setTipo("GASOLINA");
        a.setData(Date.valueOf("2020-12-12"));
        a.setKm(1000.0);
        a.setLitros(45.0);
        a.setPosto("Extra");
        a.setValor(2.5);
        lista.add(a);

        a= new Abastecimento();
        a.setTipo("GASOLINA");
        a.setData(Date.valueOf("2020-12-11"));
        a.setKm(3000.0);
        a.setLitros(55.0);
        a.setPosto("Extra");
        a.setValor(3.5);
        lista.add(a);

        a= new Abastecimento();
        a.setTipo("ALCOOL");
        a.setData(Date.valueOf("2020-12-10"));
        a.setKm(1000.0);
        a.setLitros(50.0);
        a.setPosto("Extra");
        a.setValor(3.4);
        lista.add(a);

        a= new Abastecimento();
        a.setTipo("GASOLINA");
        a.setData(Date.valueOf("2020-12-15"));
        a.setKm(999.0);
        a.setLitros(30.0);
        a.setPosto("Extra");
        a.setValor(3.1);
        lista.add(a);

        a= new Abastecimento();
        a.setTipo("ALCOOL");
        a.setData(Date.valueOf("2020-12-19"));
        a.setKm(909.0);
        a.setLitros(20.0);
        a.setPosto("Extra");
        a.setValor(3.1);
        lista.add(a);
    }*/
}
