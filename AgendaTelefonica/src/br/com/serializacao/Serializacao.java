package br.com.serializacao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import br.com.geral.Contato;

public class Serializacao 
{
	
	public static void serializar(ArrayList<Contato> listaDeContatos)
	{
		try 
		{
		
			FileOutputStream arquivo = new FileOutputStream("baseDeDados.txt"); // criando aquivo
			ObjectOutputStream objeto = new ObjectOutputStream(arquivo);
			objeto.writeObject(listaDeContatos); // escrevendo no objeto
			objeto.flush();
			
			arquivo.close();
			objeto.close();
			
			System.out.println("Serializacao feita com sucesso!");
		} catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("Nao foi possivel serializar o objeto!");
		}
		
	}
	
	public static ArrayList<Contato> deserializar()
	{
		try 
		{
			
			ArrayList<Contato>listaDeContatos = new ArrayList<Contato>();
			FileInputStream arquivo = new FileInputStream("baseDeDados.txt"); 
			ObjectInputStream objeto = new ObjectInputStream(arquivo);
			listaDeContatos = (ArrayList<Contato>) objeto.readObject();
			
			arquivo.close();
			objeto.close();
			
			System.out.println("Deserializacao feita com sucesso!");
			
			return listaDeContatos;
		} catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println("Nao foi possivel deserializar o objeto!");
			return null;
			
		}
		
	}
	

}
