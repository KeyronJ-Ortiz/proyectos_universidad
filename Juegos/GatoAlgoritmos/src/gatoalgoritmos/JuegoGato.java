/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gatoalgoritmos;

import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class JuegoGato {

    private int jugadores = 1;
    private int gato[][];
    private int filas;
    private int columnas;
    private int ficha = 1;
    private int filaDinamica;
    private int columnaDinamica;
    int contadorX;
    int contadorY;

    public JuegoGato(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        gato = new int[this.filas][this.columnas];
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public int[][] getGato() {
        return gato;
    }

    public void setGato(int[][] gato) {
        this.gato = gato;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getFilaDinamica() {
        return filaDinamica;
    }

    public void setFilaDinamica(int filaDinamica) {
        this.filaDinamica = filaDinamica;
    }

    public int getColumnaDinamica() {
        return columnaDinamica;
    }

    public void setColumnaDinamica(int columnaDinamica) {
        this.columnaDinamica = columnaDinamica;
    }

    public int getContadorX() {
        return contadorX;
    }

    public void setContadorX(int contadorX) {
        this.contadorX = contadorX;
    }

    public int getContadorY() {
        return contadorY;
    }

    public void setContadorY(int contadorY) {
        this.contadorY = contadorY;
    }

    public void limpiarMatriz() {
        for (int i = 0; i < gato.length; i++) {
            for (int j = 0; j < gato[0].length; j++) {
                gato[i][j] = 0;
            }
        }
    }

    public void colocarFicha(int fila, int columna) {
        this.filaDinamica = fila;
        this.columnaDinamica = columna;
        if (gato[fila][columna] == 0) {
            gato[fila][columna] = ficha;
        }
    }

    public boolean ganarVertical() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;
        for (int i = 0; i < gato.length; i++) {
            if (gato[i][columnaDinamica] == 1) {
                contadorFicha++;
            }
        }
        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public boolean ganarHorizontal() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;

        for (int i = 0; i < gato[0].length; i++) {
            if (gato[filaDinamica][i] == 1) {
                contadorFicha++;
            }
        }

        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public boolean ganarDiagonal() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;

        for (int i = 0; i < gato.length; i++) {
            for (int j = 0; j < gato[0].length; j++) {
                if (i == j) {
                    if (gato[i][j] == 1) {
                        contadorFicha++;
                    }
                }
            }
        }
        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public boolean ganarDiagonalInversa() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;
        if (gato[2][0] == 1 && gato[1][1] == 1 && gato[0][2] == 1) {
            contadorFicha = 3;
        }
        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public boolean ganarVerticalDos() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;
        for (int i = 0; i < gato.length; i++) {
            if (gato[i][columnaDinamica] == 2) {
                contadorFicha++;
            }
        }
        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public boolean ganarHorizontalDos() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;

        for (int i = 0; i < gato[0].length; i++) {
            if (gato[filaDinamica][i] == 2) {
                contadorFicha++;
            }
        }

        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public boolean ganarDiagonalDos() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;

        for (int i = 0; i < gato.length; i++) {
            for (int j = 0; j < gato[0].length; j++) {
                if (i == j) {
                    if (gato[i][j] == 2) {
                        contadorFicha++;
                    }
                }
            }
        }
        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public boolean ganarDiagonalInversaDos() {
        boolean jugadorGanador = false;
        int contadorFicha = 0;
        if (gato[2][0] == 2 && gato[1][1] == 2 && gato[0][2] == 2) {
            contadorFicha = 3;
        }
        if (contadorFicha == 3) {
            jugadorGanador = true;
        }
        return jugadorGanador;
    }

    public void cambioDeJugador() {
        if (jugadores == 1) {
            setFicha(1);
            setJugadores(2);
        } else if (jugadores == 2) {
            setFicha(2);
            setJugadores(1);
        }
    }

    public char marcarSimbolo() {
        char simbolo = ' ';

        if (jugadores == 1) {
            simbolo = 'X';
        } else if (jugadores == 2) {
            simbolo = 'O';
        }
        return simbolo;
    }

    public boolean ganarUno() {
        boolean jugadorGano = false;

        if (ganarVertical()) {
            jugadorGano = true;
        } else if (ganarHorizontal()) {
            jugadorGano = true;
        } else if (ganarDiagonal()) {
            jugadorGano = true;
        } else if (ganarDiagonalInversa()) {
            jugadorGano = true;
        }

        return jugadorGano;
    }

    public boolean ganarDos() {
        boolean jugadorGano = false;

        if (ganarVerticalDos()) {
            jugadorGano = true;
        } else if (ganarHorizontalDos()) {
            jugadorGano = true;
        } else if (ganarDiagonalDos()) {
            jugadorGano = true;
        } else if (ganarDiagonalInversaDos()) {
            jugadorGano = true;
        }

        return jugadorGano;
    }

    public void jugadas(int fila, int columna) {
        cambioDeJugador();
        colocarFicha(fila, columna);
        if (ganarUno()) {
            JOptionPane.showMessageDialog(null, "Ha ganado jugador X");
        } else if (ganarDos()) {
            JOptionPane.showMessageDialog(null, "Ha ganado jugador O");
        } else {
            //JOptionPane.showMessageDialog(null, "Turno de jugador:  " + marcarSimbolo());
        }
    }

    @Override
    public String toString() {
        String salida = "";
        for (int i = 0; i < gato.length; i++) {
            for (int j = 0; j < gato[0].length; j++) {
                salida += "|" + gato[i][j] + "|";
            }
            salida += "\n";
        }
        return salida;
    }
}
