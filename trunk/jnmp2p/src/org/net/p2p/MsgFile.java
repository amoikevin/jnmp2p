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
 * Licensed under GPL v3
 */

import java.io.*;
public class MsgFile {
    
    String fileName=null;
    byte[] content;
    public MsgFile(File a){
        String name=a.getName();
        fileName=name.substring(name.lastIndexOf("\\"));
        try{
        FileInputStream in=new FileInputStream(a);
        int bytes=in.available();
        content=new byte[bytes];
        in.read(content);
        }
        catch(IOException e){
            
        }
    }

}
