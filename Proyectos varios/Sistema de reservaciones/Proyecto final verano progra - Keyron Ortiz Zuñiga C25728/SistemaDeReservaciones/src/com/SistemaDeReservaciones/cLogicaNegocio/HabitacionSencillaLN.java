/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cLogicaNegocio;

import com.SistemaDeReservaciones.cDatos.DtoHabitacionSencilla;
import com.SistemaDeReservaciones.cEntidad.Habitaciones;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Esta clase conecta los datos de habitacion sencilla con la capa de
 * presentacion
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class HabitacionSencillaLN {

    DtoHabitacionSencilla habi;

    public HabitacionSencillaLN() {
        habi = new DtoHabitacionSencilla();
    }

    /**
     * Este metodo agrega habitaciones sencillas
     *
     * @param nuevaHabitacion
     * @param sobreescribir
     * @return
     */
    public boolean agregarHabitaciones(Habitaciones nuevaHabitacion, boolean sobreescribir) {
        return habi.agregarHabitaciones(nuevaHabitacion, true);
    }

    /**
     * Este metodo muestra los datos del archivo
     *
     * @return
     */
    public ArrayList<Habitaciones> consultar() {
        return habi.consultar();
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
     * Este metodo elimina una habitacion basandose en su id
     *
     * @param idHabitacion
     * @return
     */
    public boolean eliminarHabitacion(int idHabitacion) {
        return habi.eliminarHabitacion(idHabitacion);
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
        return habi.verificarHabitacionFacturableSencilla(idHabitacion);
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
        return habi.buscarIdHabitacionSencilla(idHabi);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean verificarHabitacionSencilla(int id) {
        return habi.verificarHabitacionSencilla(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public int sacarPrecioHabitacionSencilla(int id) {
        return habi.sacarPrecioHabitacion(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public String obtenerNombreClienteSencilla(int id) {
        return habi.obtenerNombreCliente(id);
    }

    /**
     *
     * @return
     */
    public ArrayList<Habitaciones> mostrarReservas() {
        return habi.mostrarReservas();
    }

    public ArrayList<Habitaciones> mostrarReservasUOcupadas() {
        return habi.mostrarReservasUOcupadas();
    }

    public int calcularUsoHabitacionesSencillas() {
        return habi.cantidadDeUsoHabitaciones();
    }

    public int mostrarMontoTotalUsoHabitaciones() {
        return habi.mostrarEntradasTotalesReservasUOcupadas();
    }

    public boolean verificarHabitacion(int idHabitacion) {
        return habi.verificarHabitacionSencilla(idHabitacion);
    }
}
