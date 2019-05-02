import MulticastyUDP.ServidorAdivinaUDP_Obj;

import java.io.IOException;
import java.net.*;

public class Servidor {
    DatagramSocket socket;
    boolean player1, player2, notEmpty;
    InetAddress client1ip, client2ip;
    int port1, port2;

    public void init(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void runServer() throws IOException{
        elegirjugador();
        while (true){
            jugar();
        }
    }

    private void jugar() throws IOException {
        byte [] recivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clienteIP;
        int clientPort;

        DatagramPacket packet = new DatagramPacket(recivingData,1024);
        socket.receive(packet);
        sendingData = processData(packet.getData(),packet.getLength());

        if (player1 && player2){
            clienteIP = packet.getAddress();
            clientPort = packet.getPort();
            if (clienteIP.equals(client1ip)){
                clientPort = port2;
                clienteIP = client2ip;
            }else {
                clientPort = port1;
                clienteIP = client1ip;
            }
            packet = new DatagramPacket(sendingData,sendingData.length,clienteIP,clientPort);
            socket.send(packet);
        }
    }

    private void elegirjugador() throws IOException {
        byte [] recibir = new byte[1024];
        byte [] enviar;
        InetAddress ipc;
        int puertoc;
        //Se comprueba si ya estan los dos players conectados para asignarle un color de ficha.
        while(!player1 || !player2){
            DatagramPacket packet = new DatagramPacket(recibir,1024);
            socket.receive(packet);
            enviar = processData(packet.getData(), packet.getLength());
            if (notEmpty){
                if (!player1){
                    enviar = "1".getBytes();
                    client1ip = packet.getAddress();
                    puertoc = packet.getPort();
                    packet = new DatagramPacket(enviar, enviar.length, client1ip, puertoc);
                    socket.send(packet);
                    player1=true;
                    notEmpty = false;
                    System.out.println("J1 puerto " + puertoc);
                    port1 = puertoc;
                } else if (!player2){
                    enviar = "2".getBytes();
                    client2ip = packet.getAddress();
                    puertoc = packet.getPort();
                    packet = new DatagramPacket(enviar, enviar.length, client2ip, puertoc);
                    socket.send(packet);
                    player2 = true;
                    System.out.println("J2 puerto " + puertoc);
                }
            }
        }
    }

    private byte[] processData(byte[] data, int lenght) {
        String msg = new String(data,0,lenght);
        if (!msg.isEmpty() && !msg.matches(".*\\d.*")){
            notEmpty = true;
        }
        System.out.println(msg);
        return msg.getBytes();
    }

    public static void main(String[] args) throws SocketException, IOException {
        Servidor servidor = new Servidor();

        try{
            servidor.init(5556);
            servidor.runServer();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Fi Servidor");
    }
}
