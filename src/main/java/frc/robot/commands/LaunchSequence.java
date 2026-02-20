package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.FuelConstants;
import frc.robot.subsystems.CANFuelSubsystem;
import edu.wpi.first.wpilibj.Joystick;

public class LaunchSequence extends SequentialCommandGroup {
public LaunchSequence(CANFuelSubsystem fuelSubsystem, CommandXboxController controller, Joystick joystick){
    
    addCommands(
        new SpinUp(fuelSubsystem).withTimeout(FuelConstants.SPIN_UP_SECONDS), 
        new Launch(fuelSubsystem, controller));
 }
}