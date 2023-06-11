package com.example.pollap.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pollap.R;
import com.example.pollap.model.Enquete;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class CriarEnquenteActivity extends AppCompatActivity {

    private TextInputEditText campoTitulo, campoOpcao01, campoOpcao02, campoOpcao03;
    private EditText campoPergunta;
    private Button btnSalvar;
    private Enquete enquete;
    private Random numRandom;
    private String numAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_enquente);

        campoPergunta = findViewById(R.id.cxTextoPergunta);
        campoTitulo = findViewById(R.id.inputTxtTitulo);
        campoOpcao01 = findViewById(R.id.inputTxtOp01);
        campoOpcao02 = findViewById(R.id.inputTxtOp02);
        campoOpcao03 = findViewById(R.id.inputTxtOp03);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = campoTitulo.getText().toString();
                String pergunta = campoPergunta.getText().toString();
                String opcao01 = campoOpcao01.getText().toString();
                String opcao02 = campoOpcao02.getText().toString();
                String opcao03 = campoOpcao03.getText().toString();

                if(!titulo.isEmpty()){
                    if(!pergunta.isEmpty()){
                        if (!opcao01.isEmpty()){
                            if (!opcao02.isEmpty()){
                                if(!opcao03.isEmpty()){
                                    enquete = new Enquete();
                                    enquete.setTitulo(titulo);
                                    enquete.setPergunta(pergunta);
                                    enquete.setOpcao01(opcao01);
                                    enquete.setOpcao02(opcao02);
                                    enquete.setOpcao03(opcao03);
                                    salvaEnquete();
                                } else {
                                    Toast.makeText(CriarEnquenteActivity.this, "Favor preencher a Opção 03", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(CriarEnquenteActivity.this, "Favor preencher a Opção 02", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CriarEnquenteActivity.this, "Favor preencher a Opção 01", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CriarEnquenteActivity.this, "Favor preencher a Pergunta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CriarEnquenteActivity.this, "Favor preencher o Titulo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void salvaEnquete(){
        numRandom = new Random();
        int num = numRandom.nextInt(10000);
        numAleatorio = String.valueOf(num);
        enquete.setId(numAleatorio);
        enquete.salvar();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Codigo Enquete");
        builder.setMessage("Enquete criada com sucesso! \n" + numAleatorio);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                chamarActivity();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void chamarActivity(){
        //Intent intent = new Intent(getApplicationContext(),VotacaoActivity.class);
        //intent.putExtra("codigo",numAleatorio);
        //startActivity(intent);
        startActivity(new Intent(CriarEnquenteActivity.this,TelaEnqueteActivity.class));
        finish();
    }

}