import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PrintTable extends JFrame
{
	public PrintTable(String sFrom, String sTo)
	{
        Container c=getContentPane();
		c.setLayout(new BorderLayout());
        
        
		JPanel Panel = new JPanel(null);
        
		Panel.setPreferredSize(new Dimension(500,200));
        
        final Object[] col ={ "From", "To", "Price", "Time" };
        
        final ArrayList<String[]> row1 = new ArrayList<String[]>();
        
        //creates the table
        JTable Table = null;
        
        //search through ArrayList and find matching 'sFrom' and 'sTo' flights
        for(int i = 0; i < row1.size();i++)
        {
            for(int j = 0; j < row1.size();j++)
            {
                //if the 'sForm' and 'sTo' match element(s), create a table displaying only those flights
                if(row1.get(j) == sFrom && row1.get(j) == sTo)
                    Table = new JTable(row1.toArray(new Object[row1.size()][]), col);
            }
        }
        
        //creates the scrollpane for the table above
        JScrollPane scrollPane = null;
        scrollPane = new JScrollPane(Table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //bounds should overlap existing worldmap.jpg image
        //scrollPane.setBounds(50,310,495,216);
        scrollPane.setVisible(true);
        
        //add components to panel
        Panel.add(scrollPane);
        
        //add panel to container
        c.add(Panel);
        
        //pack();
        //setVisible(true);
        
    }
}