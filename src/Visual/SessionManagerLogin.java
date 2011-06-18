package Visual;

import java.awt.CardLayout;

import javax.swing.JOptionPane;

import Classes.User;

public class SessionManagerLogin extends javax.swing.JPanel {
    private Santanbooks parent;

    public SessionManagerLogin(Santanbooks p) {
        this.parent = p;
        initComponents();
    }

    private void initComponents() {
        loginTitle = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userText = new javax.swing.JTextField();
        pwordText = new javax.swing.JPasswordField();
        pwordLabel = new javax.swing.JLabel();
        noAccountLabel = new javax.swing.JLabel();
        signInButton = new javax.swing.JButton();
        registerLabel = new javax.swing.JLabel();
        logoPanel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 50));
        setMinimumSize(new java.awt.Dimension(800, 50));
        setPreferredSize(new java.awt.Dimension(800, 50));
        setLayout(null);

        loginTitle.setFont(new java.awt.Font("Tahoma", 1, 16));
        loginTitle.setText("Login");
        add(loginTitle);
        loginTitle.setBounds(470, 10, 50, 20);

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 10));
        userLabel.setText("Usuário");
        add(userLabel);
        userLabel.setBounds(530, 0, 70, 14);

        userText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextActionPerformed(evt);
            }
        });
        add(userText);
        userText.setBounds(530, 15, 90, 15);

        pwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwordTextActionPerformed(evt);
            }
        });
        add(pwordText);
        pwordText.setBounds(640, 15, 90, 15);

        pwordLabel.setFont(new java.awt.Font("Tahoma", 0, 10));
        pwordLabel.setText("Password");
        add(pwordLabel);
        pwordLabel.setBounds(640, 0, 43, 13);

        noAccountLabel.setText("Não possui uma conta?");
        add(noAccountLabel);
        noAccountLabel.setBounds(530, 35, 110, 14);

        signInButton.setText("Entrar");
        signInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signInButton.setIconTextGap(2);
        signInButton.setMargin(new java.awt.Insets(2, 8, 2, 8));
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });
        add(signInButton);
        signInButton.setBounds(740, 10, 50, 20);

        registerLabel.setForeground(new java.awt.Color(0, 0, 255));
        registerLabel.setText("Cadastre-se aqui");
        registerLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerLabelMouseClicked(evt);
            }
        });
        add(registerLabel);
        registerLabel.setBounds(646, 35, 82, 14);

        logoPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Santanbooks_Small.png"))); // NOI18N
        logoPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoPanel.setMaximumSize(new java.awt.Dimension(214, 45));
        logoPanel.setMinimumSize(new java.awt.Dimension(214, 45));
        logoPanel.setPreferredSize(new java.awt.Dimension(214, 45));
        logoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoPanelMouseClicked(evt);
            }
        });
        add(logoPanel);
        logoPanel.setBounds(10, 5, 210, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void userTextActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void pwordTextActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	User u = this.parent.getSessionManager().loginChallenge(userText.getText(), pwordText.getText());
    	
    	if (u != null) {
    		this.parent.setUser(u);
    		((SessionManagerLoggedIn)this.parent.getSM()).updateForm();
    		((CardLayout)this.parent.getSM().getLayout()).show(this.parent.getSM(), Santanbooks.SESSION_LOGGED);
    	}
    	else
    		JOptionPane.showMessageDialog(null, "Usuario e Senha inválidos!");
    }

    private void logoPanelMouseClicked(java.awt.event.MouseEvent evt) {
        parent.changePanel(Santanbooks.PANEL_HOME);
    }

    private void registerLabelMouseClicked(java.awt.event.MouseEvent evt) {
        parent.changePanel(Santanbooks.PANEL_REG_USER);
    }

    private javax.swing.JLabel loginTitle;
    private javax.swing.JLabel logoPanel;
    private javax.swing.JLabel noAccountLabel;
    private javax.swing.JLabel pwordLabel;
    private javax.swing.JPasswordField pwordText;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JButton signInButton;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables
}
