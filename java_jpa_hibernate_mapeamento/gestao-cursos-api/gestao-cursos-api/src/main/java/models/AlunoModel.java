package models;

import entities.Aluno;

import javax.persistence.*;
import java.util.List;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar um aluno !!! " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização de aluno");
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar um aluno !!! " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização de aluno");
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de remoção de aluno");
            em.getTransaction().begin();
            Aluno managed = em.find(Aluno.class, aluno.getId());
            if (managed != null) {
                em.remove(managed);
                System.out.println("Aluno removido com sucesso !!!");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao remover um aluno !!! " + e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de remoção de aluno");
        }
    }
}