/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cDatos;

import com.SistemaDeReservaciones.cEntidad.Factura;
import com.SistemaDeReservaciones.cEntidad.Habitaciones;
//import com.SistemaDeReservaciones.cDatos.DtoHabitacionDeLujo;
//import com.SistemaDeReservaciones.cDatos.DtoHabitacionSencilla;
import com.SistemaDeReservaciones.cEntidad.Clientes;
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

/**
 * En esta clase se crea la factura del hotel, haciendo la creacion de archivos
 * para la misma
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class DtoFactura {

    File archivo;

    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pw;

    FileReader fr;
    BufferedReader br;

//    Habitaciones habi;
//    Clientes cliente;
//
//    DtoHabitacionDeLujo dtoHabiDL;
//    DtoHabitacionSencilla dtoHabiS;
//    DtoClientes dtoC;
    public DtoFactura() {
        archivo = new File("src\\com\\SistemaDeReservaciones\\cDatos\\archivos\\factura.txt");
//        habi = new Habitaciones();
//        cliente = new Clientes();
//
//        dtoHabiDL = new DtoHabitacionDeLujo();
//        dtoHabiS = new DtoHabitacionSencilla();
//        dtoC = new DtoClientes();
    }

    /**
     * Este metodo agrega una nueva factura al archivo
     *
     * @param nuevaFactura
     * @param sobreescribir
     * @return
     */
    public boolean agregarFactura(Factura nuevaFactura, boolean sobreescribir) {
        boolean seAgrego = true;

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            fw = new FileWriter(archivo, sobreescribir);
            bw = new BufferedWriter(fw);

            pw = new PrintWriter(bw);

            pw.println(nuevaFactura.getIdFactura() + "/"
                    + nuevaFactura.getIdCliente() + "+"
                    + nuevaFactura.getIdHabitacion() + "?"
                    + nuevaFactura.getNombrePersonaFactura() + "-"
                    + nuevaFactura.getCantidadMasajes() + "*"
                    + nuevaFactura.getCuantasVecesUsoTransporte() + "("
                    + nuevaFactura.getCuantasVecesUsoTelefono() + ")"
                    + nuevaFactura.getCuantasVecesUsoCajaSeguridad() + ":"
                    + nuevaFactura.getPrecioHabitacion() + ";"
                    + nuevaFactura.getPrecioTotal());
            pw.close();

        } catch (IOException e) {
            seAgrego = false;
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return seAgrego;
    }

    /**
     * Este metodo muestra el contenido del archivo
     *
     * @return
     */
    public ArrayList<Factura> consultar() {
        ArrayList<Factura> facturas = new ArrayList<>();

        String temporal = "";
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((temporal = br.readLine()) != null) {

                Factura factu = new Factura();

                factu.setIdFactura(Integer.parseInt(temporal.substring(0, temporal.indexOf("/"))));
                factu.setIdCliente(Integer.parseInt(temporal.substring(temporal.indexOf("/") + 1, temporal.indexOf("+"))));
                factu.setIdHabitacion(Integer.parseInt(temporal.substring(temporal.indexOf("+") + 1, temporal.indexOf("?"))));
                factu.setNombrePersonaFactura(temporal.substring(temporal.indexOf("?") + 1, temporal.indexOf("-")));
                factu.setCantidadMasajes(Integer.parseInt(temporal.substring(temporal.indexOf("-") + 1, temporal.indexOf("*"))));
                factu.setCuantasVecesUsoTransporte(Integer.parseInt(temporal.substring(temporal.indexOf("*") + 1, temporal.indexOf("("))));
                factu.setCuantasVecesUsoTelefono(Integer.parseInt(temporal.substring(temporal.indexOf("(") + 1, temporal.indexOf(")"))));
                factu.setCuantasVecesUsoCajaSeguridad(Integer.parseInt(temporal.substring(temporal.indexOf(")") + 1, temporal.indexOf(":"))));
                factu.setPrecioHabitacion(Integer.parseInt(temporal.substring(temporal.indexOf(":") + 1, temporal.indexOf(";"))));
                factu.setPrecioTotal(Double.parseDouble(temporal.substring(temporal.indexOf(";") + 1, temporal.length())));

                facturas.add(factu);
            }
            // JOptionPane.showMessageDialog(null, listaCliente.toArray());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return facturas;
    }

    /**
     * Este metodo limpia el archivo y lo deja en blanco
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
     * Este metodo elimina por completo una factura
     *
     * @param idFactura
     * @return
     */
    public boolean eliminarFactura(int idFactura) {
        boolean seElimino = false;
        ArrayList<Factura> facturas = consultar();
        try {
            Predicate<Factura> seEncontroId = facturaA -> String.valueOf(facturaA.getIdFactura()).equals(String.valueOf(idFactura));

            seElimino = facturas.removeIf(seEncontroId);

            if (limpiarArchivo()) {
                for (Factura facto : facturas) {
                    agregarFactura(facto, true);
                }
            }
        } catch (Exception e) {

        }
        return seElimino;
    }

    /**
     * Este metodo modifica los datos de una factura si su id coincide
     *
     * @param factuModificada
     * @return
     */
    public boolean modificarFactura(Factura factuModificada) {
        boolean seModifico = true;

        try {
            ArrayList<Factura> facturas = consultar();

            if (!facturas.isEmpty()) { //Verifica que la lista no este vacia

                if (limpiarArchivo()) {

                    for (Factura factu : facturas) {

                        if (factu.getIdFactura() == factuModificada.getIdFactura()) {
                            factu = factuModificada;
                        }
                        agregarFactura(factu, true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return seModifico;
    }

    /**
     * Este metodo evalua si el id recibido por parametro, existe en el archivo
     *
     * @param idFactura
     * @return
     */
    public boolean buscarIdFactura(int idFactura) {
        boolean seEncontro = false;
        ArrayList<Factura> facturas = new ArrayList<>();
        Factura factuEncontrada = new Factura();

        for (Factura c : consultar()) {
            if (c.getIdFactura() == idFactura) {  //c.getNombreCurso().equalsIgnoreCase(nombre)

                factuEncontrada = c;
                seEncontro = true;
            }
        }
        return seEncontro;
    }

    /**
     * Este metodo recibe un nombre por parametro, y si el nombre existe en el
     * archivo, muestra la factura correspondiente
     *
     * @param nombre
     * @return
     */
    public ArrayList<Factura> buscarFacturaPorNombre(String nombre) {
        ArrayList<Factura> facturas = new ArrayList<>();

        for (Factura factu : consultar()) {
            if (factu.getNombrePersonaFactura().contains(nombre)) {
                facturas.add(factu);
            }
        }
        return facturas;
    }

}
