import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ShapeClicker {
	public  ShapeClicker(JPanel panel, int[] xra, int[] yra, int[] xla, int[] yla, int[] xpa, int[] ypa, int[] xaa, int[] yaa) {
        initComponents(panel,xra,yra,xla,yla,xpa,ypa,xaa,yaa);
        panel.setBackground(Color.WHITE);
        panel.setVisible(true);
    }
	ShapePanel pnl;
    private void initComponents(JPanel panel, int[] xra, int[] yra, int[] xla, int[] yla, int[] xpa, int[] ypa, int[] xaa, int[] yaa) {
        pnl= new ShapePanel(panel,xra,yra,xla,yla,xpa,ypa,xaa,yaa);
        pnl.setBackground(Color.WHITE);
    	panel.add(pnl);
    }

	public void ML(int i) {
		// TODO Auto-generated method stub
		pnl.ML(i);
	}
}

//custom panel
class ShapePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//List<Shape> polygon=new List<Shape>();
		public JPanel frame;
		public static Shape polygon[]=new Shape[16];
		public static int a=0;
		public static int flag=0;
		public static ArrayList<Integer> xregion = new ArrayList<Integer>();
		public static ArrayList<Integer> yregion = new ArrayList<Integer>();
		public static ArrayList<Integer> xlions = new ArrayList<Integer>();
		public static ArrayList<Integer> ylions = new ArrayList<Integer>();
		public static ArrayList<Integer> xponds = new ArrayList<Integer>();
		public static ArrayList<Integer> yponds = new ArrayList<Integer>();
		public static ArrayList<Integer> xambulances = new ArrayList<Integer>();
		public static ArrayList<Integer> yambulances = new ArrayList<Integer>();
		public static ArrayList<Integer> xlionlist=new ArrayList<Integer>();
		public static ArrayList<Integer> ylionlist=new ArrayList<Integer>();
		public static ArrayList<Integer> xpondlist=new ArrayList<Integer>();
		public static ArrayList<Integer> ypondlist=new ArrayList<Integer>();
		public static ArrayList<Integer> xlchanged=new ArrayList<Integer>();
		public static ArrayList<Integer> ylchanged=new ArrayList<Integer>();
		public static ArrayList<Integer> xpchanged=new ArrayList<Integer>();
		public static ArrayList<Integer> ypchanged=new ArrayList<Integer>();
		  
	    static int globalxpond=0;
	    static int globalxlion=0;
	    int xp=0;
		 		
    private Dimension dim = new Dimension(750, 600);
    private ArrayList<Shape> shapes;
    public ShapePanel(JPanel panel, int[] xra, int[] yra, int[] xla, int[] yla, int[] xpa, int[] ypa, int[] xaa, int[] yaa) {
    	frame=panel;
    	frame.setBackground(Color.white);
    	// TODO Auto-generated constructor stub
    	for(int i=0;i<xra.length;i++){
			xregion.add(xra[i]);
			yregion.add(yra[i]);
		}
		for(int i=0;i<xla.length;i++){
			xlions.add(xla[i]);
			ylions.add(yla[i]);
		}
		for(int i=0;i<xpa.length;i++){
			xponds.add(xpa[i]);
			yponds.add(ypa[i]);
		}
		for(int i=0;i<xaa.length;i++){
			xambulances.add(xaa[i]);
			yambulances.add(yaa[i]);
		}
		}
    
	public void ML(int v)
    {
		flag=v;
		a=0;
		xlionlist.clear();
		ylionlist.clear();
		xpondlist.clear();
		ypondlist.clear();
		frame.repaint();
    }

