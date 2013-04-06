import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.*;



/**
 *
 * @author hp
 */
public class InterfaceGUI extends JFrame {

	Hashtable<String,Airport> tableAirports; 
	Hashtable<String,Plane> tablePlanes;

	/**
     *
     * @param tableAeroports
     * @param tableAvions
     */
    public InterfaceGUI( Hashtable<String,Airport> tableAeroports, Hashtable<String,Plane> tableAvions) 
	{
		tableAirports = tableAeroports;
		tablePlanes = tableAvions;


	}

	JComboBox aeroports, avions;
	JLabel avionName, avionSource, avionDest, avionDepart, avionPlace, avionSpeed;
	JLabel avionNameR, avionSourceR, avionDestR, avionDepartR, avionPlaceR, avionSpeedR;
	JLabel aeroportName, aeroportPosition, aeroportPistes;
	JLabel aeroportNameR, aeroportPositionR, aeroportPistesR;

	private int x_coin;
	private int y_coin;
	private int hauteur;
	private int largeur;

	//DrawingPanel panel = new DrawingPanel(100,100,600,600); 


	JPanel panel = new JPanel();


	/**
     *
     */
    public final void initUI() {

		int i;
		int n;
		int marge, ecart, marge2;
		int haut, gauche;
		Enumeration<String> listAirports;
		Enumeration<String> listPlanes;


		marge = 25;
		haut = 150;
		gauche = 700;
		ecart = 40;

		
		getContentPane().add(panel);
//		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		//drawingPanel.setBounds(0,0,900,900);
		//getContentPane().add(drawingPanel);

		panel.setLayout(null);
		//panel.setToolTipText("A Panel container");

		n = tableAirports.size();
		listAirports = tableAirports.keys();
		String[] nameAirports = new String[n];
		for(i=0; i<n; i++){
			nameAirports[i] = listAirports.nextElement();
		}

		avionNameR = new JLabel("");
		avionSourceR  = new JLabel("");
		avionDestR  = new JLabel("");
		avionDepartR  = new JLabel("");
		avionPlaceR  = new JLabel("");
		avionSpeedR = new JLabel("");
		aeroportNameR = new JLabel("");
		aeroportPositionR = new JLabel("");
		aeroportPistesR = new JLabel("");

		i =0;
		aeroports = new JComboBox(nameAirports);
		aeroports.setBounds(marge,haut + i*ecart,200,30);
		aeroports.setVisible(false);
		aeroports.setSelectedItem("Lisboa");
		aeroports.addItemListener(new ItemAirport());
		panel.add(aeroports);
		i++;

		aeroportName = new JLabel("Name of the airport:");
		aeroportName.setBounds(marge,haut + i*ecart, 300, 30);
		aeroportName.setVisible(false);
		panel.add(aeroportName);
		i++;

		aeroportPosition = new JLabel("Geographical situation of the airport:");
		aeroportPosition.setBounds(marge, haut + i*ecart, 300, 30);
		aeroportPosition.setVisible(false);
		panel.add(aeroportPosition);
		i++;

		aeroportPistes = new JLabel("Number of runways in the airport:");
		aeroportPistes.setBounds(marge, haut + i*ecart, 300, 30);
		aeroportPistes.setVisible(false);
		panel.add(aeroportPistes);
		i++;


		JButton airportsButton = new JButton("Airports");
		airportsButton.setBounds(gauche + 100, 60, 100, 30);
		airportsButton.setToolTipText("Voir tous les aéroports");
		airportsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				avions.setVisible(false);
				avionName.setVisible(false);
				avionSource.setVisible(false);
				avionDest.setVisible(false);
				avionDepart.setVisible(false);
				avionPlace.setVisible(false);
				avionSpeed.setVisible(false);
				avionNameR.setVisible(false);
				avionSourceR.setVisible(false);
				avionDestR.setVisible(false);
				avionDepartR.setVisible(false);
				avionPlaceR.setVisible(false);
				avionSpeedR.setVisible(false);
				aeroportNameR.setVisible(false);
				aeroportPositionR.setVisible(false);
				aeroportPistesR.setVisible(false);
				aeroports.setVisible(true);
				aeroportName.setVisible(true);
				aeroportPosition.setVisible(true);
				aeroportPistes.setVisible(true);

			}
		});  
		panel.add(airportsButton);

		n = tablePlanes.size();
		listPlanes = tablePlanes.keys();
		String[] namePlanes = new String[n];
		for(i=0; i<n; i++){
			namePlanes[i] = listPlanes.nextElement();
		}

		i=0;
		avions = new JComboBox(namePlanes);
		avions.setBounds(marge,haut + i*ecart,200,30);
		//avions.setForeground(Color.yellow);
		avions.setVisible(false);
		avions.addItemListener(new ItemPlane());
		panel.add(avions);
		i++;

		avionName = new JLabel("Name of the plane:");
		avionName.setBounds(marge, haut + i*ecart, 200, 30);
		avionName.setVisible(false);
		panel.add(avionName);
		i++;

		avionSource = new JLabel("Source of the plane:");
		avionSource.setBounds(marge, haut + i*ecart, 200, 30);
		avionSource.setVisible(false);
		panel.add(avionSource);
		i++;

		avionDest = new JLabel("Destination of the plane:");
		avionDest.setBounds(marge, haut + i*ecart, 200, 30);
		avionDest.setVisible(false);
		panel.add(avionDest);
		i++;

		avionDepart = new JLabel("Take off date of the plane:");
		avionDepart.setBounds(marge, haut + i*ecart, 200, 30);
		avionDepart.setVisible(false);
		panel.add(avionDepart);
		i++;

		avionPlace = new JLabel("Actual place of the plane:");
		avionPlace.setBounds(marge,haut + i*ecart, 200, 30);
		avionPlace.setVisible(false);
		panel.add(avionPlace);
		i++;

		avionSpeed = new JLabel("Speed of the plane:");
		avionSpeed.setBounds(marge, haut + i*ecart, 200, 30);
		avionSpeed.setVisible(false);
		panel.add(avionSpeed);
		i++;


		JButton planesButton = new JButton("Planes");
		planesButton.setBounds(gauche + 200, 60, 100, 30);
		planesButton.setToolTipText("Voir tous les avions");
		planesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				aeroports.setVisible(false);
				aeroportName.setVisible(false);
				aeroportPosition.setVisible(false);
				aeroportPistes.setVisible(false);
				aeroportNameR.setVisible(false);
				aeroportPositionR.setVisible(false);
				aeroportPistesR.setVisible(false);
				avionNameR.setVisible(false);
				avionSourceR.setVisible(false);
				avionDestR.setVisible(false);
				avionDepartR.setVisible(false);
				avionPlaceR.setVisible(false);
				avionSpeedR.setVisible(false);
				avions.setVisible(true);
				avionName.setVisible(true);
				avionSource.setVisible(true);
				avionDest.setVisible(true);
				avionDepart.setVisible(true);
				avionPlace.setVisible(true);
				avionSpeed.setVisible(true);
			}
		});  
		panel.add(planesButton);

		JButton refresh = new JButton("Refresh");
		refresh.setBounds(gauche, 60 , 100, 30);
		refresh.setToolTipText("Rafraîchir la page");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				aeroports.setVisible(false);
				aeroportName.setVisible(false);
				aeroportPosition.setVisible(false);
				aeroportPistes.setVisible(false);
				avions.setVisible(false);
				avionName.setVisible(false);
				avionSource.setVisible(false);
				avionDest.setVisible(false);
				avionDepart.setVisible(false);
				avionPlace.setVisible(false);
				avionSpeed.setVisible(false);
				aeroportNameR.setVisible(false);
				aeroportPositionR.setVisible(false);
				aeroportPistesR.setVisible(false);
				avionNameR.setVisible(false);
				avionSourceR.setVisible(false);
				avionDestR.setVisible(false);
				avionDepartR.setVisible(false);
				avionPlaceR.setVisible(false);
				avionSpeedR.setVisible(false);
			}
		});  
		panel.add(refresh);


		JLabel date = new JLabel("Date: ");
		date.setBounds(875, 90, 70, 70);
		panel.add(date);

		ImageIcon map = new ImageIcon("Carte2.png","map");
		JLabel carte = new JLabel(map);
		carte.setBounds(700,150,1050,650);
		carte.setToolTipText("Carte du monde");
		panel.add(carte);



		setTitle("Regarde où va ton avion!!!");
		setSize(1900, 1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setBounds(0,0,900,900);
		getContentPane().add(panel);






	}

	/*
    public void paint(Graphics g){
	x_coin = 700;
	y_coin = 150;
	largeur = 1050;
	hauteur = 650;
	GlobalData G = new GlobalData();
	List_carte L = new List_carte(x_coin,y_coin,x_coin+largeur,y_coin+hauteur,G.airportsCharacteristics());
	LinkedList<Airport_pos> l  = L.getAirportPosition();
	int longueur = l.size();
	System.out.printf("%d",longueur);
	int i=0;
	g.drawRect (x_coin,y_coin,largeur,hauteur);  
	while(i<160) 
	    {
		Airport_pos XY = l.removeFirst();
		int x = (int)Math.round(XY.getX());
		int y = (int)Math.round(XY.getY());
		g.drawArc(x,y,10,10,0,360);
		i=i+1;
	    }

    }
	 */

	class ItemAirport implements ItemListener{

		int marge2, i, ecart, haut;
		Airport aeroport;
		Plane avion;

		public void itemStateChanged(ItemEvent e) {
			i=1;
			marge2 = 300;
			ecart = 40;
			haut = 150;
			aeroportNameR.setVisible(false);
			aeroportPositionR.setVisible(false);
			aeroportPistesR.setVisible(false);
			avionNameR.setVisible(false);
			avionSourceR.setVisible(false);
			avionDestR.setVisible(false);
			avionDepartR.setVisible(false);
			avionPlaceR.setVisible(false);
			avionSpeedR.setVisible(false);
			aeroport = tableAirports.get(e.getItem());
			aeroportNameR = new JLabel(aeroport.name);
			aeroportNameR.setBounds(marge2,haut + i*ecart, 300, 30);
			aeroportNameR.setVisible(true);
			panel.add(aeroportNameR);
			i++;
			Point3D position;
			position = aeroport.position;
			aeroportPositionR = new JLabel("("+position.x+","+position.y+")");
			aeroportPositionR.setBounds(marge2, haut + i*ecart, 300, 30);
			aeroportPositionR.setVisible(true);
			panel.add(aeroportPositionR);
			i++;
			aeroportPistesR = new JLabel(String.valueOf(aeroport.numberOfRunways));
			aeroportPistesR.setBounds(marge2, haut + i*ecart, 300, 30);
			aeroportPistesR.setVisible(true);
			panel.add(aeroportPistesR);
			i++;
		}
	}

	class ItemPlane implements ItemListener{

		int marge2, i, ecart, haut;
		Airport aeroport;
		Plane avion;

		public void itemStateChanged(ItemEvent e) {
			i = 1;
			marge2 = 300;
			ecart = 40;
			haut = 150;
			aeroportNameR.setVisible(false);
			aeroportPositionR.setVisible(false);
			aeroportPistesR.setVisible(false);
			avionNameR.setVisible(false);
			avionSourceR.setVisible(false);
			avionDestR.setVisible(false);
			avionDepartR.setVisible(false);
			avionPlaceR.setVisible(false);
			avionSpeedR.setVisible(false);
			avion = tablePlanes.get(e.getItem());
			avionNameR = new JLabel(avion.getName());
			avionNameR.setBounds(marge2, haut + i*ecart, 200, 30);
			avionNameR.setVisible(true);
			panel.add(avionNameR);
			i++;
			aeroport = avion.getInitialSourceAirport();
			avionSourceR = new JLabel(aeroport.name);
			avionSourceR.setBounds(marge2, haut + i*ecart, 200, 30);
			avionSourceR.setVisible(true);
			panel.add(avionSourceR);
			i++;
			aeroport = avion.getInitialSourceAirport();
			avionDestR = new JLabel(aeroport.name);
			avionDestR.setBounds(marge2, haut + i*ecart, 200, 30);
			avionDestR.setVisible(true);
			panel.add(avionDestR);
			i++;
			avionDepartR = new JLabel("");
			avionDepartR.setBounds(marge2, haut + i*ecart, 200, 30);
			avionDepartR.setVisible(true);
			panel.add(avionDepartR);
			i++;
			avionPlaceR = new JLabel("");
			avionPlaceR.setBounds(marge2,haut + i*ecart, 200, 30);
			avionPlaceR.setVisible(true);
			panel.add(avionPlaceR);
			i++;
			avionSpeedR = new JLabel(Double.toString(avion.getSpeed()));
			avionSpeedR.setBounds(marge2, haut + i*ecart, 200, 30);
			avionSpeedR.setVisible(true);
			panel.add(avionSpeedR);
			i++;
		}
	}


	/*
    class DrawingPanel extends JPanel{
	private int x_coin;
	private int y_coin;
	private int hauteur;
	private int largeur;



	public DrawingPanel(int x, int y, int la, int lo){
	    x_coin=x;
	    y_coin=y;
	    hauteur=lo;
	    largeur=la;
	}

	public void paint(Graphics g){

	    GlobalData G = new GlobalData();
	    List_carte L = new List_carte(x_coin,y_coin,x_coin+largeur,y_coin+hauteur,G.airportsCharacteristics());
	    LinkedList<Airport_pos> l  = L.getAirportPosition();
	    int longueur = l.size();
	    System.out.printf("%d",longueur);
	    int i=0;
	    g.drawRect (x_coin,y_coin,largeur,hauteur);  
	    while(i<160) 
	    {
		Airport_pos XY = l.removeFirst();
		int x = (int)Math.round(XY.getX());
		int y = (int)Math.round(XY.getY());
		g.drawArc(x,y,10,10,0,360);
		i=i+1;
	    }
	}


    }
	 */

}