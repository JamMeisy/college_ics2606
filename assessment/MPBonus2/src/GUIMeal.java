//1CSC - Jam Meisy Tan

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUIMeal
{

    private JFrame frame;
    private Label lmealAR, lmealBR, lmealCR, lmealAU, lmealBU, lmealCU, lrice, lsalad, lsoup, ldessert;
    private TextField tmealAR, tmealBR, tmealCR, tmealAU, tmealBU, tmealCU, trice, tsalad, tsoup, tdessert;
    private Button bexit, bcompute;
    GUIMeal()
    {
        //Instantiating Components
        frame = new JFrame("Registrar");
        bcompute = new Button("Compute Cost");
        bexit = new Button("Exit");

        lmealAR = new Label("Meal Type: A - Regular", Label.CENTER);
        lmealBR = new Label("Meal Type: B - Regular", Label.CENTER);
        lmealCR = new Label("Meal Type: C - Regular", Label.CENTER);
        lmealAU = new Label("Meal Type: A - Upsize", Label.CENTER);
        lmealBU = new Label("Meal Type: B - Upsize", Label.CENTER);
        lmealCU = new Label("Meal Type: C - Upsize", Label.CENTER);
        lrice = new Label("Extra Rice", Label.CENTER);
        lsalad = new Label("Salad", Label.CENTER);
        lsoup = new Label("Soup", Label.CENTER);
        ldessert = new Label("Dessert", Label.CENTER);

        tmealAR = new TextField();
        tmealAU = new TextField();
        tmealBR = new TextField();
        tmealBU = new TextField();
        tmealCR = new TextField();
        tmealCU = new TextField();
        trice = new TextField();
        tsalad = new TextField();
        tsoup = new TextField();
        tdessert = new TextField();

        //Configuration of Settings
        frame.setLayout(new GridLayout(12,2));
        frame.setBounds((1920 - 800)/2, (1080 - 300)/2, 800,300);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Add Functionality to Buttons
        bcompute.addActionListener(new ComputeCost());
        bexit.addActionListener(new Exit());

        //Add all components
        frame.add(new Label());
        frame.add(new Label("Input Quantity"));
        frame.add(lmealAR);
        frame.add(tmealAR);
        frame.add(lmealBR);
        frame.add(tmealBR);
        frame.add(lmealCR);
        frame.add(tmealCR);
        frame.add(lmealAU);
        frame.add(tmealAU);
        frame.add(lmealBU);
        frame.add(tmealBU);
        frame.add(lmealCU);
        frame.add(tmealCU);
        frame.add(lrice);
        frame.add(trice);
        frame.add(lsalad);
        frame.add(tsalad);
        frame.add(lsoup);
        frame.add(tsoup);
        frame.add(ldessert);
        frame.add(tdessert);
        frame.add(bexit);
        frame.add(bcompute);

        frame.setVisible(true);
    }
    private class ComputeCost implements ActionListener //Line 1
    {
        public void actionPerformed(ActionEvent a) //Line 2
        {
            double gcost = 0, ncost = 0, mealAR = 0, mealBR = 0, mealCR = 0, mealAU = 0, mealBU = 0, mealCU = 0, rice = 0, salad = 0, soup = 0, dessert = 0;
            try
            {
                //Parses the quantity, Skips null values
                if (!tmealAR.getText().equals(""))
                    mealAR = Double.parseDouble(tmealAR.getText());
                if (!tmealBR.getText().equals(""))
                    mealBR = Double.parseDouble(tmealBR.getText());
                if (!tmealCR.getText().equals(""))
                    mealCR = Double.parseDouble(tmealCR.getText());
                if (!tmealAU.getText().equals(""))
                    mealAU = Double.parseDouble(tmealAU.getText());
                if (!tmealBU.getText().equals(""))
                    mealBU = Double.parseDouble(tmealBU.getText());
                if (!tmealCU.getText().equals(""))
                    mealCU = Double.parseDouble(tmealCU.getText());
                if (!trice.getText().equals(""))
                    rice = Double.parseDouble(trice.getText());
                if (!tsalad.getText().equals(""))
                    salad = Double.parseDouble(tsalad.getText());
                if (!tsoup.getText().equals(""))
                    soup = Double.parseDouble(tsoup.getText());
                if (!tdessert.getText().equals(""))
                    dessert = Double.parseDouble(tdessert.getText());

                //Computation
                gcost = 78.75 * mealAR
                        + 102.50 * mealAU
                        + 68.75 * mealBR
                        + 85.70 * mealBU
                        + 70.25 * mealCR
                        + 95.60 * mealCU
                        + 20 * rice
                        + 60 * salad
                        + 55 * soup
                        + 85 * dessert;
                if (gcost > 2000)
                    gcost *= 0.88;
                ncost = gcost + gcost * 0.08;

                //Instantiating Computation Prompt
                JFrame prompt = new JFrame();
                Label gross = new Label("Gross Cost", Label.CENTER);
                Label grossCost = new Label(String.format("%.2f", gcost), Label.LEFT);
                Label net = new Label("Net Cost", Label.CENTER);
                Label netCost = new Label(String.format("%.2f", ncost), Label.LEFT);

                //Adding components and display computation
                prompt.setLayout(new GridLayout(2,2));
                prompt.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                prompt.setBounds((1920- 500)/2 ,(1080 - 150)/2 , 500,150);
                prompt.add(gross);
                prompt.add(grossCost);
                prompt.add(net);
                prompt.add(netCost);
                prompt.setVisible(true);
            }
            catch (NumberFormatException e)
            {
                //Error Prompt
                JFrame errorPrompt = new JFrame();
                errorPrompt.add(new Label("Invalid Quantity", Label.CENTER));
                errorPrompt.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                errorPrompt.setBounds((1920- 500)/2 ,(1080 - 150)/2 , 500,150);
                errorPrompt.setVisible(true);
            }
        }
    }
    private class Exit implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        GUIMeal a = new GUIMeal();
    }
}