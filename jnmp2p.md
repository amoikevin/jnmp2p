# Introduction #

We really need to change the way we develop network applications. Too much time is invested in developing code to handle network communication rather than concentrate on application features.
The jnmp2p framework provides a set of API to make P2P applications as easy to develop as GUI applications.
Theyre based on the same fundamental concept, "Events". You dont have to worry about the communication between two participating devices. Just design your application logic and set up the protocol for communication and youre all set for a rich p2p application experience.
The same code runs on both machines, so you can kiss goodbye to developing seperate server code and client code. jnmp2p takes care of it for you.


# Versioning information #
v2.6beta
  * If there is an open connection to an ip address, then a new connection is not opened and the connection is recycled
  * If there is an error in the output stream it automatically closes the connection so as to avoid all those ugly "could not get input stream" errors.
  * Also it prints out some of the causes for some exceptions thrown.
  * A "connected to ip address" gets displayed when a connection is made on both reciever and sender ends.

v2.0
  * Added internal support for file transfers using MsgFile class

v1.0
  * Support for Multiple connections over a single jnmp2p object
  * Same code runs over 2 machines
  * User defined events to handle communication between devices.


# Use #
To use the jar file, set your classpath to include jnmp2p.jar. To use the classes in code
```
import org.net.Msg.*;
import org.net.p2p.*;
```

The `org.net.Msg` package contains the classes to build Msg objects. The `org.net.p2p` package contains the classes you will use to establish connections and send messages over the network.

# Establish a connection #
```
class EstablishConnection{
      public static void Main(String[] args){
             EstablishConnection e=new EstablishConnection();
             Protocol prot=new Protocol(e);
             prot.addHandler("hello","say_hello"); 
             jnmp2p jnm=new jnmp2p(prot,12000); //random port number
             //reading the ip address to connect to
             BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
             String ip=in.readLine();
             Connection conn=jnm.connect(ip); //ip address to connect to
             String msgHeader="hello"; //the header corresponding to the handler function
             String msgBody="Subodh"; //msg content
             Msg m=conn.createMsg(msgHeader,msgBody);
             conn.sendMsg(m);
             
      }
    
      public void say_hello(Connection conn,Msg msg){
             System.out.println("hello "+msg.getContent());
      }
}
```

We declare an instance of the Protocol object passing the object that is going to handle it as a parameter in this case EstablishConnection has the method `say_hello` which will handle the msg so we pass an object of EstablishConnection.

We then add a Message Handler to the protocol using the `prot.addHandler()` method. The first argument represents the message header that the function say\_hello should be called in response to and the second is the function name itself.
Create a jnmp2p object using the protocol and a port at which the application should operate then create a connection object by calling upon the connect method which takes the ip address of the computer you want to connect to.
We can create a message object from the above connection and send the message object over the connection.

# Deploying #
Start the application on both computers and input the ip address of the other machine on one of them.

You should see the output of hello Subodh on the screen.



