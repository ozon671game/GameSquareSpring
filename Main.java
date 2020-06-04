import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    private static ClassPathXmlApplicationContext context;

    public static void main(String[] args)
    {
        context=getContext();
        Window mw = context.getBean("mw", Window.class);
        mw.addWindow();
        context.close();
    }

    public static  ClassPathXmlApplicationContext getContext()
    {

        if(context==null)
        {
             context = new ClassPathXmlApplicationContext("applicationContext.xml");
        }

        return context;
    }



}