package com.example.jesus.bdremota.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jesus.bdremota.R;
import com.example.jesus.bdremota.entidades.adaptadores.usuariosAdapter;
import com.example.jesus.bdremota.entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultarListaUsuariosFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultarListaUsuariosFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarListaUsuariosFrag extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;
    ProgressDialog progreso;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    private OnFragmentInteractionListener mListener;
    public ConsultarListaUsuariosFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarListaUsuariosFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarListaUsuariosFrag newInstance(String param1, String param2) {
        ConsultarListaUsuariosFrag fragment = new ConsultarListaUsuariosFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_consultar_lista_usuarios, container, false);
        listaUsuarios=new ArrayList<>();
        recyclerUsuarios=(RecyclerView) view.findViewById(R.id.idRecycler);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerUsuarios.setHasFixedSize(true);
        requestQueue= Volley.newRequestQueue(getContext());
        cargarWebService();
        return view;
    }

    private void cargarWebService() {
        progreso= new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();
        String url="http://192.168.1.250:8080/ejemploBDremota/wsJSONConsultarLista.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        //permite conectarse al webservis y ahora puedo usar los 2 siguentes metodos onErrorResponse, onResponse
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"error "+error.toString(),Toast.LENGTH_SHORT).show();
        progreso.hide();
        Log.i("ERROR ==>> ",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Usuario user=null;
        JSONArray json=response.optJSONArray("usuario");
        try {
            for (int i=0;i<json.length();i++){
                user=new Usuario();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                user.setDni(jsonObject.optString("DNI"));
                user.setNombre(jsonObject.optString("NOMBRE"));
                user.setProfesion(jsonObject.optString("PROFESION"));
                listaUsuarios.add(user);
            }
            progreso.hide();
            usuariosAdapter adapter= new usuariosAdapter(listaUsuarios);
            recyclerUsuarios.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"error en conexion",Toast.LENGTH_SHORT).show();
            progreso.hide();
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
