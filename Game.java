public interface Game
{
    void move();// движение квадратов
    void checkCollisionsWall();// проверка на столкновения квадрата со  стеной
    void  changeColor (int i);// смена цвета
    void checkCollisions();// проверка на столкновения

}
