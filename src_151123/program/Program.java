import java.io.File;
import java.util.List;
import entities.Pessoa;
import input.Input;
import utils.LeituraArquivo;

public class Program
{
    public static void main(String[] args)
    {
        File file = new File(Input.inputString("DIGITE O CAMINHO DO ARQUIVO!\nEXEMPLO: C:\\temp\\arquivo.csv"));
        List<Pessoa> pessoas = LeituraArquivo.leituraArquivo(file);
        Menu.menuPrincipal(pessoas);
    }
}