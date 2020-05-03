package com.example.veiculos.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
    private List<Abastecimento> lista;
    Abastecimento a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_combustivel);
        recyclerView = findViewById(R.id.rvabasteci);
        recyclerView.addOnItemTouchListener(//O ENVENTO DE CLICK TEM QUE FICAR DENTRO DO CREATE.
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                a = lista.get(position);

                                Intent intent = new Intent(ListaCombustivelActivity.this, CadastroAbastecimentoActivity.class);
                                intent.putExtra("Abastecimento", a);

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                a = lista.get(position);
                                AlertDialog.Builder dialog = new AlertDialog.Builder(ListaCombustivelActivity.this);
                                dialog.setTitle("Deseja apagar?");
                                dialog.setMessage("Deseja apagar este abastecimento?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        AbastecimentoDAO ad = new AbastecimentoSQLitle(getApplicationContext());
                                        if (ad.excluirAbastecimento(a)) {
                                            listarAbastecimento();
                                            Toast.makeText(getApplicationContext(),
                                                    "Tarefa foi Excluída!",
                                                    Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Erro ao excluir tarefa!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                dialog.setNegativeButton("Não", null);

                                //Exibir dialog
                                dialog.show();


                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
        a = new Abastecimento();
    }

    @Override
    protected void onStart() {
        listarAbastecimento();
        super.onStart();
    }

    public void cadastrar(View view) {//Ação do floatButton para abrir a tela de cadastro
        Intent intent = new Intent(getApplicationContext(), CadastroAbastecimentoActivity.class);
        startActivity(intent);
    }

    public void listarAbastecimento() {
        AbastecimentoDAO ad = new AbastecimentoSQLitle(getApplicationContext());
        lista = ad.listarAbastecimento();

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        //CONFIGURAR O ADAPTER
        mAdapter = new MyAdapter(lista);
        recyclerView.setAdapter(mAdapter);

        //CONFIGURAR O RECYCLEVIEW
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recycleview----------------------------------------------------
    }


}
