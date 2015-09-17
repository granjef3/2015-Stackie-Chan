package com.team2383.commands;

import com.team2383.robot.Robot;
import com.team2383.robot.Constants;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveRotations extends PIDCommand {
	double rotations;
	double speed;

    public MoveRotations(double rotations) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("MoveRotations", Constants.AutoP, Constants.AutoI, Constants.AutoD);
    	this.getPIDController().setOutputRange(-0.35, 0.25);
    	this.getPIDController().setContinuous();
    	this.getPIDController().setAbsoluteTolerance(0.1);
    	this.rotations = rotations;
    	requires(Robot.drivetrain);
    }
    
    protected double returnPIDInput() {
    	return Robot.drivetrain.getEncoderRotations();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.setEncoderZero();
    	Robot.gyroMXP.reset();
    	Robot.drivetrain.setEncoderZero(); //call twice; see functional limitation 21.17
    	this.setSetpoint(rotations);
    	this.getPIDController().enable();
    }
    
    protected void execute() {
    	//just a stub
    }
    
    protected void usePIDOutput(double output) {
    	Robot.drivetrain.polarMecanumDrive(output, -Robot.gyroMXP.getAngle() * Constants.GyroP, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("PID Error", this.getPIDController().getError());
    	return this.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.getPIDController().disable();
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
