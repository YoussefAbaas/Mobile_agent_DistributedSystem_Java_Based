import java.net.*;
import java.io.*;
public class AreaComputers {
public static void main(String[] args ) throws IOException {
Socket s1 = new Socket( "127.0.0.1" ,1235);
String received;
InputStream inputStream = s1 .getInputStream();
DataInputStream dataInputStream = new DataInputStream( inputStream );
OutputStream outputStream = s1 .getOutputStream();
DataOutputStream dataOutputStream = new DataOutputStream( outputStream );
dataOutputStream .writeUTF( "Hello to client" );
received= new String( dataInputStream .readUTF());
if ( received .equals( "Hello to server" ))
{
System. out .println( "Area Computer: " + received );
dataOutputStream .writeUTF( "Request recommendation" );
}
while ( !received .equals( "Recommendations" ))
{
received = new String ( dataInputStream .readUTF());
}
System. out .println( "Area Computer: " + received );
dataOutputStream .writeUTF( "Acknowledge" );
dataOutputStream .writeUTF( "Finished" );
System. out .println( "Area Computer:Finished");
dataInputStream .close();
inputStream .close();
dataOutputStream .close();
outputStream .close();
s1 .close();
}
}

