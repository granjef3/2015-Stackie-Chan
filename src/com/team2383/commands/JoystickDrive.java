package com.team2383.commands;

import com.team2383.robot.Constants;
import com.team2383.robot.OI;
import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class JoystickDrive extends PIDCommand {
	boolean isGyroDirty;
	public JoystickDrive() {
		this("JoystickDrive", Constants.GyroP, 0.0, 0.0);
	}
	
	public JoystickDrive(String name, double P, double I, double D) {
		super(name, P, I, D);
		requires(Robot.drivetrain);
		
		this.getPIDController().setContinuous(false);
		this.getPIDController().setAbsoluteTolerance(1.0);
		this.getPIDController().setOutputRange(-1, 1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.setSetpoint(0);
		this.getPIDController().enable();
		Robot.gyroMXP.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {}

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

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.gyroMXP.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		double x = Robot.oi.getX();
		double y = Robot.oi.getY();
		double rotation = Robot.oi.getRotation();
		
		//make stick inputs less sensitive near center
		x = processAxis(x);
		y = processAxis(y);
		rotation = processAxis(rotation);
		
		//Use gyro
		if (
				(Math.abs(Robot.gyroMXP.getRate()) < 25)
				&& 
				(Math.abs(rotation) < 0.01)
			) {
			Robot.drivetrain.mecanumDrive(x, y, output, 0);
			if (isGyroDirty) {
				this.getPIDController().setSetpoint(Robot.gyroMXP.getAngle());
				isGyroDirty = false;
			}
		//dont use gyro
		} else {
			Robot.drivetrain.mecanumDrive(x, y, rotation, 0);
			isGyroDirty = true; //we are changing angles, so the gyro is always dirty hehe
			this.getPIDController().setSetpoint(Robot.gyroMXP.getAngle());
		}
		
		SmartDashboard.putNumber("x joy", x);
		SmartDashboard.putNumber("y joy", y);
		SmartDashboard.putNumber("rot joy", rotation);
		SmartDashboard.putNumber("gyroPID out", output);
		SmartDashboard.putNumber("gyroPID setpoint", this.getSetpoint());
	}
}
