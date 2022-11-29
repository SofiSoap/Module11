// A Java program for a Server
import java.net.*;
import java.io.*;
import javax.swing.JFrame;
 
public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
 
 
    public Server(int port){
        
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
 
            System.out.println("Waiting for a client");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
         
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
 
            String line = "";
            int number = 0;
 
          
            while (!line.equals("Over")) {
            	
                try  {
                    line = in.readUTF();
                    number = Integer.parseInt(line);
                    boolean isNumPrime;
                    //TEST IF NUMBER IS PRIME 
                    isNumPrime = isPrime(number)	;
                    
                    if(isNumPrime == true) {
                    	System.out.println(number + " is prime.");
                    }else {
                    	System.out.println(number + " is NOT prime.");
                    }
                    
                } catch(IOException i){
                    System.out.println(i);
                }
            }
            
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }
 
    public static boolean isPrime  (int testNum){
    	
    	boolean isNumPrime = true;
    	
    	if(testNum == 0 || testNum ==1) {
    		isNumPrime = false;
    	}else {
    		for(int i = 2 ; i <= testNum/2 ; i++) {
    			if(testNum % i == 0) {
    				isNumPrime = false;
    				break;
    			}
    		}
    	}
    		
    	return isNumPrime;
    }
    
    public static void main(String args[]) {
        Server server = new Server(5000);
        
        JFrame frame = new JFrame()	;
        frame.setTitle("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500); //sets x and y dimension
        frame.setVisible(true);
        
    }
}