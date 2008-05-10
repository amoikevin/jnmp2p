/*
 * MsgScan.java
 * 
 * Created on May 7, 2008, 5:14:52 PM
 */

package org.net.p2p;

/**
 *
 * @author subodh 
Copyright 2008 Subodh Iyengar
  This file is part of jnmp2p.

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