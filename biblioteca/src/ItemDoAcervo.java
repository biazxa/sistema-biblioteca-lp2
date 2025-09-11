public class ItemDoAcervo {

    private String titulo;
    private int ano;
    private StatusLivro status;

    public String getTitulo() {

        return titulo;
    }
    public void setTitulo(String titulo) {
        if (titulo == "") {
            System.out.println("ERRO: titulo invalido.");
        }else{
            this.titulo = titulo;
        }

    }

    public String getAno() {

        return titulo;
    }
    public void setAno(int ano) {
        int ano_atual = 2025;
        if (ano>ano_atual) {
            System.out.println("ERRO: ano invalido.");
        }else {
            this.ano = ano;
        }
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }

    public ItemDoAcervo(String titulo, int ano) {
        this.titulo = titulo;
        this.ano = ano;
        this.status = StatusLivro.DISPONIVEL;
    }

    public int getPrazo(){
        return 07;
    }

    public double getValorMultaPorDiaAtraso(){
        return 0.50;
    }







}