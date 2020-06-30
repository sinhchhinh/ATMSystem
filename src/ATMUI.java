import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ATMUI {
 private JButton one, two, three,four, five, six, seven; 
 private JButton eight,nine, zero, cancel, clear, enter;
 public JFrame frame;   
 private   JTextArea txtBroadcast;

 public ATMUI ()
 {

   initUI();
   frame.setTitle("Spending Note");
   frame.setPreferredSize(new Dimension(600, 600));
   frame.pack();
 }
 
 public void  initUI () {
   frame = new JFrame();
   frame.setBounds(0, 0, 500, 500);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //frame.getContentPane().setLayout(null);
   
   JLabel msgTitle =  new JLabel ("Diplay all messages from all online users",JLabel.CENTER);
   GridLayout atmLayout = new GridLayout(2,3);
   JPanel center1 = new JPanel();
   center1.setLayout(atmLayout);
   center1.add (msgTitle,"North");
  
   txtBroadcast = new JTextArea(5,30);
   txtBroadcast.setEditable(false);
   JPanel center = new JPanel();
   center.setLayout(new BorderLayout());
   center.add (msgTitle,"North");
   center.add(new JScrollPane(txtBroadcast),"Center");
 
   //center1.add(new JScrollPane(txtBroadcast),"Center");
  
   one   = new JButton("1");    
   two   = new JButton("2");    
   three   = new JButton("3");    
   four   = new JButton("4");    
   five   = new JButton("5");    
   six   = new JButton("6");    
   seven   = new JButton("7");    
   eight   = new JButton("8");    
   nine   = new JButton("9");    
   zero   = new JButton("0");  
   enter   = new JButton("Enter");  
   cancel   = new JButton("Cancel");  
   clear   = new JButton("Clear");  
   
   center1.add(one,"Center");
   center1.add(two, "Center");
   center1.add(three);
   center1.add(four);
   center1.add(five);
   center1.add(six);
   center1.add(seven);
   center1.add(eight, "North");
   center1.add(nine, "North");
   center1.add(zero, "North");  
   center1.add(enter);
   center1.add(clear);
   center1.add(cancel);




   JButton btnExit = new JButton("Exit");
   btnExit.setBounds(59, 24, 117, 29);
   btnExit.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) 
       {
           System.exit(0);
       }
   });
   JButton two = new JButton("two");
   two.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) 
       {
           System.exit(0);
       }
   });
   
   //frame.getContentPane().add(btnExit);
   
//   JLabel lblNewLabel = new JLabel("ATM Managment System");
//   lblNewLabel.setFont(new Font("American Typewriter", Font.PLAIN, 30));
//   lblNewLabel.setBounds(380, 18, 468, 30);
   
   JPanel east = new JPanel();
   east.setLayout(new BorderLayout());
   east.add(new JLabel("Online Users", SwingConstants.CENTER), "North");
   frame.getContentPane().add(btnExit, "North");
   frame.add(center1, "South");
   frame.add(center, "Center");
 }
}
