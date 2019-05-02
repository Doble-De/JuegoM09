import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class Conecta4 {

    Tablero tablero;
    int turno, rival ,columna;
    Scanner scanner = new Scanner(System.in);
    boolean ganador, movido;
    Cliente cliente;

    public Conecta4(){
        tablero = new Tablero();
        movido = false;
        cliente = new Cliente();
        //turno = (int) Math.floor(Math.random() * 2) + 1;
    }

    public void ponerficha() throws IOException {
            tablero.mostrarTablero();
            if (turno == tablero.AMARILLO){
                esperarjugada();
                tablero.mostrarTablero();
            }
            System.out.println("Turno del jugador"+turno);
            while (!movido) {
            System.out.println("Indica la columna donde quieres tirar la ficha");
            columna = scanner.nextInt()-1;

                if (columna <= 6 && columna >= 0) {
                    tablero.añadirFicha(columna, turno);
                    tablero.mostrarTablero();
                    cliente.runClient(columna);
                    movido = true;
                } else {
                    System.out.println("Columna no valida, del 1 al 7");
                }
            }
            if (turno == tablero.AMARILLO){
                ganador = tablero.ganadorAmarillo();
            }else if (turno == tablero.ROJO){
                ganador = tablero.ganadorRojo();
            }

            if (ganador){
                System.out.println("Ha ganado el j1 chao");
                exit();
            }
            movido = false;
        if (turno == tablero.ROJO){
            esperarjugada();
        }
    }

    public void conexion() throws IOException {
        cliente.init("localhost", 5556);
        System.out.println("Introduce tu nombre");
        String name = scanner.nextLine();
        turno = cliente.selectPlayer(name);
        if (turno == tablero.ROJO){
            rival = tablero.AMARILLO;
            System.out.println("Eres el jugador 1, juegas con las piezas rojas");
        } else if (turno == tablero.AMARILLO){
            rival = tablero.ROJO;
            System.out.println("Eres el jugador 2, juegas con las piezas amarillas");
        }

    }

    private void esperarjugada() throws IOException {
        int jugada;

        jugada = Integer.parseInt(cliente.getJugada());

        tablero.añadirFicha(jugada, rival);
    }
}
