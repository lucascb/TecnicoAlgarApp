package lit26.tecnicoalgarapp;

/**
 * Created by lucas on 30/03/17.
 */

public class Pessoa {
    private long id;
    private String nome;
    private String telefone;
    private String email;
    private String foto;

    public Pessoa(long id, String nome, String telefone, String email, String foto) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.foto = foto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
