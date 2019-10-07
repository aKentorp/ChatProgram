import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ChatCLient {



    public static void main(String[] args) throws Exception {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int port = 9876;
        String[] errorTest;

        System.out.println();

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        while(true){
            String userName = userInput.readLine();
            sendData = ("JOIN " + userName + ", " + IPAddress + ":" + port).getBytes();
            System.out.println("1");
            if (userName.length() < 12 && stringContainsNumber(userName) == false && !userName.contains(" ")){
                System.out.println("2");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                errorTest = new String(receivePacket.getData()).split("\\W", 2);
                System.out.println("3");

                if(errorTest[0].equalsIgnoreCase("J_OK")){

                    System.out.println(errorTest[0]);

                    while(true){
                        System.out.println("4");
                        Arrays.fill(sendData, (byte)0);
                        sendData = ("DATA " + userName + ": " + userInput.readLine()).getBytes();
                        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        clientSocket.send(sendPacket);

                        Arrays.fill(receiveData, (byte)0);
                        receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        clientSocket.receive(receivePacket);
                        System.out.println(new String(receivePacket.getData()));


                    }

                }
                else{
                    System.out.println(errorTest[0]);
                    System.out.println("5");
                    break;
                }
            }
            break;
        }
    }

    public static boolean stringContainsNumber( String s )
    {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }
}
