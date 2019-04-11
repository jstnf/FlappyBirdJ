package com.jstnf.flappybirdj.main;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Options extends JFrame {

	private JPanel contentPane;
	private JTextField gravityField;
	private JTextField speedField;
	private FlappyBird fb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Options(FlappyBird fb) {
		this.fb = fb;
		
		setTitle("FlappyBirdJ Physics Options");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 299, 123);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblGravity = new JLabel("GRAVITY");
		contentPane.add(lblGravity, "cell 0 0,alignx trailing");
		
		gravityField = new JTextField();
		gravityField.setText(fb.player.getGRAVITY() + "");
		contentPane.add(gravityField, "cell 1 0,growx");
		gravityField.setColumns(10);
		
		JLabel lblSpeed = new JLabel("SPEED");
		contentPane.add(lblSpeed, "cell 0 1,alignx trailing");
		
		speedField = new JTextField();
		speedField.setText(fb.handler.getSpeed() + "");
		contentPane.add(speedField, "cell 1 1,growx");
		speedField.setColumns(10);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fb.player.setGRAVITY(Double.parseDouble(gravityField.getText()));
				fb.handler.setSpeed(Integer.parseInt(speedField.getText()));
			}
		});
		contentPane.add(btnApply, "cell 0 2 2 1,alignx center");
	}

}
