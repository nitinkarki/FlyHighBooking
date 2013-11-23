import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class PrintTable extends JFrame
{
	public PrintTable(String sFrom, String sTo, String sClass, Integer iAdult, Integer iChildren, Integer iInfant, String sBookingDate, Integer iPrice, String sTime, ArrayList<String[]> row)
	{
		Container c=getContentPane();
		c.setLayout(new BorderLayout());


		JPanel Panel = new JPanel(null);

		Panel.setPreferredSize(new Dimension(500,200));

		final Object[] col ={ "From", "To", "Price", "Time" };

		ArrayList<String[]> results = new ArrayList<String[]>();

		//creates the table
		JTable Table = null;

		//search through ArrayList and find matching 'sFrom' and 'sTo' flights
		for(int i = 0; i < row.size();i++)
		{
			//if the 'sForm' and 'sTo' match element(s), create a table displaying only those flights
			if(row.get(i)[0].equals(sFrom) && row.get(i)[1].equals(sTo))
				results.add(row.get(i));
		}

		Table = new JTable(results.toArray(new Object[results.size()][]), col);

		JButton book = new JButton("Book flight!");
		book.addActionListener(new BookFlight(Table, sFrom, sTo, sClass, iAdult, iChildren, iInfant, sBookingDate, iPrice, sTime));
		book.setBounds(95,530,200,20);
		book.setVisible(true);
		Panel.add(book);

		//creates the scrollpane for the table above
		JScrollPane scrollPane = null;
		scrollPane = new JScrollPane(Table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//bounds should overlap existing worldmap.jpg image
		//scrollPane.setBounds(50,310,495,216);
		scrollPane.setBounds(10,10,500,200);
		scrollPane.setVisible(true);

		//add components to panel
		Panel.add(scrollPane);

		//add panel to container
		c.add(Panel);
		Panel.setVisible(true);

		//pack();
		setSize(795,580);
		setVisible(true);

	}
}

class BookFlight implements ActionListener
{
	JTable Table = null;
	String sFrom, sTo, sClass, sBookingDate, sTime;
	Integer iAdult, iChildren, iInfant, iPrice;

	public BookFlight(JTable Table, String sFrom, String sTo, String sClass, Integer iAdult, Integer iChildren, Integer iInfant, String sBookingDate, Integer iPrice, String sTime)
	{
		this.Table =        Table;
		this.sFrom =        sFrom;
		this.sTo =          sTo;
		this.sClass =       sClass;
		this.sBookingDate = sBookingDate;
		this.sTime =        sTime;
		this.iAdult =       iAdult;
		this.iChildren =    iChildren;
		this.iInfant =      iInfant;
		this.iPrice =       iPrice;
	}

	public void actionPerformed(ActionEvent e)
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
}

