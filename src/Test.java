
import java.util.ArrayList;
/**
 *
 * @author Grewalz
 */
public class Test {
    public static void main(String[] args) {
        //String str ="400+2";
        String str1 = "A+(BXC-(D÷E^F)XG)XH";
        String str2 = "5.6+5.8";
        String str3 = "5X(6+2)-12÷4";
        String str4 = "5X(6+2)-12÷4";
        String str5 = "d÷eX(c+2)";
        //InfixToPostfix my_string1 = new InfixToPostfix(str1);
        //InfixToPostfix my_string2 = new InfixToPostfix(str2);
        //InfixToPostfix my_string3 = new InfixToPostfix(str3);
       //InfixToPostfix my_string4 = new InfixToPostfix(str4);
        //InfixToPostfix my_string5 = new InfixToPostfix(str5);
        //my_string.printCharArray();
        // my_string.printFinalArray();
        // my_string.testPushPop();
        //ArrayList<String> infixToPostfix1 = my_string4.infixToPostfix();
       // int i = 0;
        /*System.out.println("===============================================");
        System.out.println("\tElements of  Postfix Array: ");
        System.out.println("===============================================");
        for (String item1 : infixToPostfix1) {

            System.out.println("Element at index " + i + " = " + item1);
            i++;
        }
        System.out.println("**********************************************");*/
        
        PostfixEvaluation obj = new PostfixEvaluation(str3);
        double result = obj.result();
        System.out.println("===============================================");
        System.out.println("\t\tRESULT: " + result);
        System.out.println("===============================================");
        
        

        //my_string2.print_postfixArray();
        //my_string3.print_postfixArray();
        //my_string4.print_postfixArray();
        //my_string5.print_postfixArray();
        //Character.isDigit('1');
        //my_string.print_array();
        //System.out.println(Character.isDigit('1'));
    }
}
