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

    public static int inputInt(String texto) {
        try {
            System.out.println(texto);
            return Integer.parseInt(sc.next());
        } catch (NumberFormatException ex) {
            System.out.println("Digite um número válido: " + ex);
            return inputInt(texto);
        }
    }
}