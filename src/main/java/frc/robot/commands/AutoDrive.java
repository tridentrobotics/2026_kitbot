package frc.robot.commands;

import static frc.robot.Constants.OperatorConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANDriveSubsystem;

public class AutoDrive extends Command{
    CANDriveSubsystem driveSubsystem;
    double xSpeed, zRotation;

    public AutoDrive(CANDriveSubsystem driveSystem, double xSpeed, double zRotation){
        addRequirements(driveSystem);
        driveSubsystem=driveSystem;
        this.xSpeed=xSpeed;
        this.zRotation=zRotation;


    }
    @Override
    public void initialize(){
    
    }
    @Override
    public void execute(){
        double forward = xSpeed;
        double turn = zRotation;

        double leftSpeed = (DRIVE_SCALING * forward) - (turn*ROTATION_SCALING);
        double rightSpeed = (DRIVE_SCALING * forward) + (turn*ROTATION_SCALING);


        driveSubsystem.tankDrive(leftSpeed, rightSpeed);
    }
    @Override
    public void end(boolean interupted){    
    driveSubsystem.tankDrive(0,0);
    }
    @Override
    public boolean isFinished()
    {
    return false;
    }


}