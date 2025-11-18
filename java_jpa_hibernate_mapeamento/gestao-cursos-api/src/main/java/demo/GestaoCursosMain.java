package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.List;

public class GestaoCursosMain {

    public static void main(String[] args) {

        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        Aluno aluno = new Aluno();
        aluno.setNome("João da Silva");
        aluno.setEmail("joao.silva@example.com");

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Flores, 123");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        aluno.addEndereco(endereco);

        Telefone telefone = new Telefone();
        telefone.setNumero("(11) 99999-9999");
        telefone.setTipo("Celular");
        aluno.addTelefone(telefone);

        alunoModel.create(aluno);

        Professor professor = new Professor();
        professor.setNome("Maria Oliveira");
        professor.setEmail("maria.oliveira@example.com");

        MaterialCurso materialCurso = new MaterialCurso();
        materialCurso.setUrlMaterial("https://meusmateriais.com/curso-java-basico");

        Curso curso = new Curso();
        curso.setNome("Java Básico");
        curso.setProfessor(professor);
        curso.setMaterialCurso(materialCurso);
        curso.addAluno(aluno);

        cursoModel.create(curso);

        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Qtde de alunos encontrados : " + alunos.size());

        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Qtde de cursos encontrados : " + cursos.size());

        System.out.println("Fim dos testes de CRUD de gestão de cursos.");
    }
}