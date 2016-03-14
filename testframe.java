import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class testframe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel panel;
	static ShapePanel la;
	static ShapeClicker obj;
	/**
	 * Launch the application.
	 */
	static List<Integer> xregion = new ArrayList<Integer>();
	static List<Integer> yregion = new ArrayList<Integer>();
	static List<Integer> xlion = new ArrayList<Integer>();
	static List<Integer> ylion = new ArrayList<Integer>();
	static List<Integer> xpond = new ArrayList<Integer>();
	static List<Integer> ypond = new ArrayList<Integer>();
	static List<Integer> xambulance = new ArrayList<Integer>();
	static List<Integer> yambulance = new ArrayList<Integer>();
	
	final static JCheckBox chckbxLion = new JCheckBox("Show lions and ponds in selected regions");
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	public static void main(String[] args) throws SQLException {
		testframe frame = new testframe();
		frame.createDataBase();
		
		int xra[]=new int[xregion.size()];
		int yra[]=new int[yregion.size()];
		int xla[]=new int[xlion.size()];
		int yla[]=new int[ylion.size()];
		int xpa[]=new int[xpond.size()];
		int ypa[]=new int[ypond.size()];
		int xaa[]=new int[xambulance.size()];
		int yaa[]=new int[yambulance.size()];
		for (int i=0;i<xregion.size();i++){
			xra[i]=xregion.get(i);
			yra[i]=yregion.get(i);
		}
		for(int i=0;i<xlion.size();i++){
			xla[i]=xlion.get(i);
			yla[i]=ylion.get(i);
		}
		for(int i=0;i<xpond.size();i++){
			xpa[i]=xpond.get(i);
			ypa[i]=ypond.get(i);
		}
		for(int i=0;i<xambulance.size();i++){
			xaa[i]=xambulance.get(i);
			yaa[i]=yambulance.get(i);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setVisible(true);
					frame.addP();
					obj = new ShapeClicker(panel,xra,yra,xla,yla,xpa,ypa,xaa,yaa);
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		frame.panelmaker();
		chckbxLion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxLion.isSelected() == true)
					{
					obj.ML(1);
					}
				else{
					obj.ML(0);
				}
				System.out.println("Click Toggle");
			}
		});
	}

		protected void addP() {
		// TODO Auto-generated method stub
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 700);
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel.setLayout(new BorderLayout(0, 0));
			setContentPane(panel);
		
			panel.setBackground(Color.WHITE);
			panel.add(chckbxLion, BorderLayout.SOUTH);
			chckbxLion.setVerticalAlignment(SwingConstants.BOTTOM);
			
	}

		public static List<Integer> regionarray = new ArrayList<Integer>();
		public static List<Integer> ambulancearray = new ArrayList<Integer>();
		public static List<Integer> lionarray = new ArrayList<Integer>();
		public static List<Integer> pondarray = new ArrayList<Integer>();
		public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:XE";
		public static final String DBUSER = "system";
		public static final String DBPASS = "sanjay";
		
		public static void getregion() throws SQLException {
			// Connect to Oracle Database
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement statement = null;
			ResultSet rs = null;
			ResultSet rset = null;
			try {
				statement = con.createStatement();
				rs = statement.executeQuery("SELECT * FROM region");
				while (rs.next()) {
					String empId = rs.getString("reg_id");
					int sides = rs.getInt("sides");
					System.out.println("REGION ID:- " + empId);
					System.out.println("REGION SHAPE:- " + sides);
					// System.out.println("Empl Name:- " +oar);
					System.out.println("==========================");
				}
				rset = statement.executeQuery("SELECT rshape from region ");
				while (rset.next()) {
					@SuppressWarnings("deprecation")
					JGeometry geom = JGeometry.load((STRUCT) rset.getObject(1));
					double[] coords = geom.getOrdinatesArray();
					for (int i = 0; i < coords.length; i++) {
						int coords1 = (int) coords[i];
						regionarray.add(coords1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException Occured..");
			} finally {
				try {
					if (rs != null) {
						rs.close(); // close resultset
					}
					if (rset != null) {
						rset.close();
					}
					if (statement != null) {
						statement.close(); // close statement
					}
					if (con != null) {
						con.close(); // close connection
					}
				} catch (SQLException e) {
					System.out.println("SQLException Occured..");
				}
			}
		}

		public static void getlion() throws SQLException {
			// Connect to Oracle Database
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement statement = null;
			ResultSet rs = null;
			ResultSet rset = null;
			try {
				statement = con.createStatement();
				rs = statement.executeQuery("SELECT * FROM lion");
				while (rs.next()) {
					String empId = rs.getString("lion_id");
					System.out.println("LION ID:- " + empId);
					System.out.println("==========================");
				}
				rset = statement.executeQuery("SELECT lionpos from lion ");
				while (rset.next()) {
					// retrieve the newly created geometry from the database
					@SuppressWarnings("deprecation")
					JGeometry geom = JGeometry.load((STRUCT) rset.getObject(1));
					double[] coords = geom.getPoint();
					// String coords1 = String.valueOf(coords);
					for (int i = 0; i < coords.length; i++) {
						int coords1 = (int) coords[i];
						lionarray.add(coords1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException Occured..");
			} finally {
				try {
					if (rs != null) {
						rs.close(); // close resultset
					}
					if (rset != null) {
						rset.close();
					}
					if (statement != null) {
						statement.close(); // close statement
					}
					if (con != null) {
						con.close(); // close connection
					}
				} catch (SQLException e) {
					System.out.println("SQLException Occured..");
				}
			}
		}

		public static void getambulance() throws SQLException {
			// Connect to Oracle Database
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement statement = null;
			ResultSet rs = null;
			ResultSet rset = null;
			statement = con.createStatement();
			try {
				rs = statement.executeQuery("SELECT * FROM ambulance");
				while (rs.next()) {
					String empId = rs.getString("amb_id");
					System.out.println("AMBULANCE ID:- " + empId);
					System.out.println("==========================");
				}
				rset = statement.executeQuery("SELECT ashape from ambulance ");
				while (rset.next()) {
					@SuppressWarnings("deprecation")
					JGeometry geom = JGeometry.load((STRUCT) rset.getObject(1));
					double[] coords = geom.getOrdinatesArray();
					// String coords1 = String.valueOf(coords);
					for (int i = 0; i < coords.length; i++) {
						int coords1 = (int) coords[i];
						ambulancearray.add(coords1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException Occured..");
			} finally {
				try {
					if (rs != null) {
						rs.close(); // close resultset
					}
					if (statement != null) {
						statement.close(); // close statement
					}
					if (con != null) {
						con.close(); // close connection
					}
					if (rset != null) {
						rset.close();
					}
				} catch (SQLException e) {
					System.out.println("SQLException Occured..");
				}
			}
		}

		public static void getpond() throws SQLException {
			// Connect to Oracle Database
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			Statement statement = null;
			ResultSet rs = null;
			ResultSet rset = null;
			try {
				statement = con.createStatement();
				rs = statement.executeQuery("SELECT * FROM pond");
				while (rs.next()) {
					String empId = rs.getString("pond_id");
					System.out.println("POND ID:- " + empId);
					System.out.println("==========================");
				}
				rset = statement.executeQuery("SELECT pshape from pond ");
				while (rset.next()) {
					// retrieve the newly created geometry from the database
					@SuppressWarnings("deprecation")
					JGeometry geom = JGeometry.load((STRUCT) rset.getObject(1));
					double[] coords = geom.getOrdinatesArray();
					// String coords1 = String.valueOf(coords);
					for (int i = 0; i < coords.length; i++) {
						int coords1 = (int) coords[i];
						pondarray.add(coords1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException Occured..");
			} finally {
				try {
					if (rs != null) {
						rs.close(); // close resultset
					}
					if (rset != null) {
						rset.close();
					}
					if (statement != null) {
						statement.close(); // close statement
					}
					if (con != null) {
						con.close(); // close connection
					}
				} catch (SQLException e) {
					System.out.println("SQLException Occured..");
				}
			}
		}

		public void createDataBase() throws SQLException {
			// Load Oracle JDBC Driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			getregion();
			getlion();
			getambulance();
			getpond();
			for (int i = 0; i < regionarray.size(); i++) {
				if (i % 2 == 0) {
					xregion.add(regionarray.get(i));
				} else
					yregion.add(regionarray.get(i));
			}
			for (int i = 0; i < lionarray.size(); i++) {
				if (i % 2 == 0) {
					xlion.add(lionarray.get(i));
				} else
					ylion.add(lionarray.get(i));
			}
			for (int i = 0; i < ambulancearray.size(); i++) {
				if (i % 2 == 0) {
					xambulance.add(ambulancearray.get(i));
				} else
					yambulance.add(ambulancearray.get(i));
			}
			for (int i = 0; i < pondarray.size(); i++) {
				if (i % 2 == 0) {
					xpond.add(pondarray.get(i));
				} else
					ypond.add(pondarray.get(i));
			}
			
			}
	 	 }