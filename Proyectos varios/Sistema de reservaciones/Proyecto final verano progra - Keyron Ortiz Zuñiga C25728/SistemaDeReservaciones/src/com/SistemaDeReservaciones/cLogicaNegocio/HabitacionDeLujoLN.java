/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cLogicaNegocio;

import com.SistemaDeReservaciones.cDatos.DtoHabitacionDeLujo;
import com.SistemaDeReservaciones.cEntidad.Habitaciones;
import java.util.ArrayList;

/**
 * Esta clase conecta los datos de una habitacion lujosa con la capa de
 * presentacion
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class HabitacionDeLujoLN {

    DtoHabitacionDeLujo habi;

    public HabitacionDeLujoLN() {
        habi = new DtoHabitacionDeLujo();
    }

    /**
     * Este metodo agrega una nueva habitacion lujosa
     *
     * @param nuevaHabitacion
     * @param sobreescribir
     * @return
     */
    public boolean agregarHabitacion(Habitaciones nuevaHabitacion, boolean sobreescribir) {
        return habi.agregarHabitaciones(nuevaHabitacion, true);
    }

    /**
     * Este metodo muestra el contenido del archivo
     *
     * @return
     */
    public ArrayList<Habitaciones> consultar() {
        return habi.consultar();
    }

    /**
     * Este metodo elimina una habitacion por completo
     *
     * @param idHabitacion
     * @return
     */
    public boolean eliminarHabitacion(int idHabitacion) {
        return habi.eliminarHabitacion(idHabitacion);
    }

    /**
     * Este metodo modifica una habitacion basandose en su id
     *
     * @param habitacionModificada
     * @return
     */
    public boolean modificarHabitacion(Habitaciones habitacionModificada) {
        return habi.modificarHabitacion(habitacionModificada);
    }

    /**
     * Este metodo asigna un id de forma automatica
     *
     * @return
     */
    public int idConsecutivo() {
        int consecutivo = 1;
        if (!consultar().isEmpty()) {
            consecutivo = consultar().get(consultar().size() - 1).getIdHabitacion() + 1;
        }
        return consecutivo;
    }

    /**
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarFacturable(int idHabitacion) {
        return habi.verificarHabitacionFacturable(idHabitacion);
    }

    /**
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarDisponible(int idHabitacion) {
        return habi.verificarHabitacionDisponible(idHabitacion);
    }

    /**
     *
     * @param idHabi
     * @return
     */
    public boolean buscarHabitacion(int idHabi) {
        return habi.buscarIdHabitacion(idHabi);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean verificarHabitacionDeLujo(int id) {
        return habi.verificarHabitacionDeLujo(id);
    }

    public int sacarPrecioHabitacion(int id) {
        return habi.sacarPrecioHabitacion(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public String obtenerNombreClienteLujo(int id) {
        return habi.obtenerNombreCliente(id);
    }

    /**
     *
     * @return
     */
    public ArrayList<Habitaciones> mostrarReservas() {
        return habi.mostrarReservas();
    }

    public ArrayList <Habitaciones> mostrarReservasUOcupadas(){
    return habi.mostrarReservasUOcupadas();
    }
    
    public int calcularUsoDeHabitacionesDeLujo() {
        return habi.cantidadDeUsoHabitaciones();
    }
    
    public int mostrarMontoTotalUsoHabitaciones(){
    return habi.mostrarEntradasTotalesReservasUOcupadas();
    }
}
