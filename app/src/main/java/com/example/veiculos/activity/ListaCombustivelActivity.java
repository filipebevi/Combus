package com.example.veiculos.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.veiculos.Abastecimento.AbastecimentoDAO;
import com.example.veiculos.Abastecimento.AbastecimentoSQLitle;
import com.example.veiculos.R;
import com.example.veiculos.adapter.MyAdapter;
import com.example.veiculos.Abastecimento.Abastecimento;
import com.example.veiculos.adapter.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ListaCombustivelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_combustivel);
    }

    @Override
    protected void onStart() {
        listarAbastecimento();
        super.onStart();
    }

    public void cadastrar(View view){//Ação do floatButton para abrir a tela de cadastro
        Intent intent = new Intent(getApplicationContext(), CadastroAbastecimentoActivity.class);
        startActivity(intent);
    }

    public void listarAbastecimento(){
        List<Abastecimento> lista = new ArrayList<>();
        AbastecimentoDAO ad = new AbastecimentoSQLitle(getApplicationContext());
        lista=ad.listarAbastecimento();

        recyclerView = findViewById(R.id.rvabasteci);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Toast toast = Toast.makeText(getApplicationContext(), "curto", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Longo", Toast.LENGTH_SHORT);
                                toast.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                ));

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





}
