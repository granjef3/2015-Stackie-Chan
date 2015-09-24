package com.team2383.commands;

import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ActivateHammer extends Command {
	boolean hasTimeout;
	boolean state;
	boolean hasState;

    public ActivateHammer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("ActivateHammer");
    	this.hasTimeout = false;
    	this.hasState = false;
    	requires(Robot.hammer);
    }

    public ActivateHammer(double time) {
    	super("HammerAuto", time);
    	this.hasTimeout = true;
    	this.hasState = false;
    	requires(Robot.hammer);
    }

    public ActivateHammer(boolean state) {
    	super("HammerAuto");
    	this.hasTimeout = false;
    	this.hasState = true;
    	this.state = state;
    	requires(Robot.hammer);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (this.hasState) {
    		if (this.state) {
    			Robot.hammer.activate();
    		} else {
    			Robot.hammer.off();
    		}
    	} else {
        	Robot.hammer.activate();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (hasTimeout && !hasState) {
    		return this.isTimedOut();
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    //also called when untoggled
    protected void interrupted() {
    	Robot.hammer.off();
    }
}
