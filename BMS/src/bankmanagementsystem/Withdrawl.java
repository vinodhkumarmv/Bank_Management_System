package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener{   
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    String pin;    
    Withdrawl(String pin){        
        this.pin = pin;   
        setTitle("WITHDRAWAL");                
        l1 = new JLabel("WITHDRAWAL");
        l1.setFont(new Font("System", Font.BOLD, 40));       
        l3 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l3.setFont(new Font("System", Font.BOLD, 35));        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));        
        b1 = new JButton("WITHDRAW");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);        
        b3 = new JButton("EXIT");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
                
        setLayout(null);                
        l1.setBounds(230,100,800,80);
        add(l1);               
        l3.setBounds(120,260,800,60);
        add(l3);        
        t1.setBounds(210,340,360,50);
        add(t1);        
        b1.setBounds(220,400,160,50);
        add(b1);       
        b2.setBounds(400,400,160,50);
        add(b2);        
        b3.setBounds(300,480,200,50);
        add(b3);               
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);                
        getContentPane().setBackground(Color.WHITE); 
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    }
        
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            java.util.Date date = new java.util.Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    Conn c1 = new Conn();                   
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
                    int balance = 0;
                    while(rs.next()){
                       if(rs.getString("mode").equals("Deposit")){
                           balance += Integer.parseInt(rs.getString("amount"));
                       }else{
                           balance -= Integer.parseInt(rs.getString("amount"));
                       }
                    }
                    if(balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }                    
                    c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");                    
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }else if(ae.getSource()==b3){ 
            setVisible(false);
                new Transactions(pin).setVisible(true);
        }}
        catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }            
    }
    public static void main(String[] args){
        new Withdrawl("").setVisible(true);
    }
}