package patientintake;

public class BMICalculator {

    public static double calculateBmi(Integer inches, Integer pounds){
        Double bmi = (double) (pounds * 703)/(inches * inches);
        return Math.floor(bmi);
    }
}
