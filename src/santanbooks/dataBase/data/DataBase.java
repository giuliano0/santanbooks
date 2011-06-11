package Trabalho.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import Trabalho.Entity.Entity;

public class DataBase{
	protected String identifier;
	protected String folder;
	protected boolean uniqueKey;
	protected int cont;
	
	DataBase(){
		this.identifier = null;
		this.cont = 0;
	}
	
	public boolean insert(Entity entity){
		String fileName;
		
		if (entity.getFileName() == null){	
			if (this.uniqueKey)
				fileName = entity.getIdentifier() + ".xml";
			else{
				int cont = 1;
				fileName = entity.getIdentifier();
				while (new File(this.folder + fileName + cont + ".xml").exists())
					cont++;
			}
		
			entity.setFileName(fileName);
			
			try{
				XMLEncoder encoder = new XMLEncoder(
		                new BufferedOutputStream(
		                    new FileOutputStream(this.folder + fileName)));
				encoder.writeObject(entity);
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
	
	public boolean update(Entity entity){
		String fileName;
		
		if (entity.getFileName() != null){	
			fileName = entity.getFileName();
			
			try{
				XMLEncoder encoder = new XMLEncoder(
		                new BufferedOutputStream(
		                    new FileOutputStream(this.folder + fileName)));
				encoder.writeObject(entity);
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
	
	public Entity get(String identifier) {
		Entity entity = null;
		String fileName;
		
		if (this.uniqueKey){
			fileName = identifier + ".xml";
		}
		else{
			this.cont = 1;
			fileName = identifier + this.cont + ".xml";
		}
		
		if (new File(this.folder + fileName).exists()){
			try{
				XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
						new FileInputStream(this.folder + fileName)));
				entity = (Entity) decoder.readObject();
				decoder.close();
			}
			catch(Exception e){
				return null;
			}
			this.identifier = identifier;
			return entity;
		}
		else
			return null;
	}

	public Entity getNext() {
		Entity entity = null;
		String fileName;
		
		if (!this.uniqueKey){
			if (this.identifier != null){
				fileName = identifier + ++this.cont + ".xml";
				
				if (new File(this.folder + fileName).exists()){
					try{
						XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
								new FileInputStream(this.folder + fileName)));
						entity = (Entity) decoder.readObject();
						decoder.close();
					}
					catch(Exception e){
						return null;
					}
					return entity;
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
