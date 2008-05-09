/*
 * Protocol.java
 * 
 * Created on May 5, 2008, 9:18:36 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net.p2p;
import java.util.*;
import java.lang.reflect.*;
/**
 *
 * @author subodh
 * Copyright 2008 Subodh Iyengar
 * Licensed under GPL v3
 */
public class Protocol implements Runnable {
    private HashMap<String,String> handlers=new HashMap<String,String>();
    
    Object caller;
    boolean lock=false;
    Msg tempMsg;
    Connection tempConn;
    
    public Protocol(Object caller){
        this.caller=caller;
        handlers.put("initiate", "connected");
    }
    
    public void addMsgHandler(String header,String function){
        handlers.put(header, function);
    }
    public void addMsgHandler(String header){
        handlers.put(header, header);
    }
    String getHandler(String header){
        return handlers.get(header);
    }
     void distribute(Connection a,Msg msg){
        while(lock){}
        tempMsg=msg;
        tempConn=a;
        new Thread(this).start();
        lock=true;
    }
    
    public void run(){
        Msg msg=tempMsg;
        Connection a=tempConn;
        lock=false;
        String header=msg.getHeader();
        String handler=handlers.get(header);
        try{
        Method m=caller.getClass().getMethod(header,new Class[]{Connection.class,Msg.class});
        m.invoke(caller, new Object[]{a,msg});
        }
        catch(NoSuchMethodException e){
            
        }
        catch(IllegalAccessException e){
            
        }
        catch(InvocationTargetException e){
            
        }
    }
    

         
        
}
