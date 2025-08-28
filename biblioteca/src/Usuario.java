import java.util.Objects;

public class Usuario {
    private String nome;
    private String id;

    public Usuario(String nome, String id) {
        setNome(nome);
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome.isEmpty()) {
            System.out.println("Erro: o nome não pode ser vazio.");
        } else {
            this.nome = nome;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}