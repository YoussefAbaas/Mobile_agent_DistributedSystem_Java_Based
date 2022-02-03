import java.net.*;
import java.io.*;
public class Sensors {
public static void main(String[] args ) throws IOException {
Socket s1 = new Socket( "127.0.0.1" ,1235); //connect with Tcp
String received="hello";
InputStream inputStream = s1 .getInputStream();
DataInputStream dataInputStream = new DataInputStream( inputStream );
OutputStream outputStream = s1 .getOutputStream();
DataOutputStream dataOutputStream = new DataOutputStream( outputStream );
while ( !received .equals( "Request Readings" ))
{
    received= new String( dataInputStream .readUTF());
}
System. out .println( "Sensors: " + received );
dataOutputStream .writeUTF( "Readings" );
dataInputStream .close();
inputStream .close();
dataOutputStream .close();
outputStream .close();
s1 .close();
}
}

