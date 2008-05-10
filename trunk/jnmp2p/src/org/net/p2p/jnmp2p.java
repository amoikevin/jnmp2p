/*
 * p2pjnm.java
 * 
 * Created on May 5, 2008, 9:06:44 PM
 */

package org.net.p2p;
import java.io.*;
import java.net.*;
import java.util.*;
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
public class jnmp2p {
    private static HashMap<String,Connection> conn=new HashMap<String,Connection>();
    private static HashMap<Connection,MsgScan> scanners=new HashMap<Connection,MsgScan>();
    private Protocol protocol=null;
    private int port;
    Server serv=null;
    
    public jnmp2p(Protocol prot,int port){
        protocol=prot;
        this.port=port;
        serv=new Server(port,this);
        Thread.currentThread().setName("main");            
        }

    void addConnection(String ip,Connection a){
        conn.put(ip, a);
        new MsgScan(protocol,a);
    }

    public Connection connect(InetAddress ip){
        Connection a=Connection.Connect(ip,port);
        conn.put(ip.getHostAddress(),a);
        if (a!=null) {
            
            scanners.put(a, new MsgScan(protocol,a));
            
    }
       // a.sendMsg(new Msg("initiate"));
        return a;
       
    }
      public Connection connect(String ip){
        Connection a=Connection.Connect(ip,port);
        conn.put(ip,a);
        if (a!=null) {
            new MsgScan(protocol,a);
            
    }
        a.sendMsg(new Msg("initiate"));
        return a;
       
    }
    
    public Connection connect(InetAddress ip,Object initiateObj){
        Connection a=Connection.Connect(ip,port);
        conn.put(ip.getHostAddress(),a);
        if (a!=null) {
            new MsgScan(protocol,a);
            
    }
        a.sendMsg(new Msg("initiate",initiateObj));
        return a;
       
    }
    
     public Connection connect(String ip,Object initiateObj){
        Connection a=Connection.Connect(ip,port);
        conn.put(ip,a);
        if (a!=null) {
            new MsgScan(protocol,a);
            
    }
        a.sendMsg(new Msg("initiate",initiateObj));
        return a;
       
    }
 public static boolean close(Connection a){
    try{
    a.getInput().close();
    a.getOutput().close();
    a.getSocket().close();
    conn.remove(a.getSocket().getInetAddress().getHostAddress());
    scanners.remove(a.getSocket().getInetAddress().getHostAddress());
    return true;
    }
    catch(IOException e){
        return false;
    }
    catch(NullPointerException e){
        return false;
    }
    }
 
 
 public void shutdown(){
        	 
        serv.stopRunning();
 
 	Iterator iterator = scanners.keySet().iterator();
 
        while (iterator.hasNext()) {
            Connection key = (Connection)iterator.next();
            MsgScan value = scanners.get(key);
            value.stopScan();
        }
       iterator = conn.keySet().iterator();
       while (iterator.hasNext()) {
            String key = (String)iterator.next();
            Connection value = conn.get(key);
            close(value);
        }
     }
    
}
