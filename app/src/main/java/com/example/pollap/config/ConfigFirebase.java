package com.example.pollap.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;

    public static DatabaseReference getDatabaseFirebase() {
        if(firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }

  /*  public static FirebaseAuth getFirebaseAutenticacao() {
        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return  autenticacao;
    }*/
}
