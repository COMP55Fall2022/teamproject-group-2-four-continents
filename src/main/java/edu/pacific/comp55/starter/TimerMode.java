package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class TimerMode extends Mode{
	int timer = 60;
	GLabel timerDisplay;
	int highScore;
	String timer2 = String.valueOf(timer);
	
	public TimerMode() {
		super();
		drawTimer();
		
	}
	
	public TimerMode(MainMenu m, GraphicsApplication x) {
		super(m,x);
		drawTimer();
	}
	
	public void drawTimer() {
		timerDisplay = new GLabel(timer2, 1200, 50);
		timerDisplay.setColor(Color.ORANGE);
		timerDisplay.setFont("Arial-Bold-50");
	}
	
	@Override 
	public boolean cutObject(Topping a) {
		return false;
		//TODO Return true if topping.isCut() returns true, and check for hazards specific to mode.
	}
	
	
	public void GameOver() { // not sure where to call gameOver
		//TODO Calls new instance of GameOver
		gameOver = new GameOver(this, Gapp, baconSliced, cheeseSliced, eggSliced);
		Gapp.switchToScreen(gameOver);
	}
	
	public void importHighScore() {
		//TODO Copies high score of specific mode from text file.
	}
	
	public void exportHighScore(int score) {
		//TODO Copies high score of mode to text file.
	}
	
	@Override
	public void resetAll() {
		//TODO Reset all parameters to their original forms.
		PMenu = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Hi");
		GObject x = Gapp.getElementAt(e.getX(), e.getY());
		
		if(x == temp_Exit) {
			System.out.println("hi");
			Gapp.switchToScreen(MMenu);
		}
		else if(x == pauseButton) {
			System.out.println("Open Pause");
			PMenu = new PauseMenu(this, Gapp);
			Gapp.switchToPause(PMenu);
		}
	}	
	@Override
	public void showContents() {
		Gapp.add(wall);
		Gapp.add(pauseButton);
		Gapp.add(temp_Exit);
		Gapp.add(timerDisplay);
	}

	@Override
	public void hideContents() {
		Gapp.remove(wall);
		Gapp.remove(pauseButton);
		Gapp.remove(temp_Exit);
		Gapp.remove(timerDisplay);
	}
}
