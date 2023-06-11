package com.example.pollap.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;


import com.example.pollap.R;
import com.example.pollap.config.Adapter;
import com.example.pollap.config.ConfigFirebase;
import com.example.pollap.model.Enquete;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TelaEnqueteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference firebase = ConfigFirebase.getDatabaseFirebase();
    private List<Enquete> listaEnquete = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_enquete);

        recyclerView = findViewById(R.id.recycler);

        recuperarDados();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //Otimizar Layout RecyclerView
        recyclerView.setHasFixedSize(true);
        //Colocando linha para separar item da lista
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }

    public void recuperarDados(){
        DatabaseReference recupDados = firebase.child("enquete");

        recupDados.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Enquete enquete = snapshot.getValue(Enquete.class);
                listaEnquete.add(enquete);
                Adapter adapter = new Adapter(listaEnquete);
                recyclerView.setAdapter(adapter);
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