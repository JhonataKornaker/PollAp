package com.example.pollap.model;

import com.example.pollap.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Enquete {

    private String id;
    private String titulo;
    private String opcao01, opcao02, opcao03;
    private String pergunta;
    private double votosOpcao01, votosOpcao02, votosOpcao03;
    private double contaVotos;
    private double contVtOp01, contVtOp02, contVtOp03;


    public Enquete(){}

    //Metodo Salvar
    public void salvar(){
        DatabaseReference firebase = ConfigFirebase.getDatabaseFirebase();
        firebase.child("enquete")
                .child(this.id)
                .setValue(this);
    }

    public double getContVtOp01() {
        return contVtOp01;
    }

    public void setContVtOp01(double contVtOp01) {
        this.contVtOp01 = contVtOp01;
    }

    public double getContVtOp02() {
        return contVtOp02;
    }

    public void setContVtOp02(double contVtOp02) {
        this.contVtOp02 = contVtOp02;
    }

    public double getContVtOp03() {
        return contVtOp03;
    }

    public void setContVtOp03(double contVtOp03) {
        this.contVtOp03 = contVtOp03;
    }

    public double getContaVotos() {
        return contaVotos;
    }

    public void setContaVotos(double contaVotos) {
        this.contaVotos = contaVotos;
    }

    public double getVotosOpcao01() {
        return votosOpcao01;
    }

    public void setVotosOpcao01(double votosOpcao01) {
        this.votosOpcao01 = votosOpcao01;
    }

    public double getVotosOpcao02() {
        return votosOpcao02;
    }

    public void setVotosOpcao02(double votosOpcao02) {
        this.votosOpcao02 = votosOpcao02;
    }

    public double getVotosOpcao03() {
        return votosOpcao03;
    }

    public void setVotosOpcao03(double votosOpcao03) {
        this.votosOpcao03 = votosOpcao03;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOpcao01() {
        return opcao01;
    }

    public void setOpcao01(String opcao01) {
        this.opcao01 = opcao01;
    }

    public String getOpcao02() {
        return opcao02;
    }

    public void setOpcao02(String opcao02) {
        this.opcao02 = opcao02;
    }

    public String getOpcao03() {
        return opcao03;
    }

    public void setOpcao03(String opcao03) {
        this.opcao03 = opcao03;
    }

}
