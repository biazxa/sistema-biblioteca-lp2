import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo; //cria um acervo privado de um a lista com o tipo livro
    private List<Usuario> listaUsuarios; //mesma coisa, so que com o tipo usuario

    public Biblioteca() { //construtor da classe biblioteca usa qnd chama o new biblioteca()
        acervo = new ArrayList<>(); //inicia uma lista acervo com um arraylist(lista dinamica) vazio
        listaUsuarios = new ArrayList<>();//inicia uma lista de usuarios com um arraylist(lista dinamica) vazio
    }

    public void cadastrarLivro(Livro livro) { //adiciona livros ao acervo, recebe um obj do tipo livro como parametro
        acervo.add(livro);//add o livro recebido no acervo
        System.out.println("Livro " + livro.getTitulo() + " adicionado no acervo.");
    }


    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(); //cria um novo obj biblioteca
        Livro l = new Livro("Java como Programar", "Deitel", 2014); //cria um novo livro usando o construtor livro
        biblioteca.cadastrarLivro(l); //chama o metodo cadastrar livro e o obj l vai ser adicionado varias vezes
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
        biblioteca.cadastrarLivro(l);
    }
}