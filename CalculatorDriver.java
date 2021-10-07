import javax.swing.JFrame;

public class CalculatorDriver
{
    public static void main(String[] args)
    {
        Calculator myWindow = new Calculator();
        myWindow.setLocation(400, 200);
        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
