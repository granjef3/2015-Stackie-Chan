package com.team2383.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI.Port;

import com.team2383.auto.AutoCommand;
import com.team2383.subsystems.Drivetrain;
import com.team2383.subsystems.Lift;
import com.team2383.subsystems.Hammer;
import com.team2383.subsystems.Slapper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Compressor compressor = new Compressor();
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Lift lift = new Lift();
	public static final Hammer hammer = new Hammer();
	public static final Slapper slapper = new Slapper();
	public static final ADXRS453Gyro gyroMXP = new ADXRS453Gyro(Port.kMXP);
	public static OI oi;
	
	CommandGroup autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		//enables the PCM controlling the compressor
		compressor.start();
		//get auto going
		autonomousCommand = new AutoCommand();
        //get the gyro going
        gyroMXP.startThread();
        //reset encoders to 0
        drivetrain.setEncoderZero();
        drivetrain.setEncoderZero();
        drivetrain.setEncoderZero();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        gyroMXP.stopCalibrating();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	lift.down();
    	hammer.off();
    	slapper.off();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        periodicTasks();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        periodicTasks();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        periodicTasks(); 
    }
    
    private void periodicTasks() {
        SmartDashboard.putNumber("gyro angle", gyroMXP.getAngle());
        SmartDashboard.putNumber("gyro rate", gyroMXP.getRate());
		drivetrain.logEncoderRotations();
    }
}
