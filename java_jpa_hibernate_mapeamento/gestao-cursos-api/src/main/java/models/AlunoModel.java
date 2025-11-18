package models;

import entities.Aluno;

import javax.persistence.*;
import java.util.List;

public class AlunoModel {

    private EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("gestao-cursos-jpa");
    }

    public void create(Aluno aluno) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar um aluno !!! " + e.getMessage());
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Aluno aluno = null;

        try {
            aluno = em.find(Aluno.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar aluno por id !!! " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            emf.close();
        }

        return aluno;
    }

    public List<Aluno> findAll() {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        List<Aluno> alunos = null;

        try {
            TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a", Aluno.class);
            alunos = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os alunos !!! " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            emf.close();
        }

        return alunos;
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar aluno !!! " + e.getMessage());
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            Aluno managed = em.find(Aluno.class, aluno.getId());
            if (managed != null) {
                em.remove(managed);
            }
            em.getTransaction().commit();
            System.out.println("Aluno removido com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao remover aluno !!! " + e.getMessage());
            if (em.isOpen()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }
}