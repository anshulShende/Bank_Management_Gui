package bank;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Admin extends JFrame implements ActionListener
{
    JButton req,del,log;
    JPanel jp;
    JTextArea ta;
	public Admin()
	{
            jp=new JPanel();
            add(jp);
            
            req=new JButton("See Requests");
            req.setBounds(10, 50, 100, 25);
            jp.add(req);
            req.addActionListener(this);
            
            del=new JButton("Delete Account");
            del.setBounds(80, 50, 100, 25);
            jp.add(del);
            del.addActionListener(this);
            
            log=new JButton("Logout");
            log.setBounds(150, 50, 100, 25);
            jp.add(log);
            log.addActionListener(this);
            
            ta=new JTextArea(12,50);
            ta.setBounds(50, 60, 140, 20);
            ta.setBackground(Color.decode("#F0F0F0"));
            jp.add(ta);
	}
        
        public void actionPerformed(ActionEvent e) 
        { 
            if(e.getSource()==req)
            {
                try 
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
                    Statement s=con.createStatement();
                    ResultSet rs=s.executeQuery("select * from request");
                    ta.append("\n\n\tACCOUNT NO\t\tPASSWORD\n\n");
                    while(rs.next())
                               ta.append("\t"+rs.getInt("acc_no")+"\t\t"+rs.getString("pass")+"\n");
                    PreparedStatement pstmt=con.prepareStatement("truncate table request");
                    pstmt.executeUpdate();
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }                 
            }
            if(e.getSource()==del)
            {
                try 
                {
                    Box box=Box.createHorizontalBox();
                    JLabel jl=new JLabel("Enter account no to delete: ");
                    box.add(jl);
                    JTextField jtf=new JTextField(24);
                    box.add(jtf);
                    int button=JOptionPane.showConfirmDialog(null,box,"Delete account",JOptionPane.OK_CANCEL_OPTION);
                    int acc=Integer.parseInt(jtf.getText());
                    
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
                    PreparedStatement pstmt=con.prepareStatement("delete from pdetails where acc_no=?");
                    pstmt.setInt(1, acc);
                    pstmt.executeUpdate();
                    PreparedStatement stmt=con.prepareStatement("delete from bdetails where acc_no=?");
                    stmt.setInt(1, acc);
                    stmt.executeUpdate();
                    PreparedStatement ps=con.prepareStatement("delete from transaction where acc_no=?");
                    ps.setInt(1, acc);
                    ps.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null,"Account number "+acc+" deleted");
                } 
                catch (Exception ex) 
                {
                    System.out.println(ex);
                }                
            }
            if(e.getSource()==log)
            {
                //System.exit(0);
                this.dispose();
                Login login=new Login();
		login.setSize(600,200);
		login.setUndecorated(true);
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		login.setResizable(false);
		login.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
        
	public static void main(String[] args) 
	{
		Admin admin=new Admin();
		admin.setSize(600,300);
		admin.setVisible(true);
		admin.setTitle("Admin Panel");
		admin.setLocationRelativeTo(null);
		admin.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

    
}