package frc.robot.commands;

import edu.wpi.first.hal.AllianceStationID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.OperatorConstants.*;

public class ExampleAuto extends SequentialCommandGroup {
    public ExampleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, 
                       CommandXboxController controller, Joystick joystick) {
        
        // Check if we're on Blue alliance (Blue1, Blue2, or Blue3)
        if (alliance == AllianceStationID.Blue1 || 
            alliance == AllianceStationID.Red1) {
            
            // BLUE ALLIANCE AUTO
            addCommands(
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(2),
                new AutoDrive(driveSubsystem, 0, .17).withTimeout(1),
                new AutoDrive(driveSubsystem, .2, 0).withTimeout(1), // will need to increase speed or time, and change depending on where robot starts
                new AutoDrive(driveSubsystem, 0, .188).withTimeout(1),
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(1.1),
                new AutoDrive(driveSubsystem, 0, .285).withTimeout(1),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4)
            );
            
        } else if (alliance == AllianceStationID.Blue2 || 
                   alliance == AllianceStationID.Red2) {
            // RED ALLIANCE AUTO (Red1, Red2, or Red3)
            addCommands(
                // Mirror the blue auto for red alliance
                // Negative Y values to flip left/right movements
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(2),
                new AutoDrive(driveSubsystem, 0, -.17).withTimeout(1),   // Flipped
                new AutoDrive(driveSubsystem, .2, 0).withTimeout(1),
                new AutoDrive(driveSubsystem, 0, -.188).withTimeout(1),  // Flipped
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(1.1),
                new AutoDrive(driveSubsystem, 0, -.285).withTimeout(1),  // Flipped
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4)
            );
        }  else if (alliance == AllianceStationID.Blue3 || 
                   alliance == AllianceStationID.Red3) {
                addCommands(
                    new AutoDrive(driveSubsystem, .3, 0).withTimeout(2),
                new AutoDrive(driveSubsystem, 0, -.17).withTimeout(1),   // Flipped
                new AutoDrive(driveSubsystem, .2, 0).withTimeout(1),
                new AutoDrive(driveSubsystem, 0, -.188).withTimeout(1),  // Flipped
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(1.1),
                new AutoDrive(driveSubsystem, 0, -.285).withTimeout(1),  // Flipped
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4)
                );
    }
}
}