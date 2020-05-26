package com.example.veiculos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.veiculos.R;
import com.example.veiculos.abastecimento.Abastecimento;
import com.example.veiculos.usuario.Usuario;
import com.example.veiculos.util.Base64Custom;
import com.example.veiculos.util.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroAbastecimentoActivity extends AppCompatActivity {

    RadioGroup tipo;
    RadioButton tipo2;
    EditText data;
    EditText posto;
    EditText km;
    EditText litros;
    EditText valor;


    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();
    String dataAtual = df.format(cal.getTime());


    Abastecimento abastecimento;

    FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    DatabaseReference dr = ConfiguracaoFirebase.getDatabase();
    String idUsuario = Base64Custom.codificar(autenticacao.getCurrentUser().getEmail());

    DatabaseReference drUser = dr.child(idUsuario).child("usuario");
    double km1, litros1, kmAlteracao, litrosAlteracao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_abastecimento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Abastecimento");
        pegarValores();

        tipo = findViewById(R.id.radioGroup);
        data = findViewById(R.id.editData);
        posto = findViewById(R.id.editPosto);
        km = findViewById(R.id.editKm);
        litros = findViewById(R.id.editLitros);
        valor = findViewById(R.id.editValor);

        abastecimento = (Abastecimento) getIntent().getSerializableExtra("Abastecimento");

        if (abastecimento != null) {
            if (abastecimento.getTipo().equals("Gasolina")) {
                tipo2 = findViewById(R.id.rbGasolina);
                tipo2.setChecked(true);
            } else {
                tipo2 = findViewById(R.id.rdAlcool);
                tipo2.setChecked(true);
            }
            data.setText(abastecimento.getData().toString());
            posto.setText(abastecimento.getPosto());
            km.setText(abastecimento.getKm().toString());
            litros.setText(abastecimento.getLitros().toString());
            valor.setText(abastecimento.getValor().toString());
            kmAlteracao = abastecimento.getKm();
            litrosAlteracao = abastecimento.getLitros();
        } else {
            abastecimento = new Abastecimento();
            data.setText(dataAtual);

        }
    }

    public void salvarAbastecimento(View view) {

        if (validarCampos()) {
            tipo2 = findViewById(tipo.getCheckedRadioButtonId());

            abastecimento.setTipo(tipo2 != null ? tipo2.getText().toString() : null);
            abastecimento.setData(data.getText().toString());
            abastecimento.setPosto(posto.getText().toString());
            abastecimento.setKm(Double.parseDouble(km.getText().toString().replaceAll(",", ".")));
            abastecimento.setLitros(Double.parseDouble(litros.getText().toString().replaceAll(",", ".")));
            abastecimento.setValor(Double.parseDouble(valor.getText().toString().replaceAll(",", ".")));
            if (abastecimento.getId() != null) {
                dr.child(idUsuario)
                        .child("abastecimento")
                        .child(abastecimento.getId())
                        .setValue(abastecimento);
                    drUser.child("km").setValue((km1 - kmAlteracao) + abastecimento.getKm());
                    drUser.child("litros").setValue((litros1 - litrosAlteracao) + abastecimento.getLitros());
                    Toast toast = Toast.makeText(this, "Abastecimento Alterado", Toast.LENGTH_SHORT);
                    toast.show();


            } else {
                dr.child(idUsuario)
                        .child("abastecimento")
                        .push()
                        .setValue(abastecimento);
                    drUser.child("km").setValue(km1 + abastecimento.getKm());
                    drUser.child("litros").setValue(litros1 + abastecimento.getLitros());
                    Toast toast = Toast.makeText(this, "Abastecimento Salvo", Toast.LENGTH_SHORT);
                    toast.show();



            }
            finish();//fecha a activity
        }

    }


    public void pegarValores() {
        drUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                km1 = usuario.getKm();
                litros1 = usuario.getLitros();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean validarCampos() {
        String msg;
        boolean dataValida=false;
        df.setLenient (false); // aqui o pulo do gato
        try {
            df.parse (data.getText().toString());
            dataValida=true;

        } catch (ParseException ex) {
            // data inválida
        }
        if (!data.getText().toString().isEmpty() && dataValida) {

            if (!litros.getText().toString().isEmpty()) {
                if (!km.getText().toString().isEmpty()) {
                    if (!posto.getText().toString().isEmpty()) {
                        if (tipo.getCheckedRadioButtonId() != (-1)) {
                            if (!valor.getText().toString().isEmpty()) {
                                return true;
                            } else {
                                msg = "Informe o valor";
                            }
                        } else {
                            msg = "Informe o tipo";
                        }
                    } else {
                        msg = "Informe o Posto";
                    }
                } else {
                    msg = "Informe o km";
                }
            } else {
                msg = "Informe os litros";
            }
        } else {
            msg = "Informe uma data válida";
        }

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
        return false;
    }


}
