package calculator;
import java.util.*;
import java.lang.*;

/**
 *
 * @Andrew Bishop, student #C0344994
 */

public class CalcBrain implements Calculations {
    
    private Stack<Float> nums;
    private String operand;
    private Float value;
    private Float total;
    
    public CalcBrain() {
        nums = new Stack<Float>();
        operand = "";
        value = new Float(0);
        total = new Float(0);
    }

    public String digit(String digit) {
        operand += digit;
        return digit;
    }

    public String operator(String op){
        //Check if stack is empty.
        if (!nums.empty()) {
            //Check if a number has been input, and push to stack if so.
            if (operand != "") {
                value = Float.parseFloat(operand);
                nums.push(value);
                operand = "";
            }
            Float num2 = nums.pop();
            /* If stack is still not empty after first pop, 
            pop another value and conduct the math. */
            if (!nums.empty()) {
                Float num1 = nums.pop();
                switch(op) {
                    case "+":
                        total = num1 + num2;
                        break;
                    case "-":
                        total = num1 - num2;
                        break;
                    case "*":
                        total = num1 * num2;
                        break;
                    case "/":
                        total = num1 / num2;
                        break;
                    case "^":
                        Double dTotal = Math.pow(num1.doubleValue(), num2.doubleValue());
                        total = dTotal.floatValue();
                }   
                nums.push(total);
                return op + "\n" + total + " ";
            } else {
                // Push the first value back to stack if no math has occurred.
                nums.push(num2);
                return "";
            }
        } else {
            return "";
        }
    }     
    
    public String clearEntry() {
        if (!nums.empty()) {
            if (operand == "") {
                nums.pop();
            } else {
                operand = "";
            }
        }
        return "\nEntry cleared\n";
    }
    
    public String clear() {
        operand = "";
        nums.clear();
        return "\nClear All\n";
    }
    
    public String enterPressed() {
        value = Float.parseFloat(operand);
        nums.push(value);
        operand = "";
        return " ";
    }
    
    public String addDecimal() {
        if (operand.contains(".")) {
            return "";
        } else { 
            operand += ".";
            return ".";
        }
    }
}