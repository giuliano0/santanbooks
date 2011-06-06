package Trabalho;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BancoDeDados implements IBancoDeDados{
	private String entidade;
	private String caminho;
	private boolean chaveUnica;
	private String id;
	private int contador;
	
	BancoDeDados(String entidade, boolean chaveUnica){
		this.entidade = entidade;
		this.chaveUnica = chaveUnica;
		this.caminho = "src/Trabalho/" + this.entidade + "/";
		this.id = null;
		this.contador = 0;
	}
	
	public boolean salvarEntidade(Entidade entidade) {
		String arquivo;
		
		if (entidade.getArquivo() == null){	
			if (this.chaveUnica)
				arquivo = entidade.getIdentificador() + ".xml";
			else{
				int contador = 1;
				arquivo = entidade.getIdentificador();
				while (new File(this.caminho + arquivo + contador + ".xml").exists())
					contador++;
			}
		
			entidade.setArquivo(arquivo);
			
			try{
				XMLEncoder encoder = new XMLEncoder(
		                new BufferedOutputStream(
		                    new FileOutputStream(this.caminho + arquivo)));
				encoder.writeObject(entidade);
				encoder.close();
			}
			catch(Exception e){
				return false;
			}
			return true;
		}
		else
			return false;
	}
	
	public boolean alterarEntidade(Entidade entidade){
		String arquivo;
		
		if (entidade.getArquivo() != null){	
			arquivo = entidade.getArquivo();
			
			try{
				XMLEncoder encoder = new XMLEncoder(
		                new BufferedOutputStream(
		                    new FileOutputStream(this.caminho + arquivo)));
				encoder.writeObject(entidade);
				encoder.close();
			}
			catch(Exception e){
				return false;
			}
			return true;
		}
		else
			return false;
	}

	public Entidade obterEntidade(String id) {
		Entidade entidade = null;
		String arquivo;
		
		if (this.chaveUnica){
			arquivo = id + ".xml";
		}
		else{
			this.contador = 1;
			arquivo = id + this.contador + ".xml";
		}
		
		if (new File(this.caminho + arquivo).exists()){
			try{
				XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
						new FileInputStream(this.caminho + arquivo)));
				entidade = (Entidade) decoder.readObject();
				decoder.close();
			}
			catch(Exception e){
				return null;
			}
			this.id = id;
			return entidade;
		}
		else
			return null;
	}

	public Entidade obterProximo() {
		Entidade entidade = null;
		String arquivo;
		
		if (!this.chaveUnica){
			if (this.id != null){
				arquivo = id + ++this.contador + ".xml";
				
				if (new File(this.caminho + arquivo).exists()){
					try{
						XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
								new FileInputStream(this.caminho + arquivo)));
						entidade = (Entidade) decoder.readObject();
						decoder.close();
					}
					catch(Exception e){
						return null;
					}
					return entidade;
				}
				else
					return null;
			}
			else
				return null;
		}
		else
			return null;
	}
}
