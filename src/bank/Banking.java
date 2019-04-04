package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class Banking extends JFrame
{
  JTabbedPane pane;
  public Banking()
  {
    pane=new JTabbedPane();
    add(pane);

    pane.add("Deposit", new deposit());
    pane.add("Withdraw",new withdraw());
    pane.add("Transfer",new transfer());
    pane.add("Recharge",new recharge());
    pane.add("Account Summary",new acc());   
  }
  
  public static void main(String[] args)
  {
    Banking bank=new Banking();
    bank.setVisible(true);
    bank.setSize(600,480);
    bank.setLocationRelativeTo(null);
    bank.setResizable(false);
    bank.setDefaultCloseOperation(EXIT_ON_CLOSE);    
  }
}

class deposit extends JPanel implements ActionListener
{
  JLabel amt;
  JTextField tamt;
  JButton dep,res;
  
  public deposit()
  {
    this.setLayout(null);

    amt=new JLabel("Enter Amount ");
    amt.setBounds(120,40,120,25);
    add(amt);

    tamt=new JTextField(10);
    tamt.setBounds(220,40,130,25);
    add(tamt);

    dep=new JButton("Deposit");
    dep.setBounds(120,80,100,25);
    add(dep);
    dep.addActionListener(this);

    res=new JButton("Reset");
    res.setBounds(240,80,100,25);
    add(res);
    res.addActionListener(this);
    
    Cursor cur = new Cursor(Cursor.HAND_CURSOR);
    dep.setCursor(cur);
    res.setCursor(cur);
  }
  
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()==dep)
      {
        new password("deposit",Integer.parseInt(tamt.getText()));       
      }
      if(e.getSource()==res)
      {
          tamt.setText("");
      }
   }
}

class withdraw extends JPanel implements ActionListener
{
  JLabel amt;
  JTextField tamt;
  JButton with,res;
  public withdraw()
  {
    this.setLayout(null);

    amt=new JLabel("Enter Amount ");
    amt.setBounds(120,40,120,25);
    add(amt);

    tamt=new JTextField(10);
    tamt.setBounds(220,40,130,25);
    add(tamt);

    with=new JButton("Withdraw");
    with.setBounds(120,80,100,25);
    add(with);
    with.addActionListener(this);

    res=new JButton("Reset");
    res.setBounds(240,80,100,25);
    add(res);
    res.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e)
  {
  	if(e.getSource()==with)
  	{
            new password("withdraw",Integer.parseInt(tamt.getText()));           
  	}
        if(e.getSource()==res)
        {
            tamt.setText("");
        }
  }
}

class transfer extends JPanel implements ActionListener
{
  JLabel amt,sel,acc_no;
  JTextField tamt,tacc;
  JButton trans,res;
  JComboBox s;
  String b[]={"Select","State Bank Of India","Bank of Baroda","Yes Bank","Kotak Mahindra Bank","Dena Bank"};
  public transfer()
  {
     this.setLayout(null);

    amt=new JLabel("Enter Amount ");
    amt.setBounds(120,40,120,25);
    add(amt);

    tamt=new JTextField(10);
    tamt.setBounds(220,40,130,25);
    add(tamt);

    sel=new JLabel("Select Bank");
    sel.setBounds(120,80,120,25);
    add(sel);

    s=new JComboBox(b);
    s.setBounds(220,80,130,25);
    add(s);

    acc_no=new JLabel("Account Number");
    acc_no.setBounds(120,120,120,25);
    add(acc_no);

    tacc=new JTextField();
    tacc.setBounds(240,120,100,25);
    add(tacc);

    trans=new JButton("Transfer");
    trans.setBounds(120,160,100,25);
    add(trans);
    trans.addActionListener(this);

    res=new JButton("Reset");
    res.setBounds(240,160,100,25);
    add(res);
    res.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e)
  {
  	if(e.getSource()==trans)
  	{
            new password("transfer",Integer.parseInt(tamt.getText()));               
  	}
        if(e.getSource()==res)
        {
            tamt.setText("");
            s.setSelectedItem("Select");
            tacc.setText("");
        }
  }
}

class recharge extends JPanel implements ActionListener
{
  JLabel amt,no;
  JTextField tamt,tno;
  JButton rec,res;
  public recharge()
  {
    this.setLayout(null);

    amt=new JLabel("Enter Amount ");
    amt.setBounds(120,40,120,25);
    add(amt);

    tamt=new JTextField(10);
    tamt.setBounds(220,40,130,25);
    add(tamt);
    
    no=new JLabel("Enter phone no ");
    no.setBounds(120,80,120,25);
    add(no);

    tno=new JTextField(10);
    tno.setBounds(220,80,130,25);
    add(tno);

    rec=new JButton("Recharge");
    rec.setBounds(120,120,100,25);
    add(rec);
    rec.addActionListener(this);

    res=new JButton("Reset");
    res.setBounds(240,120,100,25);
    add(res);
    res.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e)
  {
  	if (e.getSource()==rec)
  	{
            new password("recharge",Integer.parseInt(tamt.getText()));               
  	}
        if(e.getSource()==res)
        {
            tamt.setText("");
            tno.setText("");
        }
  }
}

