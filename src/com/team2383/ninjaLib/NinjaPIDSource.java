package com.team2383.ninjaLib;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.PIDSource;

public class NinjaPIDSource implements PIDSource {
	DoubleSupplier sourceFn;
	
	public NinjaPIDSource(DoubleSupplier sourceFn) {
		this.sourceFn = sourceFn;
	}
	@Override
	public double pidGet() {
		return sourceFn.getAsDouble();
	}
	
}