import java.io.File;
import java.util.List;

import entities.Pessoa;
import input.Input;
import utils.LeituraArquivo;

public class Menu
{
    public static void menuPrincipal()
    {
        File file = new File(Input.inputString("DIGITE O CAMINHO DO ARQUIVO .CSV!\nEXEMPLO: C:\\temp\\arquivo.csv"));
        List<Pessoa> pessoas = LeituraArquivo.leituraArquivo(file);
    }
}
