/*
 * Connection.java
 * 
 * Created on May 7, 2008, 3:27:29 PM
 */

package org.net.p2p;

/**
 *
 * @author subodh
 * Copyright 2008 Subodh Iyengar
 *  This file is part of jnmp2p.

    jnmp2p is free software; you can redistribute it and/or modify
    it under the terms of the Lesser GNU General Public License as published by
    the Free Software Foundation; either version 3 of the License, or
    (at your option) any later version.

    jnmp2p is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    Lesser GNU General Public License for more details.

    You should have received a copy of the Lesser GNU General Public License
    along with jnmp2p; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import java.io.*;
import java.net.*;

public class Connection {
   private Socket connSocket=null;
   private int port;
           
   private ObjectOutputStream output=null;
   private ObjectInputStream input=null;
   
  
  static Connection Connect(InetAddress destination,int port){
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
  static Connection Connect(String destination,int port){
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
   
 Connection(Socket a,int port){
       
       this.port=port;
       connSocket=a;
       
       try{           
       output=new ObjectOutputStream(connSocket.getOutputStream());
       input=new ObjectInputStream(connSocket.getInputStream());
       }
       catch(IOException e){
           System.out.println("Could not get Streams");
       }
       
   }
   
   Msg getMsg(){
	
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
