package com.SistemaDeReservaciones.cDatos;

import com.SistemaDeReservaciones.cEntidad.Clientes;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Descripcion : En esta clase se crea una lista de clientes utilizando
 * diferentes objetos y metodos.
 *
 * @author Keyron Ortiz Zuñiga C25728
 * @version 1.0
 */
/**
 * En esta version se implementa el uso de archivos y metodos adaptados a los
 * mismos
 *
 * @author Keyron Ortiz Zuñiga C25728
 * @version 2.0
 */
public class DtoClientes {

    //Archivo
    File archivo;
    //Escritores
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pw;

    FileReader fr;
    BufferedReader br;

    public DtoClientes() {
        archivo = new File("src\\com\\SistemaDeReservaciones\\cDatos\\archivos\\clientes.txt");
    }

    /**
     * En este metodo se agrega un cliente nuevo al archivo
     *
     * @param nuevoCliente
     * @param sobreescribir
     * @return nuevoCliente
     */
    public boolean agregarCliente(Clientes nuevoCliente, boolean sobreescribir) {
        boolean seAgrego = true;

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            fw = new FileWriter(archivo, sobreescribir);
            bw = new BufferedWriter(fw);

            pw = new PrintWriter(bw);

            nuevoCliente.setEstadoDelUsuario(true);

            pw.println(nuevoCliente.getIdCliente() + "/"
                    + nuevoCliente.getNombre() + "*"
                    + nuevoCliente.getApellidoUno() + "+"
                    + nuevoCliente.getApellidoDos() + "-"
                    + nuevoCliente.getIdentificacion() + "."
                    + nuevoCliente.getEstadoDelUsuario() + ":"
                    + nuevoCliente.getCantidadDeVisitas());
            pw.close();

        } catch (IOException e) {
            seAgrego = false;
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return seAgrego;
    }

    /**
     * En este metodo se muestra lo que hay en el archivo de cliente
     *
     * @return listaCliente
     */
    public ArrayList<Clientes> consultar() {
        ArrayList<Clientes> listaCliente = new ArrayList<>();

        String temporal = "";
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((temporal = br.readLine()) != null) {

                Clientes cliente = new Clientes();

                cliente.setIdCliente(Integer.parseInt(temporal.substring(0, temporal.indexOf("/"))));
                cliente.setNombre(temporal.substring(temporal.indexOf("/") + 1, temporal.indexOf("*")));
                cliente.setApellidoUno(temporal.substring(temporal.indexOf("*") + 1, temporal.indexOf("+")));
                cliente.setApellidoDos(temporal.substring(temporal.indexOf("+") + 1, temporal.indexOf("-")));
                cliente.setIdentificacion(temporal.substring(temporal.indexOf("-") + 1, temporal.indexOf(".")));
                cliente.setEstadoDelUsuario(Boolean.parseBoolean(temporal.substring(temporal.indexOf(".") + 1, temporal.indexOf(":"))));
                cliente.setCantidadDeVisitas(Integer.parseInt(temporal.substring(temporal.indexOf(":") + 1, temporal.length())));
                listaCliente.add(cliente);
            }
            // JOptionPane.showMessageDialog(null, listaCliente.toArray());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listaCliente;
    }

