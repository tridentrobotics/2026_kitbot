package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.OperatorConstants.*;
import frc.robot.commands.Drive;
import frc.robot.commands.Extake;
import frc.robot.commands.ExampleAuto;
import frc.robot.commands.Intake;
import frc.robot.commands.LaunchSequence;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;


public class RobotContainer {
    private final CANDriveSubsystem driveSubsystem = new CANDriveSubsystem();
    private final CANFuelSubsystem fuelSubsystem = new CANFuelSubsystem();
    public final Joystick operatorJoystick = new Joystick(OPERATOR_JOYSTICK_PORT);
    public final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
    private final SendableChooser<Command> autoChooser = new SendableChooser<>();

    public RobotContainer() {

        // 🔹 LOG ADDED HERE
        System.out.println("RobotContainer constructed");

        driveSubsystem.setDefaultCommand(new Drive(driveSubsystem, operatorJoystick, operatorController));

        configureBindings();

        autoChooser.setDefaultOption("Autonomous", new ExampleAuto(driveSubsystem, fuelSubsystem, operatorController, operatorJoystick));
    }

    private void configureBindings() {
        if (FLIGHTSTICK_ENABLED) {
        new JoystickButton(operatorJoystick, 2).whileTrue(new Intake(fuelSubsystem, operatorController, operatorJoystick));

        new JoystickButton(operatorJoystick, 1).whileTrue(new LaunchSequence(fuelSubsystem, operatorController, operatorJoystick));

        new JoystickButton(operatorJoystick, 3).whileTrue(new Extake(fuelSubsystem));
        
        
        } else {
            operatorController.leftTrigger(0).whileTrue(new Intake(fuelSubsystem, operatorController, operatorJoystick));
            operatorController.rightTrigger(0).whileTrue(new LaunchSequence(fuelSubsystem, operatorController, operatorJoystick));
            operatorController.b().whileTrue(new Extake(fuelSubsystem));
        }
        fuelSubsystem.setDefaultCommand(fuelSubsystem.run(() -> fuelSubsystem.stop())
    );
}

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}
