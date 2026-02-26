package ghostavus.solutions.view;

import ghostavus.solutions.dao.CategoriaDAO;
import ghostavus.solutions.dao.FornecedorDAO;
import ghostavus.solutions.dao.ProdutoDAO;
import ghostavus.solutions.model.Categoria;
import ghostavus.solutions.model.Fornecedor;
import ghostavus.solutions.model.Produto;
import ghostavus.solutions.model.Usuario;
import java.util.List;
import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class CadProduto extends javax.swing.JFrame {

    private Usuario usuarioLogado;

    public CadProduto(Usuario usuario) {
        initComponents();
        configurarCamposMonetarios();
        this.usuarioLogado = usuario;
        setLocationRelativeTo(null);

        txtFornec.setEditable(false);
        
        carregarCategorias();
        configurarEventos();
    }

    private void configurarEventos() {

        btnCadastrar.addActionListener(e -> cadastrarProduto());
        btnLimpar.addActionListener(e -> limparCampos());
        btnVoltar.addActionListener(e -> {
            new Cadastros(usuarioLogado).setVisible(true);
            dispose();
        });
        btnExcluir.addActionListener(e -> excluirProduto());
        
        txtIdFornec.addActionListener(e -> buscaFornecedor());
        txtIdFornec.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                buscaFornecedor();
            }
        });
        
        btnFornec.addActionListener(e -> buscarFornecedor());
           
    
        txtPrecoCusto.addPropertyChangeListener("value", evt -> calcularMargem());
        txtPrecoVenda.addPropertyChangeListener("value", evt -> calcularMargem());
    }

    private void cadastrarProduto() {

    try {
            txtPrecoCusto.commitEdit();
            txtPrecoVenda.commitEdit();

            Produto produto = new Produto();
            produto.setNome(txtDescricao.getText()); // Alterado de setDescricao para setNome

            double custo = ((Number) txtPrecoCusto.getValue()).doubleValue();
            double venda = ((Number) txtPrecoVenda.getValue()).doubleValue();
            produto.setPrecoCusto(custo);
            produto.setPrecoVenda(venda);

            // Cálculo da margem (opcional, se não estiver vindo de outro lugar)
            if (custo > 0) {
                double margemCalculada = ((venda - custo) / custo) * 100;
                produto.setMargem(margemCalculada);
            }

            // Obtendo a Categoria selecionada
            Categoria categoriaSelecionada = (Categoria) cbCategoria.getSelectedItem();
            if (categoriaSelecionada != null) {
                produto.setIdCategoria(categoriaSelecionada.getIdCategoria());
            }

            // Obtendo o Fornecedor pelo ID digitado (conforme seu código original)
            if (!txtIdFornec.getText().trim().isEmpty()) {
                produto.setIdFornecedor(Integer.parseInt(txtIdFornec.getText()));
            }

            ProdutoDAO dao = new ProdutoDAO();
            dao.cadastrar(produto);

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
            limparCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage());
        }
    }

    private void excluirProduto() {

        if (!usuarioLogado.getPerfil().equalsIgnoreCase("GERENTE")) {
            JOptionPane.showMessageDialog(this, "Apenas GERENTE pode excluir produtos!");
            return;
        }

        try {

            int id = Integer.parseInt(txtIdProduto.getText());

            ProdutoDAO dao = new ProdutoDAO();
            dao.excluir(id);

            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
            limparCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto!");
        }
    }

    private void limparCampos() {

        txtIdProduto.setText("");
        txtDescricao.setText("");
        txtPrecoCusto.setText("");
        txtPrecoVenda.setText("");
        txtMargem.setText("");
        txtIdFornec.setText("");
        txtFornec.setText("");
        cbCategoria.setSelectedIndex(0);
    }

    private void calcularMargem() {

    try {

        if (txtPrecoCusto.getValue() != null && txtPrecoVenda.getValue() != null) {

            double custo = ((Number) txtPrecoCusto.getValue()).doubleValue();
            double venda = ((Number) txtPrecoVenda.getValue()).doubleValue();

            if (custo > 0) {
                double margem = ((venda - custo) / custo) * 100;

                NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
                nf.setMaximumFractionDigits(2);

                txtMargem.setText(nf.format(margem) + " %");
            }
        }

    } catch (Exception ignored) {
    }
}

    private void carregarCategorias() {

        cbCategoria.removeAllItems();
        
        cbCategoria.addItem("Bebidas");
        cbCategoria.addItem("Alimentos");
        cbCategoria.addItem("Limpeza");
        cbCategoria.addItem("Outros");
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            Usuario usuarioTeste = new Usuario();
            usuarioTeste.setPerfil("GERENTE");
            new CadProduto(usuarioTeste).setVisible(true);
        });
    }
    
    private void configurarCamposMonetarios() {

    Locale brasil = new Locale("pt", "BR");

    java.text.DecimalFormatSymbols symbols =
            new java.text.DecimalFormatSymbols(brasil);

    java.text.DecimalFormat decimalFormat =
            new java.text.DecimalFormat("#,##0.00", symbols);

    decimalFormat.setParseBigDecimal(true);

    NumberFormatter formatter = new NumberFormatter(decimalFormat);
    formatter.setValueClass(Double.class);
    formatter.setAllowsInvalid(false);
    formatter.setMinimum(0.0);

    txtPrecoCusto.setFormatterFactory(
            new DefaultFormatterFactory(formatter));
    txtPrecoVenda.setFormatterFactory(
            new DefaultFormatterFactory(formatter));

    txtPrecoCusto.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
    txtPrecoVenda.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);

    txtPrecoCusto.setValue(0.0);
    txtPrecoVenda.setValue(0.0);
}
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdProduto = new javax.swing.JFormattedTextField();
        txtDescricao = new javax.swing.JTextField();
        btnFornec = new javax.swing.JButton();
        txtIdFornec = new javax.swing.JTextField();
        txtFornec = new javax.swing.JTextField();
        cbCategoria = new javax.swing.JComboBox<>();
        txtPrecoCusto = new javax.swing.JFormattedTextField();
        txtMargem = new javax.swing.JFormattedTextField();
        txtPrecoVenda = new javax.swing.JFormattedTextField();
        btnVoltar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtIdProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtIdProduto.addActionListener(this::txtIdProdutoActionPerformed);
        getContentPane().add(txtIdProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 190, 50));

        txtDescricao.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 870, 60));

        btnFornec.setBorderPainted(false);
        btnFornec.setContentAreaFilled(false);
        btnFornec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnFornec, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 170, 30));

        txtIdFornec.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtIdFornec.addActionListener(this::txtIdFornecActionPerformed);
        getContentPane().add(txtIdFornec, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 150, 50));

        txtFornec.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtFornec, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 690, 50));

        cbCategoria.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 280, 60));

        txtPrecoCusto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtPrecoCusto.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtPrecoCusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 470, 280, 60));

        txtMargem.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getPercentInstance())));
        txtMargem.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtMargem, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 280, 60));

        txtPrecoVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtPrecoVenda.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtPrecoVenda.addActionListener(this::txtPrecoVendaActionPerformed);
        getContentPane().add(txtPrecoVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 550, 280, 60));

        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 660, 130, 40));

        btnLimpar.setBorderPainted(false);
        btnLimpar.setContentAreaFilled(false);
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnLimpar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 140, 40));

        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 660, 180, 40));

        btnExcluir.setBorderPainted(false);
        btnExcluir.setContentAreaFilled(false);
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 660, 140, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Cadastro Produto (1).png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdFornecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdFornecActionPerformed
        buscaFornecedor();
    }//GEN-LAST:event_txtIdFornecActionPerformed

    private void txtIdProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProdutoActionPerformed

    private void txtPrecoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoVendaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFornec;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtFornec;
    private javax.swing.JTextField txtIdFornec;
    private javax.swing.JFormattedTextField txtIdProduto;
    private javax.swing.JFormattedTextField txtMargem;
    private javax.swing.JFormattedTextField txtPrecoCusto;
    private javax.swing.JFormattedTextField txtPrecoVenda;
    // End of variables declaration//GEN-END:variables

    private void buscaFornecedor() {
    
        if (txtIdFornec.getText().trim().isEmpty()) {
            txtFornec.setText("");
            return;
        }
        
        try {
            int id = Integer.parseInt(txtIdFornec.getText());
            
            FornecedorDAO dao = new FornecedorDAO();
            Fornecedor fornecedor = dao.buscarPorId(id);
            
            if (fornecedor != null) {
                txtFornec.setText(fornecedor.getRazaoSocial());
            } else {
                txtFornec.setText("");
            }
        } catch (NumberFormatException e) {
            txtFornec.setText("");
        }
    }

    private void buscarFornecedor() {
             buscaFornecedor();
}
}
