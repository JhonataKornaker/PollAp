package com.example.pollap.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pollap.R;
import com.example.pollap.config.ConfigFirebase;
import com.example.pollap.model.Enquete;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class VotacaoActivity extends AppCompatActivity {

    private RadioButton opcao01, opcao02, opcao03;
    private EditText campoPergunta;
    private TextView campoTitulo, campoCodigo;
    private Button botaoSalvar;
    private RadioGroup radioGroup;
    private DatabaseReference firebaseRef = ConfigFirebase.getDatabaseFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacao);

        campoPergunta = findViewById(R.id.editTextPergunta);
        campoPergunta.setKeyListener(null);

        botaoSalvar = findViewById(R.id.btnSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radioGroup = findViewById(R.id.radioGroup);
                campoCodigo = findViewById(R.id.txtCodigo);

                int radioButtonID = radioGroup.getCheckedRadioButtonId();

                if (radioButtonID != -1) {
                    RadioButton radioButton = findViewById(radioButtonID);
                    //String opcaoSelecionada = radioButton.getText().toString();
                    String codigo = campoCodigo.getText().toString();

                    atualizarVotos(codigo, radioButton);
                }

                startActivity(new Intent(VotacaoActivity.this, TelaEnqueteActivity.class));
                finish();
            }
        });

        recuperarDados();
    }

    public void recuperarDados() {
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String titulo = extra.getString("titulo");
            String pergunta = extra.getString("pergunta");
            String dadosOpcao01 = extra.getString("opcao01");
            String dadosOpcao02 = extra.getString("opcao02");
            String dadosOpcao03 = extra.getString("opcao03");
            String codigo = extra.getString("codigo");

            campoTitulo = findViewById(R.id.txtTest);
            campoCodigo = findViewById(R.id.txtCodigo);
            campoPergunta = findViewById(R.id.editTextPergunta);
            opcao01 = findViewById(R.id.rbtnOpcao01);
            opcao02 = findViewById(R.id.rbtnOpcao02);
            opcao03 = findViewById(R.id.rbtnOpcao03);

            campoTitulo.setText(titulo);
            campoCodigo.setText(codigo);
            campoPergunta.setText(pergunta);
            opcao01.setText(dadosOpcao01);
            opcao02.setText(dadosOpcao02);
            opcao03.setText(dadosOpcao03);
        }
    }

    public void atualizarVotos(String codigo, RadioButton radioButton) {

        DatabaseReference refEnquete = firebaseRef.child("enquete").child(codigo);

        refEnquete.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Enquete enquete = snapshot.getValue(Enquete.class);

                    if (radioButton.getId() == R.id.rbtnOpcao01) {

                        enquete.setVotosOpcao01(enquete.getVotosOpcao01() + 1);

                    } else if (radioButton.getId() == R.id.rbtnOpcao02) {

                        enquete.setVotosOpcao02(enquete.getVotosOpcao02() + 1);

                    } else if (radioButton.getId() == R.id.rbtnOpcao03) {

                        enquete.setVotosOpcao03(enquete.getVotosOpcao03() + 1);
                    }

                    enquete.setContaVotos(enquete.getContaVotos() + 1);

                    double porcentagemOp1 = (enquete.getVotosOpcao01() / enquete.getContaVotos()) * 100;
                    double porcentagemOp2 = (enquete.getVotosOpcao02() / enquete.getContaVotos()) * 100;
                    double porcentagemOp3 = (enquete.getVotosOpcao03() / enquete.getContaVotos()) * 100;

                    DecimalFormat db = new DecimalFormat("##.#");

                    enquete.setContVtOp01(Double.parseDouble(db.format(porcentagemOp1).replace(",", ".")));
                    enquete.setContVtOp02(Double.parseDouble(db.format(porcentagemOp2).replace(",", ".")));
                    enquete.setContVtOp03(Double.parseDouble(db.format(porcentagemOp3).replace(",",".")));

                    refEnquete.setValue(enquete);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
