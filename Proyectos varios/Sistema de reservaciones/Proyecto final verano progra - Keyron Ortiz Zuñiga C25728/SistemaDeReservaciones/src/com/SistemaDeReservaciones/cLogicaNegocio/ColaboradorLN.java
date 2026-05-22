/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cLogicaNegocio;

import com.SistemaDeReservaciones.cDatos.DtoColaboradores;
import com.SistemaDeReservaciones.cEntidad.Colaborador;
import java.util.ArrayList;

/**
 * En esta clase se hace la conexion de los datos del colaborador con la capa de
 * presentacion
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class ColaboradorLN {

    DtoColaboradores colabLN;

    public ColaboradorLN() {
        colabLN = new DtoColaboradores();
    }

    /**
     * En este metodo se agrega un nuevo colaborador
     *
     * @param nuevoColab
     * @param sobreescribir
     * @return
     */
    public boolean agregarColaborador(Colaborador nuevoColab, boolean sobreescribir) {
        return colabLN.agregarColaborador(nuevoColab, true);
    }

    /**
     * Este metodo muestra los datos del archivo
     *
     * @return
     */
    public ArrayList<Colaborador> consultar() {
        return colabLN.consultar();
    }

    /**
     * Este metodo elimina por completo un colaborador
     *
     * @param idColab
     * @return
     */
    public boolean eliminarColab(int idColab) {
        return colabLN.eliminarColab(idColab);
    }

    /**
     * Este metodo modifica un colaborador basandose en su id
     *
     * @param colabModificado
     * @return
     */
    public boolean modificarColab(Colaborador colabModificado) {
        return colabLN.modificarColab(colabModificado);
    }

    /**
     * Este metodo crea un id automaticamente
     *
     * @return
     */
    public int idConsecutivo() {
        int consecutivo = 1;
        if (!consultar().isEmpty()) {
            consecutivo = consultar().get(consultar().size() - 1).getIdColaborador() + 1;
        }
        return consecutivo;
    }

    public boolean buscarIdColab(int idColab) {
        return colabLN.buscarIdColaborador(idColab);
    }

    public boolean buscarIdentificacion(String identificacion) {
        return colabLN.verificarIdentificacion(identificacion);
    }
}
