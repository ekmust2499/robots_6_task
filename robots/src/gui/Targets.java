package gui;

import java.awt.*;
import java.util.ArrayList;

public class Targets implements TargetsMBean {

    public volatile ArrayList<Point> targets = new ArrayList<>();

    public void addTarget(Point target){
        targets.add(target);
    }

    public void removeTarget(Point target){
        targets.remove(target);
    }

    public ArrayList<Point> getTargetsList(){
        return targets;
    }

    @Override
    public int getTargetCount() {
        return targets.size();
    }

    @Override
    public ArrayList<Point> targets() {
        return targets;
    }
}
