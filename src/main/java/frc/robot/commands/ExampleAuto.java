package frc.robot.commands;

import edu.wpi.first.hal.AllianceStationID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.OperatorConstants.*;
import edu.wpi.first.wpilibj.DriverStation;

public class ExampleAuto extends SequentialCommandGroup {
    public static AllianceStationID alliance = DriverStation.getRawAllianceStation();
    public ExampleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem, 
                       CommandXboxController controller, Joystick joystick) {
        
        // Check if we're on Blue alliance (Blue1, Blue2, or Blue3)
        System.err.println(alliance);
        if (alliance == AllianceStationID.Blue1 || 
            alliance == AllianceStationID.Red1) {
            
            // BLUE ALLIANCE AUTO
            //Turns left from further away
            addCommands(
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(2),
                new AutoDrive(driveSubsystem, 0, -.265).withTimeout(1),
                new AutoDrive(driveSubsystem, .4, 0).withTimeout(1), 
                new AutoDrive(driveSubsystem, 0, -.27).withTimeout(1),
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(1.1),
                new AutoDrive(driveSubsystem, 0, -.375).withTimeout(1),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4.5),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4.5)
            );
            
        } else if (alliance == AllianceStationID.Blue2 || 
                   alliance == AllianceStationID.Red2) {
            // RED ALLIANCE AUTO (Red1, Red2, or Red3)
            //turns left from closer
            addCommands(
                // Mirror the blue auto for red alliance
                // Negative Y values to flip left/right movements
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(2),
                new AutoDrive(driveSubsystem, 0, -.265).withTimeout(1),
                new AutoDrive(driveSubsystem, .2, 0).withTimeout(1),
                new AutoDrive(driveSubsystem, 0, -.27).withTimeout(1), 
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(1.1),
                new AutoDrive(driveSubsystem, 0, -.375).withTimeout(1),  
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4.5),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4.5)
            );
        }  else if (alliance == AllianceStationID.Blue3 || 
                   alliance == AllianceStationID.Red3) {
                    //turns right from further away
                addCommands(
                    new AutoDrive(driveSubsystem, .3, 0).withTimeout(2),
                new AutoDrive(driveSubsystem, 0, .265).withTimeout(1),   // Flipped
                new AutoDrive(driveSubsystem, .4, 0).withTimeout(1),
                new AutoDrive(driveSubsystem, 0, .27).withTimeout(1),  // Flipped
                new AutoDrive(driveSubsystem, .3, 0).withTimeout(1.1),
                new AutoDrive(driveSubsystem, 0, .375).withTimeout(1),  // Flipped
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4.5),
                new LaunchSequence(ballSubsystem, controller, joystick).withTimeout(4.5)
                );
    }
}
}