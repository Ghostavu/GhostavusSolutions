package ghostavus.solutions.model;

public class ItemPedido {

    private int idPedido;
    private int idProduto;
    private int quantidade;
    private double precoUnitario;
    private double total;

    public ItemPedido(int idPedido, int idProduto, int quantidade, double precoUnitario, double total) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.total = total;
    }

    public int getIdPedido() { return idPedido; }
    public int getIdProduto() { return idProduto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
    public double getTotal() { return total; }
}