import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo;
    private List<Usuario> listaDeUsuarios;
    private List<Emprestimo> registroDeEmprestimos;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
        this.listaDeUsuarios = new ArrayList<>();
        this.registroDeEmprestimos = new ArrayList<>();
    }

    public void realizarEmprestimo(String idUsuario, String titulo) {
        // 1 - Buscar os objetos usuario e livro
        Usuario usuarioDoEmprestimo = pesquisarUsuarioPorId(idUsuario);
        if(usuarioDoEmprestimo == null) {
            System.out.println("Erro: esse usuário não está cadastrado.");
            return;
        }
        Livro livroDoEmprestimo = pesquisarLivroPorTitulo(titulo);
        if(livroDoEmprestimo == null) {
            System.out.println("Erro: esse livro não está cadastrado.");
            return;
        }
        // 2 - Validar a regra de negocio (verificar se o livro está disponível)
        if(livroDoEmprestimo.getStatus() == StatusLivro.EMPRESTADO) {
            System.out.println("Erro: esse livro já está emprestado.");
            return;
        }
        // 3 - Realizar o registro (Criar objeto do tipo Emprestimo e adiciona-lo ao registroDeEmprestimos)
        livroDoEmprestimo.setStatus(StatusLivro.EMPRESTADO);
        Emprestimo emprestimo = new Emprestimo(livroDoEmprestimo, usuarioDoEmprestimo, LocalDate.now());
        registroDeEmprestimos.add(emprestimo);
        System.out.println("Emprestido cadastrado com sucesso.");
    }

    public Livro pesquisarLivroPorTitulo(String titulo) {
        for(Livro livro : this.acervo) {
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Usuario pesquisarUsuarioPorId(String id) {
        for(Usuario usuario : this.listaDeUsuarios) {
            if(usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Livro> pesquisarLivroPorTermo(String termo) {
        List<Livro> listaDeLivros = new ArrayList<>();
        for (var livro : acervo) {
            if(livro.getTitulo().toLowerCase().contains(termo.toLowerCase())) {
                listaDeLivros.add(livro);
            }
        }
        return listaDeLivros;
    }

    public void listarAcervo() {
        System.out.println("Livros no Acervo");
        for (Livro livro : acervo) {
            System.out.println(livro);
        }
    }

    public void cadastrarLivro(Livro livro) {
        this.acervo.add(livro);
        System.out.println("O livro " + livro.getTitulo() + " foi cadastrado.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.listaDeUsuarios.add(usuario);
        System.out.println("O usuário " + usuario.getNome() + " foi cadastrado.");
    }

    public static void main(String[] args) {
        Livro livroJavaComoProgramar = new Livro("Java Como Programar", "Deitel", 2014);
        Livro livroMemoria = new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1881);
        Usuario meuUsuario = new Usuario("Thiago", "123");
        Biblioteca minhaBiblioteca = new Biblioteca();
        minhaBiblioteca.cadastrarLivro(livroJavaComoProgramar);
        minhaBiblioteca.cadastrarLivro(livroMemoria);
        minhaBiblioteca.cadastrarUsuario(meuUsuario);
        List<Livro> resultado = minhaBiblioteca.pesquisarLivroPorTermo("programação");
        for (var livro : resultado) {
            System.out.println("Livros encontrados");
            System.out.println(livro);
        }
        minhaBiblioteca.listarAcervo();
    }
}