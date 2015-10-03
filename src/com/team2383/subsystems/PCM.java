package com.team2383.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.team2383.commands.CompressorControl;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Sendable;

/**
 *
 */
public class PCM extends Subsystem implements Sendable {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Compressor pcm;
	Relay compressorRelay;
	AnalogInput pressureSensor;
	
	public PCM() {
		this(0);
	}

	public PCM(int pcmId) {
		super("Ninja PCM Controller");
		
		pcm = new Compressor(pcmId);
		compressorRelay = new Relay(0, Relay.Direction.kForward);
		pressureSensor = new AnalogInput(2);

		//compressor can either be hooked up directly
		//or to Relay port 0
		pcm.setClosedLoopControl(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CompressorControl());
    }
    
	public void runCompressorIfLowPressure() {
		//pressure switch is false if switch is closed
		//true if switch is open
		SmartDashboard.putBoolean("Pressure Switch Value", pcm.getPressureSwitchValue());
		SmartDashboard.putNumber("PSI", pressureSensor.getAverageVoltage()*42);
		if (!pcm.getPressureSwitchValue()) {
			compressorRelay.set(Relay.Value.kOn);
		} else {
			compressorRelay.set(Relay.Value.kOff);
		}
	}
}

