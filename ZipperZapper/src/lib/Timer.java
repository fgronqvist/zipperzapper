package lib;

/**
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class Timer {

    String[][] events = new String[5][1];
    int eventcount = 0;

    /**
     * Add a new event
     * @param milliseconds
     * @param event 
     */
    public void add(long milliseconds, String event) {
        if (eventcount == this.events.length) {
            this.newEventsTable();
        }        
        this.events[eventcount][0] = event;
        eventcount++;
    }
    
    /**
     * 
     * @return the current list of events
     */
    public String[][] getEvents(){
        return this.events;
    }
    
    /**
     * 
     * @return the current event count
     */
    public int getEventCount(){
        return this.eventcount;
    }

    /**
     * Create a new events table as the old one has filled up.
     */
    private void newEventsTable() {
        int newLength = this.events.length * 2;
        String[][] newEvents = new String[newLength][1];
        for (int i = 0; i < this.events.length; i++) {
            newEvents[i][0] = this.events[i][0];
        }
        this.events = newEvents;
    }
}
