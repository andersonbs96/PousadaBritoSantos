package telas;

import conexao.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class telaVisualizar extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public telaVisualizar() {
        initComponents();
        conexao = ModuloConexao.conector();
        Reservas();
    }
     
    public void Reservas(){
        try {
            String sql = "SELECT * FROM tabela_reserva";
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                String NumeroQuarto = rs.getString("fk_reservaQuarto");
                String DataEntrada = rs.getString("reserva_dataEntrada");
                String DataSaida = rs.getString("reserva_dataSaida");
                String Nome = rs.getString("reserva_nome");
                String CPF = rs.getString("reserva_cpf");
                String Endereco = rs.getString("reserva_endereco");
                String DDD = rs.getString("reserva_ddd");
                String Telefone = rs.getString("reserva_telefone");
                String Cidade = rs.getString("reserva_cidade");
                String Estado = rs.getString("reserva_uf");
                String Email = rs.getString("reserva_email");
                
                DefaultTableModel modelo = (DefaultTableModel) tabelaReservas.getModel();
                modelo.addRow(new Object[]{NumeroQuarto, DataEntrada, DataSaida, Nome, CPF, Endereco, DDD, Telefone, Cidade, Estado, Email});
            }   
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaReservas = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setTitle("Visualizar Reservas");

        tabelaReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero do Quarto'", "Data de Entrada", "Data de Saida", "Nome Completo", "CPF", "Endereço", "DDD", "Telefone", "Cidade", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaReservas);
        if (tabelaReservas.getColumnModel().getColumnCount() > 0) {
            tabelaReservas.getColumnModel().getColumn(0).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaReservas.getColumnModel().getColumn(1).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(1).setPreferredWidth(70);
            tabelaReservas.getColumnModel().getColumn(2).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabelaReservas.getColumnModel().getColumn(3).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabelaReservas.getColumnModel().getColumn(4).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(4).setPreferredWidth(70);
            tabelaReservas.getColumnModel().getColumn(5).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(5).setPreferredWidth(200);
            tabelaReservas.getColumnModel().getColumn(6).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(6).setPreferredWidth(30);
            tabelaReservas.getColumnModel().getColumn(7).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(7).setPreferredWidth(70);
            tabelaReservas.getColumnModel().getColumn(8).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(8).setPreferredWidth(200);
            tabelaReservas.getColumnModel().getColumn(9).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(9).setPreferredWidth(30);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaReservas;
    // End of variables declaration//GEN-END:variables
    
}
