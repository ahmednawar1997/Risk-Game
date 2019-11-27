/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riskgamegui;

import javafx.scene.Scene;

/**
 *
 * @author Shams Sherif
 */
class Singleton 
{ 
    // static variable single_instance of type Singleton 
    private static Singleton single_instance = null; 
  private Scene s=null;
    // variable of type String 

    public Scene getS() {
        return s;
    }

    public void setS(Scene s) {
        this.s = s;
    }
   
  
    // private constructor restricted to this class itself 
    private Singleton() 
    { 
        
    } 
  
    // static method to create instance of Singleton class 
    public static Singleton getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Singleton(); 
  
        return single_instance; 
    } 
} 