import javax.swing.*;
import javax.swing.JOptionPane;
//import java.lang.*;
import java.awt.event.*;
import java.sql.*;
//import com.mysql.jdbc.Driver;//C:\Program Files\Java\jre1.8.0_261\lib\ext\mysql-connector-java-8.0.21.jar

public class sqlTest
{

    public static void login() //return needs to be bool
    {
        try {
            JFrame login=new JFrame("Login to DB");
            final JTextField tf=new JTextField();  
            final JPasswordField tf1=new JPasswordField();   
            final JButton b=new JButton("Login");//creating instance of JButton  
		    final JButton reset=new JButton("Reset");
            tf.setBounds(150,50, 150,20); 
		    tf1.setBounds(150,80, 150,20);  
		    b.setBounds(100,175,100,40);//x axis, y axis, width, height  
		    reset.setBounds(225,175,75, 40);//x axis, y axis, width, height  
    
            
            JLabel t1,t2,t3;
            t1=new JLabel();
            t2=new JLabel();
            t3=new JLabel();         
            t1.setText("Username: ");
            t2.setText("Password: ");
            t3.setText("");

            t1.setBounds(50,50,150,20);
            t2.setBounds(50,80,150,20);
            t3.setBounds(110,225,150,20);

            login.add(b);login.add(reset);login.add(tf);login.add(tf1);login.add(t1);login.add(t2);login.add(t3);
            login.setSize(400,300);//400 width and 500 height  
		    login.setLayout(null);//using no layout managers  
		    login.setVisible(true);//making the frame visible  
		    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   


            b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {  
                    String u=tf.getText();
					String p=tf1.getText();
					b.setText("Checking...");  
                    
                    try 
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");  
				        Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/schema",u,p);  
                        t3.setText("Success");
                        b.setText("Login");

                        query(con);
                        login.dispose();
                        
                    } 
                    
                    catch (Exception l) 
                    {
                        b.setText("Login");
                        l.printStackTrace();
                        JOptionPane.showMessageDialog(new JFrame(),l,"Error",JOptionPane.ERROR_MESSAGE);
                    }


                }

            });

            reset.addActionListener( new ActionListener()
            {  
                public void actionPerformed(ActionEvent r)
                {  
                    tf.setText("");
                    tf1.setText("");
                }
            }); 
        }
        
        catch (Exception r) 
        {
            String error="Error!";
			JOptionPane.showMessageDialog(new JFrame(),error,"OOPSIE WOOPSIE",JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void query(Connection con){

        JFrame query=new JFrame("DB");
        final JTextField tf=new JTextField();  
        final JButton b=new JButton("Run Query");//creating instance of JButton  
        final JButton r1=new JButton("Reset");
        final JButton close=new JButton("Close");
        tf.setBounds(150,50,200,40); 
        b.setBounds(100,150,100,40);//x axis, y axis, width, height  
        r1.setBounds(225,150,100,40);//x axis, y axis, width, height  
        close.setBounds(350,150,100,40);//x axis, y axis, width, height  

        
        JLabel t1;JLabel res;
        t1=new JLabel();
        t1.setText("Query: ");
        t1.setBounds(75,50,50,20);
        res= new JLabel();
        res.setText("");
        res.setBounds(100,200,300,100);


        query.add(b);query.add(r1);query.add(tf);query.add(t1);query.add(close);
        query.add(res);
        query.setSize(600,500);//600 width and 500 height  
        query.setLayout(null);//using no layout managers  
        query.setVisible(true);//making the frame visible  
        query.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        b.addActionListener( new ActionListener()
        {  
            public void actionPerformed(ActionEvent q)
            {  
                try{
                    String u=tf.getText();
                    Statement stmt=con.createStatement();  
                    ResultSet rs=stmt.executeQuery(u);  
                    while(rs.next())  
                    res.setText(res.getText()+"\nID: "+rs.getInt(1)+" Name: "+rs.getString(2)+" Age: "+rs.getString(3)+" ");
                    //System.out.println("ID: "+rs.getInt(1)+"  Name: "+rs.getString(2)+" Age: "+rs.getString(3));  
                }
                catch(Exception s){
                    JOptionPane.showMessageDialog(new JFrame(),s,"Error",JOptionPane.ERROR_MESSAGE);
                    s.printStackTrace();
                    res.setText("");
                }
            }
        }); 


        r1.addActionListener( new ActionListener()
        {  
            public void actionPerformed(ActionEvent r)
            {  
                tf.setText("");
                res.setText("");
            }
        }); 

        close.addActionListener( new ActionListener()
        {  
            public void actionPerformed(ActionEvent c)
            {  
                try {
                    con.close();
                    query.dispose();
                    System.exit(0);
                } 
                catch (Exception y) {
                    //TODO: handle exception
                }

            }
        }); 
    }

    public static void main(String args[])
    {
        try 
        {
            login();
        } 


        catch (Exception e)
        {
            String error="Error!";
			JOptionPane.showMessageDialog(new JFrame(),error,"OOPSIE WOOPSIE",JOptionPane.ERROR_MESSAGE);
        }
    }
}

