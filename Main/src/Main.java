import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PessoaManager manager = new PessoaManager("pessoas.dat");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Salvar uma pessoa");
            System.out.println("2. Listar todas as pessoas");
            System.out.println("3. Deletar uma pessoa pelo e-mail");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consome a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine();
                    manager.adicionarPessoa(new Pessoa(nome, email));
                    break;
                case 2:
                    manager.listarPessoas();
                    break;
                case 3:
                    System.out.print("E-mail da pessoa a deletar: ");
                    String emailParaDeletar = scanner.nextLine();
                    manager.deletarPessoa(emailParaDeletar);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}