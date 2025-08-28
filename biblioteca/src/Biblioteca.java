import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo;
    private List<Usuario> listaUsuarios;

    public Biblioteca() {
        acervo = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) {
        acervo.add(livro);
        System.out.println("Livro " + livro.getTitulo() + " adicionado no acervo.");
    }


    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Livro l = new Livro("Java como Programar", "Deitel", 2014);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
    }
}