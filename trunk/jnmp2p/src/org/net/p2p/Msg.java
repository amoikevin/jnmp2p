/*
 * Msg.java
 * 
 * Created on May 5, 2008, 5:01:04 PM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.net.p2p;

import java.io.*;
/**
 *
 * @author subodh
 */
public class Msg implements Serializable{
    private String header;
    private Object content;
    private String type;
    
    private boolean seal=false;
    private String password;
    private boolean passset=false;
    public Msg(String h){
        header=h;
    }
    public Msg(String h,Object c){
            header=h;
            content=c;
            type=c.getClass().getName();
    }
    public Object getContent(){
        return content;
    }
    public String getHeader(){
        return header;
    }
   public void fill(Object c){
        if (!seal){
            content=c;
            type=c.getClass().getName();
        }
   }
   public void seal(){
       seal=true;
   }
   public void setPass(String pass){
       password=pass;
       passset=true;
   }
   
  public void unseal(String pass){
      if (pass.equals(password)){
          seal=false;
      }
  }
   
  public void unseal(){
      if (!passset){
          seal=false;
      }
  }
}