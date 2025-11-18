package models;

import entities.Curso;

import javax.persistence.*;
import java.util.List;

public class CursoModel {

    private EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("gestao-cursos-jpa");
    }

    public void create(Curso curso) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar um curso !!! " + e.getMessage());
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

    public Curso findById(Long id) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Curso curso = null;

        try {
            curso = em.find(Curso.class, id);
        } catch (Exception e) {
            System.err.println("Erro ao buscar curso por id !!! " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            emf.close();
        }

        return curso;
    }

    public List<Curso> findAll() {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = null;

        try {
            TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
            cursos = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar todos os cursos !!! " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
            emf.close();
        }

        return cursos;
    }

    public void update(Curso curso) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar curso !!! " + e.getMessage());
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

    public void delete(Curso curso) {
        EntityManagerFactory emf = getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de exclusão");
            em.getTransaction().begin();
            Curso managed = em.find(Curso.class, curso.getId());
            if (managed != null) {
                em.remove(managed);
            }
            em.getTransaction().commit();
            System.out.println("Curso removido com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao remover curso !!! " + e.getMessage());
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