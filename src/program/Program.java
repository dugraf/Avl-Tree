public class Program
{
    public static void main(String[] args)
    {
        try
        {
            Menu.arvores(Menu.menuArquivo());
            Menu.menuOpcoes();
        }
        catch(NullPointerException e)
        {
            System.out.println("NÃO FOI POSSÍVEL ENCONTRAR O ARQUIVO, TENTE NOVAMENTE!");
            Menu.arvores(Menu.menuArquivo());
        }
    }
}