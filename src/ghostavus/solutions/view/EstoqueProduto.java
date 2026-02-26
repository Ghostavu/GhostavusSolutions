package ghostavus.solutions.view;

import ghostavus.solutions.dao.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.Locale;
import ghostavus.solutions.model.Usuario;

public class EstoqueProduto extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    
    public EstoqueProduto(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        initComponents();
        centralizarTabela();
        configurarEventos();
    }

    
    private void configurarEventos() {
        btnVoltar.addActionListener(e -> voltarMenu());
        btnBuscar.addActionListener(e -> buscarEstoque());
    }

    private void voltarMenu() {
        new MenuPrincipal(usuarioLogado).setVisible(true);
        this.dispose();
    }

    private void buscarEstoque() {

        String produto = txtProduto.getText().trim();
        String fornecedor = txtFornecedor.getText().trim();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        String sql = """
            SELECT 
                p.id_produto,
                p.descricao,
                f.razao_social,
                p.quantidade_estoque,
                p.preco_venda
            FROM produtos p
            LEFT JOIN fornecedores f ON p.id_fornecedor = f.id_fornecedor
            WHERE 1=1
        """;

        if (!produto.isEmpty()) {
            sql += " AND p.id_produto = ?";
        }

        if (!fornecedor.isEmpty()) {
            sql += " AND f.id_fornecedor = ?";
        }

        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int index = 1;

            if (!produto.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(produto));
            }

            if (!fornecedor.isEmpty()) {
                stmt.setInt(index++, Integer.parseInt(fornecedor));
            }

            ResultSet rs = stmt.executeQuery();

            NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_produto"),
                        rs.getString("descricao"),
                        rs.getString("razao_social"),
                        rs.getInt("quantidade_estoque"),
                        moeda.format(rs.getDouble("preco_venda"))
                });
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Produto e Fornecedor devem ser números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao buscar estoque: " + e.getMessage());
        }
    }
    
    private void centralizarTabela() {

        javax.swing.table.DefaultTableCellRenderer centralizado =
            new javax.swing.table.DefaultTableCellRenderer();

        centralizado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        for (int i = 0; i < jTable1.getColumnCount(); i++) {
           jTable1.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtFornecedor = new javax.swing.JTextField();
        txtProduto = new javax.swing.JTextField();
        btnVoltar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Descrição Produto", "Fornecedor", "Quantidade Estoque", "Preço Venda"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 1260, 310));

        txtFornecedor.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtFornecedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 520, 60));

        txtProduto.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        getContentPane().add(txtProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 286, 380, 60));

        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 680, 330, 50));

        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 680, 310, 50));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Consulta de Estoque (1).png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtFornecedor;
    private javax.swing.JTextField txtProduto;
    // End of variables declaration//GEN-END:variables
}
