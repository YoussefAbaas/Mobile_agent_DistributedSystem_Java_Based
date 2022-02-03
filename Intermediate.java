import java.io.*; 
import java.net.*;
import java.util.ArrayList;
public class Intermediate {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(1235);
        Socket client_Socket =  new Socket("127.0.0.1",1234);
        ArrayList<IntermediateHandler>ThreadList=new ArrayList<>();
        while(true){
            // /**Connection Part */
            Socket client=null;
            client = server.accept();
            System.out.println("decision connected");
            DataInputStream dis_client=new DataInputStream(client.getInputStream());;
            DataOutputStream dos_client=new DataOutputStream(client.getOutputStream());

        IntermediateHandler t1 = new IntermediateHandler(client,client_Socket,ThreadList,dis_client,dos_client);
        ThreadList.add(t1);
        t1.start();
    }
    
    }
}
class IntermediateHandler extends Thread {
    final Socket s = null;
    private Socket client;
    private Socket server;
    private ArrayList<IntermediateHandler> thread_List;
     DataInputStream dis_server;
     DataOutputStream dos_server;
     DataInputStream dis_client;
     DataOutputStream dos_client;

    // Constructor
    public IntermediateHandler(Socket Client, Socket Server,ArrayList<IntermediateHandler>ThreadList,DataInputStream dis_Client,DataOutputStream dos_Client) {
        this.client = Client;
        this.server = Server;
        this.thread_List=ThreadList;
        dis_client=dis_Client;
        dos_client=dos_Client;
    }

    @Override
    public void run() {
        String server_message = null;
        String client_message = null;
        try{
        InputStream inputStream_server = this.server.getInputStream();
        dis_server = new DataInputStream( inputStream_server );
        OutputStream outputStream_server= this.server.getOutputStream();
        dos_server = new DataOutputStream( outputStream_server );
        }
        catch(Exception e){}
        while (true) {
            try {
                try{
                    sleep(3000); // wait for all clients to connect
                    }
                    catch(Exception e){}
                client_message = read_from_all_clients();
                if (client_message != null)
                    dos_server.writeUTF(client_message);
                    System. out .println( client_message);
                server_message=dis_server.readUTF();
                if (server_message != null)
                    send_to_all_clients(server_message);
                    System. out .println( server_message );

            } catch (IOException e) {
                break;
               //e.printStackTrace();
            }
        }
        try
        {
            // closing resources
            s.close();
            this.dis_client.close();
            this.dos_client.close();
        }
        catch(EOFException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void send_to_all_clients(String message)
    {
        for(IntermediateHandler Ch:thread_List)
        {
            try{
                Ch.dos_client.writeUTF(message);
            }
            catch(Exception e)
            {}
        }
    }
    private String read_from_all_clients()
    {
       String message=null;
        for(IntermediateHandler Ch:thread_List)
        {
            try{
                if(Ch.dis_client.available()>0)
                {
              message=Ch.dis_client.readUTF();
              break;
                }
            }
            catch(Exception e)
            {}
        }
        return message;
    }
}