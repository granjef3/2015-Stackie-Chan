package com.team2383.commands;

import com.team2383.robot.Robot;
import com.team2383.robot.Constants;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveRotations extends Command {
	double rotations;
	double error;

    public MoveRotations(double rotations) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.rotations = rotations;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	error = 0;
    	Robot.drivetrain.setEncoderZero();
    	Robot.gyroMXP.reset();
    	Robot.drivetrain.setEncoderZero(); //call twice; see functional limitation 21.17
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = rotations - Robot.drivetrain.getEncoderRotations();
    	
    	Robot.drivetrain.polarMecanumDrive(error*0.4, -Robot.gyroMXP.getAngle() * Constants.GyroP, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println(Robot.drivetrain.getEncoderRotations());

    	SmartDashboard.putNumber("Error", error);
        if (Math.abs(error) <= 0.01) {
        	return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setEncoderZero();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
