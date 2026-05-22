/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cEntidad;

/**
 *En esta clase se crea el objeto Factura, asignandole los atributos que 
 * contiene un hotel y se hace su encapsulamiento
 * 
 * @author Keyron Ortiz Zuñiga
 */
public class Factura {

    private int idFactura;
    private int idCliente;
    private int idHabitacion;
    private String nombrePersonaFactura;
    private int cantidadMasajes;
    private int cuantasVecesUsoTransporte;
    private int cuantasVecesUsoTelefono;
    private int cuantasVecesUsoCajaSeguridad;
    private int precioHabitacion;
    private double precioTotal;
//    Habitaciones habi;
//    Clientes client;

    public Factura() {
    }

    public Factura(int idFactura, int cantidadMasajes, int cuantasVecesUsoTransporte, int cuantasVecesUsoTelefono, int cuantasVecesUsoCajaSeguridad) {
        this.idFactura = idFactura;
        this.cantidadMasajes = cantidadMasajes;
        this.cuantasVecesUsoTransporte = cuantasVecesUsoTransporte;
        this.cuantasVecesUsoTelefono = cuantasVecesUsoTelefono;
        this.cuantasVecesUsoCajaSeguridad = cuantasVecesUsoCajaSeguridad;
    }

    public Factura(int idFactura, int idCliente, int idHabitacion, String nombrePersonaFactura, int cantidadMasajes, int cuantasVecesUsoTransporte, int cuantasVecesUsoTelefono, int cuantasVecesUsoCajaSeguridad, int precioHabitacion, double precioTotal) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.nombrePersonaFactura = nombrePersonaFactura;
        this.cantidadMasajes = cantidadMasajes;
        this.cuantasVecesUsoTransporte = cuantasVecesUsoTransporte;
        this.cuantasVecesUsoTelefono = cuantasVecesUsoTelefono;
        this.cuantasVecesUsoCajaSeguridad = cuantasVecesUsoCajaSeguridad;
        this.precioHabitacion = precioHabitacion;
        this.precioTotal = precioTotal;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getCantidadMasajes() {
        return cantidadMasajes;
    }

    public void setCantidadMasajes(int cantidadMasajes) {
        this.cantidadMasajes = cantidadMasajes;
    }

    public int getCuantasVecesUsoTransporte() {
        return cuantasVecesUsoTransporte;
    }

    public void setCuantasVecesUsoTransporte(int cuantasVecesUsoTransporte) {
        this.cuantasVecesUsoTransporte = cuantasVecesUsoTransporte;
    }

    public int getCuantasVecesUsoTelefono() {
        return cuantasVecesUsoTelefono;
    }

    public void setCuantasVecesUsoTelefono(int cuantasVecesUsoTelefono) {
        this.cuantasVecesUsoTelefono = cuantasVecesUsoTelefono;
    }

    public int getCuantasVecesUsoCajaSeguridad() {
        return cuantasVecesUsoCajaSeguridad;
    }

    public void setCuantasVecesUsoCajaSeguridad(int cuantasVecesUsoCajaSeguridad) {
        this.cuantasVecesUsoCajaSeguridad = cuantasVecesUsoCajaSeguridad;
    }

    public int getPrecioHabitacion() {
        return precioHabitacion;
    }

    public void setPrecioHabitacion(int precioHabitacion) {
        this.precioHabitacion = precioHabitacion;
    }

    public String getNombrePersonaFactura() {
        return nombrePersonaFactura;
    }

    public void setNombrePersonaFactura(String nombrePersonaFactura) {
        this.nombrePersonaFactura = nombrePersonaFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    /**
     * Metodo toString que muestra los atributos del objeto
     * @return 
     */
    @Override
    public String toString() {
        return "---------------------------------------------------------------\nID Factura :" + idFactura
                + "\nID Cliente :" + idCliente
                + "\nID Habitacion :" + idHabitacion + "\n-----------------------------------------------------------------"
                + "\nNombre a quien es la factura :" + nombrePersonaFactura
                + "\nCantidad de Masajes :" + cantidadMasajes
                + "\nCuantas veces uso el transporte :" + cuantasVecesUsoTransporte
                + "\nCuantas veces uso el telefono :" + cuantasVecesUsoTelefono
                + "\nCuantas veces uso la caja seguridad :" + cuantasVecesUsoCajaSeguridad
                + "\nPrecio habitacion :" + precioHabitacion;
    }

}
