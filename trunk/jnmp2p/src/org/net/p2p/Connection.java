/*
 * Connection.java
 * 
 * Created on May 7, 2008, 3:27:29 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net.p2p;

/**
 *
 * @author subodh
 * Copyright 2008 Subodh Iyengar
 * Licensed under GPL v3
 */

import java.io.*;
import java.net.*;

public class Connection {
   private Socket connSocket=null;
   private int port;
           
   private ObjectOutputStream output=null;
   private ObjectInputStream input=null;
   
   public String name;
   public static Connection Connect(InetAddress destination,int port){
       Socket tempSocket=null;
       try{
       tempSocket=new Socket(destination,port);
       return new Connection(tempSocket,port);
       }
       catch(IOException e){
       System.out.println("Cannot find other computer");
       return null;
       }
       
   }
   public static Connection Connect(String destination,int port){
       Socket tempSocket=null;
       try{
       tempSocket=new Socket(destination,port);
       if (tempSocket!=null)System.out.println("Connected to "+destination);
       return new Connection(tempSocket,port);
       }
       catch(IOException e){
       System.out.println("Cannot find other computer");
       return null;
       }
       
   }
   
   public Connection(Socket a,int port){
       
       this.port=port;
       connSocket=a;
       name=connSocket.getInetAddress().getHostAddress();
       try{           
       output=new ObjectOutputStream(connSocket.getOutputStream());
       input=new ObjectInputStream(connSocket.getInputStream());
       }
       catch(IOException e){
           System.out.println("Could not get Streams");
       }
       
   }
   
   public Msg getMsg(){
	
	try{
                Msg inputMsg=(Msg)input.readObject();
		return inputMsg;
	}
        catch(ClassNotFoundException e){
        }
        catch(NullPointerException e){
            
        }
	catch(IOException e){
		System.out.println("could not get input stream");
				}	
        return null;
    }

   public Msg createMsg(String header,Object body){
        return new Msg(header,body);
    }
   
   
   public void sendMsg(Object msg){
	try{
		output.writeObject(msg);
                output.flush();
        }
        catch(IOException e){
            
        }
        catch(NullPointerException e){
            
        }
    }
    protected ObjectInputStream getInput(){
        return input;
    }
    protected ObjectOutputStream getOutput(){
        return output;
    }
    protected Socket getSocket(){
        return connSocket;
    }
   
}
