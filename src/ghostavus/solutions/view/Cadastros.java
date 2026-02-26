/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghostavus.solutions.view;

import ghostavus.solutions.model.Usuario;

public class Cadastros extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Cadastros.class.getName());
    private String nomeUsuario;
    private Usuario usuarioLogado;
    private String nomeLogado;

 
    public Cadastros(Usuario usuario) {
        initComponents();
         setLocationRelativeTo(null);
         
         this.usuarioLogado = usuario;
         configurarBotoes();
    }
   
    private void configurarBotoes() {

    // CLIENTE
    btnCliente.addActionListener(e -> {
        CadCliente tela = new CadCliente(usuarioLogado); // Passe o usuário logado
        tela.setVisible(true);
        this.dispose();
    });

       // PRODUTO
    btnProduto.addActionListener(e -> {
        CadProduto tela = new CadProduto(usuarioLogado); // Passe o usuário logado
         tela.setVisible(true);
    this.dispose();
    });

    // VOLTAR
    btnVoltar.addActionListener(e -> {
        MenuPrincipal tela = new MenuPrincipal(usuarioLogado);
        tela.setVisible(true);
        this.dispose();
    });
}

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVoltar = new javax.swing.JButton();
        btnProduto = new javax.swing.JButton();
        btnCliente = new javax.swing.JButton();
        btnFornecedor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 640, 220, 40));

        btnProduto.setBorderPainted(false);
        btnProduto.setContentAreaFilled(false);
        btnProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 320, 40));

        btnCliente.setBorderPainted(false);
        btnCliente.setContentAreaFilled(false);
        btnCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 320, 40));

        btnFornecedor.setBorderPainted(false);
        btnFornecedor.setContentAreaFilled(false);
        btnFornecedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFornecedor.addActionListener(this::btnFornecedorActionPerformed);
        getContentPane().add(btnFornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, 320, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Cadastro (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFornecedorActionPerformed
         CadFornecedor tela = new CadFornecedor(usuarioLogado);
         tela.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_btnFornecedorActionPerformed

    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> {
        // Crie um usuário de teste para poder abrir a tela
        ghostavus.solutions.model.Usuario usuarioTeste = new ghostavus.solutions.model.Usuario();
        usuarioTeste.setPerfil("GERENTE");
        usuarioTeste.setNome("Usuário de Teste");
        new Cadastros(usuarioTeste).setVisible(true);
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnFornecedor;
    private javax.swing.JButton btnProduto;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
