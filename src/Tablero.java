

public class Tablero {

    int[][] tablero = new int[6][7];
    final String[] symbols = {"\uD83D\uDD34 ", "\033[31m\uD83D\uDD34\033[0m ", "\033[33m\uD83D\uDD34\033[0m "};
    final int BACIO = 0;
    final int ROJO = 1;
    final int AMARILLO = 2;
    int contador = 0;
    boolean ganador = false;

    void inicializarTablero(){
        for (int fila=0; fila<=5; fila++){
            for (int col=0; col<=6; col ++){
                tablero [fila][col] = BACIO;
            }
        }
    }

    void mostrarTablero(){
        System.out.println("① ② ③ ④ ⑤ ⑥ ⑦");
        for (int fila=0; fila<=5; fila++){
            for (int col=0; col<=6; col ++){
                System.out.print(symbols[tablero [fila][col]]);
            }
            System.out.println();
        }
    }

    void añadirFicha(int columna, int turno){
        for (int fila=5; fila >=0; fila--){
            if (tablero [fila] [columna] == BACIO){
                if (turno == ROJO){
                    tablero [fila] [columna] = ROJO;
                    break;
                }
                else if (turno == AMARILLO){
                    tablero [fila] [columna] = AMARILLO;
                    break;
                }
            }
        }
    }

    public boolean ganadorRojo(){
        for (int fila=5; fila>=0; fila--){
            for (int col=0; col<=6; col ++){
                if (col <4){
                    if (tablero [fila][col] == ROJO){
                        //Compobar horizontal
                        ganador = comprobarHorizontal(fila,col,ROJO);
                        if (ganador){
                            return ganador;
                        }
                        if (fila > 2 ) {
                            ganador = comprobarVertical(fila, col, ROJO);
                            if (ganador) {
                                return ganador;
                            }
                            ganador = comprobarDiagonalDere(fila,col,ROJO);
                            if (ganador){
                                return ganador;
                            }
                        }
                    }
                }
//                else if (col == 4){
//                    if (tablero [fila][col] == ROJO){
//
//                    }
//                }
//                else if (col >4){
//
//                }
//                tablero [fila][col] = BACIO;
           }
        }return false;
    }

    public boolean ganadorAmarillo(){
        for (int fila=5; fila>=0; fila--){
            for (int col=0; col<=6; col ++){
                if (col <3){
                    if (tablero [fila][col] == AMARILLO){
                        //Compobar horizontal
                        ganador = comprobarHorizontal(fila,col,AMARILLO);
                        if (ganador){
                            return ganador;
                        }
                        if (fila > 2 ) {
                            ganador = comprobarVertical(fila, col, AMARILLO);
                            if (ganador) {
                                return ganador;
                            }
                            ganador = comprobarDiagonalDere(fila,col,AMARILLO);
                            if (ganador){
                                return ganador;
                            }
                        }
                    }
                } else if (col == 3){
                    if (tablero [fila][col] == AMARILLO){
                        ganador = comprobarHorizontal(fila,col,AMARILLO);
                        if (fila > 2 ) {
                            ganador = comprobarVertical(fila, col, AMARILLO);
                            if (ganador) {
                                return ganador;
                            }
                            ganador = comprobarDiagonalDere(fila,col,AMARILLO);
                            if (ganador){
                                return ganador;
                            }
                            ganador = comprobarDiagonalIzqui(fila,col,AMARILLO);
                            if (ganador){
                                return ganador;
                            }
                        }
                    }
                } else if (col >3){
                    if (fila > 2 ) {
                        ganador = comprobarVertical(fila, col, AMARILLO);
                        if (ganador) {
                            return ganador;
                        }
                        ganador = comprobarDiagonalIzqui(fila,col,AMARILLO);
                        if (ganador){
                            return ganador;
                        }
                    }
                }
            }
        }return false;
    }

    public boolean comprobarHorizontal(int fila,int col,int tipo){
        for (int i = col; i < col +4; i++) {
            if (tablero [fila][i] == tipo){
                contador++;
            }
        }
        if (contador != 4){
            contador = 0;
            return false;
        }else if (contador == 4){
            return true;
        }return false;
    }

    public boolean comprobarVertical(int fila,int col,int tipo){
        for (int i = fila; i > fila -4; i--) {
            if (tablero [i][col] == tipo){
                contador++;
            }
        }
        if (contador != 4){
            contador = 0;
            return false;
        }else if (contador == 4){
            return true;
        }return false;
    }

    public boolean comprobarDiagonalDere(int fila,int col,int tipo){
        for (int i = col; i < col +4; i++) {
            if (tablero [fila][i] == tipo){
                contador++;
            }
            fila--;
        }
        if (contador != 4){
            contador = 0;
            return false;
        }else if (contador == 4){
            return true;
        }return false;
    }

    public boolean comprobarDiagonalIzqui(int fila,int col,int tipo){
        for (int i = col; i > col -4; i--) {
            if (tablero [fila][i] == tipo){
                contador++;
            }
            fila--;
        }
        if (contador != 4){
            contador = 0;
            return false;
        }else if (contador == 4){
            return true;
        }return false;
    }
}
