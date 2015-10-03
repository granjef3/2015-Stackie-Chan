package com.team2383.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import com.team2383.commands.*;

/**
 *
 */
public class AutoCommand extends CommandGroup {
	/*
	 * hammer = true
	 * -----
	 * SET DRIVE POWER = 0.55
	 * drive 0.5 rotations
	 * ------
	 * reset rotations
	 * ------
	 * move to angle = 75
	 * drive 15.25 rotations
	 * ------
	 * reset rotations
	 * move to angle = 142.5
	 * -----
	 * SET DRIVE POWER = 0.4
	 * drive 2 rotations
	 * -----
	 * reset rotations
	 * -----
	 * Hammer = false
	 * -----
	 * SET DRIVE POWER = 0.2
	 * drive 1.5 rotations
	 * -----
	 * SET DRIVE POWER = 0;
	 */
    public AutoCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	//addSequential(new Move(4, 0.5));
    }
}
