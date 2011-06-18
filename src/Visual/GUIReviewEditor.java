/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GUIReviewEditor.java
 *
 * Created on 18/06/2011, 02:18:28
 */
package Visual;

import Interfaces.IGUIReviewEditor;
import anima.annotation.Component;
import anima.component.view.base.FrameComponentBase;

/**
 *
 * @author Douglas Afonso de Sousa Costa ra 104825
 * 
 * by NetBeans e Eclipse
 * 
 * Para chamá-lo:
 * 		new(); --> instancia a classe
 * 		setReview(String); --> passa a review anterior 	
 * 		setVisible(true); --> visivel na tela
 * 
 */
@Component(id="<http://purl.org/dcc/Visual.GUIReviewEditor>",
			provides = "<http://purl.org/dcc/Interfaces.IGUIReviewEditor>")
public class GUIReviewEditor extends FrameComponentBase implements IGUIReviewEditor {

    private static final long serialVersionUID = 4265040241121350177L;
    private GUIReview pai;
    
    public GUIReviewEditor() {
        initComponents();
        setResizable(false);
    }
    
    public void setPai(GUIReview pai){
    	this.pai = pai;
    }
    
    /**
     * Variaveis onde ficarao a review, a cor da fonte
     * e o ponteiro pro jframe que pega a url pra um link.
     */
    private String review=null;
    private String corFonte="#000000";
    GUIURLRequest link;
    
    /**
     * @return review
     */
    public String getReview(){
    	return review;
    }
    
    /**
     * Configura a review anterior para aparecer no campo de texto.
     */
    public void setReview(String review){
        this.review=review;
        text.setText(review);
    }
    
    /**
     * Atualiza a cor da fonte do texto selecionado.
     */
    public void atualizaFonte(){
        //<font size="3" color="red">Ã© nois!</font>
        text.replaceSelection("<font color=\""+ corFonte +"\">"+ text.getSelectedText() +"</font>");
    }
    
    /**
     * Atualiza a url do link criado.
     */
    public void atualizaLink(){
        text.replaceSelection("<a href=\""+ link.getURL() +"\">"+ text.getSelectedText() +"</a>");
    }
    

    /**
     * Configura componentes.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextPane();
        letterColor = new javax.swing.JComboBox();
        left = new javax.swing.JButton();
        center = new javax.swing.JButton();
        right = new javax.swing.JButton();
        justify = new javax.swing.JButton();
        bold = new javax.swing.JButton();
        italic = new javax.swing.JButton();
        underline = new javax.swing.JButton();
        url = new javax.swing.JButton();
        send = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(text);

        letterColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cor", "Preto", "Azul", "Vermelho", "Amarelo" }));
        letterColor.setName("Cor"); // NOI18N
        letterColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                letterColorActionPerformed(evt);
            }
        });

        left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/align_left.png"))); // NOI18N
        left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftActionPerformed(evt);
            }
        });

        center.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/align_center.png"))); // NOI18N
        center.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centerActionPerformed(evt);
            }
        });

        right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/align_right.png"))); // NOI18N
        right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightActionPerformed(evt);
            }
        });

        justify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/align_justify.png"))); // NOI18N
        justify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                justifyActionPerformed(evt);
            }
        });

        bold.setText("<html><b>B</b>");
        bold.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bold.setMargin(new java.awt.Insets(2, 5, 2, 5));
        bold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldActionPerformed(evt);
            }
        });

        italic.setText("<html><i>I</i>");
        italic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        italic.setMargin(new java.awt.Insets(2, 5, 2, 5));
        italic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                italicActionPerformed(evt);
            }
        });

        underline.setText("<html><u>U</u>");
        underline.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        underline.setMargin(new java.awt.Insets(2, 5, 2, 5));
        underline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                underlineActionPerformed(evt);
            }
        });

        url.setText("URL");
        url.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        url.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlActionPerformed(evt);
            }
        });

        send.setText("Enviar");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        cancel.setText("Cancelar");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(letterColor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(url, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(center, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(justify, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bold, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(italic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(underline, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bold)
                        .addComponent(url, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(center, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(right, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(justify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(italic, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(underline, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(letterColor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    /**
     * Evento de JComboBox para alterar a cor do texto.
     */
    private void letterColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_letterColorActionPerformed
        String cor = (String)letterColor.getSelectedItem();
        if(text.getSelectedText()!=null){
            if(cor.equalsIgnoreCase("preto")){
                corFonte="#000000";                
            }
            if(cor.equalsIgnoreCase("azul")){
                corFonte="#0000ff";
            }
            if(cor.equalsIgnoreCase("vermelho")){
                corFonte="#ff0000";
            }
            if(cor.equalsIgnoreCase("amarelo")){
                corFonte="#ffff00";
            }
            atualizaFonte();
            
        }
        
        
    /**
     * Eventos dos botoes.
     * Botoes para formatacao foram feitos com codigo html.    
     */
    }//GEN-LAST:event_letterColorActionPerformed

    private void leftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftActionPerformed
        if(text.getSelectedText()!=null)
            text.replaceSelection("<p align = \"LEFT\">" + text.getSelectedText() + "</p>");
    }//GEN-LAST:event_leftActionPerformed

    private void centerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centerActionPerformed
        if(text.getSelectedText()!=null)
            text.replaceSelection("<p align = \"CENTER\">" + text.getSelectedText() + "</p>");
    }//GEN-LAST:event_centerActionPerformed

    private void rightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightActionPerformed
        if(text.getSelectedText()!=null)
            text.replaceSelection("<p align = \"RIGHT\">" + text.getSelectedText() + "</p>");
    }//GEN-LAST:event_rightActionPerformed

    private void justifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_justifyActionPerformed
        if(text.getSelectedText()!=null)
            text.replaceSelection("<p align = \"JUSTIFY\">" + text.getSelectedText() + "</p>");
    }//GEN-LAST:event_justifyActionPerformed

    private void boldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldActionPerformed
        if(text.getSelectedText()!=null)
            text.replaceSelection("<b>" + text.getSelectedText() + "</b>");
    }//GEN-LAST:event_boldActionPerformed

    private void italicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_italicActionPerformed
        if(text.getSelectedText()!=null)
            text.replaceSelection("<i>" + text.getSelectedText() + "</i>");
    }//GEN-LAST:event_italicActionPerformed

    private void underlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_underlineActionPerformed
        if(text.getSelectedText()!=null)
            text.replaceSelection("<u>" + text.getSelectedText() + "</u>");
    }//GEN-LAST:event_underlineActionPerformed
    
    
    /**
     * Atribui o texto digitado a string review.
     */
    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        String x = text.getText();
    	if(x!=null) {
            pai.refresh(x);
            dispose();
        }
        
    }//GEN-LAST:event_sendActionPerformed
    
    
    /**
     * Cancela a edicao da review.
     * Dispose.
     */
    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_cancelActionPerformed

    /**
     * Instancia GUIURLRequest para criacao de um link.
     */
    private void urlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlActionPerformed
        link = new GUIURLRequest(this);
        link.setVisible(true);
        
    }//GEN-LAST:event_urlActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bold;
    private javax.swing.JButton cancel;
    private javax.swing.JButton center;
    private javax.swing.JButton italic;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton justify;
    private javax.swing.JButton left;
    private javax.swing.JComboBox letterColor;
    private javax.swing.JButton right;
    private javax.swing.JButton send;
    private javax.swing.JTextPane text;
    private javax.swing.JButton underline;
    private javax.swing.JButton url;
    // End of variables declaration//GEN-END:variables
}
