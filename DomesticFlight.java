import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import java.io.*;
import javax.imageio.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.PreparedStatement;

public class DomesticFlight extends JFrame
{
	JComboBox CBFrom, CBTo, CBClass, CBAdult, CBChildren, CBInfant;
	JLabel LFrom, LTo, LBookingDate, LClass, LAdult, LChildren, LInfant, LBookingDetails, LPassengerDetails, LDate, LImg1, LImg2, LNotes;
	JTextField TFBookingDate;
	Icon img1, img2;
	JButton BFindFlight;
	JPanel PPanel1, PPanel2;

	LoginPage type1;

	public DomesticFlight(LoginPage type1)
	{
		Container c =getContentPane();
		c.setLayout(new BorderLayout());
		String[] sItem1={"Phoenix"};
		String[] sItem2 ={ "San Diego", "Los Angeles", "Las Vegas", "San Jose", "San Francisco", "Ontario", "Oakland", "Burbank", "Orange Conty"};
		String[] sItem3={"Economic","Business"};

		this.type1 = type1;
		PPanel1 = new JPanel(null);
		PPanel1.setPreferredSize(new Dimension(500,200));

		LBookingDetails = new JLabel("<html><b><font color=\"#C71585\">Booking Details</font></b></html>");
		LFrom = new JLabel("From");
		LTo = new JLabel("To");
		LBookingDate = new JLabel("Booking Date");
		LClass = new JLabel("Class");

		CBFrom = new JComboBox(sItem1);
		CBTo = new JComboBox(sItem2);
		CBClass = new JComboBox(sItem3);

		TFBookingDate = new JTextField(10);
		LDate = new JLabel("(DD/MM/YYYY)");
		LDate.setForeground(Color.red);

//		img1=new ImageIcon("img/worldmap.jpg");
		BufferedImage mapBI = null;
		try
		{
			URL u = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com",80,"/FlyHighBooking/img/worldmap.jpg");
			System.out.println(u);
			mapBI = ImageIO.read(u);
		} catch (Exception exc) {
			System.out.println(exc);
		}
		img1=new ImageIcon(mapBI);
		LImg1 = new JLabel(img1);

		BFindFlight = new JButton("Find Flight");

		LBookingDetails.setBounds(75,3,150,20);

		LFrom.setBounds(20,40,100,20);
		CBFrom.setBounds(100,40,100,20);

		LTo.setBounds(20,100,100,20);
		CBTo.setBounds(100,100,100,20);

		LBookingDate.setBounds(14,160,100,20);
		TFBookingDate.setBounds(100,160,100,20);
		LDate.setBounds(210,160,100,20);

		LClass.setBounds(20,220,100,20);
		CBClass.setBounds(100,220,100,20);

		BFindFlight.setBounds(50,270,100,25);

        LImg1.setBounds(50,310,495,216);

		PPanel1.add(LBookingDetails);
		PPanel1.add(LFrom);
		PPanel1.add(CBFrom);
		PPanel1.add(LTo);
		PPanel1.add(CBTo);
		PPanel1.add(LBookingDate);
		PPanel1.add(TFBookingDate);
		PPanel1.add(LDate);
		PPanel1.add(LClass);
		PPanel1.add(CBClass);
		PPanel1.add(BFindFlight);
		PPanel1.add(LImg1);
		PPanel1.setBackground(Color.white);

		c.add(PPanel1,BorderLayout.WEST);

		PPanel2 = new JPanel(null);
		PPanel2.setPreferredSize(new Dimension(320,160));

		LPassengerDetails=new JLabel("<html><b><font color=\"#C71585\">Passenger Details</font></b></html>");

		LAdult = new JLabel("Adults(12+)");

		LChildren = new JLabel("Children(2-11)");
		LInfant = new JLabel("Infants(under 2)");

		String[] item4={"1","2","3","4","5","6"};
		CBAdult = new JComboBox(item4);

		String[] item5={"0","1","2","3","4"};
		CBChildren = new JComboBox(item5);

		String[] item6={"0","1","2","3"};
		CBInfant = new JComboBox(item6);

//		img2 = new ImageIcon("./note_bg.gif");
//		LImg2 = new JLabel(img2);
//		LNotes = new JLabel("<html><body><p>NOTE: Bookings with International Credit Cards <p> have temporarily been suspended.This Service<p> will resume shortly and we will have a notice<p> posted on our website.We regret any <p>inconvenience caused to our passengers.</body></html>");

		LPassengerDetails.setBounds(75,3,150,20);

		LAdult.setBounds(40,40,100,20);
		CBAdult.setBounds(140,40,100,20);

		LChildren.setBounds(40,105,100,20);
		CBChildren.setBounds(140,105,100,20);

		LInfant.setBounds(40,170,100,20);
		CBInfant.setBounds(140,170,100,20);

//		LImg2.setBounds(16,220,320,200);
//		LNotes.setBounds(55,240,380,180);

		PPanel2.add(LPassengerDetails);
		PPanel2.add(LAdult);
		PPanel2.add(LChildren);
		PPanel2.add(LInfant);
		PPanel2.add(CBAdult);
		PPanel2.add(CBChildren);
		PPanel2.add(CBInfant);

//		PPanel2.add(LNotes);
//		PPanel2.add(LImg2);

		PPanel2.setBackground(Color.white);

		c.add(PPanel2,BorderLayout.EAST);

		setSize(795,580);
		setVisible(true);

		BFindFlight.addActionListener(new button3(this, type1));
	}
	public static void main(String args[])
	{
		LoginPage type1=null;
		new DomesticFlight(type1);
	}
}

