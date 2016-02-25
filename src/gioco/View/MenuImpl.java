package gioco.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuImpl {
	
	private Icon icon_play = new ImageIcon("res/ci_sta.jpeg");
	private Icon icon_ranked = new ImageIcon("res/rrrr.jpeg");
	private Icon icon_options = new ImageIcon("res/bau.jpg");
	private Icon icon_exit = new ImageIcon("res/exit.jpeg");
	
	private JFrame menu = new JFrame("MORPHEUS");
	private JPanel panel_title = new JPanel();
	private JPanel panel_menu = new JPanel();
	private JTextField text = new JTextField("MENU");
	private JButton play = new JButton(icon_play);
	private JButton ranked = new JButton(icon_ranked);
	private JButton options = new JButton(icon_options);
	private JButton exit = new JButton(icon_exit);
	
	public MenuImpl(){
		
		play.setMargin(new Insets(0, 0, 0, 0));
		ranked.setMargin(new Insets(0, 0, 0, 0));
		options.setMargin(new Insets(0, 0, 0, 0));
		exit.setMargin(new Insets(0, 0, 0, 0));
		
		menu.setSize(600, 300);
		menu.setResizable(false);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setLayout(new BorderLayout());
		
		panel_menu.setLayout(new FlowLayout());
		panel_menu.add(play);
		panel_menu.add(ranked);
		panel_menu.add(options);
		panel_menu.add(exit);
		
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font("NUOVO", Font.PLAIN, 40));//Modificare nuovo e plain
		text.setEditable(false);
		
		panel_title.setLayout(new BorderLayout());
		panel_title.add(text, BorderLayout.CENTER);
		
		menu.getContentPane().add(panel_title, BorderLayout.CENTER);
		menu.getContentPane().add(panel_menu, BorderLayout.SOUTH);
		
		panel_title.setBackground(Color.BLACK);
		panel_menu.setBackground(Color.BLACK);
		//menu.setBackground(Color.BLACK);
		
		menu.setVisible(true);
	}
}
