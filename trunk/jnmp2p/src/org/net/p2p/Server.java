/*
 * Server.java
 * 
 * Created on May 7, 2008, 3:22:43 PM
 */

package org.net.p2p;

/*
 * @author subodh 
 * Copyright 2008 Subodh Iyengar
 * This file is part of jnmp2p.

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
