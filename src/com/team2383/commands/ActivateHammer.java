package com.team2383.commands;

import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ActivateHammer extends Command {

    public ActivateHammer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("ActivateHammer");
    	requires(Robot.hammer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("bang");
    	Robot.hammer.activate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    //also called when untoggled
    protected void interrupted() {
    	System.out.println("unbang");
    	Robot.hammer.off();
    }
}
