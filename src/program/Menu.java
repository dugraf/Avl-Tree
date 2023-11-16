import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entities.AVLTree;
import entities.Dado;
import entities.Pessoa;
import input.Input;
import utils.EscritaArquivo;
import utils.LeituraArquivo;

public class Menu
{
    static AVLTree<Long> avlCpf = new AVLTree<>();
    static AVLTree<LocalDate> avlData = new AVLTree<>();
    static AVLTree<String> avlNome = new AVLTree<>();
    static List<Pessoa> pessoas;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static String resultados = "";
    static File arquivo;

    public static List<Pessoa> menuArquivo()
    {
        //File file = new File(Input.inputString("DIGITE O CAMINHO DO ARQUIVO!\nEXEMPLO: C:\\temp\\arquivo.csv"));
        arquivo = new File("C:\\\\temp\\\\pessoas.txt");
        //arquivo = new File(Input.inputString("DIGITE O CAMINHO DO ARQUIVO!\nEXEMPLO: C:\\temp\\arquivo.csv"));
        pessoas = LeituraArquivo.leituraArquivo(arquivo, new ArrayList<Pessoa>());
        return pessoas;
    }
    
    public static void  menuOpcoes()
    {
        exibirMenu();
        int opcao = Input.inputInt("Escolha uma opcao: ");
        List<Integer> indices = new ArrayList<>();
        while (true)
        {
            do 
            {
                switch (opcao) 
                {
                    case 1:
                        buscarPessoasPorCPF(indices);
                        menuOpcoes();
                        break;
                    case 2:
                        buscarPessoasPorNome(indices);
                        menuOpcoes();
                        break;
                    case 3:
                        buscarPessoasPorIntervaloDeData(indices);
                        menuOpcoes();
                        break;
                    case 4:
                        escreverArquivo();
                        menuOpcoes();
                        break;
                    case 5:
                        imprimePessoas();
                        menuOpcoes();
                        break;
                    case 6:
                        System.out.print("Saindo.");
                        sleep(1);
                        System.out.print(".");
                        sleep(1);
                        System.out.print(".");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        menuOpcoes();
                }
            }
            while(opcao != 4);
        }
    }

    private static void exibirMenu() {
        System.out.println("==================================");
        System.out.println("           MENU PRINCIPAL           ");
        System.out.println("==================================");
        System.out.println("[1] - Pessoas por CPF");
        System.out.println("[2] - Pessoas por nomes que começam com determinado fragmento");
        System.out.println("[3] - Pessoas cujas datas de nascimento estão entre determinado intervalo");
        System.out.println("[4] - Gravar ultimos resultados em arquivo");
        System.out.println("[5] - Imprimir pessoas");
        System.out.println("[6] - Sair");
        System.out.println("==================================");
    }


    private static void buscarPessoasPorCPF(List<Integer> indices)
    {
        long cpf = Input.inputLong("DIGITE O CPF");
        indices = avlCpf.indicePorCpf(avlCpf.getRaiz(), cpf);
        imprimeResultados(indices, pessoas);
    }

    private static void buscarPessoasPorNome(List<Integer> indices) {
        String nome = Input.inputString("DIGITE O NOME");
        indices = avlNome.indicesComecandoPor(avlNome.getRaiz(), nome, indices);
        imprimeResultados(indices, pessoas);
    }

    private static void buscarPessoasPorIntervaloDeData(List<Integer> indices) {
        LocalDate dataInicio = LocalDate.parse(Input.inputString("DIGITE A PRIMEIRA DATA"), formatter);
        LocalDate dataFim = LocalDate.parse(Input.inputString("DIGITE A SEGUNDA DATA"), formatter);
        indices = avlData.indicePorData(avlData.getRaiz(), dataInicio, dataFim, indices);
        imprimeResultados(indices, pessoas);
    }

    public static void arvores(List<Pessoa> pessoas)
    {
        for(int i = 0 ; i < pessoas.size(); i++)
        {
            avlCpf.inserir(new Dado<>(pessoas.get(i).getCpf(), i));
            avlData.inserir(new Dado<>(pessoas.get(i).getDataNascimento(), i));
            avlNome.inserir(new Dado<>(pessoas.get(i).getNome(), i));
        }
    }

    private static void imprimePessoas()
    {
        for (Pessoa pessoa : pessoas)
        {
            System.out.println(pessoa + "\n");
            resultados += pessoa+ "\n";
        }
    }

    private static void escreverArquivo()
    {
        File path = arquivo.getParentFile();
        EscritaArquivo.escritaArquivo(path, resultados);
        resultados = "";
    }

    public static void imprimeResultados(List<Integer> indices, List<Pessoa> pessoas) {
        System.out.println("||----------------- RESULTADOS ------------------||");
        if(indices.isEmpty())
        {
            System.out.println("* NÃO FORAM ENCONTRADAS PESSOAS PARA OS FILTROS INFORMADOS!\n");
            resetResultados();
            return;
        }
        else
        System.out.print("* FORAM ENCONTRADAS "+ indices.size() + " PESSOAS PARA A BUSCA SELECIONADA!\n");
        for (Integer i : indices)
        {
            System.out.println("\n" + pessoas.get(i).toString());   
            resultados += "\n" + pessoas.get(i).toString();
        }
        System.out.println("||-----------------------------------------------||\n");
    }

    public static void sleep(int segundos)
    {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void resetResultados()
    {
        resultados = "NÃO FORAM ENCONTRADAS PESSOAS PARA OS FILTROS INFORMADOS!";
    }
}