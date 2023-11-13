package input;
import java.time.LocalDate;
import java.util.Scanner;

public class Input
{
    private static Scanner sc = new Scanner(System.in);

    public static String inputString(String texto)
    {
        System.out.println(texto);
        return sc.next();
    }

    public static LocalDate inputDate(String texto)
    {
        return LocalDate.parse(sc.next());
    }
}