package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import static frc.robot.Constants.OperatorConstants.*;
import frc.robot.commands.Drive;
import frc.robot.commands.Eject;
import frc.robot.commands.ExampleAuto;
import frc.robot.commands.Intake;
import frc.robot.commands.LaunchSequence;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

public class RobotContainer{
    private final CANDriveSubsystem driveSubsytem=new CANDriveSubsystem();
    private final CANFuelSubsystem fuelSubsystem=new CANFuelSubsystem();
    private final CommandXboxController driverController=new CommandXboxController(DRIVER_CONTROLLER_PORT);
    private final CommandXboxController operatorController=new CommandXboxController(OPERATOR_CONTROLLER_PORT);
    private final SendableChooser<Command> autoChooser=new SendableChooser<>();

    public RobotContainer(){
        configureBindings();
        autoChooser.setDefaultOption("Autonoumous", new ExampleAuto(driveSubsytem, fuelSubsystem));
    }
    
    private void configureBindings(){
        operatorController.leftBumper().whileTrue(new Intake(fuelSubsystem));
        operatorController.rightBumper().whileTrue(new LaunchSequence(fuelSubsystem));
        operatorController.a().whileTrue(new Eject(fuelSubsystem));
        
        driveSubsytem.setDefaultCommand(new Drive(driveSubsytem, driverController));
        fuelSubsystem.setDefaultCommand(fuelSubsystem.run(()-> fuelSubsystem.stop()));

    }
   public Command getAutonomousCommand(){
        return autoChooser.getSelected();
    }
    
}   