    /**
     * Este metodo limpia el archivo dejandolo en blanco
     *
     * @return seLimpio
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
     * En esta metodo se modifican los datos de un cliente por completo,
     * haciendo uso del metodo limpiar
     *
     * @param clienteModificado
     * @return
     */
    public boolean modificarCliente(Clientes clienteModificado) {
        boolean seModifico = true;

        try {
            ArrayList<Clientes> listaCliente = consultar();

            if (!listaCliente.isEmpty()) { //Verifica que la lista no este vacia

                if (limpiarArchivo()) {

                    for (Clientes c : listaCliente) {

                        if (c.getIdCliente() == clienteModificado.getIdCliente()) {
                            c = clienteModificado;
                        }
                        agregarCliente(c, true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return seModifico;
    }

    /**
     * En este metodo se elimina un cliente por completo haciendo uso del metodo
     * limpiar y del predicate
     *
     * @param idCliente
     * @return seElimino
     */
    public boolean eliminarCliente(int idCliente) {
        boolean seElimino = false;
        ArrayList<Clientes> listaCliente = consultar();
        try {
            Predicate<Clientes> seEncontroId = clienteA -> String.valueOf(clienteA.getIdCliente()).equals(String.valueOf(idCliente));

            seElimino = listaCliente.removeIf(seEncontroId);
            boolean seLimpio = limpiarArchivo();

            if (seLimpio) {
                for (Clientes nuevo : listaCliente) {
                    agregarCliente(nuevo, true);
                }
            }
        } catch (Exception e) {

        }
        return seElimino;
    }

    /**
     * Este metodo verifica si el id del cliente que se recibe como parametro
     * existe en el archivo
     *
     * @param idCliente
     * @return
     */
    public boolean buscarIdCliente(int idCliente) {
        boolean seEncontro = false;
        ArrayList<Clientes> listaCliente = new ArrayList<>();
        Clientes clienteEncontrado = new Clientes();

        for (Clientes c : consultar()) {
            if (c.getIdCliente() == idCliente) {  //c.getNombreCurso().equalsIgnoreCase(nombre)

                clienteEncontrado = c;
                seEncontro = true;
            }
        }
        return seEncontro;
    }

    /**
     * En este metodo se saca el nombre del cliente que coincide con el id que
     * se recibe como parametro
     *
     * @param idCliente
     * @return
     */
    public String sacarNombreCliente(int idCliente) {
        String salida = "";
        ArrayList<Clientes> listaCliente = consultar();
        Clientes nombreCliente = new Clientes();

        for (Clientes c : listaCliente) {
            if (c.getIdCliente() == idCliente) {
                salida = c.getNombre() + " " + c.getApellidoUno() + " " + c.getApellidoDos();
                nombreCliente = c;
            }
        }
        return salida;
    }

    public boolean verificarNombreCliente(String nombre, String apellidoUno, String apellidoDos) {
        boolean seEncontro = false;

        ArrayList<Clientes> listaCliente = new ArrayList<>();
        Clientes clienteEncontrado = new Clientes();

        for (Clientes c : consultar()) {
            if (c.getNombre().equalsIgnoreCase(nombre)
                    && c.getApellidoUno().equalsIgnoreCase(apellidoUno)
                    && c.getApellidoDos().equalsIgnoreCase(apellidoDos)) {
                clienteEncontrado = c;
                seEncontro = true;
            }
        }
        return seEncontro;
    }

    public boolean verificarIdentificacionCliente(String identificacion) {
        boolean seEncontro = false;

        ArrayList<Clientes> listaCliente = new ArrayList<>();
        Clientes clienteEncontrado = new Clientes();

        for (Clientes c : consultar()) {
            if (c.getIdentificacion().equalsIgnoreCase(identificacion)) {
                clienteEncontrado = c;
                seEncontro = true;
            }
        }
        return seEncontro;
    }

    public boolean verificarClienteFrecuente(int cantidadVisitas) {
        boolean esFrecuente = false;

        ArrayList<Clientes> listaCliente = new ArrayList<>();
        Clientes frecuente = new Clientes();

        for (Clientes c : consultar()) {
            if (c.getCantidadDeVisitas() >= 4) {
                frecuente = c;
                esFrecuente = true;
            }
        }
        return esFrecuente;
    }

    public ArrayList<Clientes> listarClientesFrecuentes() {
        ArrayList<Clientes> listaClientes = new ArrayList<>();

        for (Clientes c : consultar()) {
            if (c.getCantidadDeVisitas() >= 4) {
                listaClientes.add(c);
            }
        }
        return listaClientes;
    }

    public int obtenerVisitas(int idCliente) {
        int cantidadVisitas = 0;
        ArrayList<Clientes> listaCliente = consultar();
        Clientes nombreCliente = new Clientes();

        for (Clientes c : listaCliente) {
            if (c.getIdCliente() == idCliente) {
                cantidadVisitas = c.getCantidadDeVisitas();
                nombreCliente = c;
            }
        }
        return cantidadVisitas;
    }
}
