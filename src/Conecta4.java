import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class Conecta4 {

    Tablero tablero;
    int turno, rival ,columna;
    Scanner scanner = new Scanner(System.in);
    boolean ganador, turnojugada;
    Cliente cliente;

    public Conecta4(){
        tablero = new Tablero();
        turnojugada = true;
        cliente = new Cliente();
        //turno = (int) Math.floor(Math.random() * 2) + 1;
    }

    public void ponerficha() throws IOException {
        if (turnojugada){
            turnojugada = false;
            System.out.println("Turno del jugador1");
            System.out.println("Indica la columna donde quieres tirar la ficha");
            columna = scanner.nextInt()-1;
            if (columna <=6 && columna >=0) {
                cliente.runClient(columna);
                tablero.añadirFicha(columna, turno);
                tablero.mostrarTablero();
                turno = 2;
            }
            else{
                System.out.println("Columna no valida, del 1 al 7");
            }
            ganador = tablero.ganadorRojo();

            if (ganador){
                System.out.println("Ha ganado el j1 chao");
                exit();
            }
        }
        else if(!turnojugada){
            turnojugada = true;
            System.out.println("Turno del jugador2");
            System.out.println("Indica la columna donde quieres tirar la ficha");
            columna = scanner.nextInt()-1;
            if (columna <=6 && columna >=0) {
                cliente.runClient(columna);
                tablero.añadirFicha(columna, turno);
                tablero.mostrarTablero();
                turno = 1;
            }
            else{
                System.out.println("Columna no valida, del 1 al 7");
            }

            ganador = tablero.ganadorAmarillo();

            if (ganador){
                System.out.println("Ha ganado el j2 chao");
                exit();
            }
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
}
