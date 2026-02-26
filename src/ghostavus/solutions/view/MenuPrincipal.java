package ghostavus.solutions.view;

import ghostavus.solutions.model.Usuario;

public class MenuPrincipal extends javax.swing.JFrame {

    private final Usuario usuarioLogado;

    public MenuPrincipal(Usuario usuario) {
        initComponents();
        setLocationRelativeTo(null);

        this.usuarioLogado = usuario;

        lblBemVindo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBemVindo.setText("Bem-vindo, " + usuario.getNome());
        
        java.awt.EventQueue.invokeLater(() -> centralizarLabel());
        
        configurarBotoes();
    }
    
    private void centralizarLabel(){
        
        int larguraJanela = getContentPane().getWidth();
        int larguraTexto = lblBemVindo.getPreferredSize().width;

        int posicaoX = (larguraJanela - larguraTexto) / 2;

        lblBemVindo.setLocation(posicaoX, lblBemVindo.getY());
    }
    
    private void configurarBotoes() {

        btnCadastro.addActionListener(e -> {
            new Cadastros(usuarioLogado).setVisible(true);
            this.dispose();
        });

        btnEstoque.addActionListener(e -> {
            new EstoqueProduto(usuarioLogado).setVisible(true);
            this.dispose();
        });

        btnLogoff.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            this.dispose();
        });

        btnPedido.addActionListener(e -> {
            new RegistroPedido(usuarioLogado).setVisible(true);
            this.dispose();
        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBemVindo = new javax.swing.JLabel();
        btnLogoff = new javax.swing.JButton();
        btnCadastro = new javax.swing.JButton();
        btnPedido = new javax.swing.JButton();
        btnEstoque = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBemVindo.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblBemVindo.setForeground(new java.awt.Color(16, 43, 86));
        lblBemVindo.setText("Bem-vindo");
        getContentPane().add(lblBemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        btnLogoff.setBorderPainted(false);
        btnLogoff.setContentAreaFilled(false);
        btnLogoff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnLogoff, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 640, 230, 40));

        btnCadastro.setBorderPainted(false);
        btnCadastro.setContentAreaFilled(false);
        btnCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 310, 40));

        btnPedido.setBorderPainted(false);
        btnPedido.setContentAreaFilled(false);
        btnPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 310, 50));

        btnEstoque.setBorderPainted(false);
        btnEstoque.setContentAreaFilled(false);
        btnEstoque.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 310, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Menu Principal (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastro;
    private javax.swing.JButton btnEstoque;
    private javax.swing.JButton btnLogoff;
    private javax.swing.JButton btnPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBemVindo;
    // End of variables declaration//GEN-END:variables
}
