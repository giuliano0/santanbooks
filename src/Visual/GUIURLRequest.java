package Visual;

import java.awt.event.MouseEvent;

/**
 * Opens a frame that requests the user an
 *  URL that a selected link will lead to
 *  
 * @author Jose Americo Nabuco Leva Ferreira de Freitas ra 105153
 * @author Douglas Afonso de Sousa Costa ra 104825
 */
public class GUIURLRequest extends javax.swing.JFrame {
    private GUIReviewEditor parent;
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 * Sets the window properties
	 */
    public GUIURLRequest(GUIReviewEditor p) {
        this.parent = p;
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /** 
     * Sets components
     */
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("Cancel");
        jButton1.addMouseListener(new java.awt.event.MouseListener() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
        });

        jButton2.setText("OK");
        jButton2.addMouseListener(new java.awt.event.MouseListener() {
			public void mouseClicked(MouseEvent e) {
				URL = jTextField1.getText();
                                parent.atualizaLink();
                                dispose();
			}                        
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
        });

        jLabel1.setText("Qual o destino do link?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }
    
    /**
     * Returns the link's destiny
     * @return String
     */
    public String getURL(){
    	return URL;
    }
    
    
    /**
     * Main - testing
     * @param args the command line arguments
     */
    private String URL;
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}
