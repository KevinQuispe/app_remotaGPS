package com.example.jesus.bdremota.Interfaces;

import com.example.jesus.bdremota.Fragmentos.BienvenidoFrag;
import com.example.jesus.bdremota.Fragmentos.ConsultarListaUsuariosFrag;
import com.example.jesus.bdremota.Fragmentos.ConsultarUsuarioFrag;
import com.example.jesus.bdremota.Fragmentos.DesarrolladorFrag;
import com.example.jesus.bdremota.Fragmentos.RegistrarUsuarioFrag;

/**
 * Created by jesus on 4/12/2017.
 */

public interface iFragments extends BienvenidoFrag.OnFragmentInteractionListener,ConsultarListaUsuariosFrag.OnFragmentInteractionListener
    ,ConsultarUsuarioFrag.OnFragmentInteractionListener,DesarrolladorFrag.OnFragmentInteractionListener,RegistrarUsuarioFrag.OnFragmentInteractionListener{

}
