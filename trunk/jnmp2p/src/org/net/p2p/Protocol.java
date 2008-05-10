/*
 * Protocol.java
 * 
 * Created on May 5, 2008, 9:18:36 PM
 */

package org.net.p2p;
import java.util.*;
import java.lang.reflect.*;
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
