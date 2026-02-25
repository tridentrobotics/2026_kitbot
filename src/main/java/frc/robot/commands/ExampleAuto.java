package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

public class ExampleAuto extends SequentialCommandGroup {
    public ExampleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, CommandXboxController controller, Joystick joystick) {
        addCommands(
            new AutoDrive(driveSubsystem, .3, 0).withTimeout(2.5),
            new Intake(ballSubsystem, controller, joystick).withTimeout(2),
             new AutoDrive(driveSubsystem, .0, -.35).withTimeout(1),
              new AutoDrive(driveSubsystem, .3, 0).withTimeout(1),
            new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(2)
           // new AutoDrive(driveSubsystem, -.1, -0.25).withTimeout(2)
    
        );
    }
}