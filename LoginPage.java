import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

import javax.swing.text.*;

public class LoginPage extends JFrame
{
	Container c = getContentPane();
	JPanel PFlightTypes = new JPanel(null);
	JPanel PLogin = new JPanel(null);
	JPanel PFlightDetails = new JPanel(null);

	public boolean bCheck=true;

	JLabel LDomesticFlight = new JLabel("<html><b>Domestic Flights</b></html>");
	JLabel LInternationalFlight = new JLabel("<html><b>International Flights</b></html>");

	JLabel LUserName, LPassword;

	JButton LDomesticFlight1 = new JButton("<html><b>Domestic Flight Booking</b></html>");
	JButton LInternationalFlight1 = new JButton("<html><b>International Flight Booking</b></html>");
	JButton LAdminPanel = new JButton("Administrator Panel");

	JLabel LDivideBooking = new JLabel("<html><i>Select booking method to purchase ticket(s).</i></html>");
	JLabel LDivideFlights = new JLabel("<html><i>Select flight type to view flight listings.</i></html>");
	JLabel LTableTitle = new JLabel("<html><b>Flight Listings</b></html>");
	JLabel LChooseClass = new JLabel("<----- Choose a flight class");

	JTextField TFUserName;
	JPasswordField TPPassword;

	JButton BLogin;

	final Object[] col1 ={ "From", "To", "Price", "Time" };
	final Object[] col2 = { "From", "To", "Price", "Time" };
	final Object[] col3 = { "From", "To", "Price", "Time" };

	//domestic economy
	final ArrayList<String[]> row1 = new ArrayList<String[]>();

	//domestic business
	final ArrayList<String[]> row3 = new ArrayList<String[]>();
	
	//international economy
	final ArrayList<String[]> row2 = new ArrayList<String[]>();
			
	//international business
	final ArrayList<String[]> row4 = new ArrayList<String[]>();

	JTable TDomesticFlight = null;
	JTable TInternationalFlight = null;
	JTable TDomesticFlight1 = null;
	JTable TInternationalFlight1 = null;

	JScrollPane JSP1 = null;
	JScrollPane JSP2 = null;
	JScrollPane JSP3 = null;
	JScrollPane JSP4 = null;

	Icon img1 = null;
	Icon img2 = null;

	JLabel LEconomic = null;
	JLabel LBusiness = null;
	JLabel LEconomic1 = new JLabel("Economic");
	JLabel LBusiness1 = new JLabel("Business");

