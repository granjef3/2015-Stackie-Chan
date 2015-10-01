package com.team2383.commands;

import com.team2383.robot.Constants;
import com.team2383.robot.OI;
import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PrecisionJoystickDrive extends JoystickDrive {
	public PrecisionJoystickDrive() {
		super("PrecisionJoystickDrive");
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("Christian Mode", true);
	}

	//Override processAxis from JoystickDrive to half axis values by 2
	@Override
	protected double processAxis(double axis) {
		return super.processAxis(axis)/2;
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putBoolean("Christian Mode", false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		SmartDashboard.putBoolean("Christian Mode", false);
	}
}