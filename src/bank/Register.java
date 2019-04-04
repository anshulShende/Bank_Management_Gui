package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Register extends JFrame implements ActionListener
{
  Connection con;
  int count,ano;
  String name,gen,date,phone,email,d_state,d_pass,dc_pass;
  String d_acc,nom_name,d_rel,num;
  
  JTable info;
  ButtonGroup gender;
  JLabel lname,lemail,lpass,lc_pass,lcountry,lphone,ldob,lgen,lmob,lstate,ldobc;
  JLabel pdetails,bdetails,tcountry,lacc;
  JLabel bcode,ifsc,lnom,lrel;
  JTextField tname,temail,tphone,tdob,tnom,tnum;
  JPasswordField pass,c_pass;
  JRadioButton Rgen1,Rgen2;
  JComboBox cstates,acc,crel;  
  JButton register,reset,exit,back;
  
  String relation[]={"Select","Husband","Wife","Father","Mother","Daughter","Son"};
  String account[]={"Select","Savings Accounts","Zero Balance Saving Account",
  "Salary Saving Account","Current Account","Pension Account"};
  String states[]={"Select","Maharashtra","Tamil Nadu","Gujrat","Andhra Pradesh",
  "Arunachal Pradesh","Assam","Bihar","Chattisgarh","Goa","Haryana"};

  public Register()
  {
    setLayout(null);
    setTitle("New Registration");

    pdetails=new JLabel("Personal Details:");
    pdetails.setBounds(15,10,100,20);
    add(pdetails);
     
    bdetails=new JLabel("Banking Details:");
    bdetails.setBounds(300,10,100,20);
    add(bdetails);

    lname=new JLabel("Name");
    lname.setBounds(20,40,120,25);
    add(lname);
    tname=new JTextField(10);
    tname.setBounds(120,40,130,25);
    add(tname);

    lgen=new JLabel("Gender");
    lgen.setBounds(20,75,100,25);
    add(lgen);

    gender=new ButtonGroup();
    Rgen1=new JRadioButton("Male");
    Rgen1.setActionCommand("Male");
    Rgen2=new JRadioButton("Female");
    Rgen2.setActionCommand("Female");
    Rgen1.setBounds(120,75,70,25);
    Rgen2.setBounds(190,75,80,25);
    gender.add(Rgen1);
    gender.add(Rgen2);
    Rgen1.setSelected(true);
    add(Rgen1);
    add(Rgen2);

    ldob=new JLabel("Date of Birth");
    ldob.setBounds(20,110,120,25);
    add(ldob);

    tdob=new JTextField();
    tdob.setBounds(120,110,80,25);
    add(tdob);
    
    ldobc=new JLabel("dd/mm/yyyy");
    ldobc.setBounds(210,110,70,25);
    add(ldobc);

    lphone=new JLabel("Mobile Number");
    lphone.setBounds(20,145,100,25);
    add(lphone);

    tphone=new JTextField(10);
    tphone.setBounds(120,145,140,25);
    add(tphone);

    lemail=new JLabel("Email-Id");
    lemail.setBounds(20,180,100,25);
    add(lemail);

    temail=new JTextField(30);
    temail.setBounds(120,180,140,25);
    add(temail);

    lcountry=new JLabel("Nationality");
    lcountry.setBounds(20,215,100,25);
    add(lcountry);

    tcountry=new JLabel("Indian");
    tcountry.setBounds(130,215,100,25);
    add(tcountry);

    lstate=new JLabel("State");
    lstate.setBounds(310,215,70,25);
    add(lstate);

    cstates=new JComboBox(states);
    cstates.setBounds(440,215,140,25);
    add(cstates);
    cstates.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        d_state=(String)cstates.getSelectedItem();
      }
    });

    lpass=new JLabel("Password");
    lpass.setBounds(20,250,100,25);
    add(lpass);

    pass=new JPasswordField();
    pass.setBounds(120,250,140,25);
    add(pass);

    lc_pass=new JLabel("Confirm Password");
    lc_pass.setBounds(310,250,120,25);
    add(lc_pass);

    c_pass=new JPasswordField();
    c_pass.setBounds(440,250,140,25);
    add(c_pass);

    lacc=new JLabel("Account Type");
    lacc.setBounds(310,40,100,25);
    add(lacc);

    acc=new JComboBox(account);
    acc.setBounds(440,40,140,25);
    add(acc);
    acc.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        d_acc=(String)acc.getSelectedItem();
      }
    });
    ;;
    bcode=new JLabel("Branch Code:   1011 ");
    bcode.setBounds(310,75,140,25);
    add(bcode);
    
    ifsc=new JLabel("IFSC Code:  10116606");
    ifsc.setBounds(460,75,140,25);
    add(ifsc);

    lnom=new JLabel("Nominee Name:");
    lnom.setBounds(310,110,100,25);
    add(lnom);
    
    tnom=new JTextField();
    tnom.setBounds(440,110,140,25);
    add(tnom);

    lrel=new JLabel("Nominee Relation");
    lrel.setBounds(310,145,120,25);
    add(lrel);
    crel=new JComboBox(relation);
    crel.setBounds(440,145,140,25);
    add(crel);
    crel.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        d_rel=(String)crel.getSelectedItem();
      }
    });

    JLabel lnum=new JLabel("Nominee Mobile No");
    lnum.setBounds(310,180,120,25);
    add(lnum);
    tnum=new JTextField();
    tnum.setBounds(440,180,140,25);
    add(tnum);
    
    register=new JButton("Register");
    register.setBounds(50,285,100,25);
    add(register);
    register.addActionListener(this);

    reset=new JButton("Reset");
    reset.setBounds(170,285,100,25);
    add(reset);
    reset.addActionListener(this);

    exit=new JButton("Exit");
    exit.setBounds(280,285,100,25);
    add(exit);
    exit.addActionListener(this);
    
    back=new JButton("Back To Login");
    back.setBounds(400,285,120,25);
    add(back);
    back.addActionListener(this);
       
    String data[][]={{"Account Type","Interest Rate","Minimum Balance","Charges"},
    {"Saving Account","5%pa","4000 INR","200pa"},
    {"Zero Balance Saving Accopunt","4%pa","0 INR","300pa"},
    {"Salary Saving Account","5%pa","2000 INR","200pa"},
    {"Current Account","7%pa","10,000 INR","300pa"},
    {"Pension Account","4%pa","1000 INR","0pa"}};
    String colname[]={"Account Type","Interest Rate","Minimum Balance","Charges"};
    info=new JTable(data,colname);
    info.setBounds(30,320,540,100);
    info.setEnabled(false);
    add(info);

    //Cursor code
    Cursor cur=new Cursor(Cursor.HAND_CURSOR);
    exit.setCursor(cur);
    back.setCursor(cur);
    register.setCursor(cur);
    reset.setCursor(cur);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==back)
    {
      this.setVisible(false);
      Login login=new Login();

      login.setSize(600,200);
      login.setUndecorated(true);
      login.setVisible(true);
      login.setLocationRelativeTo(null);
      login.setResizable(false);
      login.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    if(e.getSource()==exit)
    {
      System.exit(0);
    }
    if(e.getSource()==register)
    {
      validation();
      if(count>0)
        registerUserData();
    }
    if(e.getSource()==reset)
    {
      reset();
    }
  } 
  
  public void validation()
  {
    count=0;
    name=tname.getText();
    gen=gender.getSelection().getActionCommand();
    date=tdob.getText();
    phone=tphone.getText();
    email=temail.getText();
    d_pass=pass.getText();
    dc_pass=c_pass.getText();
    nom_name=tnom.getText();
    num=tnum.getText();
  
    if (name.equals(""))
    {     
      JOptionPane.showMessageDialog(null,"Enter Name");
    }
    else if (date.equals(""))
    {
      JOptionPane.showMessageDialog(null,"Enter Date Of Birth");
    }
    else if (phone.equals(""))
    {
      JOptionPane.showMessageDialog(null,"Enter Mobile Number");
    }
    else if (email.equals(""))
    {
      JOptionPane.showMessageDialog(null,"Enter Email-Id");
    }
    else if(d_state.equals("Select"))
    {
      JOptionPane.showMessageDialog(null,"Enter State");
    }
    else if(d_acc.equals("Select"))
    {
      JOptionPane.showMessageDialog(null,"Enter Account Type");
    }
    else if (nom_name.equals(""))
    {
      JOptionPane.showMessageDialog(null,"Enter Nominee Name");
    }
    else if(d_rel.equals("Select"))
    {
      JOptionPane.showMessageDialog(null,"Enter Relation with Nominee");
    }
    else if(num.equals(""))
    {
      JOptionPane.showMessageDialog(null,"Enter Nominee Mobile Number");
    }
    else if(d_pass.equals(""))
    {
      JOptionPane.showMessageDialog(null,"Enter Password");
    }
    else if(dc_pass.equals(""))
    {
      JOptionPane.showMessageDialog(null,"Enter Confirm Password");
    }
    else
    {
      if(email.contains("@")&& email.contains(".com"))
      {
        if(phone.length()==10)
        {
          if(num.length()==10)
          {
            if(d_pass.equals(dc_pass))
            {
              count++;
            }
            else
            {
              JOptionPane.showMessageDialog(null,"Password Does not match");
            }
          }
          else
          {
            JOptionPane.showMessageDialog(null,"Enter Valid 10-digit Nominee Mobile Number");
          }
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Enter Valid 10-digit Mobile Number");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null,"Enter Valid Email Address");
      }
    }
  }

  public void registerUserData()
  {
      try 
      {
          Class.forName("com.mysql.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://localhost:3307/bankdb","root","pass");
                    
          PreparedStatement pstmt=con.prepareStatement("insert into pdetails(name,gender,dob,mob,email,pass,state)"
                + "values(?,?,?,?,?,?,?)");
          pstmt.setString(1,name);
          pstmt.setString(2,gen);
          pstmt.setString(3,date);
          pstmt.setString(4,phone);
          pstmt.setString(5,email);
          pstmt.setString(6, d_pass);
          pstmt.setString(7, d_state);
          pstmt.executeUpdate();
          
          PreparedStatement stmt=con.prepareStatement("update pdetails set acc_no=id+? where name=? AND mob=?");
          stmt.setInt(1, 10110000);
          stmt.setString(2, name);
          stmt.setString(3, phone);
          stmt.executeUpdate();
          
          PreparedStatement s=con.prepareStatement("select acc_no from pdetails where name=? AND mob=?");
          s.setString(1, name);
          s.setString(2, phone);
          ResultSet rs=s.executeQuery();
          while(rs.next())
                ano=rs.getInt("acc_no");
                    
          PreparedStatement ps=con.prepareStatement("insert into bdetails(acc_no,acc_type,nom_name,nom_rel,nom_no) "
                  + "values(?,?,?,?,?)");
          ps.setInt(1, ano);
          ps.setString(2, d_acc);
          ps.setString(3, nom_name);
          ps.setString(4, d_rel);
          ps.setString(5, num);
          ps.executeUpdate();
          
          Box box=Box.createHorizontalBox();               
            JLabel jl=new JLabel("Enter initial amount: ");
            box.add(jl);
            JTextField jpf=new JTextField(24);
            box.add(jpf);
            int button=JOptionPane.showConfirmDialog(null,box,"Initial amount",JOptionPane.OK_CANCEL_OPTION);
            int amt=Integer.parseInt(jpf.getText());
            PreparedStatement p=con.prepareStatement("update bdetails set balance=? where acc_no=?");
            p.setInt(1, amt);
            p.setInt(2, ano);
            p.executeUpdate();
            
          JOptionPane.showMessageDialog(null,"Registered Sucessfully");
          JOptionPane.showMessageDialog(null,"Your account number is "+ano);
          
          con.close();
      } 
      catch (Exception e) 
      {
          System.out.println(e);
      }
  }

  public void reset()
  {
    tname.setText("");
    tnum.setText("");
    tphone.setText("");
    tnom.setText("");
    cstates.setSelectedItem("Select");
    crel.setSelectedItem("Select");
    acc.setSelectedItem("Select");
    temail.setText("");
    tdob.setText("");
    pass.setText("");
    c_pass.setText("");
  }

  public static void main(String[] args)
  {
    Register reg=new Register();

    reg.setVisible(true);
    reg.setSize(600,480);
    reg.setLocationRelativeTo(null);
    reg.setResizable(false);
    reg.setDefaultCloseOperation(EXIT_ON_CLOSE);   
  }
}                     
