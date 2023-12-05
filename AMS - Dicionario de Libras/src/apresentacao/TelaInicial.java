package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dados.Assunto;
import excessoes.SelectException;
import negocio.Gerenciador;


public class TelaInicial  {
	
	private JFrame frame;
	private JPanel panel;
	private JButton cadastroButton;
	private JButton loginButton;
	
	private JList<Assunto> Assuntos;
	private JScrollPane scroll;
	private JLabel j;
	
	Gerenciador g = Main.getGerenciador();

	public TelaInicial(){
		
		panel = new JPanel();
		panel.setBackground(Color.lightGray);
		panel.setLayout(null);
		
		frame = new JFrame();
		frame.setSize(650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		frame.add(panel);
		
		cadastroButton = new JButton("Cadastro");
		cadastroButton.setBounds(420, 10, 90, 25);
		ActionListener buttonListener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main.getCadastrarUsuario().setVisibleTrue();
			}
		};
		cadastroButton.addActionListener(buttonListener1);
		cadastroButton.setBackground(Color.BLUE.darker());
		cadastroButton.setForeground(Color.WHITE);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(530, 10, 90, 25);
		ActionListener buttonListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main.getLogin().setVisibleTrue();				
			}
		};
		loginButton.addActionListener(buttonListener2);
		loginButton.setBackground(Color.BLUE.darker());
		loginButton.setForeground(Color.WHITE);	
		
		j = new JLabel("Assuntos cadastrados:");
		j.setBounds(20, 30, 150, 20);
		j.setForeground(Color.WHITE);
		
		DefaultListModel<Assunto> list = new DefaultListModel<Assunto>();
		try {
			for(Assunto a: g.getAssuntos()) {
				list.addElement(a);
			}
		} catch (SelectException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Assuntos = new JList<Assunto>(list);
		try {
			Assuntos.setBounds(5, 0, 300, 18 * g.getAssuntos().size());
		} catch (SelectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Assuntos.setBackground(Color.gray);
		Assuntos.setForeground(Color.WHITE);
		scroll = new JScrollPane(Assuntos);
		scroll.setBounds(20, 50, 300, 400);
		
		Assuntos.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				//menu.dispose();
				//new ConteudoAssunto(g, Assuntos.getSelectedValue());
			}
		});
		
		panel.add(scroll);
		panel.add(j);
		
		panel.add(cadastroButton);
		panel.add(loginButton);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
		
	public void setVisibleTrue() {
		frame.setVisible(true);
	}
	
	public void setVisibleFalse() {
		frame.setVisible(false);
	}

}