// MySQL table was created with this command:
// CREATE TABLE flights (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, fromloc VARCHAR(100), toloc VARCHAR(100), class VARCHAR(100), booking_date VARCHAR(100), time VARCHAR(100), adults INT, children INT, infants INT, price INT);

class button3 implements ActionListener
{
	DomesticFlight type;
	LoginPage type1;
	button3(DomesticFlight type, LoginPage type1)
	{
		this.type = type;
		this.type1 = type1;
	}
	public void actionPerformed(ActionEvent e)
	{
		String sFrom = (String)type.CBFrom.getSelectedItem();
		String sTo = (String)type.CBTo.getSelectedItem();
		String sClass = (String)type.CBClass.getSelectedItem();
		String sBookingDate = type.TFBookingDate.getText();
		Integer iPrice=0;
		String sTime="";


		Integer iAdult = Integer.parseInt((String)type.CBAdult.getSelectedItem());
		Integer iChildren = Integer.parseInt((String)type.CBChildren.getSelectedItem());
		Integer iInfant = Integer.parseInt((String)type.CBInfant.getSelectedItem());

		int i = 0;

		if(sClass.equals("Economic"))
		{
			try{
				while(i<20)
				{
					System.out.println(sTo);
					if(type1.row1[i][1].equals(sTo))
					{
						iPrice = Integer.parseInt((String)type1.row1[i][2]);
						sTime = (String)type1.row1[i][3];
						break;
					}
					i++;
				}
			}catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "You have no rights to access\n"+e1);
				System.exit(0);
			}
		}
		else
		{
			try
			{
				while(i<20)
				{
					if(type1.row3[i][1].equals(sTo))
					{
						iPrice = Integer.parseInt((String)type1.row3[i][2]);
						sTime = (String)type1.row3[i][3];
						break;
					}
					i++;
				}
			}catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "You have no rights to access it");
				System.exit(0);
			}
		}
		type.setTitle(iPrice + " " + sTime);

		iPrice = (iPrice*iAdult)+(iPrice*(iChildren/2));

		int iSeatCount=0;

		try {
			String queryString = "action=query&bookingdate=" + URLEncoder.encode(sBookingDate, "utf-8") + "&from=" + URLEncoder.encode(sFrom, "utf-8") + "&to=" + URLEncoder.encode(sTo, "utf-8");
			System.out.println("yo");
			System.out.println(queryString);
			URL servletURL = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com", 8080, "/fhb/fhb?" + queryString);
	
			HttpURLConnection conn = (HttpURLConnection)servletURL.openConnection();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			iSeatCount = Integer.parseInt(resp.readLine());
			resp.close();
		} catch (Exception exc) {
			System.out.println(exc);
		}

		System.out.println(iSeatCount);

