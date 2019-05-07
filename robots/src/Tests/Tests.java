//package Tests;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import gui.Bug;
//import gui.GameVisualizer;
//import org.junit.jupiter.api.Test;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class Tests {
//    GameVisualizer game = new GameVisualizer();
//
//
//    private HashMap<Bug, ArrayList<Double>> getBugsData() {
//        HashMap<Bug, ArrayList<Double>> bugsPositions = new HashMap<>();
//        for (Bug bug : game.bugs) {
//            ArrayList<Double> points = new ArrayList<>();
//            points.add(bug.getM_bugPositionX());
//            points.add(bug.getM_bugPositionY());
//            bugsPositions.put(bug, points);
//        }
//        return bugsPositions;
//    }
//
//    @Test
//    void eatAndStopTest() {
//        game.stopTimer();
//        game.setStartPositions();
//        game.targets = new ArrayList<>(); //вся еда была "съедена"
//
//        HashMap<Bug, ArrayList<Double>> bugsPositions = getBugsData(); //соберем данные о положении жуков
//
//        game.onModelUpdateEvent(); //пытаемся заставить жуков двигаться в сторону еды
//
//        HashMap<Bug, ArrayList<Double>> bugsPositionsAfterAWhile = getBugsData(); //снова соберем данные о положении жуков
//
//        for (Bug bug : game.bugs) {
//            double x1 = bugsPositions.get(bug).get(0);
//            double y1 = bugsPositions.get(bug).get(1);
//            double x2 = bugsPositionsAfterAWhile.get(bug).get(0);
//            double y2 = bugsPositionsAfterAWhile.get(bug).get(1);
//            assertEquals(x1, x2); // после того как вся еда была "съедена" жуки перестали двигаться
//            assertEquals(y1, y2);
//        }
//    }
//
//    @Test
//    void oneSurvivorTest() {
//        game.stopTimer();
//        game.setStartPositions();
//        for (Bug bug : game.bugs) {   //поместим каждого жука в одну точку
//            bug.setM_bugPositionX(50);
//            bug.setM_bugPositionY(50);
//        }
//        game.onModelUpdateEvent();
//        assertEquals(1, game.bugs.size()); //остался всего один жук, другие были съедены
//    }
//
//    @Test
//    void runToFoodTest() {
//        game.stopTimer();
//        game.setStartPositions();
//
//        HashMap<Bug, ArrayList<Double>> bugsPositions = getBugsData(); //соберем данные о положении жуков
//
//        for (int i = 0; i < 10; i++) {
//            game.onModelUpdateEvent();
//        }
//
//        HashMap<Bug, ArrayList<Double>> bugsPositionsAfterAWhile = getBugsData(); //снова соберем данные о положении жуков
//
//        for (Bug bug : game.bugs) {
//            double x1 = bugsPositions.get(bug).get(0);
//            double y1 = bugsPositions.get(bug).get(1);
//            double x2 = bugsPositionsAfterAWhile.get(bug).get(0);
//            double y2 = bugsPositionsAfterAWhile.get(bug).get(1);
//            assertNotEquals(x1, x2); //каждый жук должен был изменить свое положение
//            assertNotEquals(y1, y2);
//        }
//    }
//
//    @Test
//    void aLotOfFoodTest() {
//        game.stopTimer();
//        game.setStartPositions();
//        game.targets = new ArrayList<>();
//
//        for (Bug bug : game.bugs) {
//            int x = (int) bug.getM_bugPositionX() + 2;
//            int y = (int) bug.getM_bugPositionY() + 2;
//            game.targets.add(new Point(x, y));  //вблизи каждого жука поставим еду
//        }
//
//        for (int i = 0; i < 25; i++) {
//            game.onModelUpdateEvent();
//        }
//
//        assertEquals(0, game.targets.size()); //каждый жук должен был съесть еду которая была самой близкой к нему
//    }
//}
