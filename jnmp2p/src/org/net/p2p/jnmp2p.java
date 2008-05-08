/*
 * p2pjnm.java
 * 
 * Created on May 5, 2008, 9:06:44 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net.p2p;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author subodh
 * Copyright 2008 Subodh Iyengar
 * Licensed under GPL v3
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

    protected void addConnection(String ip,Connection a){
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
 public static void close(Connection a){
    try{
    a.getInput().close();
    a.getOutput().close();
    a.getSocket().close();
    conn.remove(a.getSocket().getInetAddress().getHostAddress());
    scanners.remove(a.getSocket().getInetAddress().getHostAddress());
    }
    catch(IOException e){
        
    }
    catch(NullPointerException e){
        
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
