package com.team2383.robot;

/*
 * holds our magic numbers; eg drive multipliers, PID values, stuff like that.
 * Values in here are defaults, they can be overrided by a text file in RoboRIO.
 * That will be implemented later
 */

public class Constants {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	public static int rearLeftMotor = 1;
	public static int rearRightMotor = 2;
	public static int frontLeftMotor = 3;
	public static int frontRightMotor = 4;

	public static int liftLeftSolenoid1 = 0;
	public static int liftLeftSolenoid2 = 1;
	public static int liftRightSolenoid1 = 2;
	public static int liftRightSolenoid2 = 3;
	
	public static int hammerSolenoid = 5;
	public static int slapperSolenoid = 6;

	public static double DriveP = 0.5;
	public static double DriveI = 0.0001;
	public static double DriveD = 0.01;
	public static double GyroP = 0.03;
	public static double drivePow = 2.2;
	public static double deadband = 0.001;
	// constants go here!
}
