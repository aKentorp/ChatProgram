import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatServer {

    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        ArrayList<String> userNames = new ArrayList<>();
        String tempUserName;
        String tempMessage;
        boolean error = false;

        while(true){

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String[] receivedDataString = new String(receivePacket.getData()).split("\\W");
            tempUserName = receivedDataString[1];
            error = false;

            for(String userName : userNames){
                if(userName.equalsIgnoreCase(tempUserName)){
                    error=true;
                    sendData = ("J_ER 1: USER NAME TAKEN").getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);
                }
            }
            if(error != true){
                userNames.add(tempUserName);
                sendData = ("J_OK").getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
                while (true) {
                    Arrays.fill(receiveData, (byte)0);
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    System.out.println(new String(receivePacket.getData()));


                    Arrays.fill(sendData, (byte)0);
                    sendData = receiveData;
                    sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);



                }

            }





        }

    }

}
