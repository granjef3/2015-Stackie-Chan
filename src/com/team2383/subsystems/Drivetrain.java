package com.team2383.subsystems;

import com.team2383.commands.PrecisionJoystickDrive;
import com.team2383.robot.Constants;
import com.team2383.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.*;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.*;


/**
 * Drivetrain subsystem
 * TODO: Implement gyro correction using SPI gyro
 */
public class Drivetrain extends Subsystem {
	
	CANTalon rearLeft, rearRight, frontLeft, frontRight;
	RobotDrive drive;
	PIDController gyroController;
	GyroPIDSource gyroAngle;
	NullPIDOut nullOut;
	
	private boolean isGyroDirty;
	
	public Drivetrain() {
		super("Drivetrain");
		
		this.rearLeft = new CANTalon(Constants.rearLeftMotor);
		this.rearRight = new CANTalon(Constants.rearRightMotor);
		this.frontLeft = new CANTalon(Constants.frontLeftMotor);
		this.frontRight = new CANTalon(Constants.frontRightMotor);
		
		Robot.gyroMXP.reset();

		this.gyroAngle = new GyroPIDSource();
		this.nullOut = new NullPIDOut();
		this.gyroController = new PIDController(Constants.GyroP, 0, 0, 
				gyroAngle,
				nullOut
				);
		
		gyroController.setContinuous(false);
		gyroController.setAbsoluteTolerance(1.0);
		gyroController.setOutputRange(-1, 1);
		gyroController.setSetpoint(0);
		gyroController.enable();
		
		
		//Encoders are hooked up directly to Talons
		rearLeft.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		rearRight.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		frontLeft.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		frontRight.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		
		rearLeft.setCloseLoopRampRate(3);
		rearRight.setCloseLoopRampRate(3);
		frontLeft.setCloseLoopRampRate(3);
		frontRight.setCloseLoopRampRate(3);

		//set encoders backwards
		rearLeft.reverseSensor(true);
		frontLeft.reverseSensor(true);
		
		//set them motors to brake mode
		rearLeft.enableBrakeMode(true);
		rearRight.enableBrakeMode(true);
		frontLeft.enableBrakeMode(true);
		frontRight.enableBrakeMode(true);
		
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
        setDefaultCommand(new PrecisionJoystickDrive());
    }
    
    public void mecanumDrive(double x, double y, double rotation, double gyro) {
    	
    	drive.mecanumDrive_Cartesian(x, y, correctRotation(rotation), gyro);
    }
    
    public void polarMecanumDrive(double magnitude, double direction, double rotation) {
    	drive.mecanumDrive_Polar(magnitude, direction, correctRotation(rotation));
    }
    
    //return gyro corrected rotation
    private double correctRotation(double rotation) {
		SmartDashboard.putNumber("PID Output", gyroController.get());
		SmartDashboard.putNumber("PID Setpoint", gyroController.getSetpoint());
		//SmartDashboard.putNumber("Gyro Angle" , Robot.gyroMXP.getAngle());
    	
    	//Use gyro
		if (
				(Math.abs(Robot.gyroMXP.getRate()) < 25)
				&& 
				(Math.abs(rotation) < 0.01)
			) {
			if (isGyroDirty) {
				gyroController.setSetpoint(Robot.gyroMXP.getAngle());
				isGyroDirty = false;
			}
			return gyroController.get();
		//dont use gyro
		} else {
			isGyroDirty = true; //we are changing angles, so the gyro is always dirty hehe
			gyroController.setSetpoint(Robot.gyroMXP.getAngle());
			return rotation;
		}
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
    
	private class NullPIDOut implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			
		}
		
	}
	
	private class GyroPIDSource implements PIDSource {
		@Override
		public double pidGet() {
			return Robot.gyroMXP.getAngle();
		}		
	}
}

