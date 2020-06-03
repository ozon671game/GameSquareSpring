import javax.swing.*;



public class Window extends JFrame // наследуем класс JFrame

{

    public void addWindow()
    {
        setTitle("Квадрат");//
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(200,200);
        add( new GameSguare());
        setVisible(true);

    }

}
