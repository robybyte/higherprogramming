/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unibw.etti.racer.handler;

import edu.unibw.etti.racer.controller.GameControl;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author rgn
 */
public class MoveCarHandler implements EventHandler<KeyEvent>{
    GameControl gameControll  = null;
    
    public MoveCarHandler(GameControl gC) {
        this.gameControll = gC;
    }
    @Override
    public void handle(KeyEvent event) {
        
        if(null != event.getCode() && this.gameControll != null) //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch (event.getCode()) {
            case UP:
                if(event.getEventType() == KeyEvent.KEY_PRESSED) {
                    gameControll.setShiftAmount(-3.0);
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                    gameControll.setShiftAmount(0);
                }
                break;
            case DOWN: 
                if(event.getEventType() == KeyEvent.KEY_PRESSED) {
                    gameControll.setShiftAmount(0.0);
                } else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
                    gameControll.setShiftAmount(3.0);
                }
            default:
                break;
        }
        
     //   System.out.println("Key: " + event.getCharacter() + " SA: " +gameControll.getShiftAmount() );
     }
    
}
