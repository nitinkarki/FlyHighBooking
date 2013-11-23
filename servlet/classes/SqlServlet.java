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
			String sTime = request.getParameter("time");
	
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
	
					if (sTempBookingDate[i].equals(sBookingDate) && sTempTo[i].equals(sTo) && sTempFrom[i].equals(sFrom) && sTempTime[i].equals(sTime))
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

	}
}

