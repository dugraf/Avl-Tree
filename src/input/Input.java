package input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input
{
    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static String inputString(String texto)
    {
        System.out.println(texto);
        try {
            return bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "TENTE NOVAMENTE!" +inputString(texto);
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

    public static long inputLong(String texto) {
        try {
            System.out.println(texto);
            return Long.parseLong(sc.next());
        } catch (NumberFormatException ex) {
            System.out.println("Digite um número válido: " + ex);
            return inputLong(texto);
        }
    }
}