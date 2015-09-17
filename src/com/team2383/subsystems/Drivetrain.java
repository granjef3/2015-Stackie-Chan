package com.team2383.subsystems;

import com.team2383.commands.JoystickDrive;
import com.team2383.robot.Constants;
import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.*;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.*;
import edu.wpi.first.wpilibj.SPI.Port;


/**
 * Drivetrain subsystem
 * TODO: Implement gyro correction using SPI gyro
 */
public class Drivetrain extends Subsystem {
	
	CANTalon rearLeft, rearRight, frontLeft, frontRight;
	RobotDrive drive;
	
	public Drivetrain() {
		super("Drivetrain");
		
		this.rearLeft = new CANTalon(Constants.rearLeftMotor);
		this.rearRight = new CANTalon(Constants.rearRightMotor);
		this.frontLeft = new CANTalon(Constants.frontLeftMotor);
		this.frontRight = new CANTalon(Constants.frontRightMotor);
		
		//Encoders are hooked up directly to Talons
		rearLeft.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		rearRight.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		frontLeft.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		frontRight.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		
		rearLeft.setCloseLoopRampRate(2);
		rearRight.setCloseLoopRampRate(2);
		frontLeft.setCloseLoopRampRate(2);
		frontRight.setCloseLoopRampRate(2);

		//set encoders backwards bc gooby did it
		rearLeft.reverseSensor(true);
		frontLeft.reverseSensor(true);
		
		//Set our PID constants (located in robot.Constants;
		rearLeft.setPID(Constants.DriveP, Constants.DriveI, Constants.DriveD);
		rearRight.setPID(Constants.DriveP, Constants.DriveI, Constants.DriveD);
		frontLeft.setPID(Constants.DriveP, Constants.DriveI, Constants.DriveD);
		frontRight.setPID(Constants.DriveP, Constants.DriveI, Constants.DriveD);

		this.drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		this.drive.setSafetyEnabled(true);
		this.drive.setExpiration(0.3);
		this.drive.setSensitivity(0.5);
        this.drive.setInvertedMotor(MotorType.kFrontRight, true);  // invert the left side motors
        this.drive.setInvertedMotor(MotorType.kRearRight, true);   // you may need to change or remove this to match your robot

	}

    protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }
    
    public void mecanumDrive(double x, double y, double rotation, double gyro) {
    	drive.mecanumDrive_Cartesian(x, y, rotation, gyro);
    }
    
    public void polarMecanumDrive(double magnitude, double direction, double rotation) {
    	drive.mecanumDrive_Polar(magnitude, direction, rotation);
    }
    
    public void logEncoderRotations() {
    	SmartDashboard.putNumber("Rear Left", rearLeft.getPosition()/360);
    	SmartDashboard.putNumber("Rear Right", rearRight.getPosition()/360);
    	SmartDashboard.putNumber("Front Left", frontLeft.getPosition()/360);
    	SmartDashboard.putNumber("Front Right", frontRight.getPosition()/360);
    }
    
    public double getEncoderRotations() {
    	double avg = 0;
    	avg += rearLeft.getPosition();
    	avg += rearRight.getPosition();
    	avg += frontLeft.getPosition();
    	avg += frontRight.getPosition();
    	
    	avg = avg/4;
    	avg = avg/360;
    
    	return avg;
    }
    
    public void setEncoderZero() {
    	rearLeft.setPosition(0);
    	rearRight.setPosition(0);
    	frontLeft.setPosition(0);
    	frontRight.setPosition(0);
    }
}