public void drawregion(Graphics g, int[] xPoints, int[] yPoints){
	   	 int i,q=0,y=0;
		 int num_blocks=xPoints.length/5;
		 int [] xpt=new int[5];
		 int [] ypt= new int[5];
		 int [][] twodx = new int [num_blocks] [5];
		 int [][] twody = new int [num_blocks] [5];
		 for(i=0;i<num_blocks;i++)
		 for(int j=0;j<5;j++)
		 {
		 twodx[i][j]=xPoints[q];
		 twody[i][j]=yPoints[q];
		 q++;
		 }
		 int nb=0;
		 while(nb<num_blocks)
		 {
		 for(y=0;y<5;y++)
		 {
		 xpt[y]=twodx[nb][y];
		 ypt[y]=twody[nb][y];
		 }
		 g.drawPolyline(xpt, ypt, 5);
		 polygon[a]=new Polygon(xpt,ypt,5);
		 a++;
		 nb++;
		 }
  }
  
  public void drawpond(Graphics g, int[] xpond, int[] ypond){
	  int pond_blocks=xpond.length/3;
		int [][]xpnd=new int[pond_blocks][3];
		int [][]ypnd=new int[pond_blocks][3];
		int []xpd=new int[3];
		int []ypd=new int[3];
		int xp=0;
		int sendxpond[] = new int[pond_blocks];
		int sendypond[] = new int[pond_blocks];
		int w=0;
		for(int i=0;i<pond_blocks;i++)
		 for(int j=0;j<3;j++)
		 {
		 xpnd[i][j]=xpond[w];
		 ypnd[i][j]=ypond[w];
		 w++;
		 }
		 int nb1=0;
		 while(nb1<pond_blocks)
		 {
		 for(int y=0;y<3;y++)
		 {
		 xpd[y]=xpnd[nb1][y];
		 ypd[y]=ypnd[nb1][y];
		 }
		 int xc = xpd[0];
		 int yc = ypd[1];
		 int r = (xpd[2]-xpd[0])/2;
		 sendxpond[xp]=xc;
		 sendypond[xp]=yc;
		 xp++;
		 g.setColor(Color.BLACK);
		 g.drawOval(xc, yc, r, r);
		 
		 g.setColor(Color.BLUE);
		 g.fillOval(xc, yc, r, r);
		 nb1++;
		 }  
  }
  
  public void drawlion(Graphics g, int[] xlion, int[] ylion){
	  	for(int k=0;k<xlion.length;k++) 
		{ 
		g.setColor(Color.GREEN);
		g.fillOval(xlion[k], ylion[k], 5, 5); 
		}
  }
  
  public void addpolygon(){
	  	shapes = new ArrayList<>();
	  	for(int i1=0;i1<polygon.length;i1++)
	  	{
    	shapes.add(polygon[i1]);	
	  	}
  }
  protected void paintComponent(final Graphics g) {
	  super.paintComponent(g);
	  frame.setBackground(Color.white);
	   int xPoints[]=new int[xregion.size()];
			int yPoints[]=new int[yregion.size()];
			int xlion[]=new int[xlions.size()];
			int ylion[]=new int[ylions.size()];
			int xpond[]=new int[xponds.size()];
			int ypond[]=new int[yponds.size()];
			int xambulance[]=new int[xambulances.size()];
			int yambulance[]=new int[yambulances.size()];

			for (int i=0;i<xregion.size();i++){
				xPoints[i]=xregion.get(i);
				yPoints[i]=yregion.get(i);
			}
			for(int i=0;i<xlions.size();i++){
				xlion[i]=xlions.get(i);
				ylion[i]=ylions.get(i);
			}
			for(int i=0;i<xponds.size();i++){
				xpond[i]=xponds.get(i);
				ypond[i]=yponds.get(i);
			}
			for(int i=0;i<xambulances.size();i++){
				xambulance[i]=xambulances.get(i);
				yambulance[i]=yambulances.get(i);
			}
			  
		drawregion(g,xPoints,yPoints);
    	drawpond(g,xpond,ypond);
    	drawlion(g,xlion,ylion);

    	addpolygon();
    	if(flag!=0){
    	g.setColor(Color.RED);
    	for(int i=0;i<xlchanged.size();i++){
    		g.fillOval(xlchanged.get(i), ylchanged.get(i), 8, 8);    		
    	}
    	for(int i=0;i<xpchanged.size();i++){
    		g.fillOval(xpchanged.get(i), ypchanged.get(i), 15, 15);
    	}
    	}
    	for(int i=0;i<xlions.size();i++){
			xlion[i]=xlions.get(i);
			ylion[i]=ylions.get(i);
		}
		for(int i=0;i<xponds.size();i++){
			xpond[i]=xponds.get(i);
			ypond[i]=yponds.get(i);
		}
		
		int pond_blocks=xpond.length/3;
		int [][]xpnd=new int[pond_blocks][3];
		int [][]ypnd=new int[pond_blocks][3];
		int []xpd=new int[3];
		int []ypd=new int[3];
		int xp=0;
		int sendxpond[] = new int[pond_blocks];
		int sendypond[] = new int[pond_blocks];
		int w=0;
		for(int i=0;i<pond_blocks;i++)
		 for(int j=0;j<3;j++)
		 {
		 xpnd[i][j]=xpond[w];
		 ypnd[i][j]=ypond[w];
		 w++;
		 }
		 int nb1=0;
		 while(nb1<pond_blocks)
		 {
		 for(int y=0;y<3;y++)
		 {
		 xpd[y]=xpnd[nb1][y];
		 ypd[y]=ypnd[nb1][y];
		 }
		 int xc = xpd[0];
		 int yc = ypd[1];
		 sendxpond[xp]=xc;
		 sendypond[xp]=yc;
		 xp++;
		 nb1++;
		 }
		addMouseListener(new MouseAdapter() {
        	@Override
            public void mousePressed(MouseEvent me) {
                super.mousePressed(me);
                for (Shape s : shapes) {
                    if (s.contains(me.getPoint())) {//check if mouse is clicked within shape
                        if (s instanceof Polygon) {
                        	if(flag==1)
                        	{
                    			xpondlist.clear();
                    			ypondlist.clear();
                    			xlionlist.clear();
                    			ylionlist.clear();
                           // System.out.println("Clicked!!"+s);
                        	for(int i=0;i<xlions.size();i++)
                        	{
                        		if(s.contains((double)xlions.get(i), (double)ylions.get(i)))
                        		{
                        			xlionlist.add(xlions.get(i));
                        			ylionlist.add(ylions.get(i));
                        			//System.out.println("XLIONLIST Coordinates are X"+xlionlist+" and Y"+ylionlist);	
                        		}
                        	}
                        
                        	for(int m=0;m<sendxpond.length;m++)
                        	{
                        		if(s.contains((double)sendxpond[m],(double)sendypond[m]))
                        		{
                        			xpondlist.add(sendxpond[m]);
                        			ypondlist.add(sendypond[m]);
                        			//System.out.println("POND cordinates are X "+xpondlist+" Y cordi"+ypondlist);
                        			
                        		}
                        		
                        	}
                        	a=0;
                        	changer(xlionlist, ylionlist,xpondlist,ypondlist);
                        	//frame.repaint();
                        	}
                        }
                    }
                }
                }
        });
    }

  
@SuppressWarnings("unchecked")
public void changer(ArrayList<Integer> xlionlist2, ArrayList<Integer> ylionlist2, ArrayList<Integer> xpondlist2, ArrayList<Integer> ypondlist2) {
		// TODO Auto-generated method stub
	  xlchanged=(ArrayList<Integer>) xlionlist2.clone();
	  ylchanged=(ArrayList<Integer>) ylionlist2.clone();
	  xpchanged=(ArrayList<Integer>) xpondlist2.clone();
	  ypchanged=(ArrayList<Integer>) ypondlist2.clone();
	  globalxlion=1;
	  globalxpond=1;
	  frame.repaint();
  }

	@Override
    public Dimension getPreferredSize() {
        return dim;
    }
}