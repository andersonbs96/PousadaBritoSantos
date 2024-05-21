package telasReservas;

import conexao.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReservasVisualizar extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public ReservasVisualizar() {
        initComponents();
        conexao = ModuloConexao.conector();
        Reserva();
    }
    
    private void Reserva(){
        try {
            String sql = "SELECT tabela_reservas.*, tabela_clientes.clientes_nome, tabela_quartos.quartos_numero " +
               "FROM tabela_reservas " +
               "JOIN tabela_clientes ON tabela_reservas.reservas_clientesID = tabela_clientes.clientes_id " +
               "JOIN tabela_quartos ON tabela_reservas.reservas_quartosID = tabela_quartos.quartos_id ";
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()){
                int ID = rs.getInt("reservas_id");
                String dataEntrada = rs.getString("reservas_dataEntrada");
                String dataSaida = rs.getString("reservas_dataSaida");
                int clienteID = rs.getInt("reservas_clientesID");
                String clienteNome = rs.getString("clientes_nome");
                int quartoID = rs.getInt("reservas_quartosID");
                String quartoNumero = rs.getString("quartos_numero");
                
                DefaultTableModel modelo = (DefaultTableModel) tabelaReservas.getModel();
                modelo.addRow(new Object[]{ID, dataEntrada, dataSaida, clienteID, clienteNome, quartoID, quartoNumero});
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível mostrar as reservas feitas!");
        }
    }
    
    private void BuscarReserva(){
        String sql = "SELECT tabela_reservas.*, tabela_clientes.clientes_nome, tabela_quartos.quartos_numero " +
               "FROM tabela_reservas " +
               "JOIN tabela_clientes ON tabela_reservas.reservas_clientesID = tabela_clientes.clientes_id " +
               "JOIN tabela_quartos ON tabela_reservas.reservas_quartosID = tabela_quartos.quartos_id " +
               "WHERE tabela_reservas.reservas_id = ? ";
        
        try {    
            pst = conexao.prepareStatement(sql);
            pst.setString(1, reservaID.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                reservaDataEntrada.setDate(rs.getDate(2));
                reservaDataSaida.setDate(rs.getDate(3));
                reservaClienteID.setText(rs.getString(4));
                reservaClienteNome.setText(rs.getString(6));
                reservaQuartoID.setText(rs.getString(5));
                reservaQuartoNumero.setText(rs.getString(7));
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível retornar alguma informação.");
        }
    }
    
    private void ChecarDisponibilidade(){
        String sql = "SELECT quartos_disponibilidade FROM tabela_quartos WHERE quartos_id =? AND quartos_disponibilidade = 'Disponivel'";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, reservaQuartoID.getText());
            rs = pst.executeQuery();
            
            if (rs.next()){
               JOptionPane.showMessageDialog(null, "Esse quarto está disponível");
            }
            else {
               JOptionPane.showMessageDialog(null, "Esse quarto não está disponivel!");
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível checar a disponibilidade do quarto!");
        }
    }
    
    private void BuscarNumeroQuarto(){
        String sql = "SELECT quartos_numero FROM tabela_quartos WHERE quartos_id =?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, reservaQuartoID.getText());
            rs = pst.executeQuery();
            
            if (rs.next()){
                reservaQuartoNumero.setText(rs.getString(1));
            }
            else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            }    
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não Possível o nome referente ao ID selecionado");
        }
    }
    
    private void ReservaAtualizar() {
        String sql = "UPDATE tabela_reservas SET reservas_dataEntrada, reservas_dataSaida, reservas_quartosID, reservas_clientesID WHERE reservas_id =? ";        
        
        try {
            pst = conexao.prepareStatement(sql);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String dataEntrada = dateFormat.format(reservaDataEntrada.getDate());
            String dataSaida = dateFormat.format(reservaDataSaida.getDate());
            
            int numeroClienteID = Integer.parseInt(reservaClienteID.getText());
            int numeroQuartoID = Integer.parseInt(reservaQuartoID.getText());
            
            pst.setString(1, dataEntrada);
            pst.setString(2, dataSaida);
            pst.setInt(3, numeroClienteID);
            pst.setInt(4, numeroQuartoID);
        }
        catch (Exception e) {
            
        }
    }
    
    private void ReservaCancelar() {
        int conf = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta reserva?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM tabela_reservas WHERE reservas_id=?";
            
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, reservaID.getText());
                int del = pst.executeUpdate();
                
                if (del > 0){
                    JOptionPane.showMessageDialog(null, "Reserva deletada com sucesso!");
                    reservaDataEntrada.setDate(null);
                    reservaDataSaida.setDate(null);
                    reservaClienteID.setText(null);
                    reservaClienteNome.setText(null);
                    reservaQuartoID.setText(null);
                    reservaQuartoNumero.setText(null);
                }
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "Não possível deletar essa reserva!");
            }
        }
    }
    
    private void ReservaSair() {
         this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reservaClienteID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        reservaClienteNome = new javax.swing.JTextField();
        btnAtualizarReserva = new javax.swing.JButton();
        btnCancelarReserva = new javax.swing.JButton();
        reservaDataEntrada = new com.toedter.calendar.JDateChooser();
        reservaDataSaida = new com.toedter.calendar.JDateChooser();
        reservaID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnBuscarReserva = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        reservaQuartoID = new javax.swing.JTextField();
        chkDisponibilidadeQuarto = new javax.swing.JButton();
        btnNumeroQuarto = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        reservaQuartoNumero = new javax.swing.JTextField();
        btnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaReservas = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("RESERVA");

        jLabel2.setText("Data de Entrada");

        jLabel3.setText("Data de Saida");

        jLabel5.setText("ID do cliente");

        reservaClienteID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservaClienteIDActionPerformed(evt);
            }
        });

        jLabel6.setText("Nome do Cliente");

        btnAtualizarReserva.setText("ATUALIZAR");
        btnAtualizarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarReservaActionPerformed(evt);
            }
        });

        btnCancelarReserva.setText("DELETAR");
        btnCancelarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarReservaActionPerformed(evt);
            }
        });

        reservaDataEntrada.setDateFormatString("yyyy/MM/dd");

        reservaDataSaida.setDateFormatString("yyyy/MM/dd");

        jLabel7.setText("Identificador da Reserva");

        btnBuscarReserva.setText("BUSCAR");
        btnBuscarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarReservaActionPerformed(evt);
            }
        });

        jLabel4.setText("ID do Quarto");

        chkDisponibilidadeQuarto.setText("CHECAR");
        chkDisponibilidadeQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDisponibilidadeQuartoActionPerformed(evt);
            }
        });

        btnNumeroQuarto.setText("BUSCAR");
        btnNumeroQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNumeroQuartoActionPerformed(evt);
            }
        });

        jLabel9.setText("Nº do Quarto");

        btnSair.setText("CANCELAR");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAtualizarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(reservaQuartoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reservaQuartoID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkDisponibilidadeQuarto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNumeroQuarto))
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(reservaClienteNome)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1)
                                .addComponent(jLabel5)
                                .addComponent(reservaClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(218, 218, 218)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reservaID, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarReserva))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reservaDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reservaDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reservaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarReserva))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reservaDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reservaDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservaClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservaClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reservaQuartoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDisponibilidadeQuarto)
                    .addComponent(btnNumeroQuarto))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservaQuartoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtualizarReserva)
                    .addComponent(btnCancelarReserva)
                    .addComponent(btnSair))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        tabelaReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data de Entrada", "Data de Saida", "ID do Cliente", "Nome do Cliente", "ID do Quarto", "Nº do Quarto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaReservas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaReservas);
        if (tabelaReservas.getColumnModel().getColumnCount() > 0) {
            tabelaReservas.getColumnModel().getColumn(0).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelaReservas.getColumnModel().getColumn(1).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabelaReservas.getColumnModel().getColumn(2).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(2).setPreferredWidth(60);
            tabelaReservas.getColumnModel().getColumn(3).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(3).setPreferredWidth(30);
            tabelaReservas.getColumnModel().getColumn(4).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(4).setPreferredWidth(150);
            tabelaReservas.getColumnModel().getColumn(5).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(5).setPreferredWidth(30);
            tabelaReservas.getColumnModel().getColumn(6).setResizable(false);
            tabelaReservas.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtualizarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarReservaActionPerformed
        ReservaAtualizar();
    }//GEN-LAST:event_btnAtualizarReservaActionPerformed

    private void btnCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarReservaActionPerformed
        ReservaCancelar();
    }//GEN-LAST:event_btnCancelarReservaActionPerformed

    private void chkDisponibilidadeQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDisponibilidadeQuartoActionPerformed
        ChecarDisponibilidade();
    }//GEN-LAST:event_chkDisponibilidadeQuartoActionPerformed

    private void btnBuscarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarReservaActionPerformed
        BuscarReserva();
    }//GEN-LAST:event_btnBuscarReservaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        ReservaSair();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnNumeroQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNumeroQuartoActionPerformed
        BuscarNumeroQuarto();
    }//GEN-LAST:event_btnNumeroQuartoActionPerformed

    private void reservaClienteIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservaClienteIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reservaClienteIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizarReserva;
    private javax.swing.JButton btnBuscarReserva;
    private javax.swing.JButton btnCancelarReserva;
    private javax.swing.JButton btnNumeroQuarto;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton chkDisponibilidadeQuarto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField reservaClienteID;
    private javax.swing.JTextField reservaClienteNome;
    private com.toedter.calendar.JDateChooser reservaDataEntrada;
    private com.toedter.calendar.JDateChooser reservaDataSaida;
    private javax.swing.JTextField reservaID;
    private javax.swing.JTextField reservaQuartoID;
    private javax.swing.JTextField reservaQuartoNumero;
    private javax.swing.JTable tabelaReservas;
    // End of variables declaration//GEN-END:variables
}
