package frc.robot.commands;

import static frc.robot.Constants.OperatorConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANDriveSubsystem;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Drive extends Command{
    CANDriveSubsystem driveSubsystem;
    double right_offset = RIGHT_OFFSET;
    private final CommandXboxController operatorController;
    

    public Drive(CANDriveSubsystem driveSystem, CommandXboxController controller){
    addRequirements(driveSystem);
    this.driveSubsystem=driveSystem;
    this.operatorController = controller;


   
    
    }
    @Override
    public void initialize() {
        System.out.println("Drive command initialized");
        
    }
    private double lastLeftSpeed = 0;
    private double lastRightSpeed = 0;

@Override
public void execute() {
    
    double forward = MathUtil.applyDeadband(-operatorController.getLeftY(), DRIVETRAIN_DEADBAND);

    double turn = MathUtil.applyDeadband(-operatorController.getRightX(), DRIVETRAIN_DEADBAND);

    double leftSpeed = (forward * DRIVE_SCALING) - (turn * ROTATION_SCALING);

    double rightSpeed = (forward * DRIVE_SCALING * 0.65) + (turn * ROTATION_SCALING);

    driveSubsystem.tankDrive(leftSpeed, rightSpeed); 

    if (Math.abs(leftSpeed - lastLeftSpeed) > EPSILON || Math.abs(rightSpeed - lastRightSpeed) > EPSILON) {
        
        double leftRounded = new BigDecimal(leftSpeed).setScale(4, RoundingMode.HALF_UP).doubleValue();
        double rightRounded = new BigDecimal(rightSpeed).setScale(4, RoundingMode.HALF_UP).doubleValue();

        System.out.println("Tank Drive: Left=" + leftRounded + " Right=" + rightRounded);
        lastLeftSpeed = leftSpeed;
        lastRightSpeed = rightSpeed;
    }



    

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
