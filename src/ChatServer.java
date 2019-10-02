import java.net.DatagramSocket;
import java.util.ArrayList;

public class ChatServer {

    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        ArrayList<String> userNames = new ArrayList<>();



    }

}
