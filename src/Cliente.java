import MulticastyUDP.ClientAdivinaUDP_Obj;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    InetAddress serverIP;
    int portserver;
    DatagramSocket socket;
    Scanner sc;
    String columna;


    public Cliente(){
        sc = new Scanner(System.in);
    }

    public void init(String host, int port) throws UnknownHostException, SocketException {
        serverIP = InetAddress.getByName(host);
        portserver = port;
        socket = new DatagramSocket();
    }

    public String getJugada() throws IOException {
        byte[] recibir = new byte[1024];
        byte[] enviar;

        DatagramPacket packet = new DatagramPacket(recibir,1024);
        socket.receive(packet);
        enviar = getDataToRequest(packet.getData(), packet.getLength());
        return columna;
    }

    public int selectPlayer(String nom) throws IOException {
        byte[] recibir = new byte[1024];
        byte[] enviar;
        String player;

        enviar = nom.getBytes();
        DatagramPacket packet = new DatagramPacket(enviar,enviar.length,serverIP,portserver);
        socket.send(packet);
        packet = new DatagramPacket(recibir,1024);
        socket.receive(packet);
        player = new String(packet.getData(),0,packet.getLength());
        return Integer.valueOf(player);
    }


    private byte[] getDataToRequest(byte[] data, int length) {
        String rebut = new String(data,0, length);
        columna = rebut;

        return rebut.getBytes();
    }

    public void runClient(int columna) throws IOException {
        byte[]recibir = new byte[1024];
        byte[]enviar;

        enviar = (String.valueOf(columna)).getBytes();
        DatagramPacket packet = new DatagramPacket(enviar,enviar.length,serverIP,portserver);
        socket.send(packet);
    }

    private byte[] getPlayertoRequest(byte[] data, int length) {
        String rebut = new String(data,0, length);
        //Imprimeix el nom del client + el que es reb del server i demana més dades
        String msg = rebut;
        return msg.getBytes();
    }

    private boolean mustContinue(byte [] data) {
        String msg = new String(data).toLowerCase();
        if(!msg.equals("adeu")) return true;
        else return false;
    }


}
