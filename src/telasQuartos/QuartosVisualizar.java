
package telasQuartos;

import conexao.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QuartosVisualizar extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public QuartosVisualizar() {
        initComponents();
        conexao = ModuloConexao.conector();
        Quartos();
    }

    public void Quartos(){
        try {
            String sql = "SELECT * FROM tabela_quartos";
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            // Preencher tabela com os dados dos quartos
            while (rs.next()) {
                int ID = rs.getInt("quartos_id");
                String Numero = rs.getString("quartos_numero");
                String Descricao = rs.getString("quartos_descricao");
                double Preco = rs.getDouble("quartos_preco");
                String Disponibilidade = rs.getString("quartos_disponibilidade");
                
                DefaultTableModel modelo = (DefaultTableModel) tabelaQuartos.getModel();
                modelo.addRow(new Object[]{ID, Numero, Descricao, Preco, Disponibilidade});
            }   
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaQuartos = new javax.swing.JTable();

        setClosable(true);

        tabelaQuartos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Numero", "Descrição", "Preço", "Disponibilidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaQuartos);
        if (tabelaQuartos.getColumnModel().getColumnCount() > 0) {
            tabelaQuartos.getColumnModel().getColumn(0).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaQuartos.getColumnModel().getColumn(1).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabelaQuartos.getColumnModel().getColumn(2).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(2).setPreferredWidth(350);
            tabelaQuartos.getColumnModel().getColumn(3).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabelaQuartos.getColumnModel().getColumn(4).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(4).setPreferredWidth(50);
        }
        tabelaQuartos.getAccessibleContext().setAccessibleParent(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tabelaQuartos;
    // End of variables declaration//GEN-END:variables

}
