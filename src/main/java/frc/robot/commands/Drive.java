package frc.robot.commands;

import static frc.robot.Constants.OperatorConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;

public class Drive extends Command{
    CANDriveSubsystem driveSubsystem;
    CommandXboxController controller;

    public Drive(CANDriveSubsystem driveSystem, CommandXboxController driverController){
    addRequirements(driveSystem);
    driveSubsystem=driveSystem;
    controller=driverController;
    }
        @Override
public void initialize() {
    System.out.println("Drive command initialized");
}

@Override
public void execute() {
    double leftSpeed = -controller.getLeftY() * DRIVE_SCALING;
    double rightSpeed = -controller.getRightY() * DRIVE_SCALING;

    driveSubsystem.tankDrive(leftSpeed, rightSpeed);

    System.out.println("Tank Drive: Left=" + leftSpeed + " Right=" + rightSpeed);
}


        @Override
        public void end(boolean interupted)
        {
            System.out.println("Drive command ended. Interrupted=");
            driveSubsystem.tankDrive(0, 0);
        }
        @Override
        public boolean isFinished(){
            return false;
        }

}
