
package telasReservas;

import com.toedter.calendar.JDateChooser;
import conexao.ModuloConexao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReservasCadastrar extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public ReservasCadastrar() {
        initComponents();
        conexao = ModuloConexao.conector();
        Quartos();
        Clientes();
    }
    
    private void Quartos (){
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
    
    private void Clientes () {
        try {
            String sql = "SELECT * FROM tabela_clientes";
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            // Preencher tabela com os dados dos quartos
            while (rs.next()) {
                int ID = rs.getInt("clientes_id");
                String Nome = rs.getString("clientes_nome");
                String CPF = rs.getString("clientes_cpf");
                String Endereco = rs.getString("clientes_endereco");
                String DDD = rs.getString("clientes_ddd");
                String Telefone = rs.getString("clientes_telefone");
                String Cidade = rs.getString("clientes_cidade");
                String Estado = rs.getString("clientes_estado");
                String Email = rs.getString("clientes_email");
                
                DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
                modelo.addRow(new Object[]{ID, Nome, CPF, Endereco, DDD, Telefone, Cidade, Estado, Email});
            }   
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ReservaCadastrar(){
        
        try { 
            int pagar = JOptionPane.showConfirmDialog(null, "O cliente vai pagar agora?", "Aviso!", JOptionPane.YES_NO_OPTION);
            
            if (pagar == JOptionPane.YES_OPTION){
                String sql = "INSERT INTO tabela_reservas (reservas_dataEntrada, reservas_dataSaida, reservas_clientesID, reservas_quartosID) VALUES (?,?,?,?)";
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

                int add = pst.executeUpdate();

                if (add > 0){
                    rs = pst.getGeneratedKeys();
                    if (rs.next()){
                        int numeroReservaID = rs.getInt(1);
                        
                        String sqlQuarto = "SELECT quartos_preco FROM tabelas_quartos WHERE quartos_id=?";
                        pst = conexao.prepareStatement(sqlQuarto);
                        pst.setInt(1, numeroQuartoID);
                        rs = pst.executeQuery();
                        if (rs.next()){
                            double precoQuarto = rs.getDouble("quartos_preco");
                            int diferencaDias = reservaDataSaida.getDate().compareTo(reservaDataEntrada.getDate());
                            double valorTotal = precoQuarto * diferencaDias;
                            
                            String sqlPagamento = "INSERT INTO tabela_pagamentos (pagamentos_clienteID, pagamentos_reservasID, pagamentos_valorTotal, pagamentos_situacao) VALUES (?,?,?,?)";
                            pst = conexao.prepareStatement(sqlPagamento);
                            pst.setInt(1, numeroClienteID);
                            pst.setInt(2, numeroReservaID);
                            pst.setBigDecimal(3, BigDecimal.valueOf(valorTotal));
                            pst.setString(4, "Pago");
                        
                            int addPagamento = pst.executeUpdate();
                            if (addPagamento > 0){
                                JOptionPane.showMessageDialog(null, "Reserva e pagamento efetuados com sucesso!");
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"Reserva efetuada, mas não foi possível realizar o pagamento.");
                            }
                        }
                         else {
                            JOptionPane.showMessageDialog(null, "Não foi possível obter o preço do quarto!");
                        }
                    }
                else {
                    JOptionPane.showMessageDialog(null, "Não foi possível realizar a reserva");
                }   
            }
            else {
                JOptionPane.showMessageDialog(null, "Reserva efetuada sem pagamento.");
                }   
            }       
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao reservar!");
        }
    }
    
    public void BuscarClienteNome(){
        String sql = "SELECT clientes_nome FROM tabela_clientes WHERE clientes_id =?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, reservaClienteID.getText());
            rs = pst.executeQuery();
            
            if (rs.next()){
                reservaClienteNome.setText(rs.getString(1));
            }
            else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            }    
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não Possível o nome referente ao ID selecionado");
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
    
    private void ReservaSair(){
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reservaClienteID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        reservaClienteNome = new javax.swing.JTextField();
        btnReservarCliente = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        reservaQuartoID = new javax.swing.JTextField();
        reservaQuartoNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnNumeroQuarto = new javax.swing.JButton();
        chkDisponibilidadeQuarto = new javax.swing.JButton();
        reservaDataEntrada = new com.toedter.calendar.JDateChooser();
        reservaDataSaida = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaQuartos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();

        setClosable(true);
        setMaximizable(true);
        setMinimumSize(new java.awt.Dimension(1130, 537));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("RESERVA");

        jLabel2.setText("Data de Entrada");

        jLabel3.setText("Data de Saida");

        jLabel4.setText("Escolha o Quarto");

        jLabel5.setText("Digite o ID do cliente");

        jLabel6.setText("Nome do Cliente");

        btnBuscarCliente.setText("BUSCAR");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnReservarCliente.setText("FAZER RESERVA");
        btnReservarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarClienteActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel9.setText("Nº do Quarto");

        btnNumeroQuarto.setText("BUSCAR");
        btnNumeroQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNumeroQuartoActionPerformed(evt);
            }
        });

        chkDisponibilidadeQuarto.setText("CHECAR");
        chkDisponibilidadeQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDisponibilidadeQuartoActionPerformed(evt);
            }
        });

        reservaDataEntrada.setDateFormatString("yyyy/MM/dd");

        reservaDataSaida.setDateFormatString("yyyy/MM/dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnReservarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reservaClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarCliente))
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(reservaQuartoNumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(reservaQuartoID, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(12, 12, 12)
                        .addComponent(chkDisponibilidadeQuarto)
                        .addGap(18, 18, 18)
                        .addComponent(btnNumeroQuarto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(reservaDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reservaDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(reservaClienteNome))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reservaDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reservaDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reservaQuartoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNumeroQuarto)
                    .addComponent(chkDisponibilidadeQuarto))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservaQuartoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reservaClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reservaClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReservarCliente)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabelaQuartos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nº Quarto", "Descrição", "Valor Diario", "Disponivel"
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
            tabelaQuartos.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelaQuartos.getColumnModel().getColumn(1).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(1).setPreferredWidth(30);
            tabelaQuartos.getColumnModel().getColumn(2).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(2).setPreferredWidth(120);
            tabelaQuartos.getColumnModel().getColumn(3).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(3).setPreferredWidth(60);
            tabelaQuartos.getColumnModel().getColumn(4).setResizable(false);
            tabelaQuartos.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("QUARTOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("CLIENTES");

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "Endereço", "DDD", "Telefone", "Cidade", "Estado", "E-mail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelaClientes);
        if (tabelaClientes.getColumnModel().getColumnCount() > 0) {
            tabelaClientes.getColumnModel().getColumn(0).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabelaClientes.getColumnModel().getColumn(1).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabelaClientes.getColumnModel().getColumn(2).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(2).setPreferredWidth(60);
            tabelaClientes.getColumnModel().getColumn(3).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabelaClientes.getColumnModel().getColumn(4).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(4).setPreferredWidth(30);
            tabelaClientes.getColumnModel().getColumn(5).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(5).setPreferredWidth(60);
            tabelaClientes.getColumnModel().getColumn(6).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(6).setPreferredWidth(100);
            tabelaClientes.getColumnModel().getColumn(7).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(7).setPreferredWidth(30);
            tabelaClientes.getColumnModel().getColumn(8).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(8).setPreferredWidth(120);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 707, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarClienteActionPerformed
        ReservaCadastrar();
    }//GEN-LAST:event_btnReservarClienteActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ReservaSair();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNumeroQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNumeroQuartoActionPerformed
       BuscarNumeroQuarto();
    }//GEN-LAST:event_btnNumeroQuartoActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
       BuscarClienteNome();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void chkDisponibilidadeQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDisponibilidadeQuartoActionPerformed
        ChecarDisponibilidade();
    }//GEN-LAST:event_chkDisponibilidadeQuartoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNumeroQuarto;
    private javax.swing.JButton btnReservarCliente;
    private javax.swing.JButton chkDisponibilidadeQuarto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField reservaClienteID;
    private javax.swing.JTextField reservaClienteNome;
    private com.toedter.calendar.JDateChooser reservaDataEntrada;
    private com.toedter.calendar.JDateChooser reservaDataSaida;
    private javax.swing.JTextField reservaQuartoID;
    private javax.swing.JTextField reservaQuartoNumero;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTable tabelaQuartos;
    // End of variables declaration//GEN-END:variables
}
