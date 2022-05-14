package clock;

public class Clock {

    private static Clock instance;

    private int tick;
    private ActiveStage currentStage;

    private Clock(){
        this.tick  = 0;
        this.currentStage = ActiveStage.FETCH;
    }

    public static Clock getInstance(){
        if(instance == null) instance = new Clock();
        return instance;
    }

    public void nextTick(){
        this.tick++;
        this.currentStage = this.currentStage == ActiveStage.FETCH ? ActiveStage.MEMORY : ActiveStage.FETCH;
    }

    public int getTick() {
        return tick;
    }

    public ActiveStage getCurrentStage() {
        return currentStage;
    }  
}
