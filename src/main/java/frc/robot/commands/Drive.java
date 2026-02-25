package frc.robot.commands;

import static frc.robot.Constants.OperatorConstants.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.CANDriveSubsystem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class Drive extends Command{
    CANDriveSubsystem driveSubsystem;
    double right_offset = RIGHT_OFFSET;
    private final Joystick operatorStick;
    private final CommandXboxController operatorController;
    

    public Drive(CANDriveSubsystem driveSystem, Joystick joystick, CommandXboxController controller){
    addRequirements(driveSystem);
    this.driveSubsystem=driveSystem;
    this.operatorStick = joystick;
    this.operatorController = controller;


   
    
    }
    @Override
    public void initialize() {
        System.out.println("Drive command initialized");
        
    }
    private double lastLeftSpeed = 0;
    private double lastRightSpeed = 0;

    private static final double DRIVE_EXPO = 2; //1=linear
    private static final double ROTATION_EXPO = 2;


    private static double applyExpo(double value, double expo) {

        return Math.copySign(Math.pow(Math.abs(value), expo), value);
    }

@Override
public void execute() {

        double rawForward;
        double rawTurn;

        if (FLIGHTSTICK_ENABLED) {
            rawForward = -operatorStick.getY();
            rawTurn = -operatorStick.getTwist();
        } else {
            rawForward = -operatorController.getLeftY();
            rawTurn = -operatorController.getRightX();
        }
        double forwardDb = MathUtil.applyDeadband(rawForward, DRIVETRAIN_DEADBAND);
        double turnDb = MathUtil.applyDeadband(rawTurn, DRIVETRAIN_DEADBAND);

        double forwardExpo = applyExpo(forwardDb, DRIVE_EXPO);
        double turnExpo = applyExpo(turnDb, ROTATION_EXPO);

        double leftSpeed = (forwardExpo * DRIVE_SCALING) - (turnExpo * ROTATION_SCALING);
        double rightSpeed = (forwardExpo * DRIVE_SCALING * RIGHT_OFFSET) + (turnExpo * ROTATION_SCALING);

        double maxAbs = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
        if (maxAbs > 1.0) {
            leftSpeed /= maxAbs;
            rightSpeed /= maxAbs;
        }

        // send speeds to subsystem
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
