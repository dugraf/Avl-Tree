package utils;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EscritaArquivo
{
    public static void escritaArquivo(File path, String resultados)
    {
        File arquivo = new File(path+"\\resultados.txt");

        if(arquivo.exists())
            arquivo.delete();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true)))
        {
            bw.write(resultados);
            System.out.println("O arquivo foi gerado em " +arquivo);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        abrirArquivo(arquivo);
    }

    private static void abrirArquivo(File arquivo) {
        try {
            Desktop.getDesktop().open(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}