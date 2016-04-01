
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This is the project made for a calculator in android. It has two classes:
 * InfixToPostfix and PostFixEvaluation.
 * ============================================================================
 * Tasks accomplished by InfixToPostfix class: 1) Takes in a single String which
 * is an expression such as 2+3-4 2) The constructor converts this string into
 * char array and then to appropriate String List containing infix expression.
 * 3) Then this String List is converted into the corresponding postfix
 * expression by using function infixToPostfix
 * ============================================================================
 * Tasks accomplished by PostfixEvaluation class: 1) Evaluates postfix list
 * above.
 *
 * @author Hartej Singh Grewal
 */
class InfixToPostfix {

    /**
     * Character array for each character of the expression string. Example:
     * String: 300+4X20-1÷4 ; Char Array: [3,0,0,+,4,X,2,0,-,1,÷,4]
     */
    private char[] charArray;
    /**
     * String List corresponding to expression. Example:
     * charArray:[3,0,0,+,4,X,2,0,-,1,÷,4];stringList: [300,+,4,X,20,-,1,÷,4]
     */
    private ArrayList<String> stringList;
    /**
     * Stack for implementing algorithm.
     */
    private ArrayList<String> stack = new ArrayList<String>();
    /**
     * resultList for the double numbers and store result of operations.
     */
    private ArrayList<String> resultList = new ArrayList<String>();

    /**
     * Converts this string into char array and then to appropriate String List
     * containing infix expression.
     *
     * @param str
     */
    public InfixToPostfix(String str) {
        charArray = str.toCharArray();
        
        stringList = new ArrayList<String>();
        char deci = '.';
        for (int i = 0; i < charArray.length; i++) {
            if (i == 0 || stringList.isEmpty()) {
                stringList.add("" + charArray[i]);
            } else if (((Character.isDigit(charArray[i - 1])) || (charArray[(i - 1)] == deci)) && (Character.isDigit(charArray[i]))) {
                int pos = stringList.size() - 1;
                stringList.set(pos, stringList.get(pos) + charArray[i]);
            } else if (charArray[i] == deci && (Character.isDigit(charArray[i - 1]))) {
                int pos = stringList.size() - 1;
                stringList.set(pos, stringList.get(pos) + charArray[i]);
            } else {
                stringList.add("" + charArray[i]);
            }
        }
        
    }

    void printCharArray() {
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }
    }

    void printFinalArray() {

        System.out.print("Elements of stringList: ");
        for (String item : stringList) {
            System.out.print("\t" + item);
        }
        System.out.println();

    }
    
    void printResultArray() {

        System.out.print("Elements of resultList: ");
        for (String item : resultList) {
            System.out.print("\t" + item);
        }
        System.out.println();

    }

    private void push(ArrayList<String> list, String elementToBePushed) {
        list.add(elementToBePushed);
    }

    private String getLastElement(ArrayList<String> list) {
        int lastElement = list.size() - 1;
        return list.get(lastElement);
    }

    private void pop(ArrayList<String> list) {
        list.remove(list.size() - 1);
    }

    int precedence(String operator) {
        if ((operator.equals("^")) || (operator.equals("√"))) {
            return 4;
        } else if ((operator.equals("X")) || (operator.equals("÷"))) {
            return 3;
        } else if ((operator.equals("+")) || (operator.equals("-"))) {
            return 2;
        } else {
            return 1;
        }
    }

    ArrayList<String> infixToPostfix() {

        stringList.add(")");
        stack.add("(");
        while (!(stack.isEmpty())) {

            for (int i = 0; i < stringList.size(); i++) {
                if (!(stack.isEmpty())) {

                    if (!(((stringList.get(i)).equals("+"))
                            || ((stringList.get(i)).equals("-"))
                            || ((stringList.get(i)).equals("X"))
                            || ((stringList.get(i)).equals("÷"))
                            || ((stringList.get(i)).equals("^"))
                            || ((stringList.get(i)).equals("√"))
                            || ((stringList.get(i)).equals("("))
                            || ((stringList.get(i)).equals(")")))) { //  System.out.println("I'm in if loop 1 and i = "+ i);
                        resultList.add(stringList.get(i));
                    } else if ((stringList.get(i)).equals("(")) {
                        push(stack, (stringList.get(i)));
                        //System.out.println("I'm in if loop 2 and i = "+ i);
                    } else if (!(stringList.get(i).equals(")"))) {
                        // int precedenceOfStack = precedence(stack.get(stack.size() - 1));
                        int precedenceOfCurrent = precedence(stringList.get(i));
                        // System.out.println("precedenceOfCurrent = "+ precedenceOfCurrent + " i = "+ i + "\tcurrent element = " +stringList.get(i));
                        // System.out.println("precedenceOfStack = "+ precedenceOfStack + " i = "+ i + "\tstack elemant = "+ stack.get(stack.size() - 1));

                        while (precedence(stack.get(stack.size() - 1)) >= precedenceOfCurrent) {
                            resultList.add(stack.get(stack.size() - 1));
                            pop(stack);
                            //System.out.println("I'm in FIRST WHILE loop 1 and i = "+ i);

                        }
                        push(stack, (stringList.get(i)));
                        //System.out.println("I'm in OUTSIDE FIRST WHILE loop 1 and i = "+ i);
                    }

                    if (stringList.get(i).equals(")")) {
                        while (!(getLastElement(stack).equals("("))) {
                            resultList.add(stack.get(stack.size() - 1));
                            pop(stack);
                            //System.out.println("I'm in IN SECOND WHILE loop 1 and i = "+ i);
                        }
                        pop(stack);
                        // System.out.println("I'm in OUTSIDE SECOND WHILE loop 1 and i = "+ i);
                    }

                }
            }

        }
        //printResultArray();
        return resultList;
    }
}

