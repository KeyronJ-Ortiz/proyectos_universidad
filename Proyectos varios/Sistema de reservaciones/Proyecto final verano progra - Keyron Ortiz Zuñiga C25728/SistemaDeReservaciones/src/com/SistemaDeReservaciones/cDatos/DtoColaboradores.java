/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cDatos;

import com.SistemaDeReservaciones.cEntidad.Colaborador;
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
 * En clase se crean los datos mediante archivos de colaboradores
 *
 * @author Keyron Ortiz Zuñiga C25728
 */
public class DtoColaboradores {

    //Archivo
    File archivo;
    //Escritores
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter pw;

    FileReader fr;
    BufferedReader br;

    public DtoColaboradores() {
        archivo = new File("src\\com\\SistemaDeReservaciones\\cDatos\\archivos\\colaboradores.txt");
    }

    /**
     * En este metodo se agrega un colaborador nuevo al archivo
     *
     * @param nuevoColab
     * @param sobreescribir
     * @return nuevoCliente
     */
    public boolean agregarColaborador(Colaborador nuevoColab, boolean sobreescribir) {
        boolean seAgrego = true;

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            fw = new FileWriter(archivo, sobreescribir);
            bw = new BufferedWriter(fw);

            pw = new PrintWriter(bw);

            nuevoColab.setEstado(true);

            pw.println(nuevoColab.getIdColaborador() + "/"
                    + nuevoColab.getNombreColaborador() + "-"
                    + nuevoColab.getIdentificacion() + "*"
                    + nuevoColab.getCargo() + "+"
                    + nuevoColab.getEstado());
            pw.close();
            //}else{
            //JOptionPane.showMessageDialog(null, "La lista esta a su maximo, intentelo mas tarde...");
            //}
        } catch (IOException e) {
            seAgrego = false;
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return seAgrego;
    }

    /**
     * En este metodo se muestran los datos que hay en el archivo colaborador
     *
     * @return
     */
    public ArrayList<Colaborador> consultar() {
        ArrayList<Colaborador> listaColab = new ArrayList<>();

        String temporal = "";
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            while ((temporal = br.readLine()) != null) {

                Colaborador colab = new Colaborador();

                colab.setIdColaborador(Integer.parseInt(temporal.substring(0, temporal.indexOf("/"))));
                colab.setNombreColaborador(temporal.substring(temporal.indexOf("/") + 1, temporal.indexOf("-")));
                colab.setIdentificacion(temporal.substring(temporal.indexOf("-") + 1, temporal.indexOf("*")));
                colab.setCargo(temporal.substring(temporal.indexOf("*") + 1, temporal.indexOf("+")));
                colab.setEstado(Boolean.parseBoolean(temporal.substring(temporal.indexOf("+") + 1, temporal.length())));

                listaColab.add(colab);
            }
            // JOptionPane.showMessageDialog(null, listaCliente.toArray());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listaColab;
    }

    /**
     * Este metodo limpia el archivo dejandolo en blanco
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
     * En esta metodo se modifica un colaborador mediante un id
     *
     * @param colabModificado
     * @return
     */
    public boolean modificarColab(Colaborador colabModificado) {
        boolean seModifico = true;

        try {
            ArrayList<Colaborador> listaColab = consultar();

            if (!listaColab.isEmpty()) {

                if (limpiarArchivo()) {

                    for (Colaborador c : listaColab) {

                        if (c.getIdColaborador() == colabModificado.getIdColaborador()) {
                            c = colabModificado;
                        }
                        agregarColaborador(c, true);
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return seModifico;
    }

    /**
     * En este metodo se elimina por completo un colaborador mediante un id
     *
     * @param idColab
     * @return
     */
    public boolean eliminarColab(int idColab) {
        boolean seElimino = false;
        ArrayList<Colaborador> listaColab = consultar();
        try {
            Predicate<Colaborador> seEncontroId = colabA -> String.valueOf(colabA.getIdColaborador()).equals(String.valueOf(idColab));

            seElimino = listaColab.removeIf(seEncontroId);
            boolean seLimpio = limpiarArchivo();

            if (seLimpio) {
                for (Colaborador nuevo : listaColab) {
                    agregarColaborador(nuevo, true);
                }
            }
        } catch (Exception e) {

        }
        return seElimino;
    }

    public boolean buscarIdColaborador(int idColab) {
        boolean seEncontro = false;
        ArrayList<Colaborador> listaColab = new ArrayList<>();
        Colaborador colaboradorEncontrado = new Colaborador();

        for (Colaborador c : consultar()) {
            if (c.getIdColaborador() == idColab) {  //c.getNombreCurso().equalsIgnoreCase(nombre)

                colaboradorEncontrado = c;
                seEncontro = true;
            }
        }
        return seEncontro;
    }

    public boolean verificarIdentificacion(String identificacion) {
        boolean seEncontro = false;

        ArrayList<Colaborador> listaCliente = new ArrayList<>();
        Colaborador colaboradorEncontrado = new Colaborador();

        for (Colaborador c : consultar()) {
            if (c.getIdentificacion().equalsIgnoreCase(identificacion)) {
                colaboradorEncontrado = c;
                seEncontro = true;
            }
        }
        return seEncontro;
    }
}
