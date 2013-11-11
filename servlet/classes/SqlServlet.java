import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class SqlServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		if (action.equals("query"))
		{
			String sBookingDate = request.getParameter("bookingdate");
			String sFrom = request.getParameter("from");
			String sTo = request.getParameter("to");
	
	// XXX Creo que hay que empezar aca.
	
			int iCount=0;
			int iSeatCount=0;
	
			String[] sTempFrom=new String[1250];
			String[] sTempTo=new String[1250];
			String[] sTempClass=new String[1250];
			String[] sTempBookingDate=new String[1250];
			String[] sTempTime=new String[1250];
			Integer[] iTempAdult=new Integer[1250];
			Integer[] iTempChildren=new Integer[1250];
			Integer[] iTempInfant=new Integer[1250];
			Integer[] iTempPrice=new Integer[1250];
	
			String errormsg = "msg3";
			final String dbClassName = "com.mysql.jdbc.Driver";
			final String CONNECTION = "jdbc:mysql://ec2-54-201-6-28.us-west-2.compute.amazonaws.com:3306/db";
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
	
			try
			{
				Class.forName(dbClassName);
			}
			catch(Exception ex)
			{
				errormsg = ex.toString();
			}
	
			try
			{
				connection = DriverManager.getConnection(CONNECTION,"fhbb","drvtry");
			}
			catch(Exception ex)
			{
				errormsg = ex.toString();
				out.println(ex.toString());
			}
	
			try
			{
				preparedStatement = connection.prepareStatement("SELECT * from db.flights");
				resultSet = preparedStatement.executeQuery();
		
				for (int i = 0; resultSet.next(); i++)
				{
					sTempFrom[i] = resultSet.getString("fromloc");
					sTempTo[i] = resultSet.getString("toloc");
					sTempClass[i] = resultSet.getString("class");
					sTempBookingDate[i] = resultSet.getString("booking_date");
					sTempTime[i] = resultSet.getString("time");
					iTempAdult[i] = resultSet.getInt("adults");
					iTempChildren[i] = resultSet.getInt("children");
					iTempInfant[i] = resultSet.getInt("infants");
					iTempPrice[i] = resultSet.getInt("price");
	//				out.println(sTempFrom[i]);
	//				out.println(sTempTo[i]);
	
					if (sTempBookingDate[i].equals(sBookingDate) && sTempTo[i].equals(sTo) && sTempFrom[i].equals(sFrom))
						iSeatCount += iTempAdult[i] + iTempChildren[i] + iTempInfant[i];
				}
			}
			catch(Exception ex)
			{
				out.println(ex.toString());
			}
	
			out.println(iSeatCount);
		}
		else if (action.equals("update"))
		{
			String sFrom = request.getParameter("from");
			String sTo = request.getParameter("to");
			String sClass = request.getParameter("class");
			String sBookingDate = request.getParameter("bookingdate");
			String sTime = request.getParameter("time");
			int iAdult = Integer.parseInt(request.getParameter("adult"));
			int iChildren = Integer.parseInt(request.getParameter("children"));
			int iInfant = Integer.parseInt(request.getParameter("infant"));
			int iPrice = Integer.parseInt(request.getParameter("price"));

			String errormsg = "msg3";
			final String dbClassName = "com.mysql.jdbc.Driver";
			final String CONNECTION = "jdbc:mysql://ec2-54-201-6-28.us-west-2.compute.amazonaws.com:3306/db";
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
	
			try
			{
				Class.forName(dbClassName);
			}
			catch(Exception ex)
			{
				errormsg = ex.toString();
			}
	
			try
			{
				connection = DriverManager.getConnection(CONNECTION,"fhbb","drvtry");
			}
			catch(Exception ex)
			{
				errormsg = ex.toString();
				out.println(ex.toString());
			}
	
			try
			{
				preparedStatement = connection.prepareStatement("INSERT INTO db.flights VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setString(1,sFrom);
				preparedStatement.setString(2,sTo);
				preparedStatement.setString(3,sClass);
				preparedStatement.setString(4,sBookingDate);
				preparedStatement.setString(5,sTime);
				preparedStatement.setInt(6,iAdult);
				preparedStatement.setInt(7,iChildren);
				preparedStatement.setInt(8,iInfant);
				preparedStatement.setInt(9,iPrice);
				preparedStatement.executeUpdate();
				out.println("done");
			} catch (Exception exc) {
				out.println(exc);
			}
		}
		else
		{
			out.println("unknown action");
		}

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
//
//		if(iSeatCount > 10)
//		{
//			JOptionPane.showMessageDialog(null,"Seats are full. Sorry!");
//		}
//		else
//		{
//			int iChoice = JOptionPane.showConfirmDialog(null,"Seats available. Do you want to Book now?");
//			if(iChoice == JOptionPane.YES_OPTION)
//			{
//				new PrintTicket1(sFrom, sTo, sClass, iAdult, iChildren, iInfant, sBookingDate, iPrice, sTime);
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
//	//					out.println("from:  " + sTempFrom[i]);
//				}catch(Exception e1)
//				{
//					out.println(e1);
//				}
//			}
//			else
//			{
//			}
//		}
//////write into data
////				Save2 save2=new Save2(sFrom, sTo, sClass, iAdult, iChildren, iInfant, sBookingDate, iPrice, sTime);
////				ObjectOutputStream OOS1 = new ObjectOutputStream(new FileOutputStream("save2.txt"));
////				for(i=0;i<iCount;i++)
////				{
////					Save2 temp1=new Save2(sTempFrom[i], sTempTo[i], sTempClass[i], iTempAdult[i], iTempChildren[i], iTempInfant[i], sTempBookingDate[i], iTempPrice[i], sTempTime[i]);
////					OOS1.writeObject(temp1);
////out.println(temp1);
////				}
////				OOS1.writeObject(save2);
////				OOS1.close();
//	}
	}
}

