
public class Livro {
    //atributos do livro
    private String titulo;
    private String autor;
    private int ano;

    public Livro(String titulo, String autor, int ano) {
        //validação
        setTitulo(titulo);
        setAutor(autor);
        setAno(ano);
    }

    public int getAno() {  //vai retornar o ano que ta no atributo ano
        return ano;
    }

    public void setAno(int ano) {
        //atribui valor ao ano e valida
        int ano_atual = 2025;
        if (ano > ano_atual) {
            System.out.println("Erro: ano inválido.");
        } else {
            this.ano = ano; //armazena
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == "") {
            System.out.println("Erro: título inválido.");
        } else {
            this.titulo = titulo;
        }
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == "") {
            System.out.println("Erro: autor inválido.");
        } else {
            this.autor = autor;
        }
    }

    @Override
    public String toString() {//substitui o modelo feio de mostrar o endereço da memoria e mostra o texto
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + ano +
                '}';
    }
}