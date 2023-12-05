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
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dados.Assunto;
import excessoes.DeleteException;
import excessoes.SelectException;
import negocio.Gerenciador;

public class MenuPrincipal extends JFrame{

	private JFrame frame;
	private JPanel panel;
	private JButton sairButton;
	private JButton excluirUsuarioButton;
	
	private JList<Assunto> Assuntos;
	private JScrollPane scroll;
	private JButton addAssuntoButton;
	private JButton excluirAssuntoButton;
	private JLabel j;
	
	Gerenciador g = Main.getGerenciador();
	
	public MenuPrincipal() {
		
		panel = new JPanel();
		panel.setBackground(Color.lightGray);
		panel.setLayout(null);
		
		frame = new JFrame();
		frame.setSize(650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		frame.add(panel);
		
		sairButton = new JButton("Sair");
		sairButton.setBounds(550, 10, 60, 25);
		sairButton.setBackground(Color.BLUE.darker().darker());
		sairButton.setForeground(Color.WHITE);
		ActionListener buttonListener3 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Main.getGerenciador().sairUsuario();
				Main.getTelaInicial().setVisibleTrue();
			}
		};
		sairButton.addActionListener(buttonListener3);
		
		excluirUsuarioButton = new JButton("Exlcuir Conta");
		excluirUsuarioButton.setBackground(Color.BLUE.darker().darker());
		excluirUsuarioButton.setForeground(Color.WHITE);
		excluirUsuarioButton.setBounds(500, 570, 120, 25);
		ActionListener buttonListener4 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.getGerenciador().excluirUsuario(g.getU());
					frame.setVisible(false);
				} catch (DeleteException e1) {
					e1.printStackTrace();
				}
				Main.getTelaInicial().setVisibleTrue();
			}
		};
		excluirUsuarioButton.addActionListener(buttonListener4);
		
		excluirAssuntoButton = new JButton("Exlcuir Assunto");
		excluirAssuntoButton.setBounds(20, 450, 140, 20);
		ActionListener buttonListener5 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		};
		excluirAssuntoButton.addActionListener(buttonListener5);
		excluirAssuntoButton.setBackground(Color.BLUE.darker().darker());
		excluirAssuntoButton.setForeground(Color.WHITE);
		
		addAssuntoButton = new JButton("Novo Assunto");
		addAssuntoButton.setBounds(160, 450, 140, 20);
		ActionListener buttonListener6 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		};
		addAssuntoButton.addActionListener(buttonListener6);
		addAssuntoButton.setBackground(Color.BLUE.darker().darker());
		addAssuntoButton.setForeground(Color.WHITE);
		
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
		panel.add(excluirAssuntoButton);
		panel.add(addAssuntoButton);
		
		panel.add(sairButton);
		panel.add(excluirUsuarioButton);
		
		frame.setResizable(false);
		frame.setVisible(false);
	}
	
	public void setVisibleTrue() {
		frame.setVisible(true);
	}
	
	public void setVisibleFalse() {
		frame.setVisible(false);
	}
	
}