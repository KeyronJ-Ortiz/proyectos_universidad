package com.SistemaDeReservaciones.cDatos;

import com.SistemaDeReservaciones.cEntidad.Habitaciones;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Descripcion : En esta clase se hacen reservaciones de una habitacion sencilla
 * mediante distintos metodos.
 *
 * @author Keyron Ortiz Zuñiga C25728
 * @version 1.0 fecha : 25-01-23
 */
/**
 * Nueva version aplicando la clase io para utilizar la informacion archivos
 *
 * @author Keyron Ortiz Zuñiga C25728
 * @version 2.0
 */
public class DtoHabitacionSencilla {

    File archivo;

    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pw;

    FileReader fr;
    BufferedReader br;

    public DtoHabitacionSencilla() {
        archivo = new File("src\\com\\SistemaDeReservaciones\\cDatos\\archivos\\habitacionSencilla.txt");
    }

    /**
     * En este metodo se agregan habitaciones sencillas
     *
     * @param nuevaHabitacion
     * @param sobreescribir
     * @return
     */
    public boolean agregarHabitaciones(Habitaciones nuevaHabitacion, boolean sobreescribir) {
        boolean seAgrego = true;
        try {

            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            fw = new FileWriter(archivo, sobreescribir);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(nuevaHabitacion.getIdHabitacion() + "/"
                    + nuevaHabitacion.getCostoDeHabitacion() + "*"
                    + nuevaHabitacion.getCantidadMaximaDePersonas() + "+"
                    + nuevaHabitacion.getTipoDeCama() + "-"
                    + nuevaHabitacion.getAireAcondicionado() + "."
                    + nuevaHabitacion.getEstadoDeHabitacion() + ":"
                    + nuevaHabitacion.getPersonaQueReserva());

            pw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return seAgrego;
    }

    /**
     * Este metodo muestra el contenido del archivo
     *
     * @return
     */
    public ArrayList<Habitaciones> consultar() {
        ArrayList<Habitaciones> listaHabitaciones = new ArrayList<>();
        String temporal = "";

        try {

            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((temporal = br.readLine()) != null) {

                Habitaciones habi = new Habitaciones();

                habi.setIdHabitacion(Integer.parseInt(temporal.substring(0, temporal.indexOf("/"))));
                habi.setCostoDeHabitacion(Integer.parseInt(temporal.substring(temporal.indexOf("/") + 1, temporal.indexOf("*"))));
                habi.setCantidadMaximaDePersonas(Integer.parseInt(temporal.substring(temporal.indexOf("*") + 1, temporal.indexOf("+"))));
                habi.setTipoDeCama(temporal.substring(temporal.indexOf("+") + 1, temporal.indexOf("-")));
                habi.setAireAcondicionado(Boolean.parseBoolean(temporal.substring(temporal.indexOf("-") + 1, temporal.indexOf("."))));
                habi.setEstadoDeHabitacion(temporal.substring(temporal.indexOf(".") + 1, temporal.indexOf(":")));
                habi.setPersonaQueReserva(temporal.substring(temporal.indexOf(":") + 1, temporal.length()));

                listaHabitaciones.add(habi);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return listaHabitaciones;
    }

    /**
     * Este metodo limpia completamente el archivo
     *
     * @return
     */
    public boolean limpiarArchivo() {
        boolean seLimpio = true;
        try {
            fw = new FileWriter(archivo);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.print("");
            pw.close();

        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
            seLimpio = false;
        }
        return seLimpio;
    }

    /**
     * Este metodo elimina por completo una habitacion basandose en su id
     *
     * @param idHabitacion
     * @return
     */
    public boolean eliminarHabitacion(int idHabitacion) {
        boolean seElimino = false;
        try {
            ArrayList<Habitaciones> listaHabitacion = consultar();

            Predicate<Habitaciones> seEncontro = habiA -> String.valueOf(habiA.getIdHabitacion()).equals(String.valueOf(idHabitacion));

            seElimino = listaHabitacion.removeIf(seEncontro);

            if (limpiarArchivo()) {
                for (Habitaciones h : listaHabitacion) {
                    agregarHabitaciones(h, true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
            seElimino = false;
        }
        return seElimino;
    }

    /**
     * Este metodo modifica los datos de una habitacion si el id coincide
     *
     * @param habitacionModificada
     * @return
     */
    public boolean modificarHabitacion(Habitaciones habitacionModificada) {
        boolean seModifico = true;

        try {
            ArrayList<Habitaciones> listaHabitaciones = consultar();

            if (!listaHabitaciones.isEmpty()) { //Verifica que la lista no este vacia

                if (limpiarArchivo()) {

                    for (Habitaciones habi : listaHabitaciones) {

                        if (habi.getIdHabitacion() == habitacionModificada.getIdHabitacion()) {
                            habi = habitacionModificada;
                        }
                        agregarHabitaciones(habi, true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            seModifico = false;
        }
        return seModifico;
    }

    /**
     * Este metodo evalua si una habitacion es facturable basandose en el estado
     * que se encuentre
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarHabitacionFacturableSencilla(int idHabitacion) {
        boolean esFacturable = false;

        ArrayList<Habitaciones> listaHabitaciones = consultar();
        Habitaciones habiReservada = new Habitaciones();
        for (Habitaciones h : listaHabitaciones) {
            if (h.getIdHabitacion() == idHabitacion) {
                if (h.getEstadoDeHabitacion().equals("Reservada")
                        || h.getEstadoDeHabitacion().equals("Ocupada")) {
                    esFacturable = true;
                    habiReservada = h;
                }
            }
        }
        return esFacturable;
    }

    /**
     * Este metodo verifica mediante un id si la habitacion esta disponible
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarHabitacionDisponible(int idHabitacion) {
        boolean estaDisponible = false;

        ArrayList<Habitaciones> listaHabitaciones = consultar();
        Habitaciones habiReservada = new Habitaciones();
        for (Habitaciones h : listaHabitaciones) {
            if (h.getIdHabitacion() == idHabitacion) {
                if (h.getEstadoDeHabitacion().equalsIgnoreCase("Disponible")) {
                    estaDisponible = true;
                    habiReservada = h;
                }
            }
        }
        return estaDisponible;
    }

    /**
     * Este metodo verifica basandose en el id que recibe como parametro si el
     * id existe en el archivo
     *
     * @param idHabi
     * @return
     */
    public boolean buscarIdHabitacionSencilla(int idHabi) {
        boolean seEncontro = false;
        ArrayList<Habitaciones> listaHabitacion = new ArrayList<>();
        Habitaciones habitacionEncontrada = new Habitaciones();

        for (Habitaciones h : consultar()) {
            if (h.getIdHabitacion() == idHabi) {  //c.getNombreCurso().equalsIgnoreCase(nombre)

                habitacionEncontrada = h;
                seEncontro = true;
            }
        }
        return seEncontro;
    }

    /**
     * Este metodo verifica basandose en el id que recibe como parametro si la
     * habitacion contiene una caracteristica que la diferencia de la lujosa
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarHabitacionSencilla(int idHabitacion) {
        boolean seEncontro = false;
        ArrayList<Habitaciones> listaHabitacion = new ArrayList<>();
        Habitaciones habitacionDeLujo = new Habitaciones();

        for (Habitaciones h : consultar()) {
            if (h.getIdHabitacion() == idHabitacion) {
                if (h.getCostoDeHabitacion() < 35000) {
                    habitacionDeLujo = h;
                    seEncontro = true;
                }
            }
        }
        return seEncontro;
    }

    /**
     * Este metodo obtiene el precio de la habitacion evaluando si existe el id
     * que recibe como parametro
     *
     * @param idHabitacion
     * @return
     */
    public int sacarPrecioHabitacion(int idHabitacion) {
        int precio = 0;
        ArrayList<Habitaciones> listaHabitacion = consultar();
        Habitaciones precioHabitacion = new Habitaciones();

        for (Habitaciones h : listaHabitacion) {
            if (h.getIdHabitacion() == idHabitacion) {
                precio = h.getCostoDeHabitacion();
                precioHabitacion = h;
            }
        }
        return precio;
    }

    /**
     * Este metodo obtiene el nombre del cliente mediante el id que recibe como
     * parametro
     *
     * @param idHabitacion
     * @return
     */
    public String obtenerNombreCliente(int idHabitacion) {
        String salida = "";

        ArrayList<Habitaciones> listaHabitacion = consultar();
        Habitaciones nombrePersona = new Habitaciones();

        for (Habitaciones h : listaHabitacion) {
            if (h.getIdHabitacion() == idHabitacion) {
                salida = h.getPersonaQueReserva();
                nombrePersona = h;
            }
        }
        return salida;
    }

    /**
     * Este metodo muestra las habitaciones que estan con el estado de reservada
     *
     * @return
     */
    public ArrayList<Habitaciones> mostrarReservas() {
        ArrayList<Habitaciones> listaHabitaciones = new ArrayList<>();

        for (Habitaciones habi : consultar()) {
            if (habi.getEstadoDeHabitacion().contains("Reservada")) {
                listaHabitaciones.add(habi);
            }
        }
        return listaHabitaciones;
    }

    public ArrayList<Habitaciones> mostrarReservasUOcupadas() {
        ArrayList<Habitaciones> listaHabitaciones = new ArrayList<>();

        for (Habitaciones habi : consultar()) {
            if (habi.getEstadoDeHabitacion().contains("Reservada")
                    || habi.getEstadoDeHabitacion().contains("Ocupada")) {
                listaHabitaciones.add(habi);
            }
        }
        return listaHabitaciones;
    }

    public int cantidadDeUsoHabitaciones() {
        int cantidad = 0;
        cantidad = mostrarReservasUOcupadas().size();
        return cantidad;
    }

    public int mostrarEntradasTotalesReservasUOcupadas() {
        int total = 0;
        for (Habitaciones habi : consultar()) {
            if (habi.getEstadoDeHabitacion().contains("Reservada")
                    || habi.getEstadoDeHabitacion().contains("Ocupada")) {
                total += habi.getCostoDeHabitacion();
            }
        }
        return total;
    }

}
