package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANFuelSubsystem;


public class IntakeSequence extends SequentialCommandGroup {
public IntakeSequence(CANFuelSubsystem fuelSubsystem, CommandXboxController controller){
    
    addCommands(
        new Intake(fuelSubsystem, controller).withTimeout(2),
        new Intake2(fuelSubsystem));
        
}
}
