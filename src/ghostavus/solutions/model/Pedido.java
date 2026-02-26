package ghostavus.solutions.model;

import java.util.Date;

public class Pedido {

    private int id;
    private int idCliente;
    private Date data;
    private double valorTotal;
    private int idUsuarioVenda;

    public Pedido() {}

    public Pedido(int idCliente, double valorTotal) {
        this.idCliente = idCliente;
        this.valorTotal = valorTotal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    // Novos métodos getter e setter para idUsuarioVenda
    public int getIdUsuarioVenda() { return idUsuarioVenda; }
    public void setIdUsuarioVenda(int idUsuarioVenda) { this.idUsuarioVenda = idUsuarioVenda; }
}
