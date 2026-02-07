package frc.robot;
public final class Constants {
    public static final class DriveConstants{
        public static final int LEFT_LEADER_ID = 5;
        public static final int LEFT_FOLLOWER_ID = 4;
        public static final int RIGHT_LEADER_ID = 6;
        public static final int RIGHT_FOLLOWER_ID = 11;
        public static final int DRIVE_MOTOR_CURRENT_LIMIT = 60;
    
    }
    public static final class FuelConstants {
        public static final int FEEDER_MOTOR_CURRENT_LIMIT=60;
        public static final int LAUNCHER_MOTOR_CURRENT_LIMIT=60;
        
        
        public static final int FEEDER_MOTOR_ID = 12;
        public static final int INTAKE_LAUNCHER_MOTOR_ID = 5;
        public static final double INTAKING_FEEDER_VOLTAGE = -12;
        public static final double INTAKING_INTAKE_VOLTAGE = -10;
        public static final double LAUNCHING_FEEDER_VOLTAGE = 1;
        public static final double LAUNCHING_LAUNCHER_VOLTAGE = 10.6;
        public static final double SPIN_UP_FEEDER_VOLTAGE = -6;
        public static final double SPIN_UP_SECONDS = 1;
    }
    public static final class OperatorConstants{
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int OPERATOR_CONTROLLER_PORT = 1;
        public static final double DRIVE_SCALING = 0.7;
        public static final double ROTATION_SCALING = 0.8;
        }

    

    
}