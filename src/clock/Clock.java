package clock;

public class Clock {

    private static Clock instance;

    private int tick;
    private ClockState currentState;

    private Clock(){
        this.tick  = 1;
        this.currentState = ClockState.FETCH;
    }

    public static Clock getInstance(){
        if(instance == null) instance = new Clock();
        return instance;
    }

    public void nextTick(){
        this.tick++;
        this.currentState = this.currentState == ClockState.FETCH ? ClockState.MEMORY : ClockState.FETCH;
    }

    public int getTick() {
        return tick;
    }

    public ClockState getCurrentStage() {
        return currentState;
    }  
}