//		int iCount=0;
//		int iSeatCount=0;
//
//		String[] sTempFrom=new String[1250];
//		String[] sTempTo=new String[1250];
//		String[] sTempClass=new String[1250];
//		String[] sTempBookingDate=new String[1250];
//		String[] sTempTime=new String[1250];
//		Integer[] iTempAdult=new Integer[1250];
//		Integer[] iTempChildren=new Integer[1250];
//		Integer[] iTempInfant=new Integer[1250];
//		Integer[] iTempPrice=new Integer[1250];
//
//		String errormsg = "msg3";
//		final String dbClassName = "com.mysql.jdbc.Driver";
//		final String CONNECTION = "jdbc:mysql://ec2-54-201-6-28.us-west-2.compute.amazonaws.com:3306/db";
//
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//
//		try
//		{
//			Class.forName(dbClassName);
//		}
//		catch(Exception ex)
//		{
//			errormsg = ex.toString();
//		}
//
//		try
//		{
//			connection = DriverManager.getConnection(CONNECTION,"fhbb","drvtry");
//		}
//		catch(Exception ex)
//		{
//			errormsg = ex.toString();
//			System.out.println(ex.toString());
//		}
//
//		try
//		{
//			preparedStatement = connection.prepareStatement("SELECT * from db.flights");
//			resultSet = preparedStatement.executeQuery();
//	
//			for (i = 0; resultSet.next(); i++)
//			{
//				sTempFrom[i] = resultSet.getString("fromloc");
//				sTempTo[i] = resultSet.getString("toloc");
//				sTempClass[i] = resultSet.getString("class");
//				sTempBookingDate[i] = resultSet.getString("booking_date");
//				sTempTime[i] = resultSet.getString("time");
//				iTempAdult[i] = resultSet.getInt("adults");
//				iTempChildren[i] = resultSet.getInt("children");
//				iTempInfant[i] = resultSet.getInt("infants");
//				iTempPrice[i] = resultSet.getInt("price");
//
//				if (sTempBookingDate[i].equals(sBookingDate) && sTempTo[i].equals(sTo) && sTempFrom[i].equals(sFrom))
//					iSeatCount += iTempAdult[i] + iTempChildren[i] + iTempInfant[i];
//			}
//		}
//		catch(Exception ex)
//		{
//			System.out.println(ex.toString());
//		}
//
////		try
////		{
//////read from data
////			Save2 save1;
////			ObjectInputStream OIS1 = new ObjectInputStream(new FileInputStream("save2.txt"));
////			do
////			{
////				save1 = (Save2)OIS1.readObject();
////				sTempFrom[iCount] = save1.sFrom;
////				sTempTo[iCount] = save1.sTo;
////				sTempClass[iCount] = save1.sClass;
////				sTempBookingDate[iCount] = save1.sBookingDate;
////				sTempTime[iCount] = save1.sTime;
////				iTempAdult[iCount] = save1.iAdult;
////				iTempChildren[iCount] = save1.iChildren;
////				iTempInfant[iCount] = save1.iInfant;
////				iTempPrice[iCount] = save1.iPrice;
////
////				iCount++;
////				if(save1.sBookingDate.equals(sBookingDate))
////					if(save1.sTo.equals(sTo))
////						iSeatCount=iSeatCount + save1.iAdult + save1.iChildren + save1.iInfant;
////			}while(save1!=null);
////			OIS1.close();
////
////		}
////		catch(Exception e1)
////		{
////		}
//
//		iSeatCount = iSeatCount + iAdult + iChildren + iInfant;

		if(iSeatCount > 10)
		{
			JOptionPane.showMessageDialog(null,"Seats are full. Sorry!");
		}
		else
		{
			int iChoice = JOptionPane.showConfirmDialog(null,"Seats available. Do you want to Book now?");
			if(iChoice == JOptionPane.YES_OPTION)
			{
				new PrintTicket1(sFrom, sTo, sClass, iAdult, iChildren, iInfant, sBookingDate, iPrice, sTime);
				try {
					String queryString = "action=update&from=" + URLEncoder.encode(sFrom, "utf-8") + "&to=" + URLEncoder.encode(sTo, "utf-8") + "&class=" + URLEncoder.encode(sClass, "utf-8") +  "&bookingdate=" + URLEncoder.encode(sBookingDate, "utf-8") + "&time=" + URLEncoder.encode(sTime, "utf-8") + "&adult=" + URLEncoder.encode(iAdult.toString(), "utf-8") + "&children=" + URLEncoder.encode(iChildren.toString(), "utf-8") + "&infant=" + URLEncoder.encode(iInfant.toString(), "utf-8") + "&price=" + URLEncoder.encode(iPrice.toString(), "utf-8");
					URL servletURL = new URL("http", "ec2-54-201-6-28.us-west-2.compute.amazonaws.com", 8080, "/fhb/fhb?" + queryString);
			
					HttpURLConnection conn = (HttpURLConnection)servletURL.openConnection();
					BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					System.out.println(resp.readLine());
				} catch (Exception exc) {
					System.out.println(exc);
				}
			}
//				try
//				{
//					preparedStatement = connection.prepareStatement("INSERT INTO db.flights VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//					preparedStatement.setString(1,sFrom);
//					preparedStatement.setString(2,sTo);
//					preparedStatement.setString(3,sClass);
//					preparedStatement.setString(4,sBookingDate);
//					preparedStatement.setString(5,sTime);
//					preparedStatement.setInt(6,iAdult);
//					preparedStatement.setInt(7,iChildren);
//					preparedStatement.setInt(8,iInfant);
//					preparedStatement.setInt(9,iPrice);
//					preparedStatement.executeUpdate();
//			
//	//				for (i = 0; resultSet.next(); i++)
//	//				{
//	//					sTempFrom[i] = resultSet.getString("fromloc");
//	//					sTempTo[i] = resultSet.getString("toloc");
//	//					sTempClass[i] = resultSet.getString("class");
//	//					sTempBookingDate[i] = resultSet.getString("booking_date");
//	//					sTempTime[i] = resultSet.getString("time");
//	//					iTempAdult[i] = resultSet.getInt("adults");
//	//					iTempChildren[i] = resultSet.getInt("children");
//	//					iTempInfant[i] = resultSet.getInt("infants");
//	//					iTempPrice[i] = resultSet.getInt("price");
//	//					System.out.println("from:  " + sTempFrom[i]);
//				}catch(Exception e1)
//				{
//					System.out.println(e1);
//				}
//			}
			else
			{
			}
		}
