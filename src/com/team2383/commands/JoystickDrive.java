package com.team2383.commands;

import com.team2383.robot.OI;
import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickDrive extends Command {
	public JoystickDrive() {
		super("JoystickDrive");
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	// todo: add gyro code
	protected void execute() {
		Robot.drivetrain.mecanumDrive(Robot.oi.getX(), Robot.oi.getY(), Robot.oi.getRotation(), 0);
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