	public LoginPage()
	{
		WindowUtilities.setNativeLookAndFeel();
		setPreferredSize(new Dimension(796,572));

		try {
			URL deURL = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com", 80, "/FlyHighBooking/de.txt");
	
			HttpURLConnection conn = (HttpURLConnection)deURL.openConnection();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (resp.ready())
			{
				row1.add(resp.readLine().split("\t"));
			}
			resp.close();
		} catch (Exception exc) {
			System.out.println(exc);
		}

		try {
			URL deURL = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com", 80, "/FlyHighBooking/fe.txt");
	
			HttpURLConnection conn = (HttpURLConnection)deURL.openConnection();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (resp.ready())
			{
				row2.add(resp.readLine().split("\t"));
			}
			resp.close();
		} catch (Exception exc) {
			System.out.println(exc);
		}

		try {
			URL deURL = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com", 80, "/FlyHighBooking/db.txt");
	
			HttpURLConnection conn = (HttpURLConnection)deURL.openConnection();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (resp.ready())
			{
				row3.add(resp.readLine().split("\t"));
			}
			resp.close();
		} catch (Exception exc) {
			System.out.println(exc);
		}

		try {
			URL deURL = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com", 80, "/FlyHighBooking/fb.txt");
	
			HttpURLConnection conn = (HttpURLConnection)deURL.openConnection();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while (resp.ready())
			{
				row4.add(resp.readLine().split("\t"));
			}
			resp.close();
		} catch (Exception exc) {
			System.out.println(exc);
		}

		TDomesticFlight = new JTable(row1.toArray(new Object[row1.size()][]), col1);
		TInternationalFlight = new JTable(row2.toArray(new Object[row2.size()][]), col2);
		TDomesticFlight1 = new JTable(row3.toArray(new Object[row3.size()][]), col3);
		TInternationalFlight1 = new JTable(row4.toArray(new Object[row4.size()][]), col2);

		JSP1 = new JScrollPane(TDomesticFlight, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JSP2 = new JScrollPane(TInternationalFlight, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JSP3 = new JScrollPane(TDomesticFlight1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JSP4 = new JScrollPane(TInternationalFlight1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		PFlightTypes.setBackground(Color.white);
		PLogin.setBackground(Color.white);
		PFlightDetails.setBackground(Color.white);

		JSP1.setBounds(0, 340, 790, 200);
		JSP2.setBounds(0, 340, 790, 200);
		JSP3.setBounds(0, 340, 790, 200);
		JSP4.setBounds(0, 340, 790, 200);

		PFlightTypes.setBounds(0,0,500, 340);
		PLogin.setBounds(500,0,350, 340);
		PFlightDetails.setBounds(0,340,790,200);

		LUserName = new JLabel("User Name ");
		LPassword = new JLabel("Password ");
		TFUserName = new JTextField(10);
		TPPassword = new JPasswordField(10);
		BLogin = new JButton("Sign In");

		LUserName.setBounds(40, 100, 100, 21);
		LPassword.setBounds(40, 140, 100, 21);
		TFUserName.setBounds(160, 100, 100, 21);
		TPPassword.setBounds(160, 140, 100, 21);
		BLogin.setBounds(160, 200, 100,25);

		LDomesticFlight1.setBounds(60, 85, 235, 30);
		LDivideBooking.setBounds(10, 125, 350, 15);
		LInternationalFlight1.setBounds(60, 150, 235, 30);
		LAdminPanel.setBounds(60, 200, 235, 30);

		LChooseClass.setBounds(0,230,250,15);
		LChooseClass.setVisible(false);
        
		PLogin.add(LUserName);
		PLogin.add(TFUserName);
		PLogin.add(LPassword);
		PLogin.add(TPPassword);
		PLogin.add(BLogin);

		PFlightDetails.add(JSP1);
		PFlightDetails.add(JSP2);
		PFlightDetails.add(JSP3);
		PFlightDetails.add(JSP4);

		JSP1.setVisible(true);
		JSP2.setVisible(false);
		JSP3.setVisible(false);
		JSP4.setVisible(false);

		BufferedImage ecBI = null;
		try
		{
			URL u = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com",80,"/FlyHighBooking/img/economic.jpg");
			System.out.println(u);
			ecBI = ImageIO.read(u);
		} catch (Exception exc) {
			System.out.println(exc);
		}
		img1 = new ImageIcon(ecBI);
		LEconomic = new JLabel("Economic", img1, SwingConstants.LEFT);

		BufferedImage busBI = null;
		try
		{
			URL u = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com",80,"/FlyHighBooking/img/business.jpg");
			System.out.println(u);
			busBI = ImageIO.read(u);
		} catch (Exception exc) {
			System.out.println(exc);
		}
		img2 = new ImageIcon(busBI);
		LBusiness = new JLabel("Business", img2, SwingConstants.LEFT);

		LBusiness.setBounds(265, 170, 300, 125);
		LEconomic.setBounds(0, 170, 300, 125);
		LBusiness1.setBounds(280, 200, 135, 60);
		LEconomic1.setBounds(50, 200, 147, 60);

		PFlightTypes.add(LTableTitle);
		PFlightTypes.add(LEconomic);
		PFlightTypes.add(LBusiness);
		PFlightTypes.add(LEconomic1);
		PFlightTypes.add(LBusiness1);

		LBusiness.setVisible(false);
		LBusiness1.setVisible(true);
		LEconomic.setVisible(true);
		LEconomic1.setVisible(true);
	
		LDomesticFlight.setBounds(60, 40, 200, 30);
		LDivideFlights.setBounds(10,80,350,15);
		LInternationalFlight.setBounds(60, 100, 220, 30);
		LTableTitle.setBounds(350,320,250,20);

		LDomesticFlight.setVisible(false);
		LDivideFlights.setVisible(false);
		LInternationalFlight.setVisible(false);
		LTableTitle.setVisible(true);

		c.add(PFlightTypes);
		c.add(PLogin);
		c.add(PFlightDetails);

		PFlightTypes.add(LDomesticFlight);
		PFlightTypes.add(LDivideFlights);
		PFlightTypes.add(LInternationalFlight);

		pack();
		setVisible(true);

		addWindowListener(new ExitListener());

		LDomesticFlight.addMouseListener(new mouse1(this, true));
		LInternationalFlight.addMouseListener(new mouse1(this, false));

		LDomesticFlight1.addMouseListener(new mouse3(this, true));
		LInternationalFlight1.addMouseListener(new mouse3(this, false));
		LAdminPanel.addActionListener(new openadmin(this));

		LBusiness1.addMouseListener(new mouse2(this, true));
		LEconomic1.addMouseListener(new mouse2(this, false));

		BLogin.addActionListener(new button1(this));

		
	}

	public static void main(String args[])
	{
		new LoginPage();
	}
}

class openadmin implements ActionListener
{
	LoginPage type;

	public openadmin(LoginPage type)
	{
		this.type = type;
	}
	public void actionPerformed(ActionEvent e)
	{
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
			try {
				URL url = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com",80,"/FlyHighBooking/admin.php");
				desktop.browse(url.toURI());
			} catch (Exception exc) {
				System.out.println(exc);
			}
	}
}

class button1 implements ActionListener
{
	LoginPage type;
	char[] cCheck, cPassword={'a','d','m','i','n','\0'}, cPassword2={'u','s','e','r','\0'};
	JFrame f;
	String sCheck,sCheck1="admin",sCheck2="user";

	public button1(LoginPage type)
	{
		this.type = type;
	}
	public void actionPerformed(ActionEvent e)
	{
		cCheck=type.TPPassword.getPassword();
		sCheck = type.TFUserName.getText();
		if ((sCheck1.equals(sCheck)) && check())
		{
			type.LDomesticFlight.setVisible(true);
			type.LDivideFlights.setVisible(true);
			type.LInternationalFlight.setVisible(true);
			type.LChooseClass.setVisible(true);
			type.LAdminPanel.setVisible(true);

			type.PLogin.add(type.LDomesticFlight1);
			type.PLogin.add(type.LDivideBooking);
			type.PLogin.add(type.LInternationalFlight1);
			type.PLogin.add(type.LChooseClass);
			type.PLogin.add(type.LAdminPanel);

			type.PLogin.remove(type.LUserName);
			type.PLogin.remove(type.TFUserName);
			type.PLogin.remove(type.LPassword);
			type.PLogin.remove(type.TPPassword);
			type.PLogin.remove(type.BLogin);

			type.c.repaint();
		}
		else if ((sCheck2.equals(sCheck)) && check2())
		{
			type.LDomesticFlight.setVisible(true);
			type.LDivideFlights.setVisible(true);
			type.LInternationalFlight.setVisible(true);
			type.LChooseClass.setVisible(true);

			type.PLogin.add(type.LDomesticFlight1);
			type.PLogin.add(type.LDivideBooking);
			type.PLogin.add(type.LInternationalFlight1);
			type.PLogin.add(type.LChooseClass);

			type.PLogin.remove(type.LUserName);
			type.PLogin.remove(type.TFUserName);
			type.PLogin.remove(type.LPassword);
			type.PLogin.remove(type.TPPassword);
			type.PLogin.remove(type.BLogin);

			type.c.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid username or password. Try again");
		}
	}
	public boolean check()
	{
		if (cCheck.length >= 6 || cCheck.length < 4)
			return false;
		for(int i=0;i<=4;i++)
		{
			if(cCheck[i]!=cPassword[i])
				return false;
		}
		return true;
	}
	public boolean check2()
	{
		if (cCheck.length >= 5 || cCheck.length < 3)
			return false;
		for(int i=0;i<=3;i++)
		{
			if(cCheck[i]!=cPassword2[i])
				return false;
		}
		return true;
	}
}

class mouse1 extends MouseAdapter
{
	LoginPage type;
	boolean bCheck;

	public mouse1(LoginPage type, boolean bCheck)
	{
		this.type = type;
		this.bCheck = bCheck;
	}
	public void mouseEntered(MouseEvent e)
	{
		type.LDomesticFlight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		type.LInternationalFlight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	public void mouseClicked(MouseEvent e)
	{
		if(bCheck)
			type.bCheck = true;
		else
			type.bCheck = false;
		type.LEconomic.setVisible(true);
		type.LBusiness1.setVisible(true);
		type.LEconomic1.setVisible(false);
		type.LBusiness.setVisible(false);

		type.JSP1.setVisible(false);
		type.JSP2.setVisible(false);
		type.JSP3.setVisible(false);
		type.JSP4.setVisible(false);
		if(bCheck)
			type.JSP1.setVisible(true);
		else
			type.JSP2.setVisible(true);
	}
}



class mouse3 extends MouseAdapter
{
	LoginPage type;
	boolean bCheck;

	public mouse3(LoginPage type, boolean bCheck)
	{
		this.type = type;
		this.bCheck = bCheck;
	}
	public void mouseEntered(MouseEvent e)
	{
		type.LDomesticFlight1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		type.LInternationalFlight1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	public void mouseClicked(MouseEvent e)
	{
		if (bCheck)
			new DomesticFlight(type);
		else
			new InternationalFlight(type);
	}
}


class mouse2 extends MouseAdapter
{
	LoginPage type;
	boolean bCheck;

	public mouse2(LoginPage type, boolean bCheck)
	{
		this.type = type;
		this.bCheck = bCheck;
	}
	public void mouseEntered(MouseEvent e)
	{
		type.LEconomic1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		type.LBusiness1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	public void mouseClicked(MouseEvent e)
	{
		if(type.bCheck)
		{
			if (bCheck)
			{
				type.LBusiness1.setVisible(false);
				type.LBusiness.setVisible(true);
				type.LEconomic.setVisible(false);
				type.LEconomic1.setVisible(true);
				type.JSP1.setVisible(false);
				type.JSP2.setVisible(false);
				type.JSP3.setVisible(true);
				type.JSP4.setVisible(false);
			}
			else
			{
				type.LEconomic1.setVisible(false);
				type.LBusiness.setVisible(false);
				type.LBusiness1.setVisible(true);
				type.LEconomic.setVisible(true);
				type.JSP1.setVisible(true);
				type.JSP2.setVisible(false);
				type.JSP3.setVisible(true);
				type.JSP4.setVisible(false);
			}
		}
		else
		{
			if (bCheck)
			{
				type.LBusiness1.setVisible(false);
				type.LBusiness.setVisible(true);
				type.LEconomic.setVisible(false);
				type.LEconomic1.setVisible(true);
				type.JSP1.setVisible(false);
				type.JSP2.setVisible(false);
				type.JSP3.setVisible(false);
				type.JSP4.setVisible(true);
			}
			else
			{
				type.LEconomic1.setVisible(false);
				type.LBusiness.setVisible(false);
				type.LBusiness1.setVisible(true);
				type.LEconomic.setVisible(true);
				type.JSP1.setVisible(false);
				type.JSP2.setVisible(true);
				type.JSP3.setVisible(false);
				type.JSP4.setVisible(false);
			}
		}
	}
}

