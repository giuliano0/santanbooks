package Visual;

public class Results extends javax.swing.JPanel {
    private Santanbooks parent;
    private Classes.Book[] resultados;

    public Results(Santanbooks p) {
        this.parent = p;
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(50, 50));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(null);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(null);

        jTextField1.setFont(new java.awt.Font("Book Antiqua", 0, 14));
        jTextField1.setText("Digite aqui sua pesquisa");
        jPanel1.add(jTextField1);
        jTextField1.setBounds(50, 50, 610, 30);

        jButton1.setFont(new java.awt.Font("Book Antiqua", 1, 14));
        jButton1.setText("Pesquisar");
        jButton1.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jPanel1.add(jButton1);
        jButton1.setBounds(680, 50, 100, 30);

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        jLabel1.setText("Pesquisar novamente:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(50, 20, 300, 24);

        jList1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList1.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14", "Item 15", "Item 16" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jList1.setVisibleRowCount(15);
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(50, 140, 700, 390);

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 0, 18));
        jLabel2.setText("Resultados encontrados:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 110, 500, 24);

        jButton3.setFont(new java.awt.Font("Book Antiqua", 1, 12));
        jButton3.setText("Adicionar novo livro");
        jButton3.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jPanel1.add(jButton3);
        jButton3.setBounds(640, 540, 140, 25);

        jButton4.setFont(new java.awt.Font("Book Antiqua", 0, 12));
        jButton4.setText("Voltar");
        jPanel1.add(jButton4);
        jButton4.setBounds(20, 540, 63, 25);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 600);
    }
    
    public void setBooks(Classes.Book[] search) {
    	this.resultados = search;
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
}
