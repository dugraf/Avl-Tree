package utils;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import entities.Pessoa;

public class EscritaArquivo
{
    public static void escritaArquivo(File arquivo, List<Pessoa> resultados)
    {
        if(arquivo.exists())
            arquivo.delete();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true)))
        {
            bw.write("CPF;RG;NOME;DATA DE NASCIMENTO;CIDADE\n");
            for (int i = 0; i < resultados.size(); i++) {
                bw.write(Long.toString(resultados.get(i).getCpf())+ ";");
                bw.write(resultados.get(i).getRg()+ ";");
                bw.write(resultados.get(i).getNome()+ ";");
                bw.write(resultados.get(i).getDataNascimento()+ ";");
                bw.write(resultados.get(i).getCidade()+ "\n");
            }
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