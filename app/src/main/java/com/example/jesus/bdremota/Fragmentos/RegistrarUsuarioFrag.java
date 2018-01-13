package com.example.jesus.bdremota.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jesus.bdremota.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarUsuarioFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarUsuarioFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarUsuarioFrag extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText campoDNI,campoNOMBRE,campoPROFESION;
    Button btnRegisUser;
    ProgressDialog progreso;
    //establecer conexion con el server;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegistrarUsuarioFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarUsuarioFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarUsuarioFrag newInstance(String param1, String param2) {
        RegistrarUsuarioFrag fragment = new RegistrarUsuarioFrag();
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
        View view= inflater.inflate(R.layout.fragment_registrar_usuario, container, false);
        campoDNI=(EditText) view.findViewById(R.id.ETDni);
        campoNOMBRE=(EditText) view.findViewById(R.id.ETNombre);
        campoPROFESION=(EditText) view.findViewById(R.id.ETProfesion);
        btnRegisUser=(Button) view.findViewById(R.id.btnRegistrar);

        request= Volley.newRequestQueue(getContext());
        btnRegisUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });

        return view;
    }


    private void cargarWebService() {
        progreso= new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();
        String url="http://192.168.1.250:8080/ejemploBDremota/wsJSONregistro.php?DNI="+campoDNI.getText().toString()+"&NOMBRE="+
                 campoNOMBRE.getText().toString()+"&PROFESION="+campoPROFESION.getText().toString();
        //para que registre los espacias sino se registra como uno solo
        url=url.replace(" ","%20");
        //se envia a volley para q lo lea y procese la informacion q vamos a enviar
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        //permite conectarse al webservis y ahora puedo usar los 2 siguentes metodos onErrorResponse, onResponse
        request.add(jsonObjectRequest);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"error "+error.toString(),Toast.LENGTH_SHORT).show();
        progreso.hide();
        Log.i("ERROR ==>> ",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"CORRECTO",Toast.LENGTH_SHORT).show();
        progreso.hide();
        campoDNI.setText("");
        campoPROFESION.setText("");
        campoNOMBRE.setText("");
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
