package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>();

    @ManyToMany(mappedBy = "alunos")
    private List<Curso> cursos = new ArrayList<>();

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        endereco.setAluno(this);
        this.enderecos.add(endereco);
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void addTelefone(Telefone telefone) {
        telefone.setAluno(this);
        this.telefones.add(telefone);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }
}