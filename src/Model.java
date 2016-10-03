import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private static final int X = 5;

    private int iterations = 1000;
    private double step = 0.1;
    private int printStep = 1;

    private int a = 3, b1 = 1, b2 = 2, k = 4;

    private double z1 = 0, z2 = 0, z3 = 0;
    private double dz1, dz2, dz3;
    private double y;

    private Map<String, Double> results = new HashMap<String, Double>();

    /**
     * show calculated results
     */
    public void showResults() {
        for (int time=0; time < iterations; time++){
            calculate();
            results.put("" + time, y);
            printTimeAndY(time);
        }
    }

    /**
     * Calculate for loop iteration
     */
    private void calculate(){
        dz1 = z1 + step * z2;
        dz2 = z2 + step * z3;
        dz3 = z3 + step * ((X - z1 - b1 * z2 -b2*z3 - a*z2 - a*b1*z3)/a*b2);
        y = k * z1 - a * k  * z2;

        z1 = dz1;
        z2 = dz2;
        z3 = dz3;
    }

    /**
     * Print time and y values with step. Step can be set by set method
     * @param time current iteration value
     */
    private void printTimeAndY(int time){
        if(time % printStep == 0){
            System.out.println("\"" + time/printStep + "\": \"" + y + "\"");
        }

    }

    public Map<String, Double> getResults(){
        return results;
    }

    public void setIterations(int iterations){
        this.iterations = iterations;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public void setPrintStep(int printStep) {
        this.printStep = printStep;
    }
}
