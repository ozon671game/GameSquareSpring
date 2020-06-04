// импортируем классы
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner; // импортируем классы
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GameSguare extends JPanel implements ActionListener,Game  {  //имплементирую интерфейс
    private final int size = 310;     //размер поля
    private  Timer timer;            //  таймер
    public    Square [] square;     //    массив квадратов


    public GameSguare(ClassPathXmlApplicationContext context)// конструктор класса GameField
    {
        square=Square.getSguare(context);
        timer = new Timer(10, this);
        timer.start();                          // запускаю таймер
        setBackground(Color.black);//цвет поля
    }

    @Override
    protected void paintComponent(Graphics g)// метод для отрисовки компонентов
    {
        super.paintComponent(g);
        for (int i = 0; i <this.square.length ; i++)
        {
            switch (square[i].color)
            { case 0:
                g.setColor(Color.RED);
                g.fillRect(square[i].pointXSquare, square[i].pointYSquare, square[i].sizeSquare, square[i].sizeSquare);
                break;
                case 1:
                    g.setColor(Color.orange);
                    g.fillRect(square[i].pointXSquare, square[i].pointYSquare, square[i].sizeSquare, square[i].sizeSquare);
                    break;
                case 2:
                    g.setColor(Color.yellow);
                    g.fillRect(square[i].pointXSquare, square[i].pointYSquare, square[i].sizeSquare, square[i].sizeSquare);
                    break;
                case 3:
                    g.setColor(Color.green);
                    g.fillRect(square[i].pointXSquare, square[i].pointYSquare, square[i].sizeSquare, square[i].sizeSquare);
                    break;
                case 4:
                    g.setColor(Color.cyan);
                    g.fillRect(square[i].pointXSquare, square[i].pointYSquare, square[i].sizeSquare, square[i].sizeSquare);
                    break;
                case 5:
                    g.setColor(Color.blue);
                    g.fillRect(square[i].pointXSquare, square[i].pointYSquare, square[i].sizeSquare, square[i].sizeSquare);
                    break;
                case 6:
                    g.setColor(Color.magenta);
                    g.fillRect(square[i].pointXSquare, square[i].pointYSquare, square[i].sizeSquare, square[i].sizeSquare);
                    break;
            }
           }
    }


    @Override
    public void actionPerformed(ActionEvent e)// метод вызывается по таймеру
    {
        move();                 //движение квадратов
        checkCollisionsWall(); // проверка на столкновения квадрата со  стеной
        repaint();            //  перерисовка
    }

    public void move()// движение квадратов
    {
        for (int i = 0; i <this.square.length ; i++)
        {
            if (square[i].left)
            {
            square[i].pointXSquare -= 1;
            }
            if (square[i].right)
            {
               square[i].pointXSquare += 1;
            }
            if (square[i].up)
            {
               square[i].pointYSquare -= 1;
            }
            if (square[i].down)
            {
               square[i].pointYSquare += 1;
            }

            checkCollisions();// проверка на столкновения квадратов

        }

    }

    public void checkCollisionsWall()// проверка на столкновения квадрата со  стеной

    {
        for (int i = 0; i < this.square.length; i++)
        {
            if (square[i].pointXSquare + square[i].sizeSquare >= size )
            {
            square[i].left = true;
            square[i].right = false;
            }

            if (square[i].pointXSquare <= 0)
            {
            square[i].left = false;
            square[i].right = true;
            }
            if (square[i].pointYSquare + square[i].sizeSquare>= size)
            {
            square[i].down = false;
            square[i].up = true;
            }

            if (square[i].pointYSquare <= 0)  {
            square[i].down = true;
            square[i].up = false;

        }
        }
    }

    public void  changeColor (int i)// смена цвета
    {
        square[i].color=square[i].color+1;
        if(square[i].color>6)
        {
            square[i].color=0;
        }
    }

    public void checkCollisions()// проверка на столкновения квадратов

    {
        for (int i = 0; i < this.square.length; i++)
        {
            for (int j = 0; j < this.square.length; j++)
            {
                if(i!=j)
                {
                    boolean flag = false;

                    if (square[i].left)
                      {
                          if (square[i].pointXSquare == square[j].pointXSquare + square[j].sizeSquare)
                        {
                            if (square[i].sizeSquare >= square[j].sizeSquare)
                            {
                                for (int k = square[i].pointYSquare; k <square[i].pointYSquare+square[i].sizeSquare ; k++)
                                {
                                    if ( k==square[j].pointYSquare||k==square[j].pointYSquare+square[j].sizeSquare)
                                    {
                                        flag = true;
                                    }
                                }



                            }
                            if (square[i].sizeSquare < square[j].sizeSquare)
                            {

                                for (int k = square[j].pointYSquare; k <square[j].pointYSquare+square[j].sizeSquare ; k++)
                                {
                                    if ( k==square[i].pointYSquare||k==square[i].pointYSquare+square[i].sizeSquare)
                                    {
                                        flag = true;
                                    }
                                }
                            }

                            if(flag)
                            {
                                changeColor(i);
                                changeColor(j);
                                square[i].left=false;
                                square[i].right=true;
                                if(square[j].up||square[j].down)
                                {
                                    square[j].down=false;
                                    square[j].up=false;
                                    square[j].left=true;

                                }
                            }
                        }

                    }
                    else
                        {
                            flag=false;
                            if (square[i].right)
                            {

                                if (square[i].pointXSquare +square[i].sizeSquare== square[j].pointXSquare)
                                {
                                    if (square[i].sizeSquare >= square[j].sizeSquare)
                                    {
                                        for (int k = square[i].pointYSquare; k <square[i].pointYSquare+square[i].sizeSquare ; k++)
                                        {
                                            if ( k==square[j].pointYSquare||k==square[j].pointYSquare+square[j].sizeSquare)
                                            {
                                                flag = true;
                                            }
                                        }



                                    }
                                    if (square[i].sizeSquare < square[j].sizeSquare)
                                    {

                                        for (int k = square[j].pointYSquare; k <square[j].pointYSquare+square[j].sizeSquare ; k++)
                                        {
                                            if ( k==square[i].pointYSquare||k==square[i].pointYSquare+square[i].sizeSquare)
                                            {
                                                flag = true;
                                            }
                                        }
                                    }
                                }

                            }

                            if(flag)
                            {
                                changeColor(i);
                                changeColor(j);
                                square[i].left=true;
                                square[i].right=false;
                                if(square[j].up||square[j].down)
                                {
                                    square[j].down=false;
                                    square[j].up=false;
                                    square[j].right=true;

                                }
                            }

                        }

                    if (square[i].up)
                    {
                         flag=false;
                        if (square[i].pointYSquare == square[j].pointYSquare + square[j].sizeSquare)
                        {
                            if (square[i].sizeSquare >= square[j].sizeSquare)
                            {
                                for (int k = square[i].pointXSquare; k <square[i].pointXSquare+square[i].sizeSquare ; k++)
                                {
                                    if ( k==square[j].pointXSquare||k==square[j].pointXSquare+square[j].sizeSquare)
                                    {
                                        flag = true;
                                    }
                                }



                            }
                            if (square[i].sizeSquare < square[j].sizeSquare)
                            {

                                for (int k = square[j].pointXSquare; k <square[j].pointXSquare+square[j].sizeSquare ; k++)
                                {
                                    if ( k==square[i].pointXSquare||k==square[i].pointXSquare+square[i].sizeSquare)
                                    {
                                        flag = true;
                                    }
                                }
                            }
                        }

                        if(flag)
                        {
                            changeColor(i);
                            changeColor(j);
                            square[i].up=false;
                            square[i].down=true;
                            if(square[j].left||square[j].right)
                            {
                                square[j].left=false;
                                square[j].right=false;
                                square[j].up=true;

                            }
                        }

                    }
                    else
                        {
                            if (square[i].down)
                            {
                                flag=false;
                                if (square[i].pointYSquare+ square[i].sizeSquare == square[j].pointYSquare )
                                {
                                    if (square[i].sizeSquare >= square[j].sizeSquare)
                                    {
                                        for (int k = square[i].pointXSquare; k <square[i].pointXSquare+square[i].sizeSquare ; k++)
                                        {
                                            if ( k==square[j].pointXSquare||k==square[j].pointXSquare+square[j].sizeSquare)
                                            {
                                                flag = true;
                                            }
                                        }



                                    }
                                    if (square[i].sizeSquare < square[j].sizeSquare)
                                    {

                                        for (int k = square[j].pointXSquare; k <square[j].pointXSquare+square[j].sizeSquare ; k++)
                                        {
                                            if ( k==square[i].pointXSquare||k==square[i].pointXSquare+square[i].sizeSquare)
                                            {
                                                flag = true;
                                            }
                                        }
                                    }
                                }

                                if(flag)
                                {
                                    changeColor(i);
                                    changeColor(j);
                                    square[i].down=false;
                                    square[i].up=true;
                                    if(square[j].left||square[j].right)
                                    {
                                        square[j].left=false;
                                        square[j].right=false;
                                        square[j].down=true;

                                    }
                                }

                            }
                        }



                }

            }


            }

    }




}
