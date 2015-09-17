package com.team2383.subsystems;

import com.team2383.robot.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Slapper extends Subsystem {
	Solenoid slapper;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Slapper() {
		super("Slapper");

		this.slapper = new Solenoid(Constants.slapperSolenoid);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void activate() {
    	slapper.set(true);
    }
    
    public void off() {
    	slapper.set(false);
    }
}

