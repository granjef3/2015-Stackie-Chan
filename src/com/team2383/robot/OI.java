package com.team2383.robot;

import com.team2383.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    //Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new Autonomous());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new Autonomous());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new Autonomous());
	
	
	private Joystick driverController, operatorController;
	
	private DPadButton liftUpButton, liftDownButton, goForwardButton, goBackButton;
	private JoystickButton precisionButton, hammerToggleButton, slapperToggleButton;
	
	public OI() {
		this.driverController = new Joystick(0);
		this.operatorController = new Joystick(1);
		
		this.liftUpButton = new DPadButton(operatorController, DPadButton.Direction.Up);
		this.liftDownButton = new DPadButton(operatorController, DPadButton.Direction.Down);
		
		this.precisionButton = new JoystickButton(driverController, 5);
		this.goForwardButton = new DPadButton(driverController, DPadButton.Direction.Up);
		this.goBackButton = new DPadButton(driverController, DPadButton.Direction.Down);
		this.hammerToggleButton = new JoystickButton(operatorController, 3);
		this.slapperToggleButton = new JoystickButton(operatorController, 4);
		
		liftUpButton.whenPressed(new LiftUp());
		liftDownButton.whenPressed(new LiftDown());
		
		goForwardButton.whenPressed(new MoveRotations(5));
		goBackButton.whenPressed(new MoveRotations(-5));
		
		precisionButton.toggleWhenPressed(new PrecisionJoystickDrive());
		hammerToggleButton.toggleWhenPressed(new ActivateHammer());
		slapperToggleButton.toggleWhenPressed(new ActivateSlapper());
	}
	
	public double getX() {
		return driverController.getRawAxis(0);
	}
	
	public double getY() {
		return driverController.getRawAxis(1);
	}
	
	public double getRotation() {
		return driverController.getRawAxis(4);
	}
}

