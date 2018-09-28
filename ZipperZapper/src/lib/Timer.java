package lib;

/**
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class Timer {

    private static Timer instance;
    TimerEvent[] events = new TimerEvent[5];
    private int eventcount = 0;
    private int runningTime = 0;

    /**
     * Returns a singleton
     *
     * @return
     */
    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }

        return instance;
    }

    /**
     * Destroy the current Singleton object
     */
    public void reset() {
        this.eventcount = 0;
        this.runningTime = 0;
        this.events = new TimerEvent[5];
    }

    /**
     * Add a new event
     *
     * @param eventMessage
     */
    public void add(String eventMessage) {
        if (eventcount == this.events.length) {
            this.newEventsTable();
        }

        TimerEvent event = new TimerEvent(eventMessage);
        if (this.eventcount > 0) {
            this.runningTime = this.runningTime
                    + (int) (event.getTime() - this.events[eventcount - 1].getTime());
        }
        this.events[eventcount] = event;
        this.eventcount++;
    }

    /**
     *
     * @return the current list of events
     */
    public TimerEvent[] getEvents() {
        TimerEvent[] tmp = new TimerEvent[eventcount];
        for (int i = 0; i < eventcount; i++) {
            tmp[i] = this.events[i];
        }
        return tmp;
    }

    /**
     *
     * @return the current event count
     */
    public int getEventCount() {
        return this.eventcount;
    }

    /**
     * Return the current running time as microseconds.
     *
     * @return
     */
    public int getRunningTime() {
        return this.runningTime;
    }

    /**
     * Create a new events table as the old one has filled up.
     */
    private void newEventsTable() {
        int newLength = this.events.length * 2;
        TimerEvent[] newEvents = new TimerEvent[newLength];
        for (int i = 0; i < this.events.length; i++) {
            newEvents[i] = this.events[i];
        }
        this.events = newEvents;
    }
}
