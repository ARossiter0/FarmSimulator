package main.java;

public class DayTicker implements Component {
    private boolean dayTime;
    private int ticks;
    private Mediator m;
    
    public DayTicker() {
        dayTime = false;
        ticks = 0;
    }
    
    public boolean isDayTime() {
        return dayTime;
    }
    
    public int getTicks() {
        return ticks;
    }
    
    public void nextTick() {
        if (dayTime) {
            dayTime = false;
        } else {
            dayTime = true;
        }
        ticks++;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.m = mediator;
    }

    @Override
    public String getName() {
        return "DayTicker";
    }
}
