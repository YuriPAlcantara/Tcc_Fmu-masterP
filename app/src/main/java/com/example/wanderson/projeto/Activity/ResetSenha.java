package com.example.wanderson.projeto.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wanderson.projeto.DAO.ConfigFirebase;
import com.example.wanderson.projeto.Entidades.Base64Custom;
import com.example.wanderson.projeto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity {

    private EditText editEmail;
    private Button btnResetSenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);

        editEmail = (EditText)findViewById(R.id.ediEmail);
        btnResetSenha = (Button)findViewById(R.id.btnResetSenha);
        eventoClick();
    }

    private void eventoClick() {
        btnResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                resetSenha(email);
            }
        });
    }

    private void resetSenha(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(ResetSenha.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            alert("Um e-mail foi enviado para alterar sua senha!");
                            finish();
                        }else{
                            alert("E-mail não registrado");
                        }
                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(ResetSenha.this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = ConfigFirebase.getFirebaseAutentificacao();
    }
}
