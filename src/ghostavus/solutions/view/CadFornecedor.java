/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghostavus.solutions.view;

import ghostavus.solutions.model.Usuario;
import ghostavus.solutions.model.Fornecedor;
import ghostavus.solutions.dao.FornecedorDAO;
import javax.swing.JOptionPane;

public class CadFornecedor extends javax.swing.JFrame {

    private Usuario usuarioLogado;
            
    public CadFornecedor(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        initComponents();
        setLocationRelativeTo(null);
        configurarEventos();
    }
    
     private void configurarEventos() {

        btnVoltar.addActionListener(e -> {
            Cadastros tela = new Cadastros(usuarioLogado);
            tela.setVisible(true);
            dispose();
        });

        btnCadastrar.addActionListener(e -> cadastrarFornecedor());

        btnExcluir.addActionListener(e -> excluirFornecedor());
    }

    private void cadastrarFornecedor() {
        try {
            Fornecedor fornecedor = new Fornecedor();

            fornecedor.setRazaoSocial(txtRazao.getText());
            fornecedor.setCnpj(txtCnpj.getText());
            fornecedor.setTelefone(txtTelefone.getText());
            fornecedor.setEmail(txtEmail.getText());
            fornecedor.setRepresentante(txtRepresentante.getText());

            FornecedorDAO dao = new FornecedorDAO();
            dao.inserir(fornecedor);

            JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!");
            limparCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void excluirFornecedor() {
        try {
            int id = Integer.parseInt(txtId.getText());

            FornecedorDAO dao = new FornecedorDAO();
            dao.excluir(id);

            JOptionPane.showMessageDialog(this, "Fornecedor excluído com sucesso!");
            limparCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtRazao.setText("");
        txtCnpj.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtRepresentante.setText("");
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTelefone = new javax.swing.JFormattedTextField();
        txtCnpj = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JTextField();
        txtRepresentante = new javax.swing.JTextField();
        txtRazao = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        btnVoltar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ### ### ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.addActionListener(this::txtTelefoneActionPerformed);
        getContentPane().add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 280, 60));

        try {
            txtCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCnpj.addActionListener(this::txtCnpjActionPerformed);
        getContentPane().add(txtCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 460, 50));

        txtEmail.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, 430, 60));

        txtRepresentante.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtRepresentante.addActionListener(this::txtRepresentanteActionPerformed);
        getContentPane().add(txtRepresentante, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, 800, 50));

        txtRazao.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtRazao.addActionListener(this::txtRazaoActionPerformed);
        getContentPane().add(txtRazao, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 880, 50));

        txtId.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtId.addActionListener(this::txtIdActionPerformed);
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 320, 50));

        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 677, 240, 50));

        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 680, 240, 40));

        btnExcluir.setBorderPainted(false);
        btnExcluir.setContentAreaFilled(false);
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 680, 240, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Cadastro Fornecedor (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRepresentanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRepresentanteActionPerformed

    private void txtCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtRazaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRazaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRazaoActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField txtCnpj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtRazao;
    private javax.swing.JTextField txtRepresentante;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
