package ghostavus.solutions.view;

import ghostavus.solutions.model.Usuario;
import ghostavus.solutions.model.Cliente;
import ghostavus.solutions.dao.ClienteDAO;
import javax.swing.JOptionPane;

public class CadCliente extends javax.swing.JFrame {
    
    private Usuario usuarioLogado;
   
    public CadCliente(Usuario usuario) {
        initComponents();
        this.usuarioLogado = usuario;
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JTextField();
        txtEndereço = new javax.swing.JTextField();
        cbSexo = new javax.swing.JComboBox<>();
        btnCadastrar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNome.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        txtNome.addActionListener(this::txtNomeActionPerformed);
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 286, 870, 60));

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addActionListener(this::txtCpfActionPerformed);
        getContentPane().add(txtCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 376, 380, 60));

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 300, 60));

        txtEmail.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, 430, 60));

        txtEndereço.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtEndereço, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 566, 870, 60));

        cbSexo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino", "Não Especificar" }));
        getContentPane().add(cbSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 376, 360, 60));

        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);
        getContentPane().add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 681, 250, 40));

        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(this::btnVoltarActionPerformed);
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 677, 250, 50));

        btnExcluir.setBorderPainted(false);
        btnExcluir.setContentAreaFilled(false);
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 681, 250, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Cadastro Cliente (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        
        String nome = txtNome.getText().trim();
        String cpf = txtCpf.getText().trim().replaceAll("[^0-9]", "");
        String telefone = txtTelefone.getText().trim().replaceAll("[^0-9]", "");
        String email = txtEmail.getText().trim();
        String endereco = txtEndereço.getText().trim();
        String sexo = cbSexo.getSelectedItem().toString();
        
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Nome é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cpf.equals("   .   .   -  ")) {
            JOptionPane.showMessageDialog(this, "O campo CPF é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtTelefone.getValue() == null) {
            JOptionPane.showMessageDialog(this,
                    "O campo Telefone é obrigatório.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Email é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
        return;
        }
        if (endereco.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Endereço é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
        return;
        }
        
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        cliente.setSexo(sexo);
        
        ClienteDAO dao = new ClienteDAO();
        boolean sucesso = dao.cadastrar(cliente);
        
        if (sucesso) {
        
        JOptionPane.showMessageDialog(this,
                "Cliente Cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        
        limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Falha ao cadastrar o cliente.\n Verifique os dados ou consulte o log para mais detalhes.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
         if (!usuarioLogado.getPerfil().equalsIgnoreCase("GERENTE")) {
        JOptionPane.showMessageDialog(this,
                "Apenas gerente pode excluir clientes!",
                "Acesso negado",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    String cpf = txtCpf.getText().trim().replaceAll("[^0-9]","");

    if (cpf.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Informe o CPF do cliente para excluir!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    ClienteDAO dao = new ClienteDAO();
    boolean sucesso = dao.excluirPorCpf(cpf);
    
    if (sucesso) {
        JOptionPane.showMessageDialog(this,
            "Cliente excluído com sucesso!",
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE);
    limparCampos();  
    } else {
        JOptionPane.showMessageDialog(this,
                "Nenhum cliente encontrado com o CPF informado.",
                "Erro na Exclusão",
                JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnExcluirActionPerformed
}
    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
      
        Cadastros tela = new Cadastros(usuarioLogado);
        tela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Usuario usuarioTeste = new Usuario();
            usuarioTeste.setPerfil("GERENTE");
            new CadCliente(usuarioTeste).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereço;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setValue(null); // Use setValue(null) para JFormattedTextField
        txtTelefone.setValue(null);
        txtEmail.setText("");
        txtEndereço.setText("");
        cbSexo.setSelectedIndex(0); // Define a seleção para o primeiro item ("Masculino")
}
}
