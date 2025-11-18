package models;

import entities.Curso;

import javax.persistence.*;
import java.util.List;

public class CursoModel {

    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar um curso !!! " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void update(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização de curso");
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar um curso !!! " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização de curso");
        }
    }

    public void delete(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de remoção de curso");
            em.getTransaction().begin();
            Curso managed = em.find(Curso.class, curso.getId());
            if (managed != null) {
                em.remove(managed);
                System.out.println("Curso removido com sucesso !!!");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao remover um curso !!! " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de remoção de curso");
        }
    }
}