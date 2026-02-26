package ghostavus.solutions.view;

import ghostavus.solutions.dao.ClienteDAO;
import ghostavus.solutions.dao.ItemPedidoDAO;
import ghostavus.solutions.dao.PedidoDAO;
import ghostavus.solutions.dao.ProdutoDAO;
import ghostavus.solutions.model.ItemPedido;
import ghostavus.solutions.model.Pedido;
import ghostavus.solutions.model.Produto;
import ghostavus.solutions.model.Usuario;
import ghostavus.solutions.model.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;
import java.util.Locale;



public class RegistroPedido extends javax.swing.JFrame {
    
   private Usuario usuarioLogado;
           
    public RegistroPedido(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        initComponents();
        setLocationRelativeTo(null);
        configurarTabela();
        configurarValorTotal();
        configurarBotoes();
               
    }
private void configurarTabela() {
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Produto", "Preço", "Quantidade", "Total"}, 0
        );
        jTable1.setModel(modelo);
        
         jTable1.setRowHeight(30);

    
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(300); // Produto
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100); // Preço
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100); // Quantidade
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(100); // Total

    
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        jTable1.getColumnModel().getColumn(0).setCellRenderer(center);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(center);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(center);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(center);
    }

    private void configurarValorTotal() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        txtValorTotal.setValue(nf.format(0));
    }

    private void configurarBotoes() {

        btnMais.addActionListener(e -> {
            int qtde = txtQtde.getText().isEmpty() ? 0 : Integer.parseInt(txtQtde.getText());
            qtde++;
            txtQtde.setText(String.valueOf(qtde));
        });

        btnMenos.addActionListener(e -> {
            int qtde = txtQtde.getText().isEmpty() ? 0 : Integer.parseInt(txtQtde.getText());
            if (qtde > 1) {
                qtde--;
                txtQtde.setText(String.valueOf(qtde));
            }
        });

        btnAdicionar.addActionListener(e -> adicionarItem());
        btnExcluir.addActionListener(e -> excluirItem());
        btnConcluir.addActionListener(e -> concluirPedido());
        btnVoltar.addActionListener(e -> voltarMenu());
    }

    // ================= ADICIONAR ITEM =================

    private void adicionarItem() {

        try {

            int idProduto = Integer.parseInt(txtProduto.getText());
            int quantidade = Integer.parseInt(txtQtde.getText());

            ProdutoDAO dao = new ProdutoDAO();
            Produto produto = dao.buscarPorId(idProduto);

            if (produto == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                return;
            }

            double preco = produto.getPrecoVenda();
            double total = preco * quantidade;

            modelo.addRow(new Object[]{
                    produto.getId_produto(),
                    produto.getNome(),
                    preco,
                    quantidade,
                    total
            });

            if (numeroPedidoGerado == 0) {
                numeroPedidoGerado = gerarNumeroPedido();
                txtNumPedido.setText(String.valueOf(numeroPedidoGerado));
            }

            atualizarValorTotal();
            limparCamposProduto();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar item.");
        }
    }

    // ================= EXCLUIR ITEM =================

    private void excluirItem() {

        int linha = jTable1.getSelectedRow();

        if (linha != -1) {
            modelo.removeRow(linha);
            atualizarValorTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para excluir.");
        }
    }

    // ================= CONCLUIR PEDIDO =================

    private void concluirPedido() {

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Adicione itens ao pedido.");
            return;
        }

        try {

            ClienteDAO clienteDAO = new ClienteDAO();
            int idCliente = clienteDAO.buscarIdPorCpf(txtCliente.getText());

            double valorTotal = 0;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                valorTotal += (double) modelo.getValueAt(i, 4);
            }

            Pedido pedido = new Pedido(idCliente, valorTotal);
            PedidoDAO pedidoDAO = new PedidoDAO();

            int idPedidoGerado = pedidoDAO.inserir(pedido);

            ItemPedidoDAO itemDAO = new ItemPedidoDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            for (int i = 0; i < modelo.getRowCount(); i++) {

                int idProduto = (int) modelo.getValueAt(i, 0);
                int quantidade = (int) modelo.getValueAt(i, 3);
                double preco = (double) modelo.getValueAt(i, 2);
                double total = (double) modelo.getValueAt(i, 4);

                ItemPedido item = new ItemPedido(
                        idPedidoGerado,
                        idProduto,
                        quantidade,
                        preco,
                        total
                );

                itemDAO.inserir(item);
                produtoDAO.baixarEstoque(idProduto, quantidade);
            }

            JOptionPane.showMessageDialog(this, "Pedido concluído com sucesso!");

            limparTela();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao concluir pedido.");
        }
    }

    // ================= MÉTODOS AUXILIARES =================

    private void atualizarValorTotal() {

        double total = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            total += (double) modelo.getValueAt(i, 4);
        }

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        txtValorTotal.setValue(nf.format(total));
    }

    private int gerarNumeroPedido() {
        return (int) (System.currentTimeMillis() % 100000);
    }

    private void limparCamposProduto() {
        txtProduto.setText("");
        txtQtde.setText("1");
    }

    private void limparTela() {
        modelo.setRowCount(0);
        txtNumPedido.setText("");
        txtCliente.setText("");
        configurarValorTotal();
        numeroPedidoGerado = 0;
    }

    private void voltarMenu() {
        this.dispose();
        new MenuPrincipal(usuarioLogado).setVisible(true);
    }

    // ================= MAIN =================

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() ->
                new RegistroPedido(null).setVisible(true)
        );
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtProduto = new javax.swing.JTextField();
        txtQtde = new javax.swing.JTextField();
        txtValorTotal = new javax.swing.JFormattedTextField();
        btnVoltar = new javax.swing.JButton();
        txtNumPedido = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnConcluir = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnMais = new javax.swing.JButton();
        btnMenos = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtProduto.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 350, 50));

        txtQtde.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtQtde, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 310, 120, 50));

        txtValorTotal.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtValorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 190, 200, 50));

        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(this::btnVoltarActionPerformed);
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 690, 170, 40));

        txtNumPedido.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtNumPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 350, 50));

        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setContentAreaFilled(false);
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 680, 180, 50));

        btnConcluir.setBorderPainted(false);
        btnConcluir.setContentAreaFilled(false);
        btnConcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnConcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 680, 190, 50));

        btnExcluir.setBorderPainted(false);
        btnExcluir.setContentAreaFilled(false);
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(846, 690, 180, 40));

        btnMais.setText("jButton5");
        btnMais.setBorderPainted(false);
        btnMais.setContentAreaFilled(false);
        btnMais.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnMais, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 307, 40, 50));

        btnMenos.setText("jButton6");
        btnMenos.setBorderPainted(false);
        btnMenos.setContentAreaFilled(false);
        btnMenos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnMenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 310, 30, 50));

        txtCliente.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtClienteFocusLost(evt);
            }
        });
        txtCliente.addActionListener(this::txtClienteActionPerformed);
        getContentPane().add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 256, 840, 50));

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jTable1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Produto", "Preço", "Quantidade", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(80);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(2).setMinWidth(80);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(3).setMinWidth(80);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(4).setMinWidth(80);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 1010, 280));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Registro Pedido (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed

    }//GEN-LAST:event_btnVoltarActionPerformed

    private void txtClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtClienteFocusLost
        
        String cpf = txtCliente.getText()
                     .replaceAll("[^0-9]", "")
                    .trim();

        if (cpf.length() == 11) {

            System.out.println("Buscando CPF: " + cpf);

            ClienteDAO dao = new ClienteDAO();
            Cliente cliente = dao.buscarPorCpf(cpf);

         if (cliente != null) {
            txtCliente.setText(cliente.getCpf() + " - " + cliente.getNome());
        } else {
            JOptionPane.showMessageDialog(this,
                "Cliente não encontrado!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        txtCliente.setText("");
    }
}
    }//GEN-LAST:event_txtClienteFocusLost

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnConcluir;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnMais;
    private javax.swing.JButton btnMenos;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtNumPedido;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtQtde;
    private javax.swing.JFormattedTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel modelo;
    private int numeroPedidoGerado = 0;
}
