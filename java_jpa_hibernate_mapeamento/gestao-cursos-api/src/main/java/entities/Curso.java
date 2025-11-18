package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sigla;

    // Um curso tem apenas um professor (muitos cursos para um professor)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    // Um curso tem apenas um material, e o material pertence apenas a um curso
    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL)
    private MaterialCurso materialCurso;

    // Um curso pode ter um ou mais alunos
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Aluno> alunos = new ArrayList<>();

    public Curso() {
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public MaterialCurso getMaterialCurso() {
        return materialCurso;
    }

    public void setMaterialCurso(MaterialCurso materialCurso) {
        this.materialCurso = materialCurso;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
        aluno.setCurso(this);
    }
}