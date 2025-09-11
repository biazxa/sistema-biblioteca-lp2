public class Livro extends ItemDoAcervo { //extends é herdar tudo do ItemDoAcervo

    private String autor;


    public Livro(String titulo, String autor, int ano){

        super(titulo,ano); //super acessa a superclasse/classe pai(itemdoacervo) reutiliza a logica,vai herdar
        this.autor = autor;
    }

    public String getAutor() {

        return autor;
    }
    public void setAutor(String autor) {
        if (autor == "") {
            System.out.println("ERRO: autor invalido.");
        }else{
            this.autor = autor;
        }
    }

    @Override //muda ou customiza um comportamento
    public int getPrazo(){
        return 14;
    }

    @Override
    public double getValorMultaPorDiaAtraso(){
        return 0.75;
    }


    @Override
    public String toString() {
        return "Livro '" + getTitulo() + "', de " + getAutor() + " (" + getAno() + ") - Status: " + getStatus();
    }



}