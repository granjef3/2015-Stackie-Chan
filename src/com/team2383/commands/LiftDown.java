package com.team2383.commands;

import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftDown extends Command {

    public LiftDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("LiftDown");
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("LIFT: DOWN");
    	Robot.lift.down();
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
    protected void interrupted() {
    }
}
