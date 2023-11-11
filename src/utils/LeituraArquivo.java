package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Pessoa;

public class LeituraArquivo
{
    public static List<Pessoa> leituraArquivo(File file)
    {
        Scanner sc = null;
        List<Pessoa> pessoas = new ArrayList<>();
        try
        {
            sc = new Scanner(file); //LEITURA DESTE ARQUIVO
            while(sc.hasNextLine()) //SE AINDA EXISTIR ALGUMA LINHA PARA LER, ENTAO, PULAR PARA A PROXIMA LINHA
            {
                String[] campos = sc.nextLine().split(";");
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(campos[3], fmt);
                pessoas.add(new Pessoa(campos[0], campos[1], campos[2], data, campos[4]));
            }
        }
        catch (IOException e)
        {
            System.out.println("Error " +e.getMessage()); //EXCECAO
        }
        sc.close();
        return pessoas;
    }
}