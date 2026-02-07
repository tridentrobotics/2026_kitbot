package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;

public class CANDriveSubsystem extends SubsystemBase {
    private final SparkMax leftLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightLeader;
    private final SparkMax rightFollower;
    private final DifferentialDrive drive;
    @SuppressWarnings("removal")
    public CANDriveSubsystem(){
        leftLeader = new SparkMax(LEFT_LEADER_ID, MotorType.kBrushed);
        leftFollower = new SparkMax(LEFT_FOLLOWER_ID, MotorType.kBrushed);
        rightLeader = new SparkMax(RIGHT_LEADER_ID, MotorType.kBrushed);
        rightFollower = new SparkMax(RIGHT_FOLLOWER_ID, MotorType.kBrushed);
        
        drive = new DifferentialDrive(leftLeader, rightLeader);
        
        leftLeader.setCANTimeout(250);
        leftFollower.setCANTimeout(250);
        rightLeader.setCANTimeout(250);
        rightFollower.setCANTimeout(250);

        SparkMaxConfig config = new SparkMaxConfig();
        config.voltageCompensation(12);
        config.smartCurrentLimit(DRIVE_MOTOR_CURRENT_LIMIT);
        config.follow(leftLeader);
        leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.follow(rightLeader);
        rightFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        config.disableFollowerMode();
        rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.inverted(true);
        leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    
    @Override
    public void periodic() {
    }
    public void driveArcade(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed, zRotation);
    } 
}