/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gatoalgoritmos;

/**
 *
 * @author DELL
 */
public class PruebaJuegoGato {

    public static void main(String[] args) {
        JuegoGato gDeGato = new JuegoGato(3, 3);

        gDeGato.jugadas(0, 0);
        System.out.println(gDeGato);

        gDeGato.jugadas(0, 1);
        System.out.println(gDeGato);

        gDeGato.jugadas(1, 0);
        System.out.println(gDeGato);

        gDeGato.jugadas(0, 2);
        System.out.println(gDeGato);

        gDeGato.jugadas(2, 0);
        System.out.println(gDeGato);
    }
}
