package main.java;

public class Farmer implements Component {
    public static final int COSTPERDAY = 200;
    public static final int TASKSPERDAY = 2;
    private int tasksDone;
    private String name;
    private Mediator m;
    
    public Farmer(String name) {
        this.name = name;
        this.tasksDone = 0;
    }
    
    public void resetTasks() {
        tasksDone = 0;
    }
    
    public int getTasksDone() {
        return tasksDone;
    }
    
    
    /**
     * Attempts to do a task, returns true if successful and false if too many tasks
     * have been done that day
     * @return
     */
    public void doTask() {
        tasksDone++;
    }
    
    public String getFarmerName() {
        return name;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.m = mediator;
    }

    @Override
    public String getName() {
        return "Farmer";
    }
}
