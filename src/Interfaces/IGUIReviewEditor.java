package Interfaces;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;
import Visual.GUIReview;

/**
 * 
 * @author Jose Americo Nabuco Leva Ferreira de Freitas RA 105153
 * @author Douglas Afonso RA 104825
 *
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.IGUIReviewEditor>")
public interface IGUIReviewEditor extends ISupports {
	public String getReview();
	public void setReview(String review);
	public void atualizaFonte();
	public void atualizaLink();
	public void setPai(GUIReview pai);
	public void setVisible(boolean visibility);
}
