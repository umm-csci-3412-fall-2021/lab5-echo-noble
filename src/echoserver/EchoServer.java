package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {
  public static final int portNumber = 6013;

  public static void main(String[] args) {
    try {
      // Start listening on the specified port
      ServerSocket serverSocket = new ServerSocket(portNumber);

      // Run forever, which is common for server style services
      while (true) {
        // Wait until someone connects, thereby requesting a date
        Socket clientSocket = serverSocket.accept();
        System.out.println("Got a request!");

        InputStream input = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();

        int sentByte;

        while((sentByte = input.read()) != -1) {

            output.write(sentByte);
            output.flush();
        }
        
        System.out.println("The request has been processed.");
        clientSocket.shutdownOutput();
        clientSocket.close();
      }
    // *Very* minimal error handling.
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception:");
      System.err.println(ioe);
    }
  }
}
