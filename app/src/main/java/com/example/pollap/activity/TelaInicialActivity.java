package com.example.pollap.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pollap.R;
import com.example.pollap.config.ConfigFirebase;
import com.example.pollap.model.Enquete;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class TelaInicialActivity extends AppCompatActivity {

    private TextInputLayout campoCodigo;
    private Button campoCriar;
    private Button campoEntrar;
    private DatabaseReference firebase = ConfigFirebase.getDatabaseFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Metodo para esconder a ToolBar*/
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_tela_inicial);

        campoCriar = findViewById(R.id.btnCriarEnquete);
        campoEntrar = findViewById(R.id.btnEntrarEnquete);
        campoCodigo = findViewById(R.id.textInputLayout);

        campoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(TelaInicialActivity.this, TelaEnqueteActivity.class));
                EditText editText = campoCodigo.getEditText();
                String codigo = editText.getText().toString();
                if(!codigo.isEmpty()) {
                    verificandoCodigo();
                } else {
                    Toast.makeText(TelaInicialActivity.this, "Campo código vazio, favor preencher!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        campoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaInicialActivity.this, CriarEnquenteActivity.class));
            }
        });

    }

    public void verificandoCodigo(){
        DatabaseReference recupDados = firebase.child("enquete");

        recupDados.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Enquete enquete = snapshot.getValue(Enquete.class);
                campoCodigo = findViewById(R.id.textInputLayout);
                EditText editText = campoCodigo.getEditText();
                String codigo = editText.getText().toString();

                if(enquete.getId().equals(codigo)){
                    Context context = getApplicationContext();
                    Intent intent = new Intent(context, VotacaoActivity.class);

                    intent.putExtra("titulo", enquete.getTitulo());
                    intent.putExtra("pergunta", enquete.getPergunta());
                    intent.putExtra("opcao01", enquete.getOpcao01());
                    intent.putExtra("opcao02", enquete.getOpcao02());
                    intent.putExtra("opcao03", enquete.getOpcao03());
                    intent.putExtra("codigo",codigo);

                    startActivity(intent);

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}