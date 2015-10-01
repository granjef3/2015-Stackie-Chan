package com.team2383.commands;


import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PeriodicCommand extends Command {

    public PeriodicCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//dont require drivetrain bc logEncoderRotations can run
    	//without anything else going on
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("gyro angle", Robot.gyroMXP.getAngle());
        SmartDashboard.putNumber("gyro rate", Robot.gyroMXP.getRate());
		Robot.drivetrain.logEncoderRotations();
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
