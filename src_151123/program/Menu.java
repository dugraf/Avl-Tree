import java.util.List;
import entities.Pessoa;
import input.Input;

public class Menu
{
    public static void menuPrincipal(List<Pessoa> pessoas)
    {
        exibirMenu();
        menuOpcoes();
    }

    
    private static void  menuOpcoes()
    {
        int opcao = Input.inputInt("Escolha uma opcao: ");
        do 
        {
            switch (opcao) 
            {
                case 1:
                    buscarPessoasPorCPF();
                    break;
                case 2:
                    buscarPessoasPorNome();
                    break;
                case 3:
                    buscarPessoasPorIntervaloDeData();
                    break;
                case 4:
                    System.out.print("Saindo.");
                    sleep(1);
                    System.out.print(".");
                    sleep(1);
                    System.out.print(".");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        while(opcao != 4);
    }

    private static void exibirMenu() {
        System.out.println("==================================");
        System.out.println("           MENU PRINCIPAL           ");
        System.out.println("==================================");
        System.out.println("[1] - Pessoas por CPF");
        System.out.println("[2] - Pessoas por nomes que começam com determinado fragmento");
        System.out.println("[3] - Pessoas cujas datas de nascimento estão entre determinado intervalo");
        System.out.println("[4] - Sair");
        System.out.println("==================================");
    }    

    private static void buscarPessoasPorCPF() {
        // Implemente a lógica para buscar pessoas por CPF
        System.out.println("Opção 1 escolhida. Implemente a lógica para buscar por CPF.");
    }

    private static void buscarPessoasPorNome() {
        // Implemente a lógica para buscar pessoas por nome
        System.out.println("Opção 2 escolhida. Implemente a lógica para buscar por nome.");
    }

    private static void buscarPessoasPorIntervaloDeData() {
        // Implemente a lógica para buscar pessoas por intervalo de data de nascimento
        System.out.println("Opção 3 escolhida. Implemente a lógica para buscar por intervalo de data.");
    }

    public static void sleep(int segundos)
    {
        try {
            Thread.sleep(segundos * 1000);;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}