import java.time.LocalDate;

public class Emprestimo {
    //cria os atributos
    private ItemDoAcervo item; //item armazena um itemdoacervo
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    public Emprestimo(ItemDoAcervo item, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        //cria novos obj e armazena
        this.item = item;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    // get ==> retorna o valor
    // set ==> altera o valor
    public ItemDoAcervo getItem() {
        return  item;
    }

    public void setItem(ItemDoAcervo item) {
        this.item = item;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "livro=" + item.getTitulo() +
                ", usuario=" + usuario.getName() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucaoPrevista=" + dataDevolucaoPrevista +
                '}';
    }
}