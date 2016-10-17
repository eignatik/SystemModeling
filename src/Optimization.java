import java.util.ArrayList;

/**
 * Algorithm of optimization. Test function and testing
 */
public class Optimization {
    private double a = 1;
    private double b = 1;
    private double step = 1;
    private double x1 = 2;
    private double x2 = 2;
    private double functionResult;
    private ArrayList<Point> visitedPoints;

    public void calculate(){
        functionResult = getFunctionResultInPoints(x1, x2);
        visitedPoints = new ArrayList<>();
        int i = 0;
        while(true){
            x1 += step;
            if(!makeStep(x1, x2)){
                x1 = x1 - step - step;
                if(!makeStep(x1, x2)){
                    x1 += step;
                    x2 += step;
                    if(!makeStep(x1, x2)){
                        x2 = x2 - step - step;
                        if(!makeStep(x1, x2)){
                            x2 += step;
                            System.out.println("\n Not allowed ways \n");
                            System.out.println("\n\nCalculating is over.\n" +
                                    "Minimal function value in " + this.x1 + ":" + this.x2 + " point with function value " + this.functionResult + "\n");
                            break;
                        }
                    }
                }
            }

            if(i++ > 9){
                System.out.println("\n\nCalculating is over.\n" +
                        "Minimal function value in " + this.x1 + ":" + this.x2 + " point with function value " + this.functionResult + "\n");
                break;
            }

        }

    }

    private double getFunctionResultInPoints(double x1, double x2){
        return Math.pow((x1/a), 2) + Math.pow((x2/b), 2);
    }

    private boolean makeStep(double x1, double x2) {
        if(isPointExist(x1, x2)){
            return false;
        } else {
            visitedPoints.add(new Point(x1, x2));
            if(getFunctionResultInPoints(x1, x2) < functionResult){
                functionResult = getFunctionResultInPoints(x1, x2);
                return true;
            } else {
                System.out.println("\n Function value >= then current value in this point (" + x1 + ":" + x2 + ")\n");
                return false;
            }
        }
    }

    /**
     * returns true if point exists
     * @param x1 first function value
     * @param x2 second function value
     * @return
     */
    private boolean isPointExist(double x1, double x2){
        boolean isExist = false;
        for(Point point : visitedPoints){
            if(point.getX1() == x1 && point.getX2() == x2){
                System.out.println("\nPoint " + x1 + ":" + x2 + " is already exists.\n");
                isExist = true;
                break;
            }
        }
        return isExist;
    }
}