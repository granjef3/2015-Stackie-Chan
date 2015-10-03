package com.team2383.auto;

import com.team2383.robot.Robot;
import com.team2383.robot.Constants;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Move extends Command {
	double distance;
	double power;

    public Move(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.distance = distance;
    	this.power = 0.35;
    	requires(Robot.drivetrain);
    }
   
    public Move(double distance, double power) {
    	this.distance = distance;
    	this.power = power;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setEncoderZero();
    	Robot.gyroMXP.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double driveSpeed = distance > 0 ? power : -power;
    	Robot.drivetrain.polarMecanumDrive(driveSpeed, -Robot.gyroMXP.getAngle() * Constants.GyroP, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println(Robot.drivetrain.getEncoderRotations());
        return Math.abs(Robot.drivetrain.getEncoderRotations()) >= Math.abs(distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setEncoderZero();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setEncoderZero();
    }
}
