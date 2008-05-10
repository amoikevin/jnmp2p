/*
 * MsgFile.java
 * 
 * Created on May 9, 2008, 12:37:22 AM
 * 
 
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
public class MsgFile {
    
    String fileName=null;
    byte[] content;
    private int nobytes;
    public MsgFile(File a){
        String name=a.getName();
        fileName=name.substring(name.lastIndexOf("\\"));
        try{
        FileInputStream in=new FileInputStream(a);
        int bytes=in.available();
        nobytes=bytes;
        content=new byte[bytes];
        in.read(content);
        }
        catch(IOException e){
            
        }
    }
    public int getNoBytes(){
        return nobytes;
    }
    public String getfileName(){
        return fileName;
    }
    public byte[] getContent(){
        return content;
    }

}
