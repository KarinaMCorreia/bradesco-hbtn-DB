package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {

    public void create(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao criar o produto !!! " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de atualização");
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado com sucesso !!!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o produto !!! " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização");
        }
    }

    public void delete(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação de remoção");
            em.getTransaction().begin();
            Produto produtoGerenciado = em.find(Produto.class, p.getId());
            if (produtoGerenciado != null) {
                em.remove(produtoGerenciado);
                System.out.println("Produto removido com sucesso !!!");
            } else {
                System.out.println("Produto não encontrado para remoção");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao remover o produto !!! " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de remoção");
        }
    }

    public Produto findById(Produto p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto encontrado = null;

        try {
            encontrado = em.find(Produto.class, p.getId());
        } catch (Exception e) {
            System.err.println("Erro ao buscar produto por id !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return encontrado;
    }

    public List<Produto> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        List<Produto> produtos = new ArrayList<>();

        try {
            produtos = em.createQuery("from Produto", Produto.class).getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao listar produtos !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return produtos;
    }
}