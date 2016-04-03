package morpheus.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuDiProva {

	private final Icon icon_play = new ImageIcon("res/ci_sta.jpeg");
	private final Icon icon_ranked = new ImageIcon("res/rrrr.jpeg");
	private final Icon icon_options = new ImageIcon("res/bau.jpg");
	private final Icon icon_exit = new ImageIcon("res/exit.jpeg");
	
	private final JPanel panel_title = new JPanel();
	private final JPanel panel_menu = new JPanel();
	private final JLabel text = new JLabel("MENU");
	private final JButton play = new JButton(icon_play);
	private final JButton ranked = new JButton(icon_ranked);
	private final JButton options = new JButton(icon_options);
	private final JButton exit = new JButton(icon_exit);
	
	//private Morpheus menu = new Morpheus();
	
	public MenuDiProva(){
		
		play.setMargin(new Insets(0, 0, 0, 0));
		ranked.setMargin(new Insets(0, 0, 0, 0));
		options.setMargin(new Insets(0, 0, 0, 0));
		exit.setMargin(new Insets(0, 0, 0, 0));
		
		panel_menu.setLayout(new FlowLayout());
		panel_menu.add(play);
		panel_menu.add(ranked);
		panel_menu.add(options);
		panel_menu.add(exit);
		
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setFont(new Font("NUOVO", Font.PLAIN, 40));//Modificare nuovo e plain
		//text.setEditable(false);
		
		panel_title.setLayout(new BorderLayout());
		panel_title.add(text, BorderLayout.CENTER);
		
		
		/*
		menu.getContentPane().add(panel_title, BorderLayout.CENTER);
		menu.getContentPane().add(panel_menu, BorderLayout.SOUTH);*/
		
		panel_title.setBackground(Color.BLACK);
		panel_menu.setBackground(Color.BLACK);
		//menu.setBackground(Color.BLACK);
		
		
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//menu.setVisible(false);
				new GameState();
			}
		});
		
		ranked.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//menu.setVisible(false);
				new RankedState();
			}
		});

		options.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//menu.setVisible(false);
				new OptionsState();
			}
		});

		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		//menu.setVisible(true);
		
		//menu.start();
	}

}
