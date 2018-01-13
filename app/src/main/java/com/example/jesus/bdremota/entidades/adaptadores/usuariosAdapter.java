package com.example.jesus.bdremota.entidades.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jesus.bdremota.R;
import com.example.jesus.bdremota.entidades.Usuario;

import java.util.List;

/**
 * Created by jesus on 6/12/2017.
 */

public class usuariosAdapter extends RecyclerView.Adapter<usuariosAdapter.UsuariosHolder>{

    List<Usuario> listaUsuarios;

    public usuariosAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios_list,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {
        holder.txtDocumento.setText(listaUsuarios.get(position).getDni().toString());
        holder.txtNombre.setText(listaUsuarios.get(position).getNombre().toString());
        holder.txtProfesion.setText(listaUsuarios.get(position).getProfesion().toString());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView txtDocumento,txtNombre,txtProfesion;

        public UsuariosHolder(View itemView) {
            super(itemView);
            txtDocumento= (TextView) itemView.findViewById(R.id.txtDni);
            txtNombre= (TextView) itemView.findViewById(R.id.txtNombre);
            txtProfesion= (TextView) itemView.findViewById(R.id.txtProfesion);

        }
    }
}
