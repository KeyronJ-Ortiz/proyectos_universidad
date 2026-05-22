package com.SistemaDeReservaciones.cEntidad;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Descripcion : En esta clase se toman en cuenta los atributos de una
 * habitacion y se hace su respectivo encansulamiento.
 *
 * @author Keyron Ortiz Zuñiga C25728
 * @version 1.0 fecha : 25-01-23
 */
public class Habitaciones {

    private int idHabitacion;
    private String estadoDeHabitacion;
    private String personaQueReserva;
    private int costoDeHabitacion;
    private int cantidadMaximaDePersonas;
    private String tipoDeCama;
    private boolean aireAcondicionado;

    public Habitaciones() {
    }

    public Habitaciones(int idHabitacion, String estadoDeHabitacion,
            String personaQueReserva, int costoDeHabitacion,
            int cantidadMaximaDePersonas, String tipoDeCama,
            boolean aireAcondicionado) {
        this.idHabitacion = idHabitacion;
        this.estadoDeHabitacion = estadoDeHabitacion;
        this.personaQueReserva = personaQueReserva;
        this.costoDeHabitacion = costoDeHabitacion;
        this.cantidadMaximaDePersonas = cantidadMaximaDePersonas;
        this.tipoDeCama = tipoDeCama;
        this.aireAcondicionado = aireAcondicionado;
    }

    public String getPersonaQueReserva() {
        return personaQueReserva;
    }

    public void setPersonaQueReserva(String personaQueReserva) {
        this.personaQueReserva = personaQueReserva;
    }

    
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getEstadoDeHabitacion() {
        return estadoDeHabitacion;
    }

    public void setEstadoDeHabitacion(String estadoDeHabitacion) {
        this.estadoDeHabitacion = estadoDeHabitacion;
    }

    public int getCostoDeHabitacion() {
        return costoDeHabitacion;
    }

    public void setCostoDeHabitacion(int costoDeHabitacion) {
        this.costoDeHabitacion = costoDeHabitacion;
    }

    public int getCantidadMaximaDePersonas() {
        return cantidadMaximaDePersonas;
    }

    public void setCantidadMaximaDePersonas(int cantidadMaximaDePersonas) {
        this.cantidadMaximaDePersonas = cantidadMaximaDePersonas;
    }

    public String getTipoDeCama() {
        return tipoDeCama;
    }

    public void setTipoDeCama(String tipoDeCama) {
        this.tipoDeCama = tipoDeCama;
    }

    public boolean getAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    /**
     * Metodo toSting que devuelve la informacion de la clase
     *
     * @return salida
     */
    @Override
    public String toString() {
        return "-----------------Id de la habitacion : " + getIdHabitacion() + "-----------------"
                + "\nEstado de la habitacion : " + getEstadoDeHabitacion()
                + "\nPersona que reserva : " + getPersonaQueReserva()
                + "\nCosto de la habitacion : " + getCostoDeHabitacion()
                + "\nCantidad maxima de personas :" + getCantidadMaximaDePersonas()
                + "\nTipo de camas : " + getTipoDeCama()
                + "\nPosee aire acondicionado : " + getAireAcondicionado() 
                + "\n---------------------------------------------------------------";
    }
}
