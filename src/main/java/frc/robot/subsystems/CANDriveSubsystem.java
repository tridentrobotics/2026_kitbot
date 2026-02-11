package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import static frc.robot.Constants.DriveConstants.*;

public class CANDriveSubsystem extends SubsystemBase {

    private final TalonSRX leftLeader;
    private final TalonSRX leftFollower;
    private final TalonSRX rightLeader;
    private final TalonSRX rightFollower;

    public CANDriveSubsystem() {
        leftLeader = new TalonSRX(LEFT_LEADER_ID);
        leftFollower = new TalonSRX(LEFT_FOLLOWER_ID);
        rightLeader = new TalonSRX(RIGHT_LEADER_ID);
        rightFollower = new TalonSRX(RIGHT_FOLLOWER_ID);

        // Invert right side
        rightLeader.setInverted(true);
        rightFollower.setInverted(true);

        // Followers follow their leaders
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        // Optional: configure current limits
        leftLeader.configSupplyCurrentLimit(new com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration(true, DRIVE_MOTOR_CURRENT_LIMIT, DRIVE_MOTOR_CURRENT_LIMIT, 1.0));
        rightLeader.configSupplyCurrentLimit(new com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration(true, DRIVE_MOTOR_CURRENT_LIMIT, DRIVE_MOTOR_CURRENT_LIMIT, 1.0));

        System.out.println("CANDriveSubsystem initialized (TalonFX)");
    }

    /** Tank drive: left stick controls left, right stick controls right */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftLeader.set(ControlMode.PercentOutput, leftSpeed);
        rightLeader.set(ControlMode.PercentOutput, rightSpeed);
        // followers already follow
    }
}
