import Exceptions.InvalidDirectionException;

import java.util.ArrayList;
import java.util.Random;

public class ModelingExample {
    public static void main(String[] args) {
//        operateWithModel();
        Optimization opt = new Optimization();
        opt.calculate();
    }

    public static void operateWithModel(){
        Model model = new Model();
        model.setPrintStep(20);
        model.setStep(0.05);
        model.showResults();
    }
}

/**
 * Algorithm of optimization. Test function and testing
 */
class Optimization {
    private double a = 1;
    private double b = 1;
    private double step = 0.1;
    private double x1 = 1;
    private double x2 = 1;
    private double functionResult;
    private ArrayList<Point> visitedPoints;

    public void calculate(){
        functionResult = getFunctionResultInPoints();
        visitedPoints = new ArrayList<>();
        int i = 0;
        while(true){
            makeStep(setDirection());
            if(i++ > 10){
                System.out.println("\n\nCalculating is over.\n" +
                        "Minimal function value in " + this.x1 + ":" + this.x2 + " point with function value " + this.functionResult + "\n");
                break;
            }

        }

    }

    private double getFunctionResultInPoints(){
        return Math.pow((this.x1/a), 2) + Math.pow((this.x2/b), 2);
    }

    /**
     * Return true if function result is less
     * @param direction Variants: top (1), bottom (2), left (3), right (4)
     * @return
     */
    private void makeStep(int direction) {
        boolean isMin;
        try {
            makeStepInDirection(direction);
        } catch (InvalidDirectionException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getDirection());
        }
        isMin = functionResult > getFunctionResultInPoints();
        if(visitedPoints.isEmpty()){
            visitedPoints.add(new Point(this.x1, this.x2));
        }
        if(addPointIfDoesNotExist()){
            if(isMin){
                functionResult = getFunctionResultInPoints();
            } else {
                try {
                    resetStep(direction);
                } catch (InvalidDirectionException e) {
                    System.out.println(e.getMessage());
                    System.out.println(e.getDirection());
                }
            }
        } else {
            try {
                resetStep(direction);
            } catch (InvalidDirectionException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getDirection());
            }
        }
    }

    private int setDirection(){
        Random random = new Random();
        return random.nextInt(3)+1;
    }

    /**
     *
     * @param direction Variants: top (1), bottom (2), left (3), right (4)
     */
    private void makeStepInDirection(int direction) throws InvalidDirectionException {
        switch(direction){
            case 1:
                this.x2 += step;
                break;
            case 2:
                this.x2 -= step;
                break;
            case 3:
                this.x1 -= step;
                break;
            case 4:
                this.x1 += step;
                break;
            default:
                throw new InvalidDirectionException("The direction is out of range 1-4. ", direction);
        }
    }

    /**
     *
     * @return true if added
     */
    private boolean addPointIfDoesNotExist(){
        boolean isAdded = false;
        for(Point point : visitedPoints){
            if(point.getX1() == this.x1 && point.getX2() == this.x2){
                System.out.println("Point " + this.x1 + ":" + this.x2 + " already exists.\n");
                isAdded = false;
                break;
            } else {
                visitedPoints.add(new Point(this.x1, this.x2));
                isAdded = true;
                break;
            }
        }
        return isAdded;
    }

    private void resetStep(int direction) throws InvalidDirectionException{
        switch(direction){
            case 1:
                this.x2 -= step;
                break;
            case 2:
                this.x2 += step;
                break;
            case 3:
                this.x1 += step;
                break;
            case 4:
                this.x1 -= step;
                break;
            default:
                throw new InvalidDirectionException("The direction is out of range 1-4. ", direction);
        }
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getStep() {
        return step;
    }
}

class Point {
    private double x1 = 2;
    private double x2 = 2;

    public Point(){
        this.x1 = 2;
        this.x2 = 2;
    }

    public Point(double x1, double x2){
        this.x1 = x1;
        this.x2 = x2;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }
}
