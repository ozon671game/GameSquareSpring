import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    private static ClassPathXmlApplicationContext context;

    public static void main(String[] args)
    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Window mw = context.getBean("mw", Window.class);
        mw.addWindow(context);
        context.close();
    }

}