import java.util.Objects;

public class Usuario {
    private String nome;
    private String id;

    public Usuario(String nome, String id) {
        setNome(nome); //validação
        this.id = id; //atribui diretamente
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.isEmpty()) { //verifica se o nome é vazio
            System.out.println("Erro: o nome não pode ser vazio.");
        } else {
            this.nome = nome;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { //validação do tamanho
        if(this.id.length() > 11) {
            System.out.println("Erro: valor inválido.");
        } else {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) { //diz que se dois usuarios tiverem ids iguais,eles sao iguais
        if (o == null || getClass() != o.getClass()) return false; //compara se o obj é vazio e se são de classes diferentes,se for retorna falso
        Usuario usuario = (Usuario) o; // atribui o obj generico ao usuario
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id); //gerar um codigo hash
    }
}