package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.List;

public class GestaoCursosMain {

    public static void main(String[] args) {

        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        // 1) Criar um aluno com 1 endereço e 1 telefone
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

        // 2) Criar um professor
        Professor professor = new Professor();
        professor.setNome("Maria Oliveira");
        professor.setEmail("maria.oliveira@example.com");

        // 3) Criar um material de curso
        MaterialCurso materialCurso = new MaterialCurso();
        materialCurso.setUrlMaterial("https://meusmateriais.com/curso-java-basico");

        // 4) Criar um curso com professor, material e aluno
        Curso curso = new Curso();
        curso.setNome("Java Básico");
        curso.setProfessor(professor);
        curso.setMaterialCurso(materialCurso);
        curso.addAluno(aluno);

        cursoModel.create(curso);

        // 5) Buscar todos os alunos
        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Qtde de alunos encontrados : " + alunos.size());

        // 6) Buscar todos os cursos
        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Qtde de cursos encontrados : " + cursos.size());

        System.out.println("Fim dos testes de CRUD de gestão de cursos.");
    }
}