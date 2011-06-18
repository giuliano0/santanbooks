package Visual;

import java.awt.event.*;

public class Home extends javax.swing.JPanel implements ActionListener, KeyListener, MouseListener {
    private Santanbooks parent;

    public Home(Santanbooks p) {
        this.parent = p;
        initComponents();
    }

    private void initComponents() {

        logo = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        autoList = new javax.swing.JList();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(null);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("Santanbooks.png"))); // NOI18N
        add(logo);
        logo.setBounds(130, 170, 540, 110);

        searchField.setFont(new java.awt.Font("Tahoma", 0, 14));
        add(searchField);
        searchField.setBounds(140, 300, 420, 30);
        searchField.addKeyListener(this);

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        searchButton.setText("Buscar");
        add(searchButton);
        searchButton.setBounds(585, 300, 73, 30);
        searchButton.addActionListener(this);

        autoList.setVisible(false);
        autoList.setFont(new java.awt.Font("Tahoma", 0, 14));
        autoList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "<AUTOCOMPLETE>", "<AUTOCOMPLETE>", "<AUTOCOMPLETE>", "<AUTOCOMPLETE>", "<AUTOCOMPLETE>", "<AUTOCOMPLETE>" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        autoList.addMouseListener(this);
        
        jScrollPane1.setViewportView(autoList);

        add(jScrollPane1);
        jScrollPane1.setBounds(140, 330, 420, 154);
        jScrollPane1.setVisible(false);
    }

    private javax.swing.JList autoList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;

	public void actionPerformed(ActionEvent arg0) {
		if (!this.searchField.getText().equals("")) {
			((Results)parent.getPanel(Santanbooks.PANEL_RESULTS)).setBooks(
					parent.getSearchEngine().search(searchField.getText()));
			this.parent.changePanel(Santanbooks.PANEL_RESULTS);
		}
	}

	public void keyPressed(KeyEvent arg0) {}

	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {
		if (searchField.getText().length() >= 3) {
			autoList.setModel(new javax.swing.AbstractListModel() {
	            String[] strings = parent.getSearchEngine().autoComplete(searchField.getText());
	            public int getSize() { return strings.length; }
	            public Object getElementAt(int i) { return strings[i]; }
	        });
			
			jScrollPane1.setVisible(true);
			autoList.setVisible(true);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		searchField.setText((String)autoList.getSelectedValue());
		autoList.clearSelection();
		
		jScrollPane1.setVisible(false);
		autoList.setVisible(false);
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
