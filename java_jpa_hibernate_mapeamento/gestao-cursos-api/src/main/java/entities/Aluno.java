package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String matricula;

    private LocalDate nascimento;

    private String email;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Aluno() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<entities.Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<entities.Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<entities.Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<entities.Telefone> telefones) {
        this.telefones = telefones;
    }

    public entities.Curso getCurso() {
        return curso;
    }

    public void setCurso(entities.Curso curso) {
        this.curso = curso;
    }


    public void addEndereco(entities.Endereco endereco) {
        enderecos.add(endereco);
        endereco.setAluno(this);
    }

    public void addTelefone(entities.Telefone telefone) {
        telefones.add(telefone);
        telefone.setAluno(this);
    }
}