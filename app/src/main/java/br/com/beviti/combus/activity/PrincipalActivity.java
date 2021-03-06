package br.com.beviti.combus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.beviti.combus.R;
import br.com.beviti.combus.util.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {

    FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_principal);

        if (autenticacao.getCurrentUser() != null) {
            startActivity(new Intent(this, ListaCombustivelActivity.class));
            finish();
        }

    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));

    }

    public void criar(View view) {
        startActivity(new Intent(this, CadastrarActivity.class));
    }
}
