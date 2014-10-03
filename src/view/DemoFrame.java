package view;


import java.awt.Point;

import javax.swing.JFrame;

public class DemoFrame extends JFrame{
	
	public DemoFrame(Point size, Point location){
		super();
		this.setBounds(location.x, location.y, size.x, size.y);
	}

	
}
