import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String args[]) throws Exception
    {
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in)); //Modtager input fra system in

        DatagramSocket clientSocket = new DatagramSocket(); //Datagram socket er en connectionless socket

        InetAddress IPAddress = InetAddress.getByName("localhost"); //IP addresse
        byte[] sendData = new byte[1024]; //Packet størrelser
        byte[] receiveData = new byte[1024];

        String sentence = inFromUser.readLine(); //Læser en besked fra user
        sendData = sentence.getBytes(); //Laver det om til bytes


        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876); //Laver en packet med infor
        clientSocket.send(sendPacket); //Sender packet som klient

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);






        clientSocket.close();
    }
}
