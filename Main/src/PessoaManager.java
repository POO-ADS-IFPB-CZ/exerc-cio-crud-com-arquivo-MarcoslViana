import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaManager {
    private Set<Pessoa> pessoas;
    private String filePath;

    public PessoaManager(String filePath) {
        this.filePath = filePath;
        this.pessoas = carregarPessoas();
    }

    private Set<Pessoa> carregarPessoas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Set<Pessoa>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new HashSet<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    private void salvarPessoas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(pessoas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarPessoa(Pessoa pessoa) {
        if (pessoas.add(pessoa)) {
            salvarPessoas();
            System.out.println("Pessoa adicionada com sucesso.");
        } else {
            System.out.println("Já existe uma pessoa com esse e-mail.");
        }
    }

    public void listarPessoas() {
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            pessoas.forEach(System.out::println);
        }
    }

    public void deletarPessoa(String email) {
        Pessoa pessoaParaDeletar = null;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getEmail().equals(email)) {
                pessoaParaDeletar = pessoa;
                break;
            }
        }
        if (pessoaParaDeletar != null) {
            pessoas.remove(pessoaParaDeletar);
            salvarPessoas();
            System.out.println("Pessoa deletada com sucesso.");
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }
}