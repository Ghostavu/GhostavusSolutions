/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ghostavus.solutions.view;

import ghostavus.solutions.dao.UsuarioDAO;
import ghostavus.solutions.model.Usuario;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLogin.class.getName());
    private String Login;

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
    }

  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 340, 390, 40));

        txtSenha.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, 390, 40));

        btnEntrar.setBorderPainted(false);
        btnEntrar.setContentAreaFilled(false);
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);
        getContentPane().add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 240, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Acesso (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        
        String login = txtUsuario.getText().trim().toUpperCase();
        String senha = new String(txtSenha.getPassword()).trim();

    if (login.isEmpty() || senha.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Preencha usuário e senha!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    UsuarioDAO dao = new UsuarioDAO();
    Usuario usuario = dao.validarLogin(login, senha);

    if (usuario != null) {
        
        String nome = usuario.getLogin();
        
        String nomeFormatado = nome.substring(0,1).toUpperCase()
                + nome.substring(1).toLowerCase();
        
        JOptionPane.showMessageDialog(this,
                "Bem-vindo, " + nomeFormatado + "!",
                "Login realizado",
                JOptionPane.INFORMATION_MESSAGE);
        
        MenuPrincipal menu = new MenuPrincipal(usuario);
        menu.setVisible(true);
        
        this.dispose();
        
    } else {
        JOptionPane.showMessageDialog(this,
                "Usuário ou senha inválidos!",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnEntrarActionPerformed

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

         
}
