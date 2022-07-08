package Vistas;

import Modelo.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * @author Enith Vargas PB G53
 */
public class UserMenu extends javax.swing.JFrame {
    //1. Instancia de la clase Conexión;
    Conexion conexion = new Conexion ();
    Connection connection;
    //2. La librería Statement permite ejecutar los query SQL
    Statement st;
    ResultSet rs;
    //3. Creamos una instancia de la tabla de la interfaz
    DefaultTableModel contenidoTablaEmpleados;
    
    public UserMenu() {
        initComponents();
        setLocationRelativeTo(null);
        listarEmpleados();
    }
   
    //4. Método que trae todos los empleados existentes en la base de datos
    private void listarEmpleados(){
        String nombre = txtBuscarE.getText();
        if(nombre.isEmpty()){
            String queryConsulta = "SELECT * FROM empleados";
            //5. Intentar ejecutar el query y obtener una respuesta de la base de datos
            try{
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(queryConsulta);
                //6. Crear un objeto donde se almacene el resultado de la consulta
                Object[] empleados = new Object [6];
                //7. Actualizar la propiedad Model de la tabla
                contenidoTablaEmpleados = (DefaultTableModel)tblEmpleados.getModel();
                //8. Recorremos el resultado de la consulta del query
                while(rs.next()){
                    empleados[0] = rs.getInt("IdEmp");
                    empleados[1] = rs.getString("nombreEmp");
                    empleados[2] = rs.getString("apellidos");
                    empleados[3] = rs.getString("tipoDocumento");
                    empleados[4] = rs.getString("documento");
                    empleados[5] = rs.getString("correo");
                    //9. Creamos una fila dentro de la tabla por cada empleado que devuelve la consulta
                    contenidoTablaEmpleados.addRow(empleados);
                    System.out.println("id: " + empleados[0] + ", nombre: " + empleados[1] + " , apellido: " + empleados[2] + ", documento: "
			    + empleados[3] + " " + empleados[4] + ", correo: " + empleados[5]);
                    
                }
                
                tblEmpleados.setModel(contenidoTablaEmpleados);
            }catch(SQLException e){
                System.out.println("Error");
            }
        }else{
            String query = "SELECT * FROM `empleados` WHERE nombreEmp LIKE '%" + nombre + "%' OR apellidos LIKE '%" + nombre + "%';";
            try{
                connection = conexion.getConnection();
                //Creamos el queryConsulta
                st = connection.createStatement();
                //Ejecutamos el query que tiene la variable queryConsulta
                rs = st.executeQuery(query);
                //Creamos un objeto que almacenará todos los registros de empleados
                //que existen en la base de datos
                Object[] empleados = new Object [6];
                contenidoTablaEmpleados = (DefaultTableModel)tblEmpleados.getModel();
                //Mientras el resultado del queryCounsulta encuentre registros en la
                //base de datos se ingresa al while
                while(rs.next()){
                    empleados[0] = rs.getInt("IdEmp");
                    empleados[1] = rs.getString("nombreEmp");
                    empleados[2] = rs.getString("apellidos");
                    empleados[3] = rs.getString("tipoDocumento");
                    empleados[4] = rs.getString("documento");
                    empleados[5] = rs.getString("correo");
                    contenidoTablaEmpleados.addRow(empleados);
                    System.out.println(rs.getString("IdEmp") + " " + rs.getString("nombreEmp")+ " " + rs.getString("apellidos"));
                }
                tblEmpleados.setModel(contenidoTablaEmpleados);
            }catch(SQLException e){
                System.out.println("Error");
        }
        }      
    }
    
    // Cuando añado un nuevo empleado el proceso background es el siguiente:
    //1. Se almacena el nuevo empleado en la base de datos
    //2. Se eliminan los datos que tiene la tabla tblEmpleados
    //3. Se llama el método listarEmpleados para consultar nuevamente la bd
    //4. Se cargan los empleados en la tabla incluyendo el recién creado
    public void BorrarDatosTabla() {
	// for loop to deleted each element  on each row inside the table 
	for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
	    //removing each row inside the table with removeRow
	    contenidoTablaEmpleados.removeRow(i);
	    i -= 1;
	}
    }


    
    public void eliminarEmpleado(){
        //1. Identificamos la fila seleccionada en la tabla de Empleados
        int row = tblEmpleados.getSelectedRow();
        //2. Capturamos el id del empleado que corresponda a la fila seleccionada
        int id = Integer.parseInt(tblEmpleados.getValueAt(row, 0).toString());
        if (row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un empleado.", "", JOptionPane.WARNING_MESSAGE);
        }else{
            String query = "DELETE FROM empleados WHERE idEmp = " +id+ ";";
            System.out.println(query);
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnAddUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtBuscarE = new javax.swing.JTextField();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setLocationByPlatform(true);

        jTabbedPane2.addTab("Home", jTabbedPane1);

        jPanel4.setBackground(new java.awt.Color(102, 153, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/newUser.png"))); // NOI18N
        btnAddUser.setText("Add Employee");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EMPLOYEES DATA");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Number", "Name", "Last Name", "Document type", "Document", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleados);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/showUser.png"))); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Buscar");

        txtBuscarE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarEKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(jLabel1))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBuscarE, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(112, 112, 112)
                            .addComponent(btnAddUser))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnSearch)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Employees", jPanel3);
        jTabbedPane2.addTab("tab3", jTabbedPane3);
        jTabbedPane2.addTab("tab4", jTabbedPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        BorrarDatosTabla();
        listarEmpleados();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        //Creamos una instancia del diálogo
        AddUserForm addUserF = new AddUserForm(this, rootPaneCheckingEnabled);
        addUserF.setVisible(true);
        BorrarDatosTabla();
        listarEmpleados();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        // get information from row num
        int row = tblEmpleados.getSelectedRow();
        System.out.println("Fila seleccionada: " + row);

        //Validate data
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un  empleado ", " ", JOptionPane.WARNING_MESSAGE);

        } else {
            //Get employee (id, name, surname,  id type , id num , e-mail )
            int id = Integer.parseInt(tblEmpleados.getValueAt(row, 0).toString());
            // row 0 column 1 =  employee name
            String nombre = (String) tblEmpleados.getValueAt(row, 1);
            String apellidos = (String) tblEmpleados.getValueAt(row, 2);
            String tipoDocumento = (String) tblEmpleados.getValueAt(row, 3);
            String documento = (String) tblEmpleados.getValueAt(row, 4);
            String correo = (String) tblEmpleados.getValueAt(row, 5);
            System.out.println("id: " + id + ", empleado: " + nombre + " " + apellidos
                + ", tipo documento: " + tipoDocumento + ", numero: " + documento + ",  correo: " + correo);

            // instance for jDialog to show the selected employee info
            ShowUserForm showUserForm = new ShowUserForm(this, true);
            showUserForm.recibirDatos(id, nombre, apellidos, documento, correo);
            showUserForm.setVisible(true);

            //update data from table
            BorrarDatosTabla();
            listarEmpleados();

        }
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        BorrarDatosTabla();
        listarEmpleados();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void txtBuscarEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BorrarDatosTabla();
            listarEmpleados();
        }
    }//GEN-LAST:event_txtBuscarEKeyPressed

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
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(() -> {
	    new UserMenu().setVisible(true);
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtBuscarE;
    // End of variables declaration//GEN-END:variables

}
