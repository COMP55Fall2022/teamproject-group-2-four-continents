package edu.pacific.comp55.starter;
import acm.program.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import acm.graphics.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.Timer;

public class NoWasteMode extends Mode implements ActionListener{
	GPoint counter = new GPoint(830,50);
	GImage counterX = new GImage("lives3.png",750,20);
	GLabel highScoreDisplay;
	int highScore;
	String filePath = "lives";
	String fileName = ".png";
	static int count = 0;
	String oldLine;
	static int lives = 3;
	protected Timer gifTimer;
	
	
	public NoWasteMode(MainMenu m, MainApplication ma) {
		super(m, ma);
		isTimerMode = false;
		counterX.scale(0.5);
		try {
			importHighScore();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gifTimer = new Timer(1400,this);
		oldLine = "NoWaste: " + String.valueOf(highScore);
		highScoreDisplay = new GLabel("High Score: " + String.valueOf(highScore), 100/2, 970/2);
		System.out.println("NoWaste Constructor");
		
	}
	
	public void drawXCounter() {
		counterX.setImage(filePath + (lives - wasteCount) + fileName);
		highScoreDisplay.setColor(Color.decode("#ffdaa7"));
		highScoreDisplay.setFont(MainApplication.customFont);
	}
	
	
	public void importHighScore() throws FileNotFoundException {
		//TODO Copies high score of specific mode from text file.
		try {
			File file = new File("src/main/resources/HighScore.txt");
			Scanner myReader = new Scanner(file);
			String highestScore = myReader.nextLine();
			highestScore = highestScore.replaceAll("[^0-9]","");
			highScore = Integer.parseInt(highestScore);
			System.out.println(highScore);
			myReader.close();
		} catch(FileNotFoundException e) {
			System.out.println("An error occured");
		}
		
		
	}
	
	public void exportHighScore() {
		//TODO Copies high score of mode to text file.
		String newLine = "NoWaste: " + String.valueOf(highScore);
		try {
			Scanner HighScore = new Scanner(new File("src/main/resources/HighScore.txt"));
			StringBuffer buffer = new StringBuffer();
			
			while(HighScore.hasNextLine()) {
				buffer.append(HighScore.nextLine() + System.lineSeparator());
			}
			String scObj = buffer.toString();
			HighScore.close();
			scObj = scObj.replaceAll(oldLine, newLine);
			FileWriter writer = new FileWriter("src/main/resources/HighScore.txt");
			writer.append(scObj);
			System.out.println("High Score updated." + highScore);
			writer.close();
	
		}catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	@Override
	public void resetAll() {
		super.resetAll();
		wasteCount = 0;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("No Waste Mouce Clicked");
		GObject x = Mapp.getElementAt(e.getX(), e.getY());
		if(paused) {
			PMenu.mouseClicked(e);
			System.out.println("send to pause");
		} else if(x == pauseButton) {
			System.out.println("Open Pause");
			stopTimer();
			PMenu = new PauseMenu(this, Mapp, MMenu);
			PMenu.showContents();
		}
		generateObject();

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		if(scoreCounter >= highScore) {
			highScore = scoreCounter;
			highScoreDisplay.setLabel("High Score: " + String.valueOf(highScore));
		}
	}
	@Override
	public void showContents() {
		super.showTopContents();
		gifTimer.start();

	}
	
	public void mainShowContent() {
		drawXCounter();
		super.showContents();
		Mapp.add(counterX);
		Mapp.add(highScoreDisplay);
//		super.showTopContents();
		startTimer();
	}
	
//	@Override
//	public void showContents() {
//		drawXCounter();
//		super.showContents();
//		Mapp.add(counterX);
//		Mapp.add(highScoreDisplay);
//		super.showTopContents();
//		startTimer();
////		super.showTopContents();	
//		PMenu = null;
//		startTimer();
//	}

	@Override
	public void hideContents() {
		super.hideContents();
		Mapp.remove(counterX);
	}
	
	@Override
	public void callGameOver() {
		super.callGameOver();
		exportHighScore();
	}

	 public void actionPerformed(ActionEvent e) {
		 	Object source = e.getSource();
			super.actionPerformed(e);
			if(iterationCount == 5) {
				tossToppings();
				iterationCount=0;
			}
			runToppings();
			drawXCounter();
			if(wasteCount == 3) {
				stopTimer();
				callGameOver();
			}
			iterationCount++;
			if (source == gifTimer) {
				mainShowContent();
				gifTimer.stop();
			}
	 }
}