package edu.pacific.comp55.starter;
import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
public class MainMenu extends GraphicsProgram{
	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	private GImage gif, instruction, backArrow, piizaWithTitle, help, quit, noWasteModeButton, timerModeButton;
	private TimerMode timerMode;
	private NoWasteMode noWasteMode;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void run() {
		System.out.println("Hello");
	}
	
	public void drawMainMenu(){
		//Set up the menu screen
	}
	
	public void startingAnimation() {
		//Start the animation
	}
	
	public void help() {
		//It should show up the help gImage
	}
	
	public void quit() {
		//quit the game
	}
	
	public void creatTimer() {
		//TODO Create Timer Mode and check if it is Gamer Over
	}
	
	public boolean isTimerOver(boolean returned) {
		return returned;
	}
	
public void creatWaste() {
		//TODO Create No Waste Mode and check if it is Gamer Over
	}
	
	public boolean isWasteOver(boolean returned) {
		return returned;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	public static void main (String[] args) {
		new MainMenu().start();
	}

}
