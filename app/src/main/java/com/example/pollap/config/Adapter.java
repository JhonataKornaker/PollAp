package com.example.pollap.config;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pollap.R;
import com.example.pollap.model.Enquete;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Enquete> lista;

    public Adapter(List<Enquete> lista) {
        this.lista = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*configurar o layout*/
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recicler_view_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Enquete listaEnquete = lista.get(position);
        holder.titulo.setText(listaEnquete.getTitulo());
        holder.opcao01.setText(listaEnquete.getOpcao01());
        holder.opcao02.setText(listaEnquete.getOpcao02());
        holder.opcao03.setText(listaEnquete.getOpcao03());

        holder.codigo.setText(listaEnquete.getId());
        holder.votoOp01.setText(String.valueOf(listaEnquete.getContVtOp01()));
        holder.votoOp02.setText(String.valueOf(listaEnquete.getContVtOp02()));
        holder.votoOp03.setText(String.valueOf(listaEnquete.getContVtOp03()));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo, opcao01, opcao02, opcao03, codigo, votoOp01, votoOp02, votoOp03;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textTitulo);
            opcao01 = itemView.findViewById(R.id.textOpcao01);
            opcao02 = itemView.findViewById(R.id.textOpcao02);
            opcao03 = itemView.findViewById(R.id.textOpcao03);
            codigo = itemView.findViewById(R.id.textCodigo);
            votoOp01 = itemView.findViewById(R.id.txtVotoOp01);
            votoOp02 = itemView.findViewById(R.id.txtVotoOp02);
            votoOp03 = itemView.findViewById(R.id.txtVotoOp03);
        }
    }
}
