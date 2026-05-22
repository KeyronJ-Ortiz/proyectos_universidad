/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cLogicaNegocio;

import com.SistemaDeReservaciones.cDatos.DtoFactura;
import com.SistemaDeReservaciones.cLogicaNegocio.HabitacionDeLujoLN;
import com.SistemaDeReservaciones.cLogicaNegocio.HabitacionSencillaLN;
import com.SistemaDeReservaciones.cEntidad.Factura;
import com.SistemaDeReservaciones.cEntidad.Habitaciones;
import java.util.ArrayList;

/**
 * Esta clase hace que se conecten los datos de factura con la capa de
 * presentacion
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class FacturaLN {

    DtoFactura dtoF;
    Factura factu;
    Habitaciones habi;
    HabitacionDeLujoLN habiDL;
    HabitacionSencillaLN habiS;
    ClientesLN clienteLN;

    public FacturaLN() {
        dtoF = new DtoFactura();
        habiDL = new HabitacionDeLujoLN();
        habiS = new HabitacionSencillaLN();
        clienteLN = new ClientesLN();

        factu = new Factura();
        habi = new Habitaciones();
    }

    /**
     * Este metodo agrega una factura al archivo
     *
     * @param nuevaFactura
     * @param sobreescribir
     * @return
     */
    public boolean agregarFactura(Factura nuevaFactura, boolean sobreescribir) {
        return dtoF.agregarFactura(nuevaFactura, sobreescribir);
    }

    /**
     * Este metodo muestra los datos del archivo
     *
     * @return
     */
    public ArrayList<Factura> consultar() {
        return dtoF.consultar();
    }

    /**
     * Este metodo elimina una factura por completo
     *
     * @param idFactura
     * @return
     */
    public boolean eliminarFactura(int idFactura) {
        return dtoF.eliminarFactura(idFactura);
    }

    /**
     * Este metodo modifica una factura por medio de un id
     *
     * @param factuModificada
     * @return
     */
    public boolean modificar(Factura factuModificada) {
        return dtoF.modificarFactura(factuModificada);
    }

    /**
     * Este metodo muestra los datos de una factura basandose en el nombre de un
     * cliente
     *
     * @param nombre
     * @return
     */
    public ArrayList<Factura> buscarFacturaPorNombre(String nombre) {
        return dtoF.buscarFacturaPorNombre(nombre);
    }

    /**
     * Este metodo verifica si el id de la factura recibido por parametro existe
     *
     * @param idFactura
     * @return
     */
    public boolean buscarFacturaId(int idFactura) {
        return dtoF.buscarIdFactura(idFactura);
    }

    public int obtenerVisitas(int idCliente) {
        return clienteLN.obtenerCantidadVisitas(idCliente);
    }

    /**
     * Este metodo calcula la cantidad total que va pagar un cliente basandose
     * en las veces que consumio un servicio y la habitacion que reservo
     *
     * @param masajes
     * @param transporte
     * @param cajaSeguridad
     * @param telefono
     * @param precioHabitacion
     * @return
     */
    public int calcularUsos(int masajes, int transporte, int cajaSeguridad, int telefono, int precioHabitacion) {
        int precioTotal = 0;
        factu.setCantidadMasajes(masajes);
        factu.setCuantasVecesUsoTransporte(transporte);
        factu.setCuantasVecesUsoCajaSeguridad(cajaSeguridad);
        factu.setCuantasVecesUsoTelefono(telefono);
        habi.setCostoDeHabitacion(precioHabitacion);

        int totalMasajes = factu.getCantidadMasajes() * 15000;

        int totalUsoTransporte = factu.getCuantasVecesUsoTransporte() * 2000;

        int totalUsoCajaSeguridad = factu.getCuantasVecesUsoCajaSeguridad() * 7000;

        int totalUsoTelefono = factu.getCuantasVecesUsoTelefono() * 500;

        int totalPrecioHabitacion = habi.getCostoDeHabitacion();

        precioTotal = (int) ((int) (totalMasajes + totalUsoTransporte + totalUsoCajaSeguridad + totalUsoTelefono + totalPrecioHabitacion) * 1.13);
//        if (cantidadVisitas >= 4) {
//            precioTotal = (int) (precioTotal * 0.85);
//        }
        return precioTotal;
    }

    /**
     * Este metodo asigna un id de forma automatica
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
     * Este metodo verifica si se puede hacer una factura a una habitacion de
     * lujo
     *
     * @param id
     * @return
     */
    public boolean verificarFacturableDeLujo(int id) {
        return habiDL.verificarFacturable(id);
    }

    /**
     * Este metodo verifica si es una habitacion de lujo
     *
     * @param id
     * @return
     */
    public boolean verificarHabitacionDeLujo(int id) {
        return habiDL.verificarHabitacionDeLujo(id);
    }

    /**
     * Este metodo verifica si el id recibido como parametro existe en el
     * archivo
     *
     * @param id
     * @return
     */
    public boolean buscarIdHabitacionDeLujo(int id) {
        return habiDL.buscarHabitacion(id);
    }

    /**
     * Este metodo obtiene el precio de una habitacion lujosa basado en su id
     *
     * @param id
     * @return
     */
    public int obtenerPrecioHabitacionLujosa(int id) {
        return habiDL.sacarPrecioHabitacion(id);
    }

    /**
     * Obtiene el nombre de un cliente en una habitacion de lujo
     *
     * @param idHabitacion
     * @return
     */
    public String obtenerNombreLujo(int idHabitacion) {
        return habiDL.obtenerNombreClienteLujo(idHabitacion);
    }

    /**
     * Este metodo verifica si una habitacion sencilla se puede facturar
     *
     * @param id
     * @return
     */
    public boolean verificarFacturableSencilla(int id) {
        return habiS.verificarFacturable(id);
    }

    /**
     * Este metodo verifica la habitacion que coincide con el id es sencilla o
     * no
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarHabitacionSencilla(int idHabitacion) {
        return habiS.verificarHabitacion(idHabitacion);
    }

    /**
     * Este metodo busca si existe el id que se recibe como parametro
     *
     * @param idHabitacion
     * @return
     */
    public boolean buscarIdHabitacionSencilla(int idHabitacion) {
        return habiS.buscarHabitacion(idHabitacion);
    }

    /**
     * Este metodo obtiene el precio de una habitacion sencilla
     *
     * @param idHabitacion
     * @return
     */
    public int obtenerPrecioHabitacionSencilla(int idHabitacion) {
        return habiS.sacarPrecioHabitacionSencilla(idHabitacion);
    }

    /**
     * Este metodo obtiene el nombre de la persona que reserva una habitacion
     *
     * @param idHabitacion
     * @return
     */
    public String obtenerNombreSencilla(int idHabitacion) {
        return habiS.obtenerNombreClienteSencilla(idHabitacion);
    }

    /**
     * Este metodo verifica si el de un cliente existe
     *
     * @param id
     * @return
     */
    public boolean buscarIdCliente(int id) {
        return clienteLN.buscarIdCliente(id);
    }

    public String generarReporteUsoHabitacion() {
        String salida = "";
        if (habiS.calcularUsoHabitacionesSencillas() == habiDL.calcularUsoDeHabitacionesDeLujo()) {
            salida = "Las habitaciones se usan la misma cantidad de veces";

        } else if (habiS.calcularUsoHabitacionesSencillas() < habiDL.calcularUsoDeHabitacionesDeLujo()) {
            salida = "Las habitaciones mas usadas son las Habitaciones De Lujo"
                    + "\nSe usan una cantidad de :" + habiDL.calcularUsoDeHabitacionesDeLujo() + " veces";

        } else {
            salida = "Las habitaciones mas usadas son las Habitaciones Sencillas"
                    + "\nSe usan una cantidad de :" + habiS.calcularUsoHabitacionesSencillas() + " veces";
        }
        return salida;
    }

    public String calcularIngresosTotalesPorReserva() {
        String salida = "";
        int cantidadHabitacionesSencillas = habiS.mostrarReservasUOcupadas().size();
        int cantidadHabitacionesDeLujo = habiDL.mostrarReservasUOcupadas().size();

        int cantidadTotalHabitaciones = cantidadHabitacionesSencillas + cantidadHabitacionesDeLujo;

        int precioTotalHabitaciones = habiS.mostrarMontoTotalUsoHabitaciones() + habiDL.mostrarMontoTotalUsoHabitaciones();

        int montoTotal = precioTotalHabitaciones / cantidadTotalHabitaciones;

        salida = "El ingreso total de las habitaciones es : " + montoTotal;

        return salida;
    }
}
