package com.example.veiculos.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.veiculos.R;
import com.example.veiculos.adapter.MyAdapter;

public class ListagemCombustivel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fuel);

        //recycleview----------------------------------------------------
        recyclerView.setHasFixedSize(true);

        recyclerView = findViewById(R.id.rvabasteci);

        //CONFIGURAR O ADAPTER
        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);

        //CONFIGURAR O RECYCLEVIEW
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recycleview----------------------------------------------------

    }
}
