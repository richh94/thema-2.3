package model.robot;

import model.virtualmap.OccupancyMap;

import java.io.PipedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.PipedOutputStream;
import java.io.IOException;

import java.util.StringTokenizer;

/**
 * Title    :   The Mobile Robot Explorer Simulation Environment v2.0
 * Copyright:   GNU General Public License as published by the Free Software Foundation
 * Company  :   Hanze University of Applied Sciences
 *
 * @author Dustin Meijer        (2012)
 * @author Alexander Jeurissen  (2012)
 * @author Davide Brugali       (2002)
 * @version 2.0
 */

public class MobileRobotAI implements Runnable {

	private final OccupancyMap map;
	private final MobileRobot robot;

	public boolean running;

	public MobileRobotAI(MobileRobot robot, OccupancyMap map) {
		this.map = map;
		this.robot = robot;

	}

	/**
	 * In this method the gui.controller sends commands to the robot and its devices.
	 * At the moment all the commands are hardcoded.
	 * The exercise is to let the gui.controller make intelligent decisions based on
	 * what has been discovered so far. This information is contained in the OccupancyMap.
	 */
	public void run() {
		String result;
		this.running = true;
		double position[] = new double[3];
		double measures[] = new double[360];
		
		double closest_measure_value;
		int closest_measure;
		
		boolean wall_to_follow = false;
		
		while (running) {
			try {

				PipedInputStream pipeIn = new PipedInputStream();
				BufferedReader input = new BufferedReader(new InputStreamReader(pipeIn));
				PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);

				robot.setOutput(output);

//      ases where a variable value is never used after its assignment, i.e.:
				System.out.println("intelligence running");
				
				robot.sendCommand("R1.GETPOS");
				result = input.readLine();
				parsePosition(result, position);

				robot.sendCommand("L1.SCAN");
				result = input.readLine();
				parseMeasures(result, measures);
				map.drawLaserScan(position, measures);
				
				
				if (!wall_to_follow) {
					closest_measure_value = 100.0;
					closest_measure = 0;
					for (int i = 0; i < 360; i++) {
						if (measures[i] < closest_measure_value) {
							closest_measure_value = measures[i];
							closest_measure = i;
						}
					}
					
					if (closest_measure_value == 100.0) {
						robot.sendCommand("P1.MOVEFW 100");
						result = input.readLine();
					} else {
						int distance = (int) closest_measure_value;
						distance = distance - 15;
						
						robot.sendCommand("P1.ROTATERIGHT " + closest_measure);
						result = input.readLine();
						
						robot.sendCommand("P1.MOVEFW " + distance);
						result = input.readLine();
						
						robot.sendCommand("P1.ROTATELEFT " + (90));
						result = input.readLine();
						
						wall_to_follow = true;
					}
  				} else {
  					//System.out.println("In front: " + measures[0] + " Right: " + measures[90]);
  					
  					
  					if (measures[0] < 2.0 && measures[90] != 100.0) {
  						robot.sendCommand("P1.ROTATELEFT " + 90);
  						result = input.readLine();
  						
  						//int distance = (int) measures[90];
  						
  						//robot.sendCommand("P1.MOVEFW " + (distance);
  						//result = input.readLine();

  					} else if (measures[90] != 100.0) {
  						int distance = (int) measures[0];
  						if (distance > 30) 	distance = 30;
  						
  						robot.sendCommand("P1.MOVEFW " + (distance - 1));
						result = input.readLine();
						
  					} else if (measures[90] == 100.0) {
  						if (measures[0] < 30) {
  							int distance = (int) measures[0];
  	  						if (distance > 30) 	distance = 30;
  							robot.sendCommand("P1.MOVEFW " + (distance - 1));
  							result = input.readLine();
  						} else {
  							robot.sendCommand("P1.MOVEFW 30");
  							result = input.readLine();
  						}
  						
  						robot.sendCommand("P1.ROTATERIGHT " + 90);
  						result = input.readLine();
  						
  						int distance = (int) measures[270];
  						if (distance > 30) 	distance = 30;
  						robot.sendCommand("P1.MOVEFW " + distance);
  						result = input.readLine();
  					} 
  				}
				
				
				map.isExplored(this);
				
			} catch (IOException ioe) {
				System.err.println("execution stopped");
				running = false;
			}
		}

	}

	private void parsePosition(String value, double position[]) {
		int indexInit;
		int indexEnd;
		String parameter;

		indexInit = value.indexOf("X=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
		position[0] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("Y=");
		parameter = value.substring(indexInit + 2);
		indexEnd = parameter.indexOf(' ');
		position[1] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("DIR=");
		parameter = value.substring(indexInit + 4);
		position[2] = Double.parseDouble(parameter);
	}

	private void parseMeasures(String value, double measures[]) {
		for (int i = 0; i < 360; i++) {
			measures[i] = 100.0;
		}
		if (value.length() >= 5) {
			value = value.substring(5);  // removes the "SCAN " keyword

			StringTokenizer tokenizer = new StringTokenizer(value, " ");

			double distance;
			int direction;
			while (tokenizer.hasMoreTokens()) {
				distance = Double.parseDouble(tokenizer.nextToken().substring(2));
				direction = (int) Math.round(Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))));
				if (direction == 360) {
					direction = 0;
				}
				measures[direction] = distance;
				// Printing out all the degrees and what it encountered.
				//System.out.println("direction = " + direction + " distance = " + distance);
			}
		}
	}


}
