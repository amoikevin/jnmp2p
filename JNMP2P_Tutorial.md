# Objective #
This tutorial, or kick start, should I say, should give you the basics of how to use the functionality of JNMP2P. In less than 10 min you could be running you own peer to peer application.

# Required files #
Download the jnmp2p\_v2.x file and extract it into your computer. You should see a file called jnmp2p.jar. Scan the README.txt file for information on changes and the lgpl license file for licensing information.

So lets get started then.

# Use #
To use the jar file, set your current project classpath to include jnmp2p.jar. If youre using netbeans that is as simple as right clicking project properties, going to libraries and adding the jnmp2p.jar folder in the compile time libraries tab.

To use the classes in code
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

# Ease of Use #
Now you've seen the ease with which powerful p2p applications can be developed using JNMP2P. JNMP2P can be also used as a lightweight server for P2P service application or to develop server client applications, which I will tell you about in another wiki article.

# Fair Network Play #
Through its messaging system, JNMP2P reduces the network load for applications. I like to call it "fair network play". It's extemely fast and reliable. So now you know how to use it, you can develop powerful networked applications, almost on the fly. So have fun.