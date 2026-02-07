package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import static frc.robot.Constants.OperatorContants.*;

import frc.robot.commands.Drive;
import frc.robot.command.Eject;
import frc.robot.command.ExampleAuto;
import frc.robot.command.Intake;
import frc.robot.command.LaunchSequence;
import frc.robot.command.CANDriveSubsystem;
import frc.robot.command.CANFuelSubsystem;

public class RobotContainer{
    private final CANDriveSubsystem driveSubsytem=new CANDriveSubsystem();
    private final CANFuelSubsystem fuelSubsystem=new CANFuelSubsystem();
    private final CommandXboxController drivController=new CommandXboxController(DRIVER_CONTROLLER_PORT);
    private final CommandXboxController operatorController=new CommandXboxController(OPERATOR_CONTROLLER_PORT);
    private final SendableChooser<Command> autoChooser=new SendableChooser<>();

    public RobotContainer(){
        configureBindings();
        autoChooser.setDefaultOption("Autonoumous", new ExampleAuto(driveSubsytem, fuelSubsystem));
    }
    @link Trigger Trigger(java.util.function.BooleanSupplier)
    @link edu.wpi.first.wpilibj2.command.button.CommandGenericHID
    @link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
    @link edu.wpi.first.wpilibj2.command.button.CommandJoystick

    private void configureBindings(){
        operatorController.leftBumper().whileTrue(new Intake(fuelSubsystem));
        operatorController.rightBumper().whileTrue(new LaunchSequence(fuelSubsystem));
        operatorController.a().whileTrue(new Eject(fuelSubsystem));
        
        driveSubsytem.setDefaultCommand(new Drive(driveSubsytem, driverController));
        fuelSubsystem.setDefaultCommand(fuelSubsystem.run(()-> fuelSubsystem.stop()));

    }
    @link Robot
    @return

    public Command getAutonomousCommand(){
        return autoChooser.getSelected();
    }
    
}   
