package com.example.veiculos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veiculos.R;
import com.example.veiculos.adapter.MyAdapter;
import com.example.veiculos.abastecimento.Abastecimento;
import com.example.veiculos.adapter.RecyclerItemClickListener;
import com.example.veiculos.usuario.Usuario;
import com.example.veiculos.util.Base64Custom;
import com.example.veiculos.util.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaCombustivelActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Abastecimento> lista = new ArrayList<>();
    private Abastecimento abastecimento;
    private Usuario usuario;
    private double media,km,litro;
    private TextView textView_media;
    private TextView textView_sadacao;



    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference dr = ConfiguracaoFirebase.getDatabase();
    private String idUsuario = Base64Custom.codificar(autenticacao.getCurrentUser().getEmail());

    private DatabaseReference drAbastecimento = dr.child(idUsuario).child("abastecimento");
    private DatabaseReference drUsuario = dr.child(idUsuario).child("usuario");



    //O menu superior direito para salvar o abastecimento---------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//atribuir o menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//trata a ação do menu
        switch (item.getItemId()) {
            case R.id.sair_menu:
                autenticacao.signOut();
                finish();
                startActivity(new Intent(this, PrincipalActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_combustivel);
        textView_media = findViewById(R.id.textView_media);
        textView_sadacao = findViewById(R.id.textView_saudacao);
        recyclerView = findViewById(R.id.rvabasteci);
        recyclerView.addOnItemTouchListener(//O ENVENTO DE CLICK TEM QUE FICAR DENTRO DO CREATE.
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                abastecimento = lista.get(position);
                                Intent intent = new Intent(ListaCombustivelActivity.this, CadastroAbastecimentoActivity.class);
                                intent.putExtra("Abastecimento", abastecimento);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                abastecimento = lista.get(position);
                                AlertDialog.Builder dialog = new AlertDialog.Builder(ListaCombustivelActivity.this);
                                dialog.setTitle("Deseja apagar?");
                                dialog.setMessage("Deseja apagar este abastecimento?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        drAbastecimento.child(abastecimento.getId()).removeValue();
                                        drUsuario.child("km").setValue(usuario.getKm() - abastecimento.getKm());
                                        drUsuario.child("litros").setValue(usuario.getLitros() - abastecimento.getLitros());
                                            Toast toast = Toast.makeText(getApplicationContext(), "Abastecimento apagado!", Toast.LENGTH_SHORT);
                                            toast.show();



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
        abastecimento = new Abastecimento();

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
        drUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.getValue(Usuario.class);
                km=usuario.getKm();
                litro=usuario.getLitros();
                media=litro==0.0?0.0:km/litro;

                textView_sadacao.setText("Olá, "+usuario.getNome()+"!");
                textView_media.setText(String.format("%.2f",media)+" Km/l");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        drAbastecimento.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lista.clear();
                for(DataSnapshot dados : dataSnapshot.getChildren()){
                    abastecimento = dados.getValue(Abastecimento.class);
                    abastecimento.setId(dados.getKey());//Recuperar a chave
                    lista.add(abastecimento);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        //CONFIGURAR O ADAPTER
        mAdapter = new MyAdapter(lista);
        recyclerView.setAdapter(mAdapter);

        //CONFIGURAR O RECYCLEVIEW
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recycleview----------------------------------------------------
    }


}
