import java.util.EmptyStackException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ButtonFunction
{

    public static void clear()
    {

        for(int i = 0; i < Calculator.postfixArray.length; i++)
            Calculator.postfixArray[i] = null;
        for(int j = 0; j < Calculator.infixArray.length; j++)
            Calculator.infixArray[j]=null;


        Calculator.textField.setText("");
        Calculator.elements = "";
        Calculator.buttonLabel = "";
        Calculator.infixArrayCount = 0;
        Calculator.postfixArrayCount = 0;
    }

    public static void backspace()
    {
        try
        {

            String theText = Calculator.textField.getText();
            Calculator.textField.setText(theText.substring(0, theText.length()-2));

            Calculator.infixArray[Calculator.infixArrayCount] =
                    Calculator.infixArray[Calculator.infixArrayCount].substring(0, Calculator.infixArray[Calculator.infixArrayCount].length()-1);

            Calculator.elements = Calculator.infixArray[Calculator.infixArrayCount];
        }


        catch(StringIndexOutOfBoundsException errorString )
        {
            try
            {
                Calculator.infixArrayCount--;

                Calculator.infixArray[Calculator.infixArrayCount] =
                        Calculator.infixArray[Calculator.infixArrayCount].substring(0, Calculator.infixArray[Calculator.infixArrayCount].length()-1);

                Calculator.elements = Calculator.infixArray[Calculator.infixArrayCount];
            }


            catch(ArrayIndexOutOfBoundsException errorArray)
            {
                clear();
            }
        }

        catch(NullPointerException signError)
        {
            Calculator.infixArrayCount--;

            Calculator.infixArray[Calculator.infixArrayCount] =
                    Calculator.infixArray[Calculator.infixArrayCount].substring(0, Calculator.infixArray[Calculator.infixArrayCount].length()-1);
        }
    }

    public static void operandButton()
    {
        Calculator.elements += Calculator.buttonLabel;
        Calculator.infixArray[Calculator.infixArrayCount] = Calculator.elements;


    }

    public static void negativeSign()
    {
        if(Calculator.elements.equals(""))
        {
            Calculator.elements += "-";
            Calculator.infixArray[Calculator.infixArrayCount] = Calculator.elements;
        }
        else
        {

            JOptionPane.showMessageDialog(new JFrame(), "You can not use the NEGATIVE sign for subtraction!", "Calculation Error", JOptionPane.ERROR_MESSAGE);

            clear();
        }
    }

    public static void operatorButton()
    {
        Calculator.infixArrayCount++;

        Calculator.infixArray[Calculator.infixArrayCount] = Calculator.buttonLabel;

        Calculator.elements = "";

        Calculator.infixArrayCount++;
    }


    public static void memoryStore()
    {
        String theText = Calculator.textField.getText();
        Calculator.textField.setText(theText.substring(0, theText.length() - 2));


        if(Calculator.infixArrayCount==0 && Calculator.MS.length()==0 && Calculator.infixArray[Calculator.infixArrayCount]!=null)
        {
            Calculator.MS = Calculator.infixArray[Calculator.infixArrayCount];

            System.out.println("The " + Calculator.MS + " was added into memory!");
            Calculator.history.setText(Calculator.MS + " was added into memory!" + "\n\n"+ Calculator.history.getText());
        }

        else if(Calculator.infixArrayCount==0 && Calculator.MS.length()>0)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Clear the memory first!", "Error", JOptionPane.ERROR_MESSAGE);

            Calculator.history.setText(Calculator.MS + " is already in the memory!" + "\n\n"+ Calculator.history.getText());
        }
        else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Please insert a valid number!", "Error", JOptionPane.ERROR_MESSAGE);

            Calculator.history.setText(Calculator.textField.getText() + " is not a valid number to be added in memory!" + "\n\n"+ Calculator.history.getText());
        }
    }

    public static void memoryClear()
    {

        String theText = Calculator.textField.getText();
        Calculator.textField.setText(theText.substring(0, theText.length() - 2));


        if(Calculator.MS.length()>0)
        {
            Calculator.history.setText(Calculator.MS + " was deleted from the memory!" + "\n\n"+ Calculator.history.getText());

            Calculator.MS = "";
        }
        else if(Calculator.MS.length()==0)
        {
            Calculator.history.setText(Calculator.MS + "The memory is clear! " + "\n\n"+ Calculator.history.getText());

            clear();
        }
    }


    public static void memoryRecall()
    {

        String theText = Calculator.textField.getText();


        if(Calculator.MS.equals(""))
        {

            Calculator.textField.setText(theText.substring(0, theText.length() - 2));

            Calculator.history.setText(Calculator.MS + "The memory is clear! " + "\n\n"+ Calculator.history.getText());
        }
        else
        {

            Calculator.textField.setText(theText.substring(0, theText.length() - 2));

            Calculator.infixArray[Calculator.infixArrayCount] = Calculator.MS;

            Calculator.textField.setText(Calculator.textField.getText() + Calculator.MS);
        }
    }

    public static void equal()
    {
        if(Calculator.infixArray[0]==null)
        {
            Calculator.infixArray[0]="0";

            Calculator.textField.setText("0" + Calculator.textField.getText());
        }


        Calculator.postfixArrayCount = ArithmeticOperations.postfixStack(Calculator.postfixArray, Calculator.infixArray, Calculator.infixArrayCount);
        try
        {
            String total = ArithmeticOperations.result(Calculator.postfixArray, Calculator.postfixArrayCount);


            if(ArithmeticOperations.theNumberIsADouble(total))
            {
                Calculator.history.setText(Calculator.textField.getText() +  total + "\n\n"+ Calculator.history.getText());
                System.out.println("DECIMAL POINT " +  total);

                Calculator.textField.setText(total);

                Calculator.infixArrayCount = 0;

                Calculator.infixArray[Calculator.infixArrayCount] = total;


                try {
                    if(Double.parseDouble(Calculator.infixArray[0])==Double.POSITIVE_INFINITY || Double.parseDouble(Calculator.infixArray[0])==Double.NEGATIVE_INFINITY
                            ||Double.isNaN(Double.parseDouble(Calculator.infixArray[0])) )
                        throw new CalculatorExceptions();
                }

                catch(CalculatorExceptions InfinityResult)
                {
                    System.out.println("Division by 0, handeled");
                }
            }

            else if(ArithmeticOperations.isBigDecimal(total))// adding the whole calculation to the HISTORY TEXT AREA
            {	// if the TOTAL is very big and is written in scientific notation, display to the screen as a bigDecimal
                Calculator.history.setText(Calculator.textField.getText() +  total + "\n\n"+ Calculator.history.getText());
                System.out.println("DECIMAL POINT " +  total); // TEST - CONSOLE READING
                Calculator.textField.setText(total);
                Calculator.infixArrayCount = 0;
                Calculator.infixArray[Calculator.infixArrayCount] = total;
            }

            else
            {
                total = total.substring(0, total.indexOf('.'));
                Calculator.history.setText(Calculator.textField.getText() +  total + "\n\n"+ Calculator.history.getText());
                System.out.println("NO DECIMAL POINT " +  total);
                Calculator.textField.setText(total);
                Calculator.infixArrayCount = 0;
                Calculator.infixArray[Calculator.infixArrayCount] = total;
            }
        }

        catch(EmptyStackException tooManySymbols)
        {
            Calculator.history.setText(Calculator.textField.getText() +  "Invalid Input" + "\n\n"+ Calculator.history.getText());
            Calculator.textField.setText("Invalid input");
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);

            ButtonFunction.clear();
        }

        catch(StringIndexOutOfBoundsException pressingEqualWithNoOP)
        {
            Calculator.history.setText(Calculator.textField.getText() +  "No Operators/Operands found!" + "\n\n"+ Calculator.history.getText());
            Calculator.textField.setText("Invalid input");
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);

            ButtonFunction.clear();
        }
    }


    public static void checkDecimalPoint()
    {
        if(Calculator.infixArray[Calculator.infixArrayCount]!=null)
        {
            if(Calculator.infixArray[Calculator.infixArrayCount].contains("."))
            {
                String theText = Calculator.textField.getText();
                Calculator.textField.setText(theText.substring(0, theText.length()-1));
                System.out.println("Decimal point ignored"); // TEST - CONSOLE READING
            }
            else
                operandButton();
        }
        else
        {
            String theText = Calculator.textField.getText();
            Calculator.textField.setText(theText.substring(0, theText.length()-1));
            System.out.println("Decimal point ignored"); // TEST - CONSOLE READING
        }
    }
}