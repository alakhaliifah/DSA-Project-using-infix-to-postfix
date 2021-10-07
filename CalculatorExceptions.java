import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CalculatorExceptions extends Exception
{
    public CalculatorExceptions()
    {
        super();

        ButtonFunction.clear();

        Calculator.history.setText(Calculator.textField.getText() +  "Cannot Divide by 0!" + "\n\n"+ Calculator.history.getText());

        Calculator.infixArray[Calculator.infixArrayCount]=null;

        JOptionPane.showMessageDialog(new JFrame(), "The denominator cannot be 0!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}