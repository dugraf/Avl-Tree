import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entities.AvlTree;
import entities.Dado;
import entities.Pessoa;
import input.Input;
import utils.Delay;
import utils.EscritaArquivo;
import utils.LeituraArquivo;

public class Menu
{
    static AvlTree<Long> avlCpf = new AvlTree<>();
    static AvlTree<Long> avlRg = new AvlTree<>();
    static AvlTree<LocalDate> avlData = new AvlTree<>();
    static AvlTree<String> avlNome = new AvlTree<>();
    static AvlTree<String> avlCidade = new AvlTree<>();
    static List<Pessoa> pessoas;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static List<Pessoa> resultados = new ArrayList<>();
    static File arquivo;

    public static List<Pessoa> menuArquivo()
    {
        arquivo = new File(Input.inputString("DIGITE O CAMINHO DO ARQUIVO!\nEXEMPLO: C:\\temp\\arquivo.csv"));
        pessoas = LeituraArquivo.leituraArquivo(arquivo, new ArrayList<Pessoa>());
        return pessoas;
    }

    public static void arvores(List<Pessoa> pessoas)
    {
        for(int i = 0 ; i < pessoas.size(); i++)
        {
            avlCpf.inserir(new Dado<>(pessoas.get(i).getCpf(), i));
            avlRg.inserir(new Dado<>(pessoas.get(i).getRg(), i));
            avlData.inserir(new Dado<>(pessoas.get(i).getDataNascimento(), i));
            avlNome.inserir(new Dado<>(pessoas.get(i).getNome(), i));
            avlCidade.inserir(new Dado<>(pessoas.get(i).getCidade(), i));
        }
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
                        buscarPessoasPorCidade(indices);
                        menuOpcoes();
                        break;
                    case 5:
                        buscarPessoasPorRg(indices);
                        menuOpcoes();
                        break;
                    case 6:
                        escreverArquivo();
                        menuOpcoes();
                        break;
                    case 7:
                        imprimePessoas();
                        menuOpcoes();
                        break;
                    case 8:
                        System.out.print("Saindo.");
                        Delay.sleep(1);
                        System.out.print(".");
                        Delay.sleep(1);
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
        System.out.println("[4] - Pessoas por cidade");
        System.out.println("[5] - Pessoas por RG");
        System.out.println("[6] - Gravar ultimos resultados em arquivo");
        System.out.println("[7] - Imprimir pessoas");
        System.out.println("[8] - Sair");
        System.out.println("==================================");
    }

    private static void buscarPessoasPorCPF(List<Integer> indices) //OPCAO 1
    {
        long cpf = Input.inputLong("DIGITE O CPF");
        indices = avlCpf.indicePorLong(avlCpf.getRaiz(), cpf);
        imprimeResultados(indices, pessoas);
    }

    private static void buscarPessoasPorNome(List<Integer> indices) //OPCAO 2
    {
        String nome = Input.inputString("DIGITE O NOME");
        indices = avlNome.indicesComecandoPor(avlNome.getRaiz(), nome, indices);
        imprimeResultados(indices, pessoas);
    }

    private static void buscarPessoasPorIntervaloDeData(List<Integer> indices) //OPCAO 3
    {
        LocalDate dataInicio = LocalDate.parse(Input.inputString("DIGITE A PRIMEIRA DATA"), formatter);
        LocalDate dataFim = LocalDate.parse(Input.inputString("DIGITE A SEGUNDA DATA"), formatter);
        indices = avlData.indicePorData(avlData.getRaiz(), dataInicio, dataFim, indices);
        imprimeResultados(indices, pessoas);
    }

    private static void buscarPessoasPorCidade(List<Integer> indices) //OPCAO 4
    {
        String cidade = Input.inputString("DIGITE A CIDADE");
        indices = avlCidade.indicesComecandoPor(avlCidade.getRaiz(), cidade, indices);
        imprimeResultados(indices, pessoas);
    }

    private static void buscarPessoasPorRg(List<Integer> indices) //OPCAO 5
    {
        long rg = Input.inputLong("DIGITE O RG");
        indices = avlCpf.indicePorLong(avlRg.getRaiz(), rg);
        imprimeResultados(indices, pessoas);
    }

    private static void escreverArquivo() //OPCAO 6
    {
        File path = arquivo.getParentFile();
        File arquivoEscrita = new File(path +"\\"+ Input.inputString("DIGITE O NOME DO RELATORIO") + ".csv");
        EscritaArquivo.escritaArquivo(arquivoEscrita, resultados);
        resultados.clear();
    }

    private static void imprimePessoas() //OPCAO 7
    {
        for (Pessoa pessoa : pessoas)
        {
            System.out.println(pessoa + "\n");
            resultados.add(pessoa);
        }
    }

    public static void imprimeResultados(List<Integer> indices, List<Pessoa> pessoas) {
        System.out.println("||----------------- RESULTADOS ------------------||");
        if(indices.isEmpty())
        {
            System.out.println("* NÃO FORAM ENCONTRADAS PESSOAS PARA OS FILTROS INFORMADOS!\n");
            return;
        }
        else
            System.out.print("* FORAM ENCONTRADAS "+ indices.size() + " PESSOAS PARA A BUSCA SELECIONADA!\n");
        for (Integer i : indices)
        {
            System.out.println("\n" + pessoas.get(i).toString());   
            resultados.add(pessoas.get(i));
        }
        System.out.println("||-----------------------------------------------||\n");
    }
}