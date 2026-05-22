/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cDatos;

import com.SistemaDeReservaciones.cEntidad.Habitaciones;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

/**
 * En esta clase se crea el archivo de habitacion lujosa con sus rrespectivos
 * datos
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class DtoHabitacionDeLujo {

    File archivo;

    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pw;

    FileReader fr;
    BufferedReader br;

    public DtoHabitacionDeLujo() {
        archivo = new File("src\\com\\SistemaDeReservaciones\\cDatos\\archivos\\habitacionDeLujo.txt");

    }

    /**
     * En este metodo se agrega una habitaciones de lujo
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return seAgrego;
    }

    /**
     * Este metodo muestra los datos del archivo
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return listaHabitaciones;
    }

    /**
     * Este metodo limpia por completo el archivo
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

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            seLimpio = false;
        }
        return seLimpio;
    }

    /**
     * Este metodo modifica los datos de una habitacion basandose en su id
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
        }
        return seModifico;
    }

    /**
     * Este metodo elimina por completo una habitacion mediante su id
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
     * Este metodo evalua si una habituacion esta supuesta a ser facturable,
     * siendo factuable si la habitacion posee estado reservada u ocupada,
     * haciendo estas verificaciones mediante el id que recibe como parametro
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarHabitacionFacturable(int idHabitacion) {
        boolean esFacturable = false;

        ArrayList<Habitaciones> listaHabitaciones = consultar();
        Habitaciones habiReservada = new Habitaciones();
        for (Habitaciones h : listaHabitaciones) {
            if (h.getIdHabitacion() == idHabitacion) {
                if (h.getEstadoDeHabitacion().equalsIgnoreCase("Reservada")
                        || h.getEstadoDeHabitacion().equalsIgnoreCase("Ocupada")) {
                    esFacturable = true;
                    habiReservada = h;
                }
            }
        }
        return esFacturable;
    }

    /**
     * Este metodo mediante el id que recibe como parametro, evalua si la
     * habitacion con el mismo id posee el estado de disponible
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
     * Este metodo evalua si el id que se recibio como parametro existe en el
     * archivo
     *
     * @param idHabi
     * @return
     */
    public boolean buscarIdHabitacion(int idHabi) {
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
     * Este metodo evalua basandose en el id si una habitacion es de lujo,
     * basandose en su precio
     *
     * @param idHabitacion
     * @return
     */
    public boolean verificarHabitacionDeLujo(int idHabitacion) {
        boolean seEncontro = false;
        ArrayList<Habitaciones> listaHabitacion = new ArrayList<>();
        Habitaciones habitacionDeLujo = new Habitaciones();

        for (Habitaciones h : consultar()) {
            if (h.getIdHabitacion() == idHabitacion) {
                if (h.getCostoDeHabitacion() >= 35000) {
                    habitacionDeLujo = h;
                    seEncontro = true;
                }
            }
        }
        return seEncontro;
    }

    /**
     * Este metodo basandose en el id que recibe como parametro extrae el precio
     * de la habitacion que coincide
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
     * Este metodo basandose en un id, extrae el nombre del cliente que coincide
     *
     * @param id
     * @return
     */
    public String obtenerNombreCliente(int id) {
        String salida = "";

        ArrayList<Habitaciones> listaHabitacion = consultar();
        Habitaciones nombrePersona = new Habitaciones();

        for (Habitaciones h : listaHabitacion) {
            if (h.getIdHabitacion() == id) {
                salida = h.getPersonaQueReserva();
                nombrePersona = h;
            }
        }
        return salida;
    }

    /**
     * Este metodo muestra todas las habitaciones que estan reservadas
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
        // ArrayList<Habitaciones> listaHabitaciones = new ArrayList<>();
        int total = 0;
        for (Habitaciones habi : consultar()) {
            if (habi.getEstadoDeHabitacion().contains("Reservada")
                    || habi.getEstadoDeHabitacion().contains("Ocupada")) {
                total += habi.getCostoDeHabitacion();
                //listaHabitaciones.add(habi);
            }
        }
        return total;
    }
}
