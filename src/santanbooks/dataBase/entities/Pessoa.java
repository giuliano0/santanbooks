package santanbooks.dataBase.entities;

import santanbooks.dataBase.Entity;

public class Pessoa extends Entity{
	private String nome;
	private int idade;

	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setIdade(int idade){
		this.idade = idade;
	}
	
	public int getIdade(){
		return this.idade;
	}
	
}
