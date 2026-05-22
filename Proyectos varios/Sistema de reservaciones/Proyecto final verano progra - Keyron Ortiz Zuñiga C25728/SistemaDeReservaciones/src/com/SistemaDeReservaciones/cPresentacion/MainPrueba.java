/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SistemaDeReservaciones.cPresentacion;

import com.SistemaDeReservaciones.cDatos.DtoClientes;
import com.SistemaDeReservaciones.cDatos.DtoFactura;
import com.SistemaDeReservaciones.cDatos.DtoHabitacionDeLujo;
import com.SistemaDeReservaciones.cDatos.DtoHabitacionSencilla;
import com.SistemaDeReservaciones.cEntidad.Clientes;
import com.SistemaDeReservaciones.cEntidad.Factura;
import com.SistemaDeReservaciones.cEntidad.Habitaciones;
import com.SistemaDeReservaciones.cLogicaNegocio.FacturaLN;
import com.SistemaDeReservaciones.cLogicaNegocio.HabitacionDeLujoLN;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class MainPrueba {

    public static void main(String[] args) {

        Clientes clienteA = new Clientes(1, "Michael", "Gutierrez", "Mendoza", "888888");
        Clientes clienteB = new Clientes(2, "Keyron", "Ortiz", "Zuñiga", "703060052");
        Clientes clienteC = new Clientes(3, "Kevin", "Espinoza", "Castro", "77777");
        Clientes clienteD = new Clientes(4, "Keyron", "Ortiz", "Zuñiga", "703060052");
        Clientes clienteE = new Clientes(1, "Ashton", "Brenes", "Rojas", "8");
        Clientes clienteF = new Clientes(2, "Kevin", "Espinoza", "Castro", "77777");
        Clientes clienteG = new Clientes(1, "Kevin", "Espinoza", "Castro", "77777", 4);

        Habitaciones habi = new Habitaciones();
        Habitaciones habiA = new Habitaciones(0, "Reservada", "Keyron Ortiz", 10000, 1, "Individual", false);
        Habitaciones habiB = new Habitaciones(1, "Disponible", "No hay", 10000, 1, "Individual", false);

        Factura factuA = new Factura(1, 1, 1, "Keyron", 3, 4, 2, 6, 15000, 20000.0);
        Factura factuB = new Factura(2, 2, 2, "Keinth", 3, 4, 2, 6, 15000, 20000.0);
        Factura factuC = new Factura(3, 3, 3, "Ashton", 3, 4, 2, 6, 15000, 20000.0);
        Factura factuD = new Factura(4, 4, 4, "Valery", 3, 4, 2, 6, 15000, 20000.0);

        DtoClientes dtoC = new DtoClientes();
        DtoHabitacionSencilla dtoHS = new DtoHabitacionSencilla();
        DtoHabitacionDeLujo dtoHDL = new DtoHabitacionDeLujo();
        DtoFactura dtoF = new DtoFactura();

        HabitacionDeLujoLN habiDLLN = new HabitacionDeLujoLN();

        FacturaLN factuLN = new FacturaLN();

//        System.out.println("Se agrego : " + dtoF.agregarFactura(factuA, true));
//        System.out.println("Se agrego : " + dtoF.agregarFactura(factuB, true));
//        System.out.println("Se agrego : " + dtoF.agregarFactura(factuC, true));
//        System.out.println("Se agrego : " + dtoF.agregarFactura(factuD, true));
//        //JOptionPane.showMessageDialog(null, dtoF.buscarFacturaPorNombre("Key").toArray());
//        //System.out.println(dtoF.buscarIdFactura(1));
//        System.out.println(factuLN.calcularUsos(3, 2, 0, 0, 15000));
        //System.out.println("Se agrego habitacion : " + dtoH.agregarHabitaciones(1, habi, true));
//        System.out.println("Se agrego habitacion : " + dtoH.agregarHabitaciones(2,habi, true)); 
//        System.out.println("Se agrego habitacion : " + dtoH.agregarHabitaciones(3,habi, true)); 
//        System.out.println("Se agrego habitacion : " + dtoH.agregarHabitaciones(4,habi, true)); 
//        System.out.println("Se agrego habitacion : " + dtoH.agregarHabitaciones(5,habi, true));        
//        System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteA, true));
//        System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteB, true));
//        System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteC, true));
//        System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteD, true));
        //System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteE, true));
//        System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteF, true));
//         System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteG, true));
//        System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteB));
//        System.out.println("Se agrego cliente :" + dtoC.agregarCliente(clienteC));
//for(Clientes sa : dtoC.consultar()){      
//System.out.println(sa);
//}
        // System.out.println("Se modifico : " + dtoC.modificarCliente(clienteF));
//JOptionPane.showMessageDialog(null, dtoH.consultar(1).toArray());
        //System.out.println(dtoH.consultar(5));
        //System.out.println(dtoC.desactivarCliente(1));
        //System.out.println("Se modifico :" + dtoH.modificarHabitacion(1, habi));
        //System.out.println("Se limpio :" + dtoH.limpiarArchivo(1));
        //JOptionPane.showMessageDialog(null, dtoC.consultar().toArray());
        //System.out.println("Se modifico" + dtoH.modificarHabitacion(1, habiA));
        //JOptionPane.showMessageDialog(null, dtoH.consultar(1).toArray());
        // System.out.println(dtoH.verificarHabitacionReservada(11, "Reservada"));
//        System.out.println(factuLN.obtenerPrecioHabitacionLujosa(11));
//        System.out.println(factuLN.obtenerPrecioHabitacionSencilla(14));
        //System.out.println(habiDLLN.calcularUsoDeHabitacionesDeLujo());
        //System.out.println(dtoH.cantidadDeUsoHabitaciones());
        //System.out.println(factuLN.generarReporteUsoHabitacion());
//        System.out.println(dtoHDL.mostrarEntradasTotalesReservasUOcupadas());
//        System.out.println(dtoHDL.mostrarReservasUOcupadas().size());
//        
//        System.out.println(dtoHS.mostrarEntradasTotalesReservasUOcupadas());
//        System.out.println(dtoHS.mostrarReservasUOcupadas().size());
//        
//        System.out.println(factuLN.calcularIngresosTotalesPorReserva());
        //System.out.println(dtoHDL.buscarClienteNombre());
        System.out.println(dtoC.agregarCliente(clienteG, true));
    }
}
