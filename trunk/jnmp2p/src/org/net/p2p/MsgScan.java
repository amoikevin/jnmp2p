/*
 * MsgScan.java
 * 
 * Created on May 7, 2008, 5:14:52 PM
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
class MsgScan implements Runnable{
    Protocol protocol;
    Connection conn;
    boolean running=true;
    
    MsgScan(Protocol prot,Connection a){
        protocol=prot;
        conn=a;
        new Thread(this).start();
    }
    public void run(){
        
        try{ 
        while(running){
		
                Msg currentMsg=conn.getMsg();
                protocol.distribute(conn,currentMsg);
	    }   
        }
        catch(NullPointerException e){
            running=false;
            jnmp2p.close(conn);
        }
        }
    public void stopScan(){
        running=false;
    }
    
}