class acc extends JPanel implements ActionListener
{
    JButton sum,del,log;
    JTextArea ta;
  public acc()
  {
    sum=new JButton("Account Summary");
    sum.setBounds(40, 30, 100, 25);
    add(sum);
    sum.addActionListener(this);
    
    log=new JButton("Logout");
    log.setBounds(180, 30, 100, 25);
    add(log);
    log.addActionListener(this);
    
    del=new JButton("Request to Delete Account");
    del.setBounds(320, 30, 100, 25);
    add(del);
    del.addActionListener(this);
    
    ta=new JTextArea(20,50);
    ta.setBounds(100, 50, 140, 20);
    add(ta);
    ta.setBackground(Color.decode("#F0F0F0"));
  }
  
    public void actionPerformed(ActionEvent e) 
    {
        int ano = 0;
        if(e.getSource()==sum)
        {
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");  
                Statement s=con.createStatement();
                ResultSet r=s.executeQuery("select acc_no from session");
                while(r.next())
                    ano=r.getInt("acc_no");
                
                PreparedStatement stmt=con.prepareStatement("select * from transaction where acc_no=?");
                stmt.setInt(1,ano);
                
                ResultSet rs=stmt.executeQuery();
                ta.append("\n\n     TRANSACTION\tTRANSACTION AMOUNT\t\tBALANCE\n\n");
                while(rs.next())
                {
                      ta.append("    "+rs.getString(2)+"\t\t"+rs.getInt(3)+"\t\t\t"+rs.getInt(4)+"\n");
                }
            } 
            catch (Exception ex) 
            {
                System.out.println(ex);
            } 
        }
        if(e.getSource()==log)
        {
         PreparedStatement pstmt;
         try 
         {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from session");
            if(rs.next())
            {
                pstmt=con.prepareStatement("truncate table session");
                pstmt.executeUpdate();
            }
            else{}
         } 
         catch (Exception ex) 
         {
             System.out.println(ex);
         }
           System.exit(0);
        }
        if(e.getSource()==del)
        {
            int acno = 0;
            String pass = "";
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from session");
                while(rs.next())
                {
                    acno=rs.getInt("acc_no");
                    pass=rs.getString("pass");
                }
                
                PreparedStatement pstmt=con.prepareStatement("insert into request(acc_no,pass) values(?,?)");
                pstmt.setInt(1, acno);
                pstmt.setString(2, pass);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Request sent");
            }     
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
    }
}

class password
{
    String pwd;
    public password(String task,int amount)
	{
		Box box=Box.createHorizontalBox();               
  		JLabel jl=new JLabel("Password: ");
  		box.add(jl);
                JPasswordField jpf=new JPasswordField(24);
  		box.add(jpf);
                int button=JOptionPane.showConfirmDialog(null,box,"Confirm your Password",JOptionPane.OK_CANCEL_OPTION);
                String pass=jpf.getText();

                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
                    Statement s=con.createStatement();
                    ResultSet rs=s.executeQuery("select pass from session");
                    while(rs.next())
                        pwd=rs.getString("pass");
                    if(pass.equals(pwd))
                    {
                        JOptionPane.showMessageDialog(null, "Transaction Completed");
                        new bankingdb(task,amount);
                    }   
                    else if(pass.equals(""))
                    {}
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Incorrect password");
                        new password(task,amount);
                    }   
                } 
                catch (Exception ex) 
                {
                    System.out.println(ex);
                }
        }
}

class bankingdb
{
    public bankingdb(String task,int amount)
    {
        int ano = 0,bal = 0;
        PreparedStatement pstmt,ps,stmt;
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select acc_no from session");
                    while(rs.next())
                        ano=rs.getInt("acc_no");
                    
            if(task.equals("deposit"))
            {
                pstmt=con.prepareStatement("update bdetails set balance=balance+? where acc_no=?");
                pstmt.setInt(1, amount);
                pstmt.setInt(2, ano);
                pstmt.executeUpdate();
                
                
            }
            else if(task.equals("withdraw") || task.equals("transfer") || task.equals("recharge"))
            {
                pstmt=con.prepareStatement("update bdetails set balance=balance-? where acc_no=?");
                pstmt.setInt(1, amount);
                pstmt.setInt(2, ano);
                pstmt.executeUpdate();
                
            }

            stmt=con.prepareStatement("select balance from bdetails where acc_no=?");
            stmt.setInt(1, ano);
            ResultSet r=stmt.executeQuery();
            while(r.next())
                bal=r.getInt("balance");
            
            ps=con.prepareStatement("insert into transaction(acc_no,trans,t_amt,balance) values(?,?,?,?)");
                ps.setInt(1, ano);
                ps.setString(2, task);
                ps.setInt(3, amount);
                ps.setInt(4, bal);
                ps.executeUpdate();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
}

