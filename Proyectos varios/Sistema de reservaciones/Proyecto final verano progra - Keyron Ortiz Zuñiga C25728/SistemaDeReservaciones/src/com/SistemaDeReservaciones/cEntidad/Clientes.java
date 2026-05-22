package com.SistemaDeReservaciones.cEntidad;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Descripcion : En esta clase se toman en cuenta los atributos del cliente y se
 * hace su respectivo encansulamiento.
 *
 * @author Keyron Ortiz Zuñiga C25728
 * @version 1.0 fecha : 25-01-23
 */
public class Clientes {

    private String nombre;
    private String apellidoUno;
    private String apellidoDos;
    private int idCliente;
    private String identificacion;
    private boolean estadoDelUsuario;
    private int cantidadDeVisitas;

    public Clientes() {
    }

    public Clientes(int idCliente, String nombre, String apellidoUno, String apellidoDos,
            String identificacion) {
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.idCliente = idCliente;
        this.identificacion = identificacion;
    }

    public Clientes(int idCliente, String nombre, String apellidoUno, String apellidoDos, String identificacion, int cantidadDeVisitas) {
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.idCliente = idCliente;
        this.identificacion = identificacion;
        //this.estadoDelUsuario = estadoDelUsuario;
        this.cantidadDeVisitas = cantidadDeVisitas;
    }

    public Clientes(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoUno() {
        return apellidoUno;
    }

    public void setApellidoUno(String apellidoUno) {
        this.apellidoUno = apellidoUno;
    }

    public String getApellidoDos() {
        return apellidoDos;
    }

    public void setApellidoDos(String apellidoDos) {
        this.apellidoDos = apellidoDos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setEstadoDelUsuario(boolean estadoDelUsuario) {
        this.estadoDelUsuario = estadoDelUsuario;
    }

    public boolean getEstadoDelUsuario() {
        return estadoDelUsuario;
    }

    public int getCantidadDeVisitas() {
        return cantidadDeVisitas;
    }

    public void setCantidadDeVisitas(int cantidadDeVisitas) {
        this.cantidadDeVisitas = cantidadDeVisitas;
    }

    /**
     * Metodo toSting que devuelve la informacion de la clase
     *
     * @return salida
     */
    @Override
    public String toString() {
        String salida = "----------------------" + "ID cliente : " + getIdCliente() + " ----------------------";
        salida += "\nNombre del cliente: " + getNombre()
                + "\nApellidos : " + getApellidoUno() + " " + getApellidoDos()
                + "\nIdentificacion : " + getIdentificacion()
                + "\nCantidad de visitas al año :" + getCantidadDeVisitas()
                + "\nEstado del usuario : " + getEstadoDelUsuario() + "\n--------------------------------------------------------------";
        return salida;
    }
}
