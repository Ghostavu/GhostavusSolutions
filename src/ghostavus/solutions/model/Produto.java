package ghostavus.solutions.model;

public class Produto {
    private int id_produto;
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private double margem;
    private int quantidadeEstoque;
    private int idFornecedor;
    private int idCategoria;

    public Produto() {}

    public int getId_produto() { return id_produto; }
    public void setId_produto(int id_produto) { this.id_produto = id_produto; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto(double precoCusto) { this.precoCusto = precoCusto; }

    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public double getMargem() { return margem; }
    public void setMargem(double margem) { this.margem = margem; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public int getIdFornecedor() { return idFornecedor; }
    public void setIdFornecedor(int idFornecedor) { this.idFornecedor = idFornecedor; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }
}
