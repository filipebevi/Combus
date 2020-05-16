package com.example.veiculos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.veiculos.R;
import com.example.veiculos.util.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {
    EditText editLogin;
    EditText editSenha;
    FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (autenticacao.getCurrentUser() != null) {
            startActivity(new Intent(this, ListaCombustivelActivity.class));
            finish();
        }
        editLogin = findViewById(R.id.edit_login);
        editSenha = findViewById(R.id.edit_senha);

    }

    public void logar(View view) {
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();


        if (!login.isEmpty()) {
            if (!senha.isEmpty()) {

                autenticacao.signInWithEmailAndPassword(login, senha)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    abrirPrincipal();
                                } else {
                                    String excecao;
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidUserException e) {
                                        excecao = "Usuário nao cadastrado ou desabilitado";
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        excecao = "Senha incorreta";
                                    } catch (Exception e) {
                                        excecao = "Ocorreu um erro, o usuário não esta logado";
                                        e.printStackTrace();

                                    }
                                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();
                                }

                            }
                        });

            } else {
                Toast.makeText(LoginActivity.this, "Preencha a senha", Toast.LENGTH_LONG).show();
            }


        } else {
            Toast.makeText(LoginActivity.this, "Preencha o Login", Toast.LENGTH_LONG).show();
        }

    }
    public void abrirPrincipal(){
        startActivity(new Intent(this,ListaCombustivelActivity.class));
        finish();
    }

}
