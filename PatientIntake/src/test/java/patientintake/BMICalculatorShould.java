package patientintake;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BMICalculatorShould {

    @Test
    @DisplayName("calculate BMI")
    void calculateCorrectly(){
        assertEquals(19.0, BMICalculator.calculateBmi(69, 130));
        assertEquals(21.0, BMICalculator.calculateBmi(70, 150));

    }

}
