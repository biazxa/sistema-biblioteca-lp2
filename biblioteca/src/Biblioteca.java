import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    //cria os atributos(carcteristicas) da classe
    private List<ItemDoAcervo> acervo; //uma lista que vai receber livros, DVDs, revistas etc.
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
        // 1 - Buscar os objetos usuario e item
        Usuario usuarioDoEmprestimo = pesquisarUsuarioPorId(idUsuario);
        if(usuarioDoEmprestimo == null) {
            System.out.println("Erro: usuário não cadastrado.");
            return;
        }

        ItemDoAcervo item = pesquisarItemPorTitulo(titulo);
        if(item == null) {
            System.out.println("Erro: item não cadastrado.");
            return;
        }

        if(item.getStatus() == StatusLivro.EMPRESTADO) {
            System.out.println("Erro: item já emprestado.");
            return;
        }

        // realiza o empréstimo
        item.setStatus(StatusLivro.EMPRESTADO);
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(item.getPrazo());
        Emprestimo emprestimo = new Emprestimo(item, usuarioDoEmprestimo, dataEmprestimo, dataDevolucaoPrevista);
        registrosDeEmprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("O item '" + item.getTitulo()
                + "' foi emprestado para o usuário " + usuarioDoEmprestimo.getName()
                + " na data " + emprestimo.getDataEmprestimo()
                + " e tem de ser devolvido em " + emprestimo.getDataDevolucaoPrevista());
    }

    public Emprestimo buscarEmprestimoAtivoPorItem(ItemDoAcervo item) {
        for (Emprestimo emprestimo : registrosDeEmprestimos) {
            if(emprestimo.getItem().getTitulo().equalsIgnoreCase(item.getTitulo())) {
                if(emprestimo.getDataDevolucaoPrevista() == null) {
                    return emprestimo;
                }
            }
        }
        return null;
    }

    public void realizarDevolucao(String titulo) {
        ItemDoAcervo item = pesquisarItemPorTitulo(titulo);
        if(item == null) {
            System.out.println("Erro: esse item não está cadastrado.");
            return;
        }

        Emprestimo emprestimo = buscarEmprestimoAtivoPorItem(item);
        if(emprestimo == null) {
            System.out.println("Erro: esse empréstimo não existe.");
            return;
        }

        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), hoje);

        if(dias > 0) {
            double multa = dias * item.getValorMultaPorDiaAtraso();
            System.out.println("Item devolvido. Você precisa pagar uma multa de R$" + multa);
        } else {
            System.out.println("Item devolvido.");
        }

        item.setStatus(StatusLivro.DISPONIVEL);
        emprestimo.setDataDevolucaoPrevista(hoje);
    }

    public ItemDoAcervo pesquisarItemPorTitulo(String titulo) {
        for(ItemDoAcervo item : this.acervo) {
            if(item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
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
        System.out.println("Itens no Acervo:");
        for (var item : acervo) {
            System.out.println(item);
        }
    }

    public void cadastrarItem(ItemDoAcervo item) {
        this.acervo.add(item);
        System.out.println("O item " + item.getTitulo() + " foi cadastrado.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        this.listaDeUsuarios.add(usuario);
        System.out.println("O usuário " + usuario.getName() + " foi cadastrado.");
    }

    // Getter para acessar os registros de empréstimos (útil no main)
    public List<Emprestimo> getRegistrosDeEmprestimos() {
        return registrosDeEmprestimos;
    }
    public List<ItemDoAcervo> buscar(String termo) {
        List<ItemDoAcervo> resultados = new ArrayList<>();
        String termoMinusculo = termo.toLowerCase();

        for (ItemDoAcervo item : acervo) {
            if (item.getTitulo().toLowerCase().contains(termoMinusculo)) {
                resultados.add(item);
                continue;
            }

            if (item instanceof Livro livro) {
                if (livro.getAutor().toLowerCase().contains(termoMinusculo)) {
                    resultados.add(livro);
                }
            }
        }

        return resultados;
    }

    public static void main(String[] args) {
        Biblioteca minhaBiblioteca = new Biblioteca();

        // Itens
        Livro livroJavaComoProgramar = new Livro("Java Como Programar", "Deitel", 2014);
        Livro livroMemoria = new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", 1881);
        Livro livroTolkien = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954);
        DVD dvd = new DVD("O Rei Leão", 1994, 89);

        // Usuário
        Usuario meuUsuario = new Usuario("123", "Thiago", "123");

        // Cadastro
        minhaBiblioteca.cadastrarItem(livroJavaComoProgramar);
        minhaBiblioteca.cadastrarItem(livroMemoria);
        minhaBiblioteca.cadastrarItem(livroTolkien);
        minhaBiblioteca.cadastrarItem(dvd);
        minhaBiblioteca.cadastrarUsuario(meuUsuario);

        // Listagem
        minhaBiblioteca.listarAcervo();

        // Empréstimo de livro
        minhaBiblioteca.realizarEmprestimo("123", "Java Como Programar");
        minhaBiblioteca.listarAcervo();

        // Simular devolução atrasada
        minhaBiblioteca.getRegistrosDeEmprestimos().get(0).setDataDevolucaoPrevista(LocalDate.of(2025, 8, 31));
        minhaBiblioteca.realizarDevolucao("Java Como Programar");
        minhaBiblioteca.listarAcervo();

        // Empréstimo do DVD
        minhaBiblioteca.realizarEmprestimo("123", "O Rei Leão");

        // Simular devolução atrasada do DVD (5 dias)
        minhaBiblioteca.getRegistrosDeEmprestimos().get(1).setDataDevolucaoPrevista(LocalDate.now().minusDays(5));
        minhaBiblioteca.realizarDevolucao("O Rei Leão");

        minhaBiblioteca.listarAcervo();
        List<ItemDoAcervo> resultados = minhaBiblioteca.buscar("Tolkien");
        for (ItemDoAcervo item : resultados) {
            System.out.println(item);
        }

        resultados = minhaBiblioteca.buscar("Java");
        for (ItemDoAcervo item : resultados) {
            System.out.println(item);
        }
    }
}
