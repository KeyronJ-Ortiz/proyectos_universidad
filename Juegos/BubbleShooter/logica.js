var areaJuego = [];
var ak47 = 1;
var cantidadFilas;
var cantidadColumnas;
var columnaDisparo;
var dificultad;
var seDisparo;
var cantidadBurbujas = 4;
var areaJuegoEliminadas = [];
var tablero = document.getElementById('tablero');
var contadorPuntaje = document.getElementById('puntajeUsuario');
var crearJuego = document.getElementById('crearJuego');
var disparar = document.getElementById('glock');
var puntaje = 0;


function evaluarDificultad() {
    dificultad = document.getElementById('dificultad').value;
    if (dificultad == 'normal') {
        cantidadBurbujas = 4;
    } else if (dificultad == 'medio') {
        cantidadBurbujas = 5;
    } if (dificultad == 'dificil') {
        cantidadBurbujas = 6;
    }
    return cantidadBurbujas;
}

function generarNumeroRandom() {
    var numero = Math.floor(Math.random() * evaluarDificultad() + 1);
    return numero;
}

function crearMatriz() {
    cantidadFilas = document.getElementById('cantidadFilas').value;
    cantidadColumnas = document.getElementById('cantidadColumnas').value;
    var columnaDinamica = Math.floor(cantidadColumnas / 2);
    var filaDinamica = cantidadFilas / 2;

    evaluarDificultad();

    for (let i = 0; i < cantidadFilas; i++) {
        areaJuego[i] = new Array();
        for (let j = 0; j < cantidadColumnas; j++) {
            if (i < filaDinamica) {
                areaJuego[i][j] = generarNumeroRandom();
            } else {
                areaJuego[i][j] = 0;
            }
        }
    }
    areaJuego[Math.floor(cantidadFilas - 1)][columnaDinamica] = ak47;

    console.log(areaJuego.length);
    console.log(areaJuego[0].length);
}

function crearRegistroEliminadas() {
    for (let i = 0; i < areaJuego.length; i++) {
        areaJuegoEliminadas[i] = new Array();
        for (let j = 0; j < areaJuego[0].length; j++) {
            areaJuegoEliminadas[i][j] = false;
        }
    }
}

function verificarArriba(fila, columna, color) {
    var esIgual = false;

    if (fila > 0) {
        var valorAdyacente = areaJuego[fila - 1][columna];
        if (valorAdyacente == color) {
            esIgual = true;
        }
    }
    return esIgual;
}

function verificarAbajo(fila, columna, color) {
    var esIgual = false;
    var filaNueva = fila + 1;

    if (filaNueva < areaJuego.length) {
        var valorAdyacente = areaJuego[filaNueva][columna];
        if (valorAdyacente == color) {
            esIgual = true;
        }
    }
    return esIgual;
}


function verificarIzquierda(fila, columna, color) {
    var esIgual = false;

    if (columna > 0) {
        var valorAdyacente = areaJuego[fila][columna - 1];
        if (valorAdyacente == color) {
            esIgual = true;
        }
    }

    return esIgual;
}

function verificarDerecha(fila, columna, color) {
    var esIgual = false;
    columnaSumada = columna++;

    if (columna < cantidadColumnas - 1) {
        var valorAdyacente = areaJuego[fila][columna];
        if (valorAdyacente == color) {
            esIgual = true;
        }
    }
    return esIgual;
}



function verificarGruposAdyacentes() {

    crearRegistroEliminadas();

    for (let i = 0; i < areaJuego.length; i++) {
        for (let j = 0; j < areaJuego[0].length; j++) {
            if (areaJuego[i][j] != 0 && !areaJuegoEliminadas[i][j]) {

                var colorBurbuja = areaJuego[i][j];

                if (verificarArriba(i, j, colorBurbuja)) {
                    var fila = i;
                    while (fila >= 0 && areaJuego[fila][j] == ak47) {
                        areaJuegoEliminadas[fila][j] = true;
                        fila--;
                    }
                }

                if (verificarAbajo(i, j, colorBurbuja)) {
                    var fila = i;
                    while (fila < areaJuego.length && areaJuego[fila][j] == ak47) {
                        areaJuegoEliminadas[fila][j] = true;
                        fila++;
                    }
                }

                if (verificarIzquierda(i, j, colorBurbuja)) {
                    var columna = j;
                    while (columna >= 0 && areaJuego[i][columna] == ak47) {
                        areaJuegoEliminadas[i][columna] = true;
                        columna--;
                    }
                }

                if (verificarDerecha(i, j, colorBurbuja)) {
                    var columna = j;
                    while (columna < areaJuego[0].length && areaJuego[i][columna] == ak47) {
                        areaJuegoEliminadas[i][columna] = true;
                        columna++;
                    }
                }

                if (areaJuegoEliminadas[i][j]) {
                    puntaje++;
                }

            }
        }
    }
}


function eliminarBurbujas() {
    for (let i = 0; i < areaJuego.length; i++) {
        for (let j = 0; j < areaJuego[0].length; j++) {
            if (areaJuegoEliminadas[i][j] && areaJuego[i][j] == ak47) {
                areaJuego[i][j] = 9;
            }
        }
    }
}

