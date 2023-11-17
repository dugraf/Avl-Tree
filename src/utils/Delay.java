package utils;

public class Delay
{
    public static void sleep(int segundos)
    {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
