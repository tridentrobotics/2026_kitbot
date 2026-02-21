package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;
import edu.wpi.first.wpilibj.Joystick;

public class ExampleAuto extends SequentialCommandGroup{
    public ExampleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, CommandXboxController controller, Joystick joystick){
        addCommands(
            
            new AutoDrive(driveSubsystem, 0.5, 0.0).withTimeout(0.25),
            
            new Launch(ballSubsystem, controller, joystick).withTimeout(10));

    }
}