function realizarCaida() {
    var filas = Math.floor(areaJuego.length / 2) - 1;
    var columnas = areaJuego[0].length;
    var intercambiado = true;

    while (intercambiado) {
        intercambiado = false;

        for (let i = filas; i > 0; i--) {
            for (let j = 0; j < columnas; j++) {
                if (areaJuego[i][j] == 9 && areaJuego[i - 1][j] != 9) {
                    var valorDeAbajo = areaJuego[i][j];
                    areaJuego[i][j] = areaJuego[i - 1][j];
                    areaJuego[i - 1][j] = valorDeAbajo;
                    intercambiado = true;
                }
            }
        }
    }
    caerBurbujas();
}


function caerBurbujas() {
    var mitadFilas = Math.floor(areaJuego.length / 2);

    for (let i = 0; i < areaJuego.length; i++) {
        for (let j = 0; j < areaJuego[0].length; j++) {
            if (i < mitadFilas) {
                if (areaJuego[i][j] == 9) {
                    areaJuego[i][j] = generarNumeroRandom();
                }
            }

        }
    }
}


function dispararBurbuja() {
    seDisparo = false;
    var fila = 0;
    columnaDisparo = document.getElementById('columnaDisparo').value;

    for (let i = areaJuego.length - 2; i >= 0; i--) {
        if (areaJuego[i][columnaDisparo] != 0) {
            fila = i + 1;
            break;
        }
    }
    if (verificarAbajo(fila, columnaDisparo, ak47) || verificarArriba(fila, columnaDisparo, ak47)
        || verificarIzquierda(fila, columnaDisparo, ak47) || verificarDerecha(fila, columnaDisparo, ak47)) {
        areaJuego[fila][columnaDisparo] = ak47;
        seDisparo = true;
    }
}


function recargarCanon() {
    ak47 = generarNumeroRandom();
    var columnaDinamica = Math.floor(cantidadColumnas / 2)
    areaJuego[cantidadFilas - 1][columnaDinamica] = ak47;
}

function pintarAreaJuego() {
    tablero.innerHTML = " ";
    var salida = "";

    salida += "<div class='juego'>  </div>";
    salida += "<div class='juego'>  </div>";
    for (let j = 0; j < areaJuego[0].length; j++) {
        salida += "<div id = 'indiceColumnas'class='juego'>" + j + "</div>";
    }
    salida += "<br>";

    for (let i = 0; i < areaJuego.length; i++) {
        salida += "<div id ='indiceFilas'class='juego'>" + i + "|" + "</div>";

        for (let j = 0; j < areaJuego[0].length; j++) {
            salida += "<div id= 'tablero' class='juego'>";
            if (areaJuego[i][j] == 1) {
                salida += "<div id='contenido' class='azul'>" + areaJuego[i][j] + "</div>";
            } else if (areaJuego[i][j] == 2) {
                salida += "<div id='contenido' class='rojo'>" + areaJuego[i][j] + "</div>";
            } else if (areaJuego[i][j] == 3) {
                salida += "<div id='contenido' class='verde'>" + areaJuego[i][j] + "</div>";
            } else if (areaJuego[i][j] == 4) {
                salida += "<div id='contenido' class='amarillo'>" + areaJuego[i][j] + "</div>";
            } else if (areaJuego[i][j] == 5) {
                salida += "<div id='contenido' class='rosa'>" + areaJuego[i][j] + "</div>";
            } else if (areaJuego[i][j] == 6) {
                salida += "<div id='contenido' class='morado'>" + areaJuego[i][j] + "</div>";
            } else {
                salida += "<div id='contenido' class='enBlanco'>" + areaJuego[i][j] + "</div>";
            }
            salida += "</div>";
        }
        salida += "<br>";
    }

    tablero.innerHTML = salida;
}

function pintarPuntaje() {
    contadorPuntaje.innerHTML = " ";
    var salida = "";
    salida += "<div id='puntajeUsuario'>" + 'Puntos:' + puntaje + "</div>";
    contadorPuntaje.innerHTML = salida;
}


pintarPuntaje();

crearJuego.addEventListener('click', function () {
    crearMatriz();
    pintarAreaJuego();
    pintarPuntaje();
    console.log(areaJuego);
    document.getElementById('cantidadFilas').value = "";
    document.getElementById('cantidadColumnas').value = "";
});

disparar.addEventListener('click', function () {
    dispararBurbuja();
    pintarAreaJuego();
    pintarPuntaje();
    setTimeout(() => {
        verificarGruposAdyacentes();
        eliminarBurbujas();
        pintarAreaJuego();
        pintarPuntaje();
    }, 250);
    setTimeout(() => {
        realizarCaida();
        recargarCanon();
        pintarAreaJuego();
        pintarPuntaje();
    }, 350);
    document.getElementById('columnaDisparo').value = "";
    console.log(areaJuegoEliminadas);
});

console.log('ola');