////write into data
//				Save2 save2=new Save2(sFrom, sTo, sClass, iAdult, iChildren, iInfant, sBookingDate, iPrice, sTime);
//				ObjectOutputStream OOS1 = new ObjectOutputStream(new FileOutputStream("save2.txt"));
//				for(i=0;i<iCount;i++)
//				{
//					Save2 temp1=new Save2(sTempFrom[i], sTempTo[i], sTempClass[i], iTempAdult[i], iTempChildren[i], iTempInfant[i], sTempBookingDate[i], iTempPrice[i], sTempTime[i]);
//					OOS1.writeObject(temp1);
//System.out.println(temp1);
//				}
//				OOS1.writeObject(save2);
//				OOS1.close();
	}
}


class Save2 implements Serializable
{
	String sFrom, sTo, sClass, sBookingDate, sTime;
	Integer iPrice, iAdult, iChildren, iInfant;
//	int iCount;
	public Save2(String sFrom, String sTo, String sClass, Integer iAdult, Integer iChildren, Integer iInfant, String sBookingDate, Integer iPrice, String sTime)
	{
		this.sFrom=sFrom;
		this.sTo=sTo;
		this.sClass=sClass;
		this.iAdult=iAdult;
		this.iChildren=iChildren;
		this.iInfant=iInfant;
		this.sBookingDate=sBookingDate;
		this.iPrice=iPrice;
		this.sTime=sTime;
//		this.iCount = iCount;
	}
	public String toString()
	{
		return sFrom+" "+sTo+" "+sClass+" "+iAdult+" "+iChildren+" "+iInfant+" "+sBookingDate+" "+iPrice+" "+sTime;
	}
}
