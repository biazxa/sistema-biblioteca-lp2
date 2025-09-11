import java.util.Objects;

public class Usuario {

    private String user;
    private String name;
    private String password;

    public Usuario(String user, String name, String  password){
        setUser(user);
        setName(name);
        setPassword(password);
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        if (user == "") {
            System.out.println("ERRO: O usuario não pode ser vazio. ");
        }else{
            this.user = user;
        }
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == "") {
            System.out.println("ERRO: O nome não pode ser vazio.");
        } else {
            this.name = name;
        }
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if (password == "") {
            System.out.println("ERRO: A senha não pode ser vazia.");
        }else{
            this.password = password;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(user, usuario.user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user);
    }
}