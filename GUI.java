package lab6;
import javax.swing.*;
import javax.swing.JFrame; 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class GUI extends JFrame implements ActionListener{
	
	private JButton jb1, jb2, jb3, jb4, jb5, jb6;
	private JTextField tField1, tField2, tField3;
	private JPanel jp1, jp2, jp3;
	private PhoneBook phonebook = new PhoneBook();
	
	public GUI() {
		
		Font f = new Font("SansSerif", Font.PLAIN, 20);
		
		jb1 = new JButton("Load phonebook");
		jb2 = new JButton("Save phonebook");
		jb3 = new JButton("Search");
		jb4 = new JButton("Next name");
		jb5 = new JButton("Add person");
		jb6 = new JButton("Delete person");
		
		jb2.setEnabled(false);
		jb3.setEnabled(false);
		jb4.setEnabled(false);
		jb5.setEnabled(false);
		jb6.setEnabled(false);
		
		jb1.setFont(f);
		jb2.setFont(f);
		jb3.setFont(f);
		jb4.setFont(f);
		jb5.setFont(f);
		jb6.setFont(f);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		
		jp1 = new JPanel(); 
		jp2 = new JPanel(); 
		jp3 = new JPanel();
		
		jp1.setLayout(new GridLayout(1,2));
		jp2.setLayout(new GridLayout(1,2));
		jp3.setLayout(new GridLayout(1,2));
		
		jp1.add(jb1);
		jp1.add(jb2);
		
		jp2.add(jb3);
		jp2.add(jb4);
		
		jp3.add(jb5);
		jp3.add(jb6);
		
		tField1 = new JTextField();
		tField2 = new JTextField();
		tField3 = new JTextField();
		
		tField2.setEditable(false);
		tField3.setEditable(false);
		
		tField1.setFont(f);
		tField2.setFont(f);
		tField3.setFont(f);
		
		tField1.addActionListener(this);
		tField2.addActionListener(this);
		tField3.addActionListener(this);
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(3,3));
		
		c.add(jp1);
		c.add(tField1);
		c.add(jp2);
		c.add(tField2);
		c.add(jp3);
		c.add(tField3);
		
		pack();
		setVisible(true);
		setTitle("interactive phone book");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == jb1 || ae.getSource() == tField1) {
			tField2.setText(phonebook.Load(tField1.getText()));
			tField1.setText("");
			if (!tField2.getText().equals("Try again")) {
				tField2.setText("Phone book loaded");
				jb2.setEnabled(true);
				jb3.setEnabled(true);
				jb5.setEnabled(true);
				jb6.setEnabled(true); 
			}
		}

		if (ae.getSource() == jb2) {
			if (tField1.getText().equals("")) {
				tField2.setText("Provide a file name");
			} else {
			try {  
				tField2.setText(phonebook.save(tField1.getText()));
				tField1.setText("");
			}
			catch (IOException ie){ tField2.setText("try again"); }
			}
		}
		
		if (ae.getSource() == jb3) {
			ArrayList<Person> tempL = new ArrayList<Person>();
			tempL = phonebook.search(tField1.getText());
			if (tempL.size()<= 0) {
				tField1.setText("");
				tField2.setText("Person not found");
			}else {
				if (tempL.size()>1) {
					jb4.setEnabled(true);				
				} else {
					jb4.setEnabled(false);
					tField1.setText("");
				}
				tField2.setText(tempL.get(0).getFullName());
				tField3.setText(Integer.toString(tempL.get(0).getPhoneNumber()));			
			}
		}
		
		int i = 0;
		if (ae.getSource() == jb4) {
			i++;
			ArrayList<Person> tempL = new ArrayList<Person>();
			tempL = phonebook.search(tField1.getText());
			tField2.setText(tempL.get(i).getFullName());
			tField3.setText(Integer.toString(tempL.get(i).getPhoneNumber()));
			if (i+1==tempL.size()) {
				jb4.setEnabled(false);
				tField1.setText("");
			}
		}
		
		if (ae.getSource() == jb5) {
			if (tField2.isEditable() && tField3.isEditable()) {
				try {
					phonebook.addPerson(tField2.getText(), Integer.parseInt(tField3.getText()));
					tField2.setText("");
					tField1.setText("Added person!"); 
					} catch (Exception e){
					tField1.setText("Try again");	
					tField2.setText("");
					tField3.setText("");
			 	}
			}
			
			if (tField2.isEditable() == false && tField3.isEditable()== false) {
			tField1.setText("Input name and phone number");
			tField2.setText("");
			tField3.setText("");
			tField2.setEditable(true);
			tField3.setEditable(true);
			}
			
			if (tField1.getText().equals("Added person!")){
				tField2.setText("");
				tField3.setText("");
				tField2.setEditable(false);
				tField3.setEditable(false);
			}
	}

		if (ae.getSource() == jb6) {
			try {
				tField1.setText(phonebook.deletePerson(tField2.getText(),Integer.parseInt(tField3.getText())));	
				if (!tField1.getText().equals("Person does not exist")) {
					tField2.setText("");
					tField3.setText("");
				}
			} catch (Exception e){
				;
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		GUI gooey = new GUI();

	}

}




