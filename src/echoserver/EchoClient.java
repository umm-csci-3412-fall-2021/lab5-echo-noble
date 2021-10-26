package echoserver;
import java.net.*;
import java.io.*;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
      String server;
      // Use "127.0.0.1", i.e., localhost, if no server is specified.
      if (args.length == 0) {
        server = "127.0.0.1";
      } else {
        server = args[0];
      }
  
      try {
        // Connect to the server
        Socket serverSocket = new Socket(server, portNumber);
  
        // Get the input stream so we can read from that socket
        InputStream input = serverSocket.getInputStream();
        OutputStream output = serverSocket.getOutputStream();
  
        // Initialize variables for bytes sent and received.
        int sentByte = System.in.read(), receivedByte = 0;

        while((sentByte) != -1) {
            output.write(sentByte);

            receivedByte = input.read();
            System.out.write(receivedByte);

            sentByte = System.in.read();
        }

        System.out.flush();
        output.flush();
        System.out.println("Your data has been received.");
        serverSocket.shutdownOutput();
        serverSocket.close();
  
      // Provide some minimal error handling.
      } catch (ConnectException ce) {
        System.out.println("We were unable to connect to " + server + ".");
        System.out.println("You should make sure the server is running.");
      } catch (IOException ioe) {
        System.out.println("We caught an unexpected exception:");
        System.err.println(ioe);
      }
    }
}