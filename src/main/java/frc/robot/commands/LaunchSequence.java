package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.FuelConstants;
import frc.robot.subsystems.CANFuelSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import static frc.robot.Constants.OperatorConstants.*;

public class LaunchSequence extends SequentialCommandGroup {
    private final Joystick operatorStick;
    private final CommandXboxController operatorController;
    
    public LaunchSequence(CANFuelSubsystem fuelSubsystem, CommandXboxController controller, Joystick joystick) {
        
        this.operatorStick = joystick;
        this.operatorController = controller;
        
        addCommands(
            // Start spinning the flywheel
            new SpinUp(fuelSubsystem),
            
            // Wait until the correct button is pressed
            new WaitUntilCommand(() -> {
                if (FLIGHTSTICK_ENABLED) {
                    // Button 4 on flight stick
                    return operatorStick.getRawButton(4);
                } else {
                    // Y button on Xbox controller
                    return operatorController.y().getAsBoolean();
                }
            }),
            
            // Then launch
            new Launch(fuelSubsystem, controller, joystick)
        );
    }
}