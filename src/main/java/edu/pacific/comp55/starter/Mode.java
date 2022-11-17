package edu.pacific.comp55.starter;
import acm.program.*;
import acm.util.*;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;

public class Mode extends GraphicsPane implements ActionListener{
	public static final int WINDOWS_WIDTH = 1920/2, WINDOWS_HEIGHT = 1080/2;
	private static RandomGenerator probability = new RandomGenerator(), toppingChooser = new RandomGenerator(), hazardChooser = new RandomGenerator(), upgradeChooser = new RandomGenerator();
	private static double comboEntryX, comboEntryY, comboLaterX, comboLaterY, lineSlope = 0, lineB = 0;
	protected ArrayList <Topping> toppingArray = new ArrayList<Topping>();
	protected GImage pauseButton, button, wall;
	protected GLabel bothScores;
	protected PauseMenu PMenu;
	protected GameOver gameOver;
	protected int baconSliced, cheeseSliced, eggSliced, scoreCounter, comboCounter = 1;
	protected Timer Timer;
	protected GLine comboLine;
	protected MainMenu MMenu;
	protected MainApplication Mapp;
	protected boolean paused;
	private static boolean isTimerMode;
	
	public Mode() {
		drawBoard();
		paused = false;
		System.out.println("Mode Constructor");
	}
	
	public Mode(MainMenu m, MainApplication x) {
		super();
		MMenu = m;
		Mapp = x;
		drawBoard();
		Timer = new Timer(110,this);
		paused = false;
		System.out.println("Mode Constructor");
	}
	
	
	public void drawBoard() {
		wall = new GImage("BackgroundWall.png");
		wall.scale(0.5);
		pauseButton = new GImage("Pause button.png",1695/2,810/2);
		pauseButton.scale(0.5);
	}
	
	public MainMenu getMMenu() {
		return MMenu;
	}
	
	
	
	public Topping getObject(int x, int y) {
		GObject currTopping = Mapp.getElementAt(x,y); 
		for(Topping t : toppingArray) {
			if (t.getCurX()*-1 == currTopping.getX() && t.getCurY()*-1 == currTopping.getY() && t.getImage() == currTopping) {
				return t;
			}
		}
		return null;
	}
	
	public boolean cutObject(Topping t) {
		if(t.isCut() == false) {
			t.cutTopping();
			return true;
		} else {
			return false;
		}
	}
	
	public void resetAll() {
		//TODO returns all variables back to their original forms.
	}
	
	public void generateObject() {
		int chance = probability.nextInt(1, 100);
		if(chance < 81) { //Toppings 80% chance
			toppingArray.add(new Topping(ToppingType.values()[toppingChooser.nextInt(0,2)], Mapp));
		} else if (chance > 80 && chance < 91) { //Hazards 10% chance
			toppingArray.add(new Topping(ToppingType.values()[hazardChooser.nextInt(3,4)], Mapp));
		} else { //Upgrades 10% chance
			if(isTimerMode) { 
				toppingArray.add(new Topping(ToppingType.values()[upgradeChooser.nextInt(5,6)],Mapp));
			} else {
				if(chance % 2 == 0) {
					toppingArray.add(new Topping(ToppingType.ROCK, Mapp));
				}
			}
		}
	}
	
	public void exceedHighScore() {
		//TODO if scoreCounter is greater than or equal to highscore then 
		// make scoreCounter the new high score.
	}
	
	public boolean fallenOffScreen(Topping t) {
		if(!t.shouldMove()) {
			deleteTopping(t);
			return true;
		}
		return false;
	}
	
	public void deleteTopping(Topping t) {
		Mapp.remove(t.getImage());
		toppingArray.remove(t);
	}
	
	public boolean knifeSharpened() {
		return false;
		//TODO Returns true if knife is sharpened
	}
	
	public boolean inLine(Topping t) {
		return false;
		//TODO Retrun true if Topping is in comboLine.
	}
	//TODO: delete this
	public void stopTimer() {
		Timer.stop();
		//TODO Stops timer.
	}
	public void startTimer() {
		//TODO Start TImer and set PMenu to null
		PMenu = null;
		Timer.start();
	}
	
	public void setPauseToNull() {
		PMenu = null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		System.out.println(x.toString());
		if (paused) {
			paused = false;
			PMenu.mouseClicked(e);
		} else if(x == pauseButton) {
			System.out.println("Open Pause");
			Timer.stop();
			PMenu = new PauseMenu(this, Mapp);
			Mapp.switchToPause(PMenu);
		} else if(x == button) {
			gameOver = new GameOver(this, Mapp,10,10,10);
			Mapp.switchToScreen(gameOver);
			System.out.println("hi");
			paused = true;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(getObject(e.getX(),e.getY()) != null) {
			cutObject(getObject(e.getX(),e.getY()));
		}
	}

	@Override
	public void showContents() {
		Mapp.add(wall);
		Mapp.add(pauseButton);
	}

	@Override
	public void hideContents() {
		for(Topping t: toppingArray) {
			Mapp.remove(t.getImage());
		}
		Mapp.remove(wall);
		Mapp.remove(pauseButton);
	}
	
	public void returnToMenu() {
		//TODO Has Main Menu call isTimeOver()
		PMenu = null;
		gameOver = null;
		System.out.println("Quit the game");
		Mapp.switchToScreen(MMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//    @Override
//    public void actionPerformed(ActionEvent e) {
//    	generateObject();
//    	for (Topping t: toppingArray) {
//    		t.moveTopping();
//    		fallenOffScreen(t);
//    	}
//    }
	
}
