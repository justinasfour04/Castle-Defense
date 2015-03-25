package controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javafx.scene.control.Cell;

import javax.swing.Timer;

import map.Map;
import critterModels.Critter;
import domain.Player;
import presentation.*;
import towerModels.*;

public class GameController implements ActionListener {

	private Player player;
	private Map map;
	private Field field;
	private SideMenu side_menu;
	private Timer timer;
	private MouseMaster mouse_master;
	private ArrayList<Tower> list_of_towers_on_map;
	private ArrayList<Critter> list_of_critters_on_map;
	private ArrayList<Button> list_of_buttons;
	
	private boolean mouseClicked;
	private int mousePos[];
	private int mouse_position_at_click[];
	
	public GameController() {
		
		mouse_master = new MouseMaster();
		player = Player.getPlayerInstance();
		map = Map.createGeneric();		
		list_of_critters_on_map = new ArrayList<>();
		list_of_towers_on_map = new ArrayList<>();
		list_of_buttons = new ArrayList<>();
		
		//create Field with paint function defined in controller
		setField(new Field() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doFieldDrawing(g);
		        Toolkit.getDefaultToolkit().sync();
			}
		});
		
		// populate field with cells
		for (int i = 0; i < map.Grid.length; i++) {
			for (int j = 0; j < map.Grid[0].length; j++) {
				field.add(map.Grid[i][j].getComponent());
			}
		}
		
		setSideMenu(new SideMenu() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				doMenuDrawing(g);
		        Toolkit.getDefaultToolkit().sync();
			}
		});
		
		// Set up the buttons
		side_menu.add(new BuyRegularTowerButton(10, 80, 30, 30));
		side_menu.add(new BuySplashTowerButton(70, 80, 30, 30));
		side_menu.add(new BuyRadialTowerButton(130, 80, 30, 30));
		
		timer = new Timer(Application.TIMEOUT,this);
		timer.start();
		
	}
	
	protected void doFieldDrawing(Graphics g) {
		drawGrid(g);
	}
	
	protected void doMenuDrawing(Graphics g) {
		drawSideMenu(g);
	}
	
	/**
	 * Draws all the cells in the grid
	 * @param g
	 */
	private void drawGrid(Graphics g) {
		
		for (int i = 0; i < field.getComponents().length; i++) {
			field.getComponent(i).paint(g);
		}
	}
	
	private void drawSideMenu(Graphics g) {
		
		g.setColor(Color.white);
		g.drawString("Lives: " + player.getLives(), 10, 20);
		g.drawString("Money: " + player.getMoney(), 10, 40);
		g.drawString("Towers: ", 10, 70);
		
		for (int i = 0; i < side_menu.getComponents().length; i++) {
			side_menu.getComponent(i).paint(g);
		}
		
	}

	private void fireTowers() {
		
		for (Tower tower : list_of_towers_on_map) {
			tower.setAllCrittersOnMap(list_of_critters_on_map);
			tower.fire();
		}
		
	}
	
	
	private void buyTower() {
		
		
		
	}
	
	private void placeTower() {
		
		field.getGraphics().setColor(Color.BLACK);
		field.getGraphics().drawRect(mouse_position_at_click[0], mouse_position_at_click[1], 15, 15);
		field.paint(field.getGraphics());
//		field.paintComponents(field.getGraphics());
	}
	
	private void sellTower(Tower tower) {
		player.changeMoney(tower.getRefundValue());
		list_of_towers_on_map.remove(tower);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (mouseClicked) {
			mouseClicked = false;
			System.out.println("x="+mouse_position_at_click[0]+" y="+mouse_position_at_click[1]);
			placeTower();
		}
		
		fireTowers();
		field.repaint();
		side_menu.repaint();
		
	}

	
	/*
	 * Getters and Setters
	 */
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}

	public void setMouseClicked(boolean mouseClicked) {
		this.mouseClicked = mouseClicked;
	}

	public int[] getMousePos() {
		return mousePos;
	}

	public void setMousePos(int mousePos[]) {
		this.mousePos = mousePos;
	}

	public int[] getMousePositionAtClick() {
		return mouse_position_at_click;
	}

	public void setMousePositionAtClick(int mouse_position_at_click[]) {
		this.mouse_position_at_click = mouse_position_at_click;
	}

	public SideMenu getSideMenu() {
		return side_menu;
	}

	public void setSideMenu(SideMenu side_menu) {
		this.side_menu = side_menu;
	}

	private class MouseMaster extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
				
//			System.out.println(e.getComponent().getX());
//			
//			// Selects a cell
//			for (int i = 0; i < map.Grid.length; i++) {
//				for (int j = 0; j < map.Grid[0].length; j++) {
//					map.Grid[i][j].selectCell(e.getX(), e.getY());;
//				}
//			}
//			
//			for (Button button : list_of_buttons) {
//				button.selectCell(e.getX(), e.getY());
//			}
//			
//			setMouseClicked(true);
//			int temp[] = new int[2];
//			temp[0] = e.getX();
//			temp[1] = e.getY();
//			setMousePositionAtClick(temp);

		}
		
		
		
		@Override
		public void mouseMoved(MouseEvent e) {
//			
//			System.out.println("MOVED! ZOMEGGS");
//			
//			for (Button button : list_of_buttons) {
//				button.hoverCell(e.getX(), e.getY());
//			}
//			
//			int temp[] = new int[2];
//			temp[0] = e.getX();
//			temp[1] = e.getY();
//			setMousePos(temp);

		}

	}

	
}
