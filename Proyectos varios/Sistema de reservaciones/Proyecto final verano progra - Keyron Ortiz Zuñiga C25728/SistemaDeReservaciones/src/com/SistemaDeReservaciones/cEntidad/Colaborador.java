/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cEntidad;

/**
 *En esta clase se crea al Colaborador, el cual es alguien que trabaja en el 
 * hotel, teniendo sus respectivas caracteristicas y su encapsulamiento
 * 
 * @author Keyron Ortiz Zuñiga
 */
public class Colaborador {

    int idColaborador;
    String nombreColaborador;
    String identificacion;
    String cargo;
    boolean estado;

    public Colaborador() {
    }

    public Colaborador(int idColaborador, String nombreColaborador, String identificacion, String cargo) {
        this.idColaborador = idColaborador;
        this.nombreColaborador = nombreColaborador;
        this.identificacion = identificacion;
        this.cargo = cargo;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "----------------------" + "ID cliente :" + getIdColaborador() + "----------------------"
                + "\nNombre Colaborador :" + nombreColaborador
                + "\nIdentificacion :" + getIdentificacion()
                + "\nCargo :" + cargo
                + "\nEstado :" + estado
                + "\n------------------------------------------------------------";
    }

}
