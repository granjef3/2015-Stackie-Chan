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

    public MoveRotations(double rotations) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.rotations = rotations;
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setEncoderZero();
    	Robot.gyroMXP.reset();
    	Robot.drivetrain.setEncoderZero(); //call twice; see functional limitation 21.17
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double driveSpeed = rotations > 0 ? 0.5 : -0.5;
    	Robot.drivetrain.polarMecanumDrive(driveSpeed, -Robot.gyroMXP.getAngle() * Constants.GyroP, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println(Robot.drivetrain.getEncoderRotations());
        return Math.abs(Robot.drivetrain.getEncoderRotations()) >= Math.abs(rotations);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setEncoderZero();
    	Robot.drivetrain.setEncoderZero();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setEncoderZero();
    	Robot.drivetrain.setEncoderZero();
    }
}
