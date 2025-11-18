package demo;

import entities.*;
import models.CursoModel;

import java.time.LocalDate;

public class GestaoCursosMain {

    public static void main(String[] args) {

        entities.Aluno aluno = new entities.Aluno();
        aluno.setNomeCompleto("João da Silva");
        aluno.setMatricula("A001");
        aluno.setNascimento(LocalDate.of(1995, 1, 10));
        aluno.setEmail("joao.silva@example.com");

        entities.Endereco endereco = new entities.Endereco();
        endereco.setLogradouro("Rua Principal");
        endereco.setEndereco("Casa");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCep("00000-000");

        aluno.addEndereco(endereco);

        entities.Telefone telefone = new entities.Telefone();
        telefone.setDdd("11");
        telefone.setNumero("999999999");

        aluno.addTelefone(telefone);

        entities.Professor professor = new entities.Professor();
        professor.setNomeCompleto("Maria Aparecida");
        professor.setMatricula("P001");
        professor.setEmail("maria.aparecida@example.com");

        entities.Curso curso = new entities.Curso();
        curso.setNome("Java com Hibernate e JPA");
        curso.setSigla("JAVA-JPA");
        curso.setProfessor(professor);

        curso.addAluno(aluno);

        entities.MaterialCurso materialCurso = new entities.MaterialCurso();
        materialCurso.setUrl("https://exemplo.com/material-java-jpa");
        materialCurso.setCurso(curso);
        curso.setMaterialCurso(materialCurso);

        CursoModel cursoModel = new CursoModel();
        cursoModel.create(curso);

        System.out.println("Dados inseridos no banco de dados SQLite com sucesso.");
    }
}