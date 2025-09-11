import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    //cria os atributos(carcteristicas) da classe
    private List<Livro> acervo; //uma lista acervo que vai receber livrod
    private List<Usuario> listaDeUsuarios;
    private List<Emprestimo> registrosDeEmprestimos;
    //define valores padroes que n podem ser mudados
    private static final int PRAZO_EMPRESTIMO_DIAS = 14;
    private static final double VALOR_MULTA_DIA = 0.75;

    public Biblioteca() {
        //inicializa as listas vazias
        this.acervo = new ArrayList<>();
        this.listaDeUsuarios = new ArrayList<>();
        this.registrosDeEmprestimos = new ArrayList<>();
    }

    public void realizarEmprestimo(String idUsuario, String titulo) {
        // 1 - Buscar os objetos usuario e livro
        Usuario usuarioDoEmprestimo = pesquisarUsuarioPorId(idUsuario); //usa o metodo e armazena nessa variavel
        if(usuarioDoEmprestimo == null) {
            System.out.println("Erro: usuário não cadastrado.");
            return;
        }
        Livro livroDoEmprestimo = pesquisarLivroPorTitulo(titulo);
        if(livroDoEmprestimo == null) {
            System.out.println("Erro: livro não cadastrado.");
            return;
        }
        // (verificar se o livro está disponível)
        if(livroDoEmprestimo.getStatus() == StatusLivro.EMPRESTADO) {
            System.out.println("Erro: livro já emprestrado.");
            return;
        }
        // se passar nessas condições,ele vem pra cá
        livroDoEmprestimo.setStatus(StatusLivro.EMPRESTADO); //altera o estado do livro
        LocalDate dataEmprestimo = LocalDate.now(); //registra a data
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(PRAZO_EMPRESTIMO_DIAS); //calcula a data para devolução
        Emprestimo emprestimo = new Emprestimo(livroDoEmprestimo, usuarioDoEmprestimo, dataEmprestimo, dataDevolucaoPrevista); //cria um obj que vai receber tufo isso
        registrosDeEmprestimos.add(emprestimo); //guarda ba lista
        System.out.println("Emprestimo realizado com sucesso!");
        System.out.println("O livro '"+livroDoEmprestimo.getTitulo()
                +"' foi emprestado para o usuário " + usuarioDoEmprestimo.getName()
                +" na data " + emprestimo.getDataEmprestimo()
                +" e tem de ser devolvido em " + emprestimo.getDataDevolucaoPrevista());
    }

    public Emprestimo buscarEmprestimoAtivoPorLivro(Livro livro) {
        for (Emprestimo emprestimo : registrosDeEmprestimos) {
            if(emprestimo.getItem().getTitulo().equalsIgnoreCase(livro.getTitulo())) {
                if(emprestimo.getDataDevolucaoPrevista() == null) { //ainda n foi devolvido
                    return emprestimo;
                }
            }
        }
        return null;
    }

    public void realizarDevolucao(String titulo) {
        Livro livro = pesquisarLivroPorTitulo(titulo); //chama o metodo
        if(livro == null) {
            System.out.println("Erro: esse livro não está cadastrado.");
            return;
        }
        Emprestimo emprestimo = buscarEmprestimoAtivoPorLivro(livro);
        if(emprestimo == null) {
            System.out.println("Erro: esse emprestimo não existe.");
            return;
        }
        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), hoje); // ve se há atraso

        if(dias > 0) {
            double multa = dias * VALOR_MULTA_DIA;
            System.out.println("Livro devolvido. Você precisa pagar uma multa de R$" + multa);
        } else {
            System.out.println("Livro devolvido.");
        }
        emprestimo.getItem().setStatus(StatusLivro.DISPONIVEL); //muda o status do livro
        emprestimo.setDataDevolucaoPrevista(hoje); //registra a data da devolução
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
            if(usuario.getUser().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public void listarAcervo() {
        System.out.println("Livros no Acervo");
        for (var livro : acervo) { //var serve para ele descobrir o tipo automaticamente
            System.out.println(livro);
        }
    }

    public void cadastrarLivro(Livro livro) {
        this.acervo.add(livro);
        System.out.println("O livro " + livro.getTitulo() + " foi cadastrado.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.listaDeUsuarios.add(usuario);
        System.out.println("O usuário " + usuario.getName() + " foi cadastrado.");
    }

    public static void main(String[] args) {
        Livro livroJavaComoProgramar = new Livro("Java Como Programar", "Deitel", 2014);
        Livro livroMemoria = new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1881);
        Usuario meuUsuario = new Usuario("Thiago", "thiago", "123");
        Biblioteca minhaBiblioteca = new Biblioteca();
        minhaBiblioteca.cadastrarLivro(livroJavaComoProgramar);
        minhaBiblioteca.cadastrarLivro(livroMemoria);
        minhaBiblioteca.cadastrarUsuario(meuUsuario);
        minhaBiblioteca.listarAcervo();
        minhaBiblioteca.realizarEmprestimo("123", "Java Como Programar");
        minhaBiblioteca.listarAcervo();
        minhaBiblioteca.registrosDeEmprestimos.get(0).setDataDevolucaoPrevista(LocalDate.of(2025, 8, 31));
        minhaBiblioteca.realizarDevolucao("Java Como Programar");
        minhaBiblioteca.listarAcervo();
    }
}