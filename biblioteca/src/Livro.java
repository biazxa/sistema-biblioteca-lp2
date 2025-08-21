//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Livro {
    private String titulo;
    private String autor;
    private int ano;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        int ano_atual = 2025;
        if(ano > ano_atual){
            System.out.println("Erro: ano inválido.");
        }else {
            this.ano = ano;
        }
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        if(titulo == " "){
            System.out.println("erro: titulo invalido");
        }else{
            this.titulo = titulo;
        }
    }
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + ano +
                '}';
    }
}