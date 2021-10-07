import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ArithmeticOperations
{

    public static String result(String postfixArray[], int postfixArrayCount)
    {
        Stack <String> resultStack =  new Stack<String>();

        for(int i = 0; i < postfixArrayCount; i++)
        {
            if(postfixArray[i] != null)
            {

                if(isNumber(postfixArray[i]))
                    resultStack.push(postfixArray[i]);


                else if(isOperator(postfixArray[i]))
                {
                    System.out.println("PEEK FIRST: " + resultStack.peek());
                    double first = Double.parseDouble(resultStack.pop());
                    System.out.println("PEEK SECOND: " + resultStack.peek());
                    double second = Double.parseDouble(resultStack.pop());
                    System.out.println("OPERATOR: " + postfixArray[i]);
                    String operator = postfixArray[i];
                    double  calculation = 0;

                    switch(operator)
                    {
                        case"+": calculation = second + first; break;
                        case"-": calculation = second - first; break;
                        case"*": calculation = second * first; break;
                        case"/": calculation = second / first; break;
                    }

                    resultStack.push(calculation+"");
                }
            }
        }// TEST - CONSOLE READING

        return resultStack.pop();
    }

    public static boolean isNumber(String checkNumber)
    {
        try
        {
            Double.parseDouble(checkNumber);
            return true;
        }
        catch(NumberFormatException | NullPointerException nfe)
        {
            return false;
        }
    }

    public static boolean isOperator(String checkOperator)
    {
        switch (checkOperator)
        {
            case"+": return true;
            case"-": return true;
            case"*": return true;
            case"/": return true;
            default: return false;
        }
    }

    public static int thePriority(String operator)
    {
        int priority = 0;

        if(operator.equals("(") || operator.equals(")"))
            priority = 1; // last priority
        if(operator.equals("+") || operator.equals("-"))
            priority = 2; // middle priority
        if(operator.equals("*") || operator.equals("/"))
            priority = 3; // first priority

        return priority;
    }

    public static int postfixStack(String postfixArray[], String infixArray[], int infixArrayCount)
    {
        Stack <String> postfix =  new Stack<String>();
        int countPostFix = 0;

        for(int i = 0; i <= infixArrayCount; i++)
        {
            if(infixArray[i]==null || infixArray[i].equals(""))
                continue;

            if(infixArray[i].equals("("))
            {
                postfix.push("(");
            }

            else if(infixArray[i].equals(")"))
            {
                try
                {
                    while(!postfix.peek().equals("("))
                    {
                        postfixArray[countPostFix] = postfix.pop();
                        countPostFix++;
                    }

                    if(postfix.peek().equals("("))
                        postfix.pop();
                }

                catch(EmptyStackException noMatchingBraket)
                {
                    Calculator.history.setText(Calculator.textField.getText() +  "Open bracket missing!" + "\n\n"+ Calculator.history.getText());
                    Calculator.textField.setText("Matching bracket missing");
                    JOptionPane.showMessageDialog(new JFrame(), "Open bracket missing!", "Error", JOptionPane.ERROR_MESSAGE);

                    ButtonFunction.clear();
                }
            }

            else if(isOperator(infixArray[i]))
            {

                if(postfix.isEmpty() || thePriority(infixArray[i]) > thePriority(postfix.peek()))
                {
                    countPostFix++;
                    postfix.push(infixArray[i]);
                }

                else
                {
                    while(!postfix.isEmpty() && thePriority(infixArray[i]) <= thePriority(postfix.peek()))
                    {
                        postfixArray[countPostFix] = postfix.pop();
                        countPostFix++;
                    }

                    postfix.push(infixArray[i]);
                }
            }

            else if(isNumber(infixArray[i]))
            {
                postfixArray[countPostFix] = infixArray[i];
                countPostFix++;
            }
        }

        while(!postfix.isEmpty())
        {
            postfixArray[countPostFix] = postfix.pop();
            countPostFix++;
        }

        return countPostFix;
    }

    public static boolean theNumberIsADouble(String result)
    {

        boolean answer = true;

        double theResult = Double.parseDouble(result);
        if(theResult%1==0)
            answer = false;

        return answer;
    }

    public static boolean isBigDecimal(String result)
    {
        return result.contains("E");
    }

}
