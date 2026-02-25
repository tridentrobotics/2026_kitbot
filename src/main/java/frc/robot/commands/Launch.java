package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;
import static frc.robot.Constants.OperatorConstants.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import edu.wpi.first.wpilibj.DriverStation;


public class Launch extends Command {
    CANFuelSubsystem fuelSubsystem;
    private final CommandXboxController operatorController;
    private final Joystick operatorJoystick;

    public Launch(CANFuelSubsystem fuelSystem, CommandXboxController controller, Joystick joystick){
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
        this.operatorController = controller;
        this.operatorJoystick = joystick;

   }
   
   @Override
   public void initialize(){
    System.out.println("Launching"); 
}

private double lastIntakeVoltage = 0;
private double lastFeederVoltage = 0;
private double intakeVoltage = 0;
private double feederVoltage = 0;
private double speedMulti = 1;
private double d = 1;
private double c = .65;
@Override
public void execute(){
    speedMulti = c+((d-c)*(-operatorJoystick.getRawAxis(3))/2);
    if (FLIGHTSTICK_ENABLED) {
    intakeVoltage = LAUNCHING_LAUNCHER_VOLTAGE * speedMulti;
    feederVoltage = -LAUNCHING_FEEDER_VOLTAGE * speedMulti;

    } else if (!FLIGHTSTICK_ENABLED) {
        intakeVoltage = operatorController.getRightTriggerAxis() * LAUNCHING_LAUNCHER_VOLTAGE;
        feederVoltage = -operatorController.getRightTriggerAxis() * LAUNCHING_FEEDER_VOLTAGE;
    } else if (DriverStation.isAutonomous()) {
        intakeVoltage = LAUNCHING_LAUNCHER_VOLTAGE;
        feederVoltage = LAUNCHING_FEEDER_VOLTAGE;
    }
        

        if (Math.abs(intakeVoltage - lastIntakeVoltage) > EPSILON || Math.abs(feederVoltage - lastFeederVoltage) > EPSILON) {
            
                double intakeRounded = new BigDecimal(intakeVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();
                double feederRounded = new BigDecimal(feederVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();

            
                System.out.println("Intake voltage: " + intakeRounded + ", Feeder voltage: " + feederRounded + ", Speed Multi: " + speedMulti);

                lastFeederVoltage = feederVoltage;
                lastIntakeVoltage = intakeVoltage;
                
            }

        fuelSubsystem.setIntakeLauncherRoller(intakeVoltage);
        fuelSubsystem.setFeederRoller(-feederVoltage);
}

@Override
public void end(boolean interrupted){
    fuelSubsystem.stop();
    System.out.println("Intake command ended. Stopping motors.");
}

@Override 
public boolean isFinished(){
    
    return false; 
}
}
