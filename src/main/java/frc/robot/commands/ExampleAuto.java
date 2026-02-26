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
            new AutoDrive(driveSubsystem, 0,.175 ).withTimeout(1),
            new AutoDrive(driveSubsystem, .2, 0).withTimeout(1),
            new AutoDrive(driveSubsystem, 0, .185).withTimeout(1),
            new AutoDrive(driveSubsystem, .3, 0).withTimeout(1.6),
            new AutoDrive(driveSubsystem, 0, .275).withTimeout(1),
            new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(6.5)
            //new AutoDrive(driveSubsystem, .2, 0.15).withTimeout(5),
            //new Intake(ballSubsystem, controller, joystick).withTimeout(1),
            //new AutoDrive(driveSubsystem, .2, -.15).withTimeout(5),
            //new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(2)
        );
    }
}