public class PostfixEvaluation {

    ArrayList<String> postFixList;

    public PostfixEvaluation(String str) {
        InfixToPostfix infixToPostfix = new InfixToPostfix(str);
        postFixList = infixToPostfix.infixToPostfix();

    }

    public double result() {
        return evaluatePostfix(postFixList);
    }
    ArrayList<Double> numbersList = new ArrayList<Double>();

    private double evaluatePostfix(ArrayList<String> postfixList) {
        postfixList.add(")");
        //int i = 0;
        for (int i = 0; (!(postfixList.get(i).equals(")"))); i++) {

            String currentItem = postfixList.get(i);

            if (!((currentItem.equals("+"))
                    || (currentItem.equals("-"))
                    || (currentItem.equals("X"))
                    || (currentItem.equals("÷"))
                    || (currentItem.equals("^"))
                    || (currentItem.equals("√")))) {
                try {
                    
                    numbersList.add(Double.valueOf(postfixList.get(i)));
                } catch (NumberFormatException e) {
                    System.out.println(postfixList.get(i) + " is not a valid Number[Caught in Exception]");
                }
            }
            else if ((numbersList.size() == 1) && (currentItem.equals("+"))) {
                Double firstNumber = numbersList.get(0);
                numbersList.set(0, +firstNumber);
            }
            else if ((numbersList.size() == 1) && (currentItem.equals("-"))) {
                Double firstNumber = numbersList.get(0);
                numbersList.set(0, -firstNumber);
            }
            else if (currentItem.equals("+")) {
                double TopElement = pop(numbersList);
                double NextToTopElement = pop(numbersList);
                BigDecimal bd1 = BigDecimal.valueOf(TopElement);
                BigDecimal bd2 = BigDecimal.valueOf(NextToTopElement);
                BigDecimal res = bd2.add(bd1);
                push(numbersList, (res.doubleValue()));
            }
            else if (currentItem.equals("-")) {
                double TopElement = pop(numbersList);
                double NextToTopElement = pop(numbersList);
                BigDecimal bd1 = BigDecimal.valueOf(TopElement);
                BigDecimal bd2 = BigDecimal.valueOf(NextToTopElement);
                BigDecimal res = bd2.subtract(bd1);
                push(numbersList, (res.doubleValue()));
            }
            else if (currentItem.equals("X")) {
                double TopElement = pop(numbersList);
                double NextToTopElement = pop(numbersList);
                push(numbersList, (NextToTopElement * TopElement));
            }
            else if (currentItem.equals("÷")) {
                double TopElement = pop(numbersList);
                double NextToTopElement = pop(numbersList);
                push(numbersList, (NextToTopElement / TopElement));
            }
            else if (currentItem.equals("^")) {
                double TopElement = pop(numbersList);
                double NextToTopElement = pop(numbersList);
                push(numbersList, (Math.pow(NextToTopElement, TopElement)));
            }
            else if (currentItem.equals("√")) {
                double TopElement = pop(numbersList);
                push(numbersList, (Math.sqrt(TopElement)));
            }
            else {
                System.out.println(postfixList.get(i) + " is not a valid Number");
            }
        }
        return numbersList.get(numbersList.size() - 1);
    }

    private void push(ArrayList<Double> list, Double elementToBePushed) {
        list.add(elementToBePushed);
    }

    private double pop(ArrayList<Double> list) {

        double poppedItem = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return poppedItem;
    }
}
