package com.team2383.auto;

import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class MoveAngle extends PIDCommand {
	double error;
	double distance;
	double power;
	double angle;
	boolean resetGyro;

    public MoveAngle(double distance, double power, double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("Move Angle", 0.05, 0.0, 0.0);
    	this.distance = distance;
    	this.power = power;
    	this.angle = angle;
    	this.resetGyro = false;
    	this.getPIDController().setAbsoluteTolerance(1);
    	requires(Robot.drivetrain);
    }
    
    public MoveAngle(double distance, double power, double angle, boolean resetGyro) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("Move Angle", 0.05, 0.0, 0.0);
    	this.distance = distance;
    	this.power = power;
    	this.angle = angle;
    	this.resetGyro = resetGyro;
    	this.getPIDController().setAbsoluteTolerance(1);
    	requires(Robot.drivetrain);
    }
    
    protected double returnPIDInput() {
    	return Robot.gyroMXP.getAngle();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.error = 0;
    	Robot.drivetrain.setEncoderZero();
    	if (resetGyro) {
    		Robot.drivetrain.setEncoderZero();
    	}
    	Robot.drivetrain.setEncoderZero();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {}
    
    protected void usePIDOutput(double output) {
    	double driveSpeed = distance > 0 ? power : -power;
    	Robot.drivetrain.polarMecanumDrive(driveSpeed, 0, output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	error = Math.abs(this.distance) - Math.abs(Robot.drivetrain.getEncoderRotations());
        return (this.getPIDController().onTarget() && error == 0);
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
