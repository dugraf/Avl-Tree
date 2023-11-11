import java.io.File;
import java.util.List;

import entities.Pessoa;
import utils.LeituraArquivo;

public class Program
{
    public static void main(String[] args)
    {
        //12345678910;543216;Fulano de Tal;01/02/1958;Porto Alegre
        //10987654321;44556601;Cicrana Beltrana Delgrana;25/12/2972;São Leopoldo
        File file = new File("C:\\temp\\pessoas.txt");
        List<Pessoa> pessoas = LeituraArquivo.leituraArquivo(file);
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }
}