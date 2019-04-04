package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.LineBorder;

public class Login extends JFrame implements ActionListener
{
	JLabel username,password,image;
	JTextField userfield;
	JPasswordField passField;
	JPanel panel;
	JButton login_but,register_but,admin,close;
        
        Connection con;
        
        String pwd,udb,pdb;
        int uname;
        
	public Login()
	{
		setLayout(null);
		
		getContentPane().setBackground(Color.decode("#ffffff")); //JFrame Background;
                
		username=new JLabel("Account No");
		username.setBounds(30,60,100,20);
		add(username);

		userfield=new JTextField();
		userfield.setBounds(120,60,110,20);
		add(userfield);

		password=new JLabel("Password");
		password.setBounds(30,90,100,20);
		add(password);

		passField=new JPasswordField();
		passField.setBounds(120,90,110,20);
		passField.setEchoChar('*');
		add(passField);

		login_but=new JButton("Login");
		login_but.setBounds(20,130,90,25);
                add(login_but);
		login_but.addActionListener(this);

		register_but=new JButton("New Registration");
		register_but.setBounds(120,130,165,25);
		add(register_but);
   		register_but.addActionListener(this);

		admin=new JButton("Admin");
		admin.setBounds(5,5,80,25);
		add(admin);
		admin.addActionListener(this);

		close=new JButton("Close");
		close.setBounds(220,5,80,25);
		add(close);
		close.addActionListener(this);

		//Bank Image in JPanel
		ImageIcon icon=new ImageIcon("bankicon.png");
		image=new JLabel(icon);
		//icon.getImage().getScaledInstance(10,10,java.awt.Image.SCALE_DEFAULT);
		panel=new JPanel();
		panel.setBounds(300,0,300,200);
		panel.add(image);                
		panel.setBackground(Color.decode("#ffffff")); //JPanel Background;
                //panel.setBorder(new LineBorder(Color.blue));
		add(panel);

		//cursor Code
		Cursor cur=new Cursor(Cursor.HAND_CURSOR);
		login_but.setCursor(cur);
		close.setCursor(cur);
		register_but.setCursor(cur);
		admin.setCursor(cur);

		//Setting Foreground and Background to swing Controls

//		login_but.setForeground(Color.decode("#ffffff"));
//		register_but.setForeground(Color.decode("#ffffff"));
//		admin.setForeground(Color.decode("#ffffff"));
//		close.setForeground(Color.decode("#ffffff"));

//		login_but.setBackground(Color.decode("#0000FF"));
//		register_but.setBackground(Color.decode("#0000FF"));
//		admin.setBackground(Color.decode("#0000FF"));
//		close.setBackground(Color.decode("#0000FF"));

		
		//cHANGING FONT PROPERTIES

		Font f =new Font("Arial",Font.BOLD,14);

		login_but.setFont(f);
		register_but.setFont(f);
		admin.setFont(f);
		close.setFont(f);
		username.setFont(f);
		password.setFont(f);

	}

	public void actionPerformed(ActionEvent e)
	{            
                if(e.getSource()==register_but)
		{
			this.setVisible(false);
			Register reg=new Register();
			reg.setVisible(true);
			reg.setSize(600,480);
			reg.setResizable(false);
                        reg.setLocationRelativeTo(null);
                        reg.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
                
		if(e.getSource()==close)
			System.exit(0);
                
		if(e.getSource()==login_but)
		{
                    uname=Integer.parseInt(userfield.getText());
                    pwd=passField.getText();
                    try 
                    {
                        Class.forName("com.mysql.jdbc.Driver");
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
                        
                        PreparedStatement pstmt=con.prepareStatement("select pass from pdetails where acc_no=?");
                        pstmt.setInt(1, uname);
                        ResultSet rs=pstmt.executeQuery();
                        
                        while(rs.next())   
                            pdb=rs.getString("pass");
                        
                        if(pwd.equals(pdb))
                        {
                            PreparedStatement ps=con.prepareStatement("insert into session(acc_no,pass) values(?,?)");
                            ps.setInt(1, uname);
                            ps.setString(2, pwd);
                            ps.executeUpdate();
                            
                            JOptionPane.showMessageDialog(null,"Successfully Logged In");
                            this.setVisible(false);
                            Banking bank=new Banking();
                            bank.setVisible(true);
                            bank.setSize(600,480);
                            bank.setLocationRelativeTo(null);
                            bank.setResizable(false);
                            bank.setDefaultCloseOperation(EXIT_ON_CLOSE);                          
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Incorrect username or password");                        
                    }
                    catch (Exception ex) 
                    {
                        System.out.println(ex);
                    }
		}
                
		if(e.getSource()==admin)
		{
			Box box=Box.createHorizontalBox();
  			JLabel jl=new JLabel("Password: ");
  			box.add(jl);
  			JPasswordField jpf=new JPasswordField(24);
  			box.add(jpf);
                        int button=JOptionPane.showConfirmDialog(null,box,"Enter Admin Password",JOptionPane.OK_CANCEL_OPTION);
  			if(jpf.getText().equals("123"))
  			{	
				this.setVisible(false);
				Admin admin=new Admin();
				admin.setSize(600,300);
				admin.setVisible(true);
				admin.setTitle("Admin Panel");
				admin.setLocationRelativeTo(null);
				admin.setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		}    
	}
               
	public static void main(String[] args)
	{
		Login login=new Login();
		login.setSize(600,200);
		login.setUndecorated(true);
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		login.setResizable(false);
		login.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
