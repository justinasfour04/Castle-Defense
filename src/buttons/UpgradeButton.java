package buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class UpgradeButton extends ClickButton implements Serializable {

	public UpgradeButton(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
	
	@Override
	public void drawButton(Graphics g) {
		
		g.drawImage(images.upgradeIcon.getScaledInstance(size.width, size.height, 0), 0, 0, null);
		super.drawButton(g);
		
//		selectColor(g, REGULAR_BUTTON_COLOR);
//		g.fillRect(position.x, position.y, size.width, size.height);
//		g.setColor(Color.white);
//		g.drawRect(position.x, position.y, size.width, size.height);
//		g.drawString("Upgrade", position.x, position.y + 45);
//		g.drawString("$" + AreaOfEffectTower.COST, position.x, position.y + 60);
		
	}

	@Override
	protected void OnRelease() {
		button_selector.setUpgradeTowerSelected(true);
		setSelected(false);
	}
	

}
