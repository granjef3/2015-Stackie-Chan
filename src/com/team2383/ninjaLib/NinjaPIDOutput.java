package com.team2383.ninjaLib;

import java.util.function.DoubleConsumer;

import edu.wpi.first.wpilibj.PIDOutput;

public class NinjaPIDOutput implements PIDOutput {
	DoubleConsumer outputFn;
	
	public NinjaPIDOutput(DoubleConsumer outputFn) {
		this.outputFn = outputFn;
	}
	
	@Override
	public void pidWrite(double output) {
		this.outputFn.accept(output);
	}
	
}