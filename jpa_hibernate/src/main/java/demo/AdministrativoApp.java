package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.Date;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {

        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        produtoModel.create(p1);
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        if (!produtos.isEmpty()) {
            Produto primeiro = produtos.get(0);
            primeiro.setPreco(350.0);
            produtoModel.update(primeiro);
        }

        if (!produtos.isEmpty()) {
            Produto busca = new Produto();
            busca.setId(produtos.get(0).getId());
            Produto encontrado = produtoModel.findById(busca);
            if (encontrado != null) {
                System.out.println("Produto encontrado: " + encontrado.getNome()
                        + " - Preço: " + encontrado.getPreco());
            }
        }

        if (!produtos.isEmpty()) {
            Produto paraRemover = new Produto();
            paraRemover.setId(produtos.get(0).getId());
            produtoModel.delete(paraRemover);
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Maria da Silva");
        pessoa.setEmail("maria.silva@example.com");
        pessoa.setIdade(30);
        pessoa.setCpf("123.456.789-00");
        pessoa.setDataNascimento(new Date());

        pessoaModel.create(pessoa);

        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas : " + pessoas.size());

        if (!pessoas.isEmpty()) {
            Pessoa primeiraPessoa = pessoas.get(0);
            primeiraPessoa.setNome("Maria da Silva Souza");
            pessoaModel.update(primeiraPessoa);
        }

        if (!pessoas.isEmpty()) {
            Pessoa buscaPessoa = new Pessoa();
            buscaPessoa.setId(pessoas.get(0).getId());
            Pessoa encontrada = pessoaModel.findById(buscaPessoa);
            if (encontrada != null) {
                System.out.println("Pessoa encontrada: " + encontrada.getNome()
                        + " - Email: " + encontrada.getEmail());
            }
        }

        if (!pessoas.isEmpty()) {
            Pessoa paraRemoverPessoa = new Pessoa();
            paraRemoverPessoa.setId(pessoas.get(0).getId());
            pessoaModel.delete(paraRemoverPessoa);
        }

        System.out.println("Fim dos testes de CRUD.");
    }
}