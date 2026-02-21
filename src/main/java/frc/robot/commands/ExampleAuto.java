package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

public class ExampleAuto extends SequentialCommandGroup {
    public ExampleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, CommandXboxController controller, Joystick joystick) {
        addCommands(
            
            new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(3),
            new AutoDrive(driveSubsystem, 0.5, 0.5).withTimeout(2),
            new Intake(ballSubsystem, controller, joystick).withTimeout(2),
            new AutoDrive(driveSubsystem, -0.5, -0.5).withTimeout(2),
            new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(3)
        );
    }
}