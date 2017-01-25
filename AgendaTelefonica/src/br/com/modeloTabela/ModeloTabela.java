package br.com.modeloTabela;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import br.com.geral.Contato;

public class ModeloTabela extends AbstractTableModel
{

	private ArrayList<Contato> listaDeContatos;
	private final String[] colunas = {"Nome","Telefone","Celular"};

	public ModeloTabela()
	{
		listaDeContatos = new ArrayList<Contato>();
	}
	public void adicionarPessoa(Contato contato)
	{
		listaDeContatos.add(contato);
		fireTableDataChanged();
	}
	public void removerPessoa(int indiceLinha)
	{
		listaDeContatos.remove(indiceLinha);
		fireTableDataChanged();
	}
	
	public void atualizarTabela()
	{
		fireTableDataChanged();
	}
	public Contato getPessoa(int indiceLinha)
	{
		return listaDeContatos.get(indiceLinha);
	}
	
	public int getColumnCount() 
	{
		return colunas.length;
	}

	public int getRowCount() 
	{
		return listaDeContatos.size();
	}
	
	// metodo que altera o nome das colunas na tabela
	public String getColumnName(int indiceColuna)
	{
		return colunas[indiceColuna];
	}
	
	public Object getValueAt(int indiceLinha, int indiceColuna) 
	{
		switch(indiceColuna)
		{
			case 0:
				return listaDeContatos.get(indiceLinha).getNome();
			case 1:
				return listaDeContatos.get(indiceLinha).getTelefone();
			case 2:
				return listaDeContatos.get(indiceLinha).getCelular();
			default:
			throw new IndexOutOfBoundsException("Coluna Inv�lida!!!");
		}
	}

	public boolean isCellEditable(int row, int col) 
	{
		return true;
	}
	
	/*
	// modifica valor na propria linha
	public void setValueAt(Object value, int indiceLinha, int indiceColuna) {
		
		fireTableCellUpdated(indiceLinha, indiceColuna);
	}   
	*/

}
