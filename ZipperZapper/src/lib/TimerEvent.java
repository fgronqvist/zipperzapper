/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class TimerEvent {
    private String event;
    private String memory;
    private Long time;

    TimerEvent(String eventMessage) {
        this.setEvent(eventMessage);
    }
        
    /**
     * @return the event
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    private void setEvent(String event) {
        this.setTime();
        this.setMemory();
        this.event = event;
    }

    /**
     * @return the memory
     */
    public String getMemory() {
        return memory;
    }

    /**
     */
    private void setMemory() {
        this.memory = String.format("Total: %s, Free: %s",
                Runtime.getRuntime().totalMemory(),
                Runtime.getRuntime().freeMemory());                
    }

    /**
     * @return the time
     */
    public Long getTime() {
        return time;
    }

    /**
     */
    private void setTime() {
        this.time = System.currentTimeMillis();
    }
    
    
}
