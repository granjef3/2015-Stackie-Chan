package com.team2383.subsystems;

import com.team2383.robot.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hammer extends Subsystem {
	Solenoid hammer;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Hammer() {
		super("Hammer");

		this.hammer = new Solenoid(Constants.hammerSolenoid);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void activate() {
    	hammer.set(true);
    }
    
    public void off() {
    	hammer.set(false);
    }
}

