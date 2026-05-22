/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cLogicaNegocio;

import com.SistemaDeReservaciones.cDatos.DtoClientes;
import com.SistemaDeReservaciones.cEntidad.Clientes;
import java.util.ArrayList;

/**
 * Esta clase es la conexion de los datos del archivo cliente con la capa de
 * presentacion
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class ClientesLN {

    DtoClientes dtoC;

    public ClientesLN() {
        dtoC = new DtoClientes();
    }

    /**
     * Este metodo agrega un cliente al archivo
     *
     * @param nuevoCliente
     * @param sobreescribir
     * @return
     */
    public boolean agregarCliente(Clientes nuevoCliente, boolean sobreescribir) {
        return dtoC.agregarCliente(nuevoCliente, true);
    }

    /**
     * Este metodo muestra los datos del archivo
     *
     * @return
     */
    public ArrayList<Clientes> consultar() {
        return dtoC.consultar();
    }

    /**
     * Este metodo elimina un cliente basandose en su id
     *
     * @param idCliente
     * @return
     */
    public boolean eliminarCliente(int idCliente) {
        return dtoC.eliminarCliente(idCliente);
    }

    /**
     * Este metodo crea un id automatico
     *
     * @return
     */
    public int consecutivoId() {
        int consecutivo = 1;
        if (!consultar().isEmpty()) {
            consecutivo = consultar().get(consultar().size() - 1).getIdCliente() + 1;
        }
        return consecutivo;
    }

    /**
     * Este metodo modifica un cliente basandose si el id coincide
     *
     * @param clienteModificado
     * @return
     */
    public boolean modificarCliente(Clientes clienteModificado) {
        return dtoC.modificarCliente(clienteModificado);
    }

    /**
     * Este metodo verifica si el id recibido como parametro existe en el
     * archivo
     *
     * @param id
     * @return
     */
    public boolean buscarIdCliente(int id) {
        return dtoC.buscarIdCliente(id);
    }

    /**
     * Este metodo extrae el nombre del cliente basandose en su id
     *
     * @param id
     * @return
     */
    public String sacarNombreCliente(int id) {
        return dtoC.sacarNombreCliente(id);
    }

    public boolean verificarNombreCliente(String nombre, String apellidoUno, String apellidoDos) {
        return dtoC.verificarNombreCliente(nombre, apellidoUno, apellidoDos);
    }

    public boolean verificarIdentificacion(String identificacion) {
        return dtoC.verificarIdentificacionCliente(identificacion);
    }

    public boolean verificarClienteFrecuente(int cantidadVisitas) {
        return dtoC.verificarClienteFrecuente(cantidadVisitas);
    }

    public ArrayList<Clientes> listarFrecuentes() {
        return dtoC.listarClientesFrecuentes();
    }

    public int obtenerCantidadVisitas(int idCliente) {
        return dtoC.obtenerVisitas(idCliente);
    }
}
