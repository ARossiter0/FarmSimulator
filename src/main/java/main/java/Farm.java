package main.java;

public class Farm implements Component {
    private int acreage;
    private Mediator m;
    
    public Farm() {
        this.acreage = 1;
    }
    

    @Override
    public void setMediator(Mediator mediator) {
        this.m = mediator;
    }

    @Override
    public String getName() {
        return "Farm";
    }
    
    public int getAcreage() {
        return acreage;
    }
    
    public void upgradeFarm() {
        acreage++;
    }
    
    
}
