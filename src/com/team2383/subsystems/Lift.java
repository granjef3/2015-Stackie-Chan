package com.team2383.subsystems;

import com.team2383.robot.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class Lift extends Subsystem {
	DoubleSolenoid liftLeft, liftRight;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Lift() {
		super("Lift");

		this.liftLeft = new DoubleSolenoid(Constants.liftLeftSolenoid1, Constants.liftLeftSolenoid2);
		this.liftRight = new DoubleSolenoid(Constants.liftRightSolenoid1, Constants.liftRightSolenoid2);
	}

    protected void initDefaultCommand() {
    	//no default command
    }
    
    public void up() {
    	liftLeft.set(Value.kReverse);
    	liftRight.set(Value.kReverse);
    }
    
    public void down() {
    	liftLeft.set(Value.kForward);
    	liftRight.set(Value.kForward);
    }
    
    public void off() {
    	liftLeft.set(Value.kOff);
    	liftRight.set(Value.kOff);
    }
}

