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

