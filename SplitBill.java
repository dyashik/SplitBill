import java.util.*;
import java.util.regex.PatternSyntaxException;

public class SplitBill {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        double arr[] = format(str);
        if(arr == null){
            s.close();
            return;
        }
        double amount = calculate(arr);
        amount = Math.round(amount * 100);
        amount = amount / 100;
        System.out.println("Each Person Pays: " + "\033[0;1m" + "$" + amount);
        System.out.println();
        s.close();
    }

    public static double[] format(String str){
        double arr[] = new double[3];
        double total;
        int people;
        double tip;
        try{
            String array[] = str.split(" ");
            //System.out.println(Arrays.toString(array));
            if(array.length == 3){
                total = Double.parseDouble(array[0]);
                people = Integer.parseInt(array[1]);
                if(array[2].endsWith("%")){
                    tip = Integer.parseInt(array[2].substring(0, array[2].length() - 1));
                } else if(isNumeric(array[2])){
                    tip = Integer.parseInt(array[2]);
                } else {
                    throw new NumberFormatException();
                }
                arr[0] = total;
                arr[1] = people;
                arr[2] = tip / 100;
            } else if (array.length == 2) {
                total = Double.parseDouble(array[0]);
                people = Integer.parseInt(array[1]);
                arr[0] = total;
                arr[1] = people;
                arr[2] = 0;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Format");
            return null;
        } catch (PatternSyntaxException e) {
            System.out.println("Invalid Spacing Format");
            return null;
        }
        return arr;
    }

    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }

    public static double calculate(double arr[]){
        double total = arr[0];
        double people = arr[1];
        double tip = arr[2];

        System.out.println("Your Receipt");
        System.out.println();
        System.out.println("-------------------------------");

        System.out.println("Total: " + arr[0]);
        System.out.println("People: " + ((int)arr[1]));
        System.out.println("Tip: " + (arr[2] * 100) + "%");

        System.out.println("-------------------------------");
        System.out.println();

        return (total / people) * (1.0 + tip);

    }
}
