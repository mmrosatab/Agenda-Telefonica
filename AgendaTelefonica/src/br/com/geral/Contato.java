package br.com.geral;

import java.io.Serializable;

public class Contato implements Serializable
{

	private String nome;
	private String telefone;
	private String celular;
	
	public String getNome() 
	{
		return nome;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public String getTelefone() 
	{
		return telefone;
	}
	public void setTelefone(String telefone) 
	{
		this.telefone = telefone;
	}
	public String getCelular() 
	{
		return celular;
	}
	public void setCelular(String celular) 
	{
		this.celular = celular;
	}
	
	
}
