package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

public class ExampleAuto extends SequentialCommandGroup {
    public ExampleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, CommandXboxController controller, Joystick joystick) {
        addCommands(
            
            new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(2),
            new AutoDrive(driveSubsystem, .1, 0.1).withTimeout(.5),
            new Intake(ballSubsystem, controller, joystick).withTimeout(1),
            new AutoDrive(driveSubsystem, -.1, -0.1).withTimeout(0.5),
            new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(2)
        );
    }
}