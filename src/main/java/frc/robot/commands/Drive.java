package frc.robot.commands;

import static frc.robot.Constants.OperatorConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;


public class Drive extends Command{
    CANDriveSubsystem driveSubsystem;
    public final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
    double right_offset = RIGHT_OFFSET;

    public Drive(CANDriveSubsystem driveSystem, CommandXboxController driverController){
    addRequirements(driveSystem);
    driveSubsystem=driveSystem;
    
    }
    @Override
    public void initialize() {
        System.out.println("Drive command initialized");
        
    }

@Override
public void execute() {
        double leftTurnSpeed = -operatorController.getRightX() * ROTATION_SCALING;
        double rightTurnSpeed = operatorController.getRightX() * ROTATION_SCALING;
        
        double leftSpeed = (-(operatorController.getLeftY()) * DRIVE_SCALING)-leftTurnSpeed;
        double rightSpeed = (-(operatorController.getLeftY()) * DRIVE_SCALING*.65)-rightTurnSpeed;
        
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
