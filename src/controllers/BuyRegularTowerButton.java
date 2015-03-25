package controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import towerModels.RegularTower;

public class BuyRegularTowerButton extends Button {


	
	public BuyRegularTowerButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}

	@Override
	public void drawButton(Graphics g) {
		
		selectColor(g, BUTTON_COLOR);
		g.fillRect(position.x, position.y, size.width, size.height);
		g.setColor(Color.white);
		g.drawRect(position.x, position.y, size.width, size.height);
		g.drawString("Regular", position.x, position.y + 45);
		g.drawString("$" + RegularTower.COST, position.x, position.y + 60);
		g.setColor(RegularTower.TOWER_COLOR);
		g.fillOval(position.x + 2, position.y + 2, size.width - 4, size.height - 4);
	}

}
