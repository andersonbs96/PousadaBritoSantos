
package telasPagamentos;

import conexao.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PagamentosVisualizar extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public PagamentosVisualizar() {
        initComponents();
        conexao = ModuloConexao.conector();
        Pagamentos();
    }

    private void Pagamentos(){
        try {
            String sql = "SELECT tabela_pagamentos.*, tabela_clientes.clientes_nome " +
                         "FROM tabela_pagamentos " +
                         "JOIN tabela_clientes ON tabela_pagamentos.pagamentos_clientesID = tabela_clientes.clientes_id " +
                         "JOIN tabela_reservas ON tabela_pagamentos.pagamentos_reservasID = tabela_reservas.reservas_id " ;
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()){
                int ID = rs.getInt("pagamentos_id");
                String dataReserva = rs.getString("pagamentos_dataReserva");
                int reservaID = rs.getInt("pagamentos_reservasID");
                int clienteID = rs.getInt("pagamentos_clientesID");
                String clienteNome = rs.getString("clientes_nome");
                double valorTotal = rs.getDouble("pagamentos_valorTotal");
                String situacao = rs.getString("pagamentos_situacao");
                
                DefaultTableModel modelo = (DefaultTableModel) tabelaPagamentos.getModel();
                modelo.addRow(new Object[]{ID, dataReserva, reservaID, clienteID, clienteNome, valorTotal, situacao});
                
                valorTotal = 0;
            }
            
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a procura!");
        }
    }
    
    private void BuscarNome(){
        String sql = "SELECT clientes_nome FROM tabela_clientes WHERE clientes_id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, ClienteID.getText());

            rs = pst.executeQuery();
            
            if (rs.next()){
                ClienteNome.setText(rs.getString(1));
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi buscar o nome do cliente!");
        }
    }
    
    public class CartaoValidador{
        public static boolean validarCartao(String cartaoStr){
            if (cartaoStr.length() != 16) {
                JOptionPane.showMessageDialog(null, "Cartão deve ter 16 dígitos. Tente novamente.");
                return false;
            }
            int soma = 0;
            boolean alternar = false;
            for (int i = cartaoStr.length() - 1; i >= 0; i--) {
                int digito = Integer.parseInt(cartaoStr.substring(i, i + 1));
                if (alternar) {
                    digito *= 2;
                    if (digito > 9) {
                        digito = (digito % 10) + 1;
                    }
                }
                soma += digito;
                alternar = !alternar;
            }
            return (soma % 10 == 0);
        }
    }
    
    private void ConfirmarPagamento(){
        String cartao = numeroCartao.getText();
        boolean cartaoValido = PagamentosVisualizar.CartaoValidador.validarCartao(cartao);
            if (!cartaoValido) {
                JOptionPane.showMessageDialog(null, "Numero de cartão inválido.");
                return;
            }        
        try {
            String sql = "INSERT INTO tabela_clientesPagamento (clientesPagamento_clientesID, clientesPagamento_metodo, clientesPagamento_numeroCartao,clientesPagamento_nomeCartao,clientesPagamento_validadeCartao,clientesPagamento_nomeBanco)" +
                     "VALUES" + 
                     "(?,?,?,?,?,?)";
            pst = conexao.prepareStatement(sql);
            
            int clienteID = Integer.parseInt(ClienteID.getText());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String validade = dateFormat.format(validadeCartao.getDate());
                    
            pst.setInt(1, clienteID);
            pst.setString(2, formaPagamento.getSelectedItem().toString());
            pst.setString(3, numeroCartao.getText());
            pst.setString(4, titularCartao.getText());
            pst.setString(5, validade);
            pst.setString(6, nomeBanco.getText());
            
            
            int add = pst.executeUpdate();
            
            if (add > 0){
                ClienteID.setText(null);
                formaPagamento.setSelectedItem(null);
                numeroCartao.setText(null);
                titularCartao.setText(null);
                validadeCartao.setDate(null);
                nomeBanco.setText(null);
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Pagamento não efetuado!");
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPagamentos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ClienteID = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        ClienteNome = new javax.swing.JTextField();
        formaPagamento = new javax.swing.JComboBox<>();
        labelNumero = new javax.swing.JLabel();
        numeroCartao = new javax.swing.JTextField();
        titularCartao = new javax.swing.JTextField();
        labelTitular = new javax.swing.JLabel();
        validadeCartao = new com.toedter.calendar.JDateChooser();
        labelValidade = new javax.swing.JLabel();
        nomeBanco = new javax.swing.JTextField();
        labelBanco = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setTitle("Pagamentos");

        tabelaPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data da Reserva", "ID da Reserva", "ID do Cliente", "Nome Cliente", "Valor Total", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
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
        tabelaPagamentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaPagamentos);
        if (tabelaPagamentos.getColumnModel().getColumnCount() > 0) {
            tabelaPagamentos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaPagamentos.getColumnModel().getColumn(1).setPreferredWidth(75);
            tabelaPagamentos.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabelaPagamentos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabelaPagamentos.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabelaPagamentos.getColumnModel().getColumn(5).setPreferredWidth(75);
            tabelaPagamentos.getColumnModel().getColumn(6).setPreferredWidth(90);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );

        jLabel7.setText("Insira os dados do cliente");

        jLabel8.setText("ID do Cliente");

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        formaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Escolha uma forma --", "Dinheiro", "Cartão de Crédito" }));

        labelNumero.setText("Numero do Cartão");

        labelTitular.setText("Titular do Cartão");

        validadeCartao.setDateFormatString("yyyy/MM/dd");

        labelValidade.setText("Validade do Cartão");

        labelBanco.setText("Banco");

        btnConfirmar.setText("CONFIRMAR");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        jLabel9.setText("Nome do Cliente");

        jLabel10.setText("Metodo de Pagamento");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 15, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelValidade)
                                            .addComponent(labelBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar))
                            .addComponent(ClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titularCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validadeCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titularCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTitular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(validadeCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBanco))
                .addGap(18, 18, 18)
                .addComponent(btnConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 321, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarNome();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        ConfirmarPagamento();
    }//GEN-LAST:event_btnConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ClienteID;
    private javax.swing.JTextField ClienteNome;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> formaPagamento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBanco;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelTitular;
    private javax.swing.JLabel labelValidade;
    private javax.swing.JTextField nomeBanco;
    private javax.swing.JTextField numeroCartao;
    private javax.swing.JTable tabelaPagamentos;
    private javax.swing.JTextField titularCartao;
    private com.toedter.calendar.JDateChooser validadeCartao;
    // End of variables declaration//GEN-END:variables
}
