package com.example.veiculos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.veiculos.R;
import com.example.veiculos.model.Abastecimento;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Abastecimento> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void salvar(View view){
        RadioGroup tipo = findViewById(R.id.radioGroup);
        EditText data = findViewById(R.id.editData);
        EditText posto = findViewById(R.id.editPosto);
        EditText km = findViewById(R.id.editKm);
        EditText litros = findViewById(R.id.editLitros);
        EditText valor = findViewById(R.id.editValor);


        Abastecimento a = new Abastecimento();

        a.setTipo(tipo.getCheckedRadioButtonId()==0 ? "Gasolina" : "Alcool");
        a.setData(Date.valueOf(data.getText().toString()));
        a.setPosto(posto.getText().toString());
        a.setKm(Double.valueOf(km.getText().toString()));
        a.setLitros(Double.valueOf(litros.getText().toString()));
        a.setValor(Double.valueOf(valor.getText().toString()));

        lista.add(a);
    }

    public void listar(View view){

        Intent intent = new Intent(getApplicationContext(), ListagemCombustivel.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("lista", (Serializable) lista);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
