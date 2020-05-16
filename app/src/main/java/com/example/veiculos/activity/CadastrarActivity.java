package com.example.veiculos.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.veiculos.R;
import com.example.veiculos.usuario.Usuario;
import com.example.veiculos.util.Base64Custom;
import com.example.veiculos.util.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastrarActivity extends AppCompatActivity {

    EditText editNome;
    EditText editLogin;
    EditText editSenha;
    FirebaseAuth autenticacao;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        editNome = findViewById(R.id.edit_nome);
        editLogin = findViewById(R.id.edit_login);
        editSenha = findViewById(R.id.edit_senha);
        usuario = new Usuario();


    }

    public void cadastrar(View view) {
        String nome = editNome.getText().toString();
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        String id = Base64Custom.codificar(login);


        usuario.setEmail(login);
        usuario.setNome(nome);
        usuario.setId(id);


        if (!nome.isEmpty()) {
            if (!login.isEmpty()) {
                if (!senha.isEmpty()) {
                    autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                    autenticacao.createUserWithEmailAndPassword(login, senha)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                    usuario.salvar();
                                    finish();

                                    } else {
                                        String excecao;
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthWeakPasswordException e) {
                                            excecao = "Coloque uma Senha Mais forte";
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            excecao = "Digite um email válido";
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            excecao = "Este email já foi cadastrado";
                                        } catch (Exception e) {
                                            excecao = "Ocorreu um erro, usuário não foi cadastrado";
                                            e.printStackTrace();

                                        }
                                        Toast.makeText(CadastrarActivity.this, excecao, Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                } else {
                    Toast.makeText(CadastrarActivity.this, "Preencha a senha", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(CadastrarActivity.this, "Preencha o Login", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(CadastrarActivity.this, "Preencha o nome", Toast.LENGTH_LONG).show();


        }


    }


}
