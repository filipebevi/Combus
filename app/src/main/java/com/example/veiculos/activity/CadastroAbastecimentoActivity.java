package com.example.veiculos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.veiculos.Abastecimento.AbastecimentoDAO;
import com.example.veiculos.Abastecimento.AbastecimentoSQLitle;
import com.example.veiculos.R;
import com.example.veiculos.Abastecimento.Abastecimento;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CadastroAbastecimentoActivity extends AppCompatActivity {

    RadioGroup tipo;
    RadioButton tipo2;
    EditText data;
    EditText posto;
    EditText km;
    EditText litros;
    EditText valor;

    Abastecimento a;

    //O menu superior direito para salvar o abastecimento---------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//atribuir o menu
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//trata a ação do menu
        switch (item.getItemId()) {
            case R.id.salvar_menu:
                salvarAbastecimento();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    //------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_abastecimento);

        tipo = findViewById(R.id.radioGroup);
        data = findViewById(R.id.editData);
        posto = findViewById(R.id.editPosto);
        km = findViewById(R.id.editKm);
        litros = findViewById(R.id.editLitros);
        valor = findViewById(R.id.editValor);


        a = (Abastecimento) getIntent().getSerializableExtra("Abastecimento");

        if (a != null) {

            if(a.getTipo().equals("Gasolina")){
                tipo2 = findViewById(R.id.rbGasolina);
                tipo2.setChecked(true);
            } else {
                tipo2 = findViewById(R.id.rdAlcool);
                tipo2.setChecked(true);
            }


            data.setText(a.getData());
            posto.setText(a.getPosto());
            km.setText(a.getKm().toString());
            litros.setText(a.getLitros().toString());
            valor.setText(a.getValor().toString());
        } else {
            a = new Abastecimento();
        }

    }


    public void salvarAbastecimento() {
        tipo2 = findViewById(tipo.getCheckedRadioButtonId());
        a.setTipo(tipo2.getText().toString());
        int i = tipo.getCheckedRadioButtonId();
        a.setData(data.getText().toString());
        a.setPosto(posto.getText().toString());
        a.setKm(Double.valueOf(km.getText().toString()));
        a.setLitros(Double.valueOf(litros.getText().toString()));
        a.setValor(Double.valueOf(valor.getText().toString()));

        AbastecimentoDAO ab = new AbastecimentoSQLitle(getApplicationContext());


        ab.salvarAbastecimento(a);


        Toast toast = Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT);
        toast.show();

        finish();
    }


}
