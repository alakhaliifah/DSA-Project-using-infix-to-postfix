import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Calculator extends JFrame
{

    protected static JTextField textField;


    protected static JTextArea history;


    private JButton button;


    private JLabel historyLabel;


    private JPanel forText;
    private JPanel mainButtons;
    private JPanel memoryButtons;
    private JPanel allButtons;
    private JPanel bigPanel;
    private JPanel space;
    private JPanel historyPanel;


    protected static String buttonLabel = "";
    protected static String elements = "";

    protected static int max = 100;

    protected static String infixArray[] = new String[max];
    protected static int infixArrayCount = 0;

    protected static String postfixArray[] = new String[max];
    protected static int postfixArrayCount;


    private ButtonListener readLabel = new ButtonListener();


    protected static String MS = "";


    public Calculator()
    {
        super("Calculator");

        // text field
        forText = new JPanel();
        textField = new JTextField("", 25);

        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setBackground(Color.white);
        textField.setFont(new Font("Arial", Font.BOLD, 12));


        forText.setLayout(new GridLayout(1,1));
        forText.add(textField);
        forText.setPreferredSize(new Dimension(300,100));


        mainButtons = new JPanel();
        mainButtons.setLayout(new GridLayout(6,4,1,1));


        space = new JPanel();
        mainButtons.add(space);
        space = new JPanel();
        mainButtons.add(space);
        space = new JPanel();
        mainButtons.add(space);
        // delete button -> /u232b is the unicode for backspace symbol
        button = new JButton("\u232b");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Backspace");
        button.setFont(button.getFont().deriveFont(15f));

        button = new JButton("C");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Clear");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("(");
        button.addActionListener(readLabel);
        button.setToolTipText("Open Parenthese");
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton(")");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Close Parenthese");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("/");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Division");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("7");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("8");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("9");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("*");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Multiplication");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("4");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("5");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("6");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("-");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Subtraction");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("1");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("2");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("3");
        button.addActionListener(readLabel);
        button.setBackground(Color.white);
        mainButtons.add(button);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("+");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Addition");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("\u2212");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Negative Number");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("0");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setBackground(Color.white);
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton(".");
        button.addActionListener(readLabel);
        mainButtons.add(button);
        button.setToolTipText("Decimal Point");
        button.setFont(button.getFont().deriveFont(20f));

        button = new JButton("=");
        button.addActionListener(readLabel);
        button.setBackground(Color.CYAN);
        mainButtons.add(button);
        button.setToolTipText("Equal");
        button.setFont(button.getFont().deriveFont(20f));

        mainButtons.setPreferredSize(new Dimension(300,300));


        memoryButtons = new JPanel();
        memoryButtons.setLayout(new GridLayout(1,3,2,2));

        button = new JButton("MS");
        button.addActionListener(readLabel);
        button.setToolTipText("Store in memory");
        button.setFont(button.getFont().deriveFont(15f));
        memoryButtons.add(button);

        button = new JButton("MR");
        button.addActionListener(readLabel);
        memoryButtons.add(button);
        button.setToolTipText("Memory Recall");
        button.setFont(button.getFont().deriveFont(15f));

        button = new JButton("MC");
        button.addActionListener(readLabel);
        button.setToolTipText("Clear Memory");
        button.setFont(button.getFont().deriveFont(15f));
        memoryButtons.add(button);



        allButtons = new JPanel();
        allButtons.setLayout(new BorderLayout());
        allButtons.add(mainButtons, BorderLayout.NORTH);
        allButtons.add(memoryButtons, BorderLayout.SOUTH);


        history = new JTextArea();
        history.setPreferredSize(new Dimension(320,410));
        history.setEditable(false); // no input from the user, just display the history
        history.setFont(new Font("Arial", Font.BOLD, 12));
        historyLabel= new JLabel("HISTORY:");


        historyPanel = new JPanel();
        historyPanel.setLayout(new BorderLayout());
        historyPanel.add(historyLabel, BorderLayout.NORTH);
        historyPanel.add(history, BorderLayout.SOUTH);


        bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());
        bigPanel.add(forText, BorderLayout.NORTH);
        bigPanel.add(allButtons, BorderLayout.SOUTH);


        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(bigPanel);
        add(historyPanel);
        pack();
    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent readLabel)
        {

            buttonLabel = readLabel.getActionCommand();
            textField.setText(textField.getText() + buttonLabel);


            if(buttonLabel.equals("C"))
                ButtonFunction.clear();


            if(buttonLabel.equals("\u232b"))
                ButtonFunction.backspace();


            if(buttonLabel.equals("0"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("1"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("2"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("3"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("4"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("5"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("6"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("7"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("8"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("9"))
                ButtonFunction.operandButton();

            else if(buttonLabel.equals("."))
                ButtonFunction.checkDecimalPoint();

            else if(buttonLabel.equals("\u2212"))
                ButtonFunction.negativeSign();



            if(buttonLabel.equals("/"))
                ButtonFunction.operatorButton();


            else if(buttonLabel.equals("*"))
                ButtonFunction.operatorButton();


            else if(buttonLabel.equals("-"))
                ButtonFunction.operatorButton();


            else if(buttonLabel.equals("+"))
                ButtonFunction.operatorButton();


            else if(buttonLabel.equals("("))
                ButtonFunction.operatorButton();


            else if(buttonLabel.equals(")"))
                ButtonFunction.operatorButton();


            else if(buttonLabel.equals("="))
                ButtonFunction.equal();


            if(buttonLabel.equals("MS"))
                ButtonFunction.memoryStore();


            if(buttonLabel.equals("MC"))
                ButtonFunction.memoryClear();


            if(buttonLabel.equals("MR"))
                ButtonFunction.memoryRecall();



            System.out.println("*************************************************");
            for(int i = 0; i < infixArrayCount+1; i++)
            {
                if(infixArray[i] != null)
                    System.out.println(infixArray[i] + " INFIX "  + i + "\t");
            }

            System.out.println("=================================================");
            for(int i = 0; i < postfixArrayCount; i++)
            {
                if(postfixArray[i] != null)
                    System.out.println(postfixArray[i] + " POSTFIX " + i + "\t");
            }
        }
    }
}
