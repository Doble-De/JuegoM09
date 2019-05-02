import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Tablero tablero = new Tablero();
        Conecta4 conecta4 = new Conecta4();


        conecta4.conexion();
        tablero.inicializarTablero();
        while (true){
            conecta4.ponerficha();
        }
    }
}
