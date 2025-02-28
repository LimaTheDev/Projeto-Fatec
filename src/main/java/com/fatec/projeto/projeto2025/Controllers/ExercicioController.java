package com.fatec.projeto.projeto2025.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioController {

    @GetMapping("")
    public String HelloWorld() {
        return "hello";
    }


    @GetMapping("/get-age/{age}")
    public String ReturnAge(@PathVariable Integer age) {
        try {
         if (age < 0) {
            throw new NumberFormatException();
         }

         if (age < 12) {
            return "Child";
         } else if (age <= 18) {
            return "Adolescent";
         } else if (age <= 60) {
            return "Adult";
         } else {
            return "Elder";
         }

        } catch (NumberFormatException e) {
            return "Error: 'age' is not a valuable name";
        }
    }

    @GetMapping("/get-number/{number}")
    public String ReturnEvenOrOddsNumbers(@PathVariable String number) {
        if (IsNumeric(number)) {
            if (Integer.parseInt(number) % 2 == 0) {
                return "is even";
            } else {
                return "is odd";
            }
        } else {
            return "Error: is not a number";
        }
    }

    private Boolean IsNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @GetMapping("/get-imc/{weight}_{height}")
    public String ReturnImc(@PathVariable String weight, @PathVariable String height) {
        
        double imc;
        
        if (IsNumber(weight) && IsNumber(height)) {
            Float.parseFloat(weight);
            Float.parseFloat(height);
        
            imc = Float.parseFloat(weight) / Math.pow(Float.parseFloat(height), 2);
        
            if (imc < 18.5) {
                return "abaixo do peso";
            } else if (imc >= 18.5 && imc <= 24.9) {
                return "peso normal";
            } else if (imc >= 25.0 && imc <= 29.9) {
                return "sobrepeso";
            } else if (imc >= 30.0 && imc <= 34.9) {
                return "obesidade grau 1";
            } else if (imc >= 35.0 && imc <= 39.9) {
                return "obesidade grau 2";
            } else {
                return "obesidade grau 3";
            }
        } else {
            return "Error: is not a number";
        }
    }

    private Boolean IsNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


