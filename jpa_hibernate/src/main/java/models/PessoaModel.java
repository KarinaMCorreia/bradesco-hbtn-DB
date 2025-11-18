package models;

import entities.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de criação de pessoa");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar a pessoa !!! " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de criação de pessoa");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização de pessoa");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar a pessoa !!! " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização de pessoa");
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de remoção de pessoa");
            em.getTransaction().begin();
            Pessoa pessoaGerenciada = em.find(Pessoa.class, p.getId());
            if (pessoaGerenciada != null) {
                em.remove(pessoaGerenciada);
                System.out.println("Pessoa removida com sucesso !!!");
            } else {
                System.out.println("Pessoa não encontrada para remoção");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao remover a pessoa !!! " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de remoção de pessoa");
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa encontrada = null;

        try {
            encontrada = em.find(Pessoa.class, p.getId());
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoa por id !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return encontrada;
    }

    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        List<Pessoa> pessoas = new ArrayList<>();

        try {
            pessoas = em.createQuery("from Pessoa", Pessoa.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar pessoas !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return pessoas;
    }
}