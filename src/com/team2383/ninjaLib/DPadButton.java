package com.team2383.ninjaLib;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;

public class DPadButton extends Button {
	private Joystick joystick;
	private Direction direction;
	
	public DPadButton(Joystick joystick, Direction direction) {
		this.joystick = joystick;
		this.direction = direction;
	}

	//NOTE: Doesn't support multiple directions
	@Override
	public boolean get() {
		int degree = joystick.getPOV(0);
		
		return degree == direction.degree;
	}
    
    public enum Direction {
    	Up (0),
    	Down (180),
    	Left (270),
    	Right (90);
    	
    	int degree;
    	Direction(int degree) {
    		this.degree = degree;
    	}
    }
}
