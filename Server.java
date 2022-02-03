import java.net.*;
import java.util.ArrayList;
import java.io.*;
public class Server {
public static void main(String[] args ) throws IOException {
ServerSocket ss = new ServerSocket(1234);
System.out.println("Server started");
System.out.println("Waiting for a client ...");
ArrayList<ClientHandler>ThreadList=new ArrayList<>();
while(true)
{
Socket s=null;
try
{
s = ss .accept();
DataInputStream dis = new DataInputStream(s.getInputStream());
DataOutputStream dos = new DataOutputStream(s.getOutputStream());
System.out.println("Assigning new thread for this client");
                // create a new thread object
                ClientHandler thread = new ClientHandler(s, dis, dos,ThreadList);
                ThreadList.add(thread);
                // Invoking the start() method
                thread.start();
}

catch (Exception e){
    s.close();
    e.printStackTrace();
}
}
}
}
class ClientHandler extends Thread 
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    private ArrayList<ClientHandler> thread_List;
      
  
    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos,ArrayList<ClientHandler>ThreadList) 
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.thread_List=ThreadList;

    }
  
    @Override
    public synchronized void run() 
    {
        String received;
        while (true) 
        {
            try{
received = new String ( dis .readUTF());
if ( received .equals( "Hello to client" ))
{
System. out .println( "Server: " + received );
//dos .writeUTF( "Hello to server" );
send_to_all_clients( "Hello to server" );
}
//received = new String ( dis .readUTF());
else if ( received .equals( "Request recommendation" ))
{
System. out .println( "Server: " + received );
//dos .writeUTF( "Request Readings" );
send_to_all_clients("Request Readings");
}
//received = new String ( dis .readUTF());
else if( received .equals( "Readings" ))
{
System. out .println( "Server: " + received );
//dos .writeUTF( "Recommendations" );
send_to_all_clients("Recommendations");
}
//received = new String ( dis .readUTF());
else if ( received .equals( "Acknowledge" ))
{
System. out .println( "Server: " + received );
}
//received = new String ( dis .readUTF());
else if ( received .equals( "Finished" ))
{
System. out .println( "Server: " + received );
}
            }
            catch(EOFException e) {
                //This isn't problem
                break;
            }
catch (IOException e) {
    e.printStackTrace();
}
}           
{
         try
        {
            // closing resources
            s.close();
            this.dis.close();
            this.dos.close();
        }
        catch(EOFException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    }
     private void send_to_all_clients(String outputString)
     {
         for(ClientHandler Ch:thread_List)
         {
             try {
                Ch.dos.writeUTF(outputString);
             } catch (Exception e) {
                 //TODO: handle exception
             }
         }
     }
    }
