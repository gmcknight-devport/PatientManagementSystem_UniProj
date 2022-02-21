/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

/**
 *
 * @author Glenn McKnight
 */
public class DoctorGUI extends javax.swing.JFrame {

    /**
     * Creates new form DoctorGUI
     */
    public DoctorGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleJLabel = new javax.swing.JLabel();
        logoutJButton = new javax.swing.JButton();
        backgroundJTabbedPane = new javax.swing.JTabbedPane();
        homeJPanel = new javax.swing.JPanel();
        welcomeJLabel = new javax.swing.JLabel();
        userInfoJText = new javax.swing.JTextArea();
        detailsJLabel = new javax.swing.JLabel();
        messagesJLabel = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        messagesJList = new javax.swing.JList<>();
        deleteMessageJButton = new javax.swing.JButton();
        appointmentsJPanel = new javax.swing.JPanel();
        setAppointmentJLabel = new javax.swing.JLabel();
        appointmentDateJLabel = new javax.swing.JLabel();
        appointmentDateJSpinner = new javax.swing.JSpinner();
        appointmentTimeJLabel = new javax.swing.JLabel();
        appointmentTimeJCombo = new javax.swing.JComboBox<>();
        appointmentPatientJLabel = new javax.swing.JLabel();
        appointmentPatientJCombo = new javax.swing.JComboBox<>();
        createAppointmentJButton = new javax.swing.JButton();
        currentAppointmentsJLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        appointmentsJList = new javax.swing.JList<>();
        patientJPanel = new javax.swing.JPanel();
        patientJLabel = new javax.swing.JLabel();
        patientHistoryJCombo = new javax.swing.JComboBox<>();
        patientHistoryJabel = new javax.swing.JLabel();
        prescritionHistoryJLabel = new javax.swing.JLabel();
        addNotesJLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        addNotesJText = new javax.swing.JTextArea();
        addNotesJButton = new javax.swing.JButton();
        jscrollPane10 = new javax.swing.JScrollPane();
        patientHistoryJList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        prescriptionHistoryJList = new javax.swing.JList<>();
        medicinesJPanel = new javax.swing.JPanel();
        medicinesJLabel = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        medicinesJList = new javax.swing.JList<>();
        medicinePatientIDJLabel = new javax.swing.JLabel();
        medicinePatientJCombo = new javax.swing.JComboBox<>();
        prescribeMedicineJButton = new javax.swing.JButton();
        createMedicineJLabel = new javax.swing.JLabel();
        medNamJLabel = new javax.swing.JLabel();
        commonUserJLabel = new javax.swing.JLabel();
        medicineDosageJLabel = new javax.swing.JLabel();
        medNameJText = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        commonUsesJText = new javax.swing.JTextArea();
        dosageJText = new javax.swing.JTextField();
        createJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(680, 490));

        titleJLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleJLabel.setText("Indisposed Clinic");

        logoutJButton.setText("Log Out");

        welcomeJLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        welcomeJLabel.setText("Welcome Back!");

        userInfoJText.setEditable(false);
        userInfoJText.setColumns(20);
        userInfoJText.setRows(5);
        userInfoJText.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        detailsJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        detailsJLabel.setText("Your details:");

        messagesJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        messagesJLabel.setText("Messages");

        jScrollPane6.setViewportView(messagesJList);

        deleteMessageJButton.setText("Delete Message");

        javax.swing.GroupLayout homeJPanelLayout = new javax.swing.GroupLayout(homeJPanel);
        homeJPanel.setLayout(homeJPanelLayout);
        homeJPanelLayout.setHorizontalGroup(
            homeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(welcomeJLabel)
                    .addGroup(homeJPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(homeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteMessageJButton)
                            .addGroup(homeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(messagesJLabel)
                                .addComponent(userInfoJText, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                                .addComponent(detailsJLabel)
                                .addComponent(jScrollPane6)))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        homeJPanelLayout.setVerticalGroup(
            homeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeJLabel)
                .addGap(31, 31, 31)
                .addComponent(detailsJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userInfoJText, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagesJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteMessageJButton)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        backgroundJTabbedPane.addTab("Home", homeJPanel);

        setAppointmentJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        setAppointmentJLabel.setText("Appointment Setting");

        appointmentDateJLabel.setText("Appointment date:");

        appointmentDateJSpinner.setModel(new javax.swing.SpinnerDateModel());

        appointmentTimeJLabel.setText("Appointment time:");

        appointmentPatientJLabel.setText("Patient:");

        createAppointmentJButton.setText("Create Appointment");

        currentAppointmentsJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        currentAppointmentsJLabel.setText("Appointments");

        jScrollPane1.setViewportView(appointmentsJList);

        javax.swing.GroupLayout appointmentsJPanelLayout = new javax.swing.GroupLayout(appointmentsJPanel);
        appointmentsJPanel.setLayout(appointmentsJPanelLayout);
        appointmentsJPanelLayout.setHorizontalGroup(
            appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentsJPanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentAppointmentsJLabel)
                    .addComponent(setAppointmentJLabel)
                    .addGroup(appointmentsJPanelLayout.createSequentialGroup()
                        .addGroup(appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appointmentDateJLabel)
                            .addComponent(appointmentTimeJLabel)
                            .addComponent(appointmentPatientJLabel))
                        .addGap(38, 38, 38)
                        .addGroup(appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(appointmentPatientJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(appointmentTimeJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(appointmentDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createAppointmentJButton))
                    .addComponent(jScrollPane1))
                .addGap(28, 28, 28))
        );
        appointmentsJPanelLayout.setVerticalGroup(
            appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appointmentsJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentAppointmentsJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(setAppointmentJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appointmentDateJLabel)
                    .addComponent(appointmentDateJSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointmentTimeJLabel)
                    .addComponent(appointmentTimeJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(appointmentsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointmentPatientJLabel)
                    .addComponent(appointmentPatientJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createAppointmentJButton))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        backgroundJTabbedPane.addTab("Appointments", appointmentsJPanel);

        patientJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        patientJLabel.setText("Patient");

        patientHistoryJabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        patientHistoryJabel.setText("Patient History");

        prescritionHistoryJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        prescritionHistoryJLabel.setText("Prescription History");

        addNotesJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        addNotesJLabel.setText("Add Notes");

        addNotesJText.setColumns(20);
        addNotesJText.setRows(3);
        jScrollPane4.setViewportView(addNotesJText);

        addNotesJButton.setText("Add Notes");

        jscrollPane10.setViewportView(patientHistoryJList);

        jScrollPane2.setViewportView(prescriptionHistoryJList);

        javax.swing.GroupLayout patientJPanelLayout = new javax.swing.GroupLayout(patientJPanel);
        patientJPanel.setLayout(patientJPanelLayout);
        patientJPanelLayout.setHorizontalGroup(
            patientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patientJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                    .addComponent(patientHistoryJCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addNotesJButton))
                    .addComponent(jscrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, patientJPanelLayout.createSequentialGroup()
                        .addGroup(patientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(patientJLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientHistoryJabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addNotesJLabel, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prescritionHistoryJLabel, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        patientJPanelLayout.setVerticalGroup(
            patientJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(patientJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientHistoryJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(patientHistoryJabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prescritionHistoryJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addNotesJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addNotesJButton)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        backgroundJTabbedPane.addTab("Patients", patientJPanel);

        medicinesJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        medicinesJLabel.setText("Medicines");

        jScrollPane.setViewportView(medicinesJList);

        medicinePatientIDJLabel.setText("Patient ID");

        prescribeMedicineJButton.setText("Prescribe");
        prescribeMedicineJButton.setActionCommand("");

        createMedicineJLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        createMedicineJLabel.setText("New Medicine");

        medNamJLabel.setText("Medicine Name:");

        commonUserJLabel.setText("Common Uses:");

        medicineDosageJLabel.setText("Dosage: ");

        commonUsesJText.setColumns(20);
        commonUsesJText.setRows(3);
        jScrollPane5.setViewportView(commonUsesJText);

        createJButton.setText("Create Medicine");

        javax.swing.GroupLayout medicinesJPanelLayout = new javax.swing.GroupLayout(medicinesJPanel);
        medicinesJPanel.setLayout(medicinesJPanelLayout);
        medicinesJPanelLayout.setHorizontalGroup(
            medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicinesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(medicinesJPanelLayout.createSequentialGroup()
                        .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                            .addGroup(medicinesJPanelLayout.createSequentialGroup()
                                .addComponent(medicinesJLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(medicinesJPanelLayout.createSequentialGroup()
                                .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(medicinePatientIDJLabel)
                                    .addComponent(medNamJLabel)
                                    .addComponent(medicineDosageJLabel))
                                .addGap(37, 37, 37)
                                .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(medNameJText)
                                    .addComponent(medicinePatientJCombo, 0, 170, Short.MAX_VALUE)
                                    .addComponent(dosageJText))
                                .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(medicinesJPanelLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(commonUserJLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane5))
                                    .addGroup(medicinesJPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(prescribeMedicineJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicinesJPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(createJButton)))
                        .addGap(17, 17, 17))
                    .addGroup(medicinesJPanelLayout.createSequentialGroup()
                        .addComponent(createMedicineJLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        medicinesJPanelLayout.setVerticalGroup(
            medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicinesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(medicinesJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prescribeMedicineJButton)
                    .addComponent(medicinePatientJCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicinePatientIDJLabel))
                .addGap(18, 18, 18)
                .addComponent(createMedicineJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(medicinesJPanelLayout.createSequentialGroup()
                        .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medNamJLabel)
                            .addComponent(medNameJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(commonUserJLabel))
                        .addGap(18, 18, 18)
                        .addGroup(medicinesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medicineDosageJLabel)
                            .addComponent(dosageJText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createJButton)
                .addContainerGap())
        );

        backgroundJTabbedPane.addTab("Medicines", medicinesJPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleJLabel)
                .addGap(161, 161, 161)
                .addComponent(logoutJButton)
                .addContainerGap())
            .addComponent(backgroundJTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoutJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backgroundJTabbedPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DoctorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorGUI().setVisible(true);
            }
        });
    }
    /**
     * Displays a JOptionPane to provide information to the user when required. 
     * @param message to be displayed.
     */
    public void displayMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    /**
     * Set the data in the patient JCombos. 
     * @param patientIDs list of patIDs to be displayed.
     */    
    public void setPatientCombos(List<String> patientIDs){
        patientIDs.forEach((s) -> {
            appointmentPatientJCombo.addItem(s);
            medicinePatientJCombo.addItem(s);
        });
    }         
    ///////////////////////////////////////////////////////////////////////////
    //Home page
    /**
     * Register button handler to respond to logout button.
     * @param controller to respond to event.
     */
    public void addLogoutButtonHandler(ActionListener controller){
        logoutJButton.addActionListener(controller);
    }
    /**
     * Sets text box with logged in user information.
     * @param info about logged in user.
     */
    public void setDoctorInfo(String info){
        userInfoJText.setText(info);
    }
    /**
     * Set the messages the user has received to the messageJList.
     * @param messages of user to be displayed.
     */
    public void setUserMessages(List<String> messages){
        String[] allMessages = new String[messages.size()]; 
        
        for(int i = 0; i < messages.size(); i++){
            allMessages[i] = messages.get(i);
        }
        
        messagesJList.setListData(allMessages);
    }
    /**
     * Register button handler for delete message button.
     * @param controller to respond to event.
     */
    public void addDeleteMessageButtonHandler(ActionListener controller){
        deleteMessageJButton.addActionListener(controller);
    }
    /**
     * Get the index of the selected message to be deleted.
     * @return 
     */
    public int getMessageToDeleteIndex(){
        return messagesJList.getSelectedIndex();
    }
    ////////////////////////////////////////////////////////////////////////////
    //Appointments page
    /**
     * Sets JList of appointments booked for user.
     * @param appointments array of appointments.
     */
    public void setAppointmentsJList(String[] appointments){
        appointmentsJList.setListData(appointments);
    }
    /**
     * Sets format of date spinner to local date to avoid confusion between 
     * different potential date formats.
     */
    public void setAppointmentDateSpinnerFormat(){
        
        try{
            SimpleDateFormat format;
            format = ((JSpinner.DateEditor) appointmentDateJSpinner.getEditor()).getFormat();
            format.applyPattern("dd/MM/yy");
            appointmentDateJSpinner.commitEdit();
            
        } catch(ParseException ex){
            displayMessage("Appointment booking date format error");
        }
    }
    /**
     * Fills JComboBox with possible appointment times.
     * @param times to be set.
     */
    public void setAppointmentTimeCombo(List<String> times){
        times.forEach((s) -> {
            appointmentTimeJCombo.addItem(s);
        });
    }
    /**
     * Return selected appointment date from JSpinner.
     * @return date selected.
     */
    public LocalDate getAppointmentDate(){
        try{            
            Date date = (Date)appointmentDateJSpinner.getValue();
            LocalDate returnDate = date.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
            
            returnDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return returnDate;
            
        } catch(ClassCastException ex){
            displayMessage("Couldn't get appointment date");            
            return null;
        }
    }
    /**
     * Get selected time for appointment.
     * @return appointment time requested.
     */
    public String getAppointmentTime(){
        return appointmentTimeJCombo.getSelectedItem().toString();
    }
    /**
     * Return the patient which the appointment is to be booked for.
     * @return selected patient.
     */
    public String getAppointmentPatient(){
        return appointmentPatientJCombo.getSelectedItem().toString();
    }    
    /**
     * Register button hander for appointment creation button.
     * @param controller to respond to event. 
     */
    public void addCreateAppointmentButtonHandler(ActionListener controller){
        createAppointmentJButton.addActionListener(controller);
    }
    ////////////////////////////////////////////////////////////////////////////
    //Patients page
    /**
     * Fills JCombos with patientIDs
     * @param patients list of IDs to be set.
     */
    public void setPatientIDJCombos(List<String> patients){
        for(String s : patients){
            patientHistoryJCombo.addItem(s);
        }
    }
    /**
     * Get the patient selected to display their appointment history. 
     * @return 
     */
    public String getPatientHistoryID(){
        return patientHistoryJCombo.getSelectedItem().toString();
    }
    /**
     * Display the patient history that had been selected by the JCombo Box.
     * @param history to be set for patient.
     */
    public void setPatientHistory(String[] history){
        patientHistoryJList.setListData(history);
    }
    /**
     * Display the patient prescription history that had been selected by the JCombo Box.
     * @param history to be set for patient.
     */
    public void setPrescriptionHistory(String[] history){
        prescriptionHistoryJList.setListData(history);
    }
    /**
     * Get any added notes for the patient.
     * @return notes about patient.
     */
    public String getNotes(){
        return addNotesJText.getText() + 
                LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }
    /**
     * Clears text from notes JTextField so more can be made about another patient.
     */
    public void clearNotesJText(){
        addNotesJText.setText("");
    }
    /**
     * Register Button Handler for the patient JComboBox. 
     * @param controller to respond to event.
     */
    public void addPatientJComboListener(ActionListener controller){
        patientHistoryJCombo.addActionListener(controller);
    }
    /**
     * Register button handler for the add notes JButton.
     * @param controller to respond to event.
     */
    public void addNotesButtonHandler(ActionListener controller){
        addNotesJButton.addActionListener(controller);
    }
    ////////////////////////////////////////////////////////////////////////////
    //Medicines page
    /**
     * Display a list of available and information about them in a JList.
     * @param medicines to be displayed.
     */
    public void setMedicinesJList(String[] medicines){
        for(String s : medicines){
            medicinesJList.setListData(medicines);
        }
    }
    /**
     * Get the index of the selected medicine from the MedicinesJList.
     * @return selected medicine index. 
     */
    public int getMedicinesIndex(){
        return medicinesJList.getSelectedIndex();       
    }
    /**
     * Get the selected medicine value from the medicinesJlist/
     * @return selected medicine.
     */
    public String getPrescribedMedicine(){
        return medicinesJList.getSelectedValue();
    }
    /**
     * Get the ID of the patient the medicine is to be prescribed to.
     * @return patient ID to be given medicine.
     */
    public String getMedicinesPatientID(){
        return medicinePatientJCombo.getSelectedItem().toString();
    }
    /**
     * Register Button Handler for add prescription button.
     * @param controller to respond to event.
     */
    public void addPrescribeButtonHandler(ActionListener controller){
        prescribeMedicineJButton.addActionListener(controller);
    }
    /**
     * Get medicine name to be added.
     * @return medicine name.
     */
    public String getMedName(){
        return medNameJText.getText();
    }
    /**
     * Get Medicine dosage to be added.
     * @return medicine dosage.
     */
    public String getMedDosage(){
        return dosageJText.getText();
    }
    /**
     * Get common uses to be added.
     * @return common uses text.
     */
    public String getCommonUses(){
        return commonUsesJText.getText();
    }
    /**
     * Register button handler for create medicine button.
     * @param controller to respond to event.
     */
    public void addCreateMedicineButtonHandler(ActionListener controller){
        createJButton.addActionListener(controller);
    }
    /**
     * Clear all create medicine fields so another one can be created if required.
     */
    public void clearCreateMedicineFields(){
        medNameJText.setText("");
        dosageJText.setText("");
        commonUsesJText.setText("");
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNotesJButton;
    private javax.swing.JLabel addNotesJLabel;
    private javax.swing.JTextArea addNotesJText;
    private javax.swing.JLabel appointmentDateJLabel;
    private javax.swing.JSpinner appointmentDateJSpinner;
    private javax.swing.JComboBox<String> appointmentPatientJCombo;
    private javax.swing.JLabel appointmentPatientJLabel;
    private javax.swing.JComboBox<String> appointmentTimeJCombo;
    private javax.swing.JLabel appointmentTimeJLabel;
    private javax.swing.JList<String> appointmentsJList;
    private javax.swing.JPanel appointmentsJPanel;
    private javax.swing.JTabbedPane backgroundJTabbedPane;
    private javax.swing.JLabel commonUserJLabel;
    private javax.swing.JTextArea commonUsesJText;
    private javax.swing.JButton createAppointmentJButton;
    private javax.swing.JButton createJButton;
    private javax.swing.JLabel createMedicineJLabel;
    private javax.swing.JLabel currentAppointmentsJLabel;
    private javax.swing.JButton deleteMessageJButton;
    private javax.swing.JLabel detailsJLabel;
    private javax.swing.JTextField dosageJText;
    private javax.swing.JPanel homeJPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jscrollPane10;
    private javax.swing.JButton logoutJButton;
    private javax.swing.JLabel medNamJLabel;
    private javax.swing.JTextField medNameJText;
    private javax.swing.JLabel medicineDosageJLabel;
    private javax.swing.JLabel medicinePatientIDJLabel;
    private javax.swing.JComboBox<String> medicinePatientJCombo;
    private javax.swing.JLabel medicinesJLabel;
    private javax.swing.JList<String> medicinesJList;
    private javax.swing.JPanel medicinesJPanel;
    private javax.swing.JLabel messagesJLabel;
    private javax.swing.JList<String> messagesJList;
    private javax.swing.JComboBox<String> patientHistoryJCombo;
    private javax.swing.JList<String> patientHistoryJList;
    private javax.swing.JLabel patientHistoryJabel;
    private javax.swing.JLabel patientJLabel;
    private javax.swing.JPanel patientJPanel;
    private javax.swing.JButton prescribeMedicineJButton;
    private javax.swing.JList<String> prescriptionHistoryJList;
    private javax.swing.JLabel prescritionHistoryJLabel;
    private javax.swing.JLabel setAppointmentJLabel;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JTextArea userInfoJText;
    private javax.swing.JLabel welcomeJLabel;
    // End of variables declaration//GEN-END:variables
}
