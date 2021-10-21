package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {
  public static final int portNumber = 6013;

  public static void main(String[] args) {
    try {
      // Start listening on the specified port
      ServerSocket server = new ServerSocket(portNumber);

      // Run forever, which is common for server style services
      while (true) {
        // Wait until someone connects, thereby requesting a date
        Socket client = server.accept();
        System.out.println("Got a request!");

        InputStream input = client.getInputStream();
        OutputStream output = client.getOutputStream();

        while(!client.isInputShutdown()) {
            int sentByte = input.read();

            output.write(sentByte);
            output.flush();
        }
        
        client.shutdownOutput();
      }
    // *Very* minimal error handling.
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}
