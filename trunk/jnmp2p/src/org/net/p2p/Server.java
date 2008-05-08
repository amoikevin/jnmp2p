/*
 * Server.java
 * 
 * Created on May 7, 2008, 3:22:43 PM
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
import java.util.*;

class Server implements Runnable{
    private ServerSocket service;
    private Socket client;
    private boolean running=true;
    private  int port;
    private jnmp2p jnm;
    
    public Server(int por,jnmp2p jnmp){
    try{
        port=por;
	service=new ServerSocket(port);
        jnm=jnmp;
	Thread t=new Thread(this);
	t.start();
    }
    catch(IOException e){	
		System.out.println("Could not start server");
	}
    }
    
    public void run(){
        while (running){
            try{
            client=service.accept();
            Connection a=new Connection(client,port);
            if (a==null) System.out.println("null conn object");
            jnm.addConnection(client.getInetAddress().getHostAddress(),a);
            
            }
            catch(IOException e){
            
            }
        }
    }
    
    public void stopRunning(){
        running=false;
    }
    
    
}
