package br.com.grafico;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.geral.Contato;
import br.com.modeloTabela.ModeloTabela;
import br.com.serializacao.Serializacao;

public class Janela extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTable tabela;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldCelular;
	private JScrollPane scrollPane;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnAlterar;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblCelular;
	private ModeloTabela modelo;
	private static ArrayList<Contato> listaDeContatos;
	

	public Janela() 
	{
		super("Agenda Telefonica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,400);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 424, 160);
		contentPane.add(scrollPane);
		
		tabela = new JTable();
		modelo = new ModeloTabela();
		tabela.setModel(modelo);
		scrollPane.setViewportView(tabela);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Showcard Gothic", Font.PLAIN, 9));
		btnAdicionar.setBounds(10, 309, 89, 23);
		contentPane.add(btnAdicionar);
		
		btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Showcard Gothic", Font.PLAIN, 9));
		btnRemover.setBounds(109, 309, 89, 23);
		contentPane.add(btnRemover);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Showcard Gothic", Font.PLAIN, 9));
		btnAlterar.setBounds(208, 309, 89, 23);
		contentPane.add(btnAlterar);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Showcard Gothic", Font.PLAIN, 9));
		lblNome.setBounds(10, 217, 46, 14);
		contentPane.add(lblNome);
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Showcard Gothic", Font.PLAIN, 9));
		lblTelefone.setBounds(10, 242, 73, 14);
		contentPane.add(lblTelefone);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Showcard Gothic", Font.PLAIN, 9));
		lblCelular.setBounds(10, 267, 46, 14);
		contentPane.add(lblCelular);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(96, 214, 86, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(96, 239, 86, 20);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);
		
		textFieldCelular = new JTextField();
		textFieldCelular.setBounds(96, 264, 86, 20);
		contentPane.add(textFieldCelular);
		textFieldCelular.setColumns(10);
		
		JLabel lblMm = new JLabel("M.M");
		lblMm.setFont(new Font("Curlz MT", Font.PLAIN, 11));
		lblMm.setBounds(388, 346, 46, 14);
		contentPane.add(lblMm);
		
		// estanciando array de contatos
		this.listaDeContatos = new ArrayList<Contato>();
		
		carregaBaseDeDados();
		
		// metodo que imprime conteudo do arrayList s� para teste
		imprimeBaseDeDados(listaDeContatos);
		
		// atualiza JTable
		tabela(this.listaDeContatos);
		
		// verifica eventos do mouse na JTable
		tabela.addMouseListener(new java.awt.event.MouseAdapter() 
		{
		    public void mouseClicked(java.awt.event.MouseEvent evt) 
		    {
		    	int linhaSelecionada = tabela.getSelectedRow();
		    	
		    	textFieldNome.setText((String)tabela.getValueAt(linhaSelecionada, 0));
		    	textFieldTelefone.setText((String)tabela.getValueAt(linhaSelecionada, 1));
		    	textFieldCelular.setText((String)tabela.getValueAt(linhaSelecionada, 2));
		    }
		});

	
		btnAdicionar.addActionListener(this);
		btnRemover.addActionListener(this);
		btnAlterar.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnAdicionar)
		{
			if(textFieldNome.getText().isEmpty()||textFieldCelular.getText().isEmpty()||textFieldTelefone.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Para adicionar um contato todos os campos devem ser preenchidos!");
			}else
			{
				// cria nova pessoa
				Contato c1 = new Contato();
				
				// seta valores
				c1.setNome(textFieldNome.getText());
				c1.setTelefone(textFieldTelefone.getText());
				c1.setCelular(textFieldCelular.getText());
				
				//adiciona na tabela
				modelo.adicionarPessoa(c1);
				
				//adiciona contato no arrayList de contatos
				this.listaDeContatos.add(c1);
				
				Serializacao.serializar(listaDeContatos);
				
				//imprimi mensagem para usuario
				JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso!");
				
				//limpa caixas de texto
				textFieldNome.setText("");
				textFieldTelefone.setText("");
				textFieldCelular.setText("");
			}
			
		}
	
		if(e.getSource() == btnRemover)
		{
			
			// pega linha da tabela
			int indiceLinha = tabela.getSelectedRow();
			
			if(indiceLinha<0)
			{
				JOptionPane.showMessageDialog(null, "Para remover um contato voc� deve selecionar uma linha na tabela!");
			}else
			{
			// remove pessoa da tabela 
			modelo.removerPessoa(indiceLinha);
			
			// remove pessoa do ArrayList
			this.listaDeContatos.remove(indiceLinha);
				
			Serializacao.serializar(listaDeContatos);
			
			JOptionPane.showMessageDialog(null, "Contato removido com sucesso!");
			
			//limpa caixas de texto
			textFieldNome.setText("");
			textFieldTelefone.setText("");
			textFieldCelular.setText("");
			
			
			}
		}
		if(e.getSource() == btnAlterar)
		{
			
			// pega linha da tabela
			int indiceLinha = tabela.getSelectedRow();
			
			if(indiceLinha<0)
			{
				JOptionPane.showMessageDialog(null, "Para alterar um contato voc� deve selecionar uma linha na tabela!");
			}else{
			
			// pegar o indice no array e seta os valores
			listaDeContatos.get(indiceLinha).setNome(textFieldNome.getText());
			listaDeContatos.get(indiceLinha).setTelefone(textFieldTelefone.getText());
			listaDeContatos.get(indiceLinha).setCelular(textFieldCelular.getText());
			
			Serializacao.serializar(listaDeContatos);
			
			modelo.atualizarTabela();
						
			JOptionPane.showMessageDialog(null, "Contato alterado com sucesso!!");
			
			//limpa caixas de texto
			textFieldNome.setText("");
			textFieldTelefone.setText("");
			textFieldCelular.setText("");

			}
		}
		
	}
	
	public void carregaBaseDeDados()
	{
		
		String nomeDoArquivo = "baseDeDados.txt";
		File f = new File(nomeDoArquivo);
		if (! f.exists())
		{
			Serializacao.serializar(listaDeContatos);
		}
		
		this.listaDeContatos = Serializacao.deserializar();
	}
	
	// esse metodo deveria estar na Classe modeloTabela
	public void tabela(ArrayList<Contato> listaDeContatos)
	{
		
		for(int i=0;i<listaDeContatos.size();i++)
		{
			modelo.adicionarPessoa(listaDeContatos.get(i));
		}
	}
	
	public void imprimeBaseDeDados(ArrayList<Contato> listaDeContatos)
	{
		for(int i=0;i<listaDeContatos.size();i++)
		{
			System.out.println(listaDeContatos.get(i).getNome());
			System.out.println(listaDeContatos.get(i).getTelefone());
			System.out.println(listaDeContatos.get(i).getCelular());
			System.out.println();
		}
	}
}