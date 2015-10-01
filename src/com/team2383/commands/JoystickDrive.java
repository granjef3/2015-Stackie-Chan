package com.team2383.commands;

import com.team2383.robot.Constants;
import com.team2383.robot.OI;
import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class JoystickDrive extends Command {
	public JoystickDrive() {
		super("JoystickDrive");
		requires(Robot.drivetrain);
	}
	
	public JoystickDrive(String name) {
		super(name);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	// todo: add gyro code
	protected void execute() {
		double x = Robot.oi.getX();
		double y = Robot.oi.getY();
		double rotation = Robot.oi.getRotation();
		
		//make stick inputs less sensitive near center
		x = processAxis(x);
		y = processAxis(y);
		rotation = processAxis(rotation);
		
		SmartDashboard.putNumber("x joy", x);
		SmartDashboard.putNumber("y joy", y);
		SmartDashboard.putNumber("rot joy", rotation);
		
		Robot.drivetrain.mecanumDrive(x, y, rotation, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	//deadband and stick scaling
	//Math.pow doesnt like negative bases
	//so theres a little magic down there to deal with it
	//look up javadocs for copySign if you are confused :)
	protected double processAxis(double axis) {
		double sign = axis;
		axis = (Math.abs(axis) > Math.abs(Constants.deadband)) ? axis : 0.0;
		axis = Math.pow(Math.abs(axis), Constants.drivePow);
		return Math.copySign(axis, sign);
	}

	// Called once after isFinished returns true
	protected void end() {}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {}
}
