package frc.robot;



public final class Constants {
    public static final class DriveConstants{
        public static final int LEFT_LEADER_ID = 5;
        public static final int LEFT_FOLLOWER_ID = 6;
        public static final int RIGHT_LEADER_ID = 12;
        public static final int RIGHT_FOLLOWER_ID = 4;
        public static final int DRIVE_MOTOR_CURRENT_LIMIT = 60;
    
    }
    public static final class FuelConstants {
        public static final int FEEDER_MOTOR_ID = 1;
        public static final int INTAKE_LAUNCHER_MOTOR_ID = 11;
        
        
        public static final int FEEDER_MOTOR_CURRENT_LIMIT=60;
        public static final int LAUNCHER_MOTOR_CURRENT_LIMIT=60;
        public static final double INTAKING_FEEDER_VOLTAGE = 12;
        public static final double INTAKING_INTAKE_VOLTAGE = -9.5;
        public static final double LAUNCHING_FEEDER_VOLTAGE = -12;
        public static final double LAUNCHING_LAUNCHER_VOLTAGE = -12;
        public static final double EJECTING_FEEDER_VOLTAGE = -11;
        public static final double EJECTING_LAUNCHER_VOLTAGE = 11;
        public static final double SPIN_UP_FEEDER_VOLTAGE = -2;
        public static final double SPIN_UP_SECONDS = .75;
    }
    public static final class OperatorConstants{
        public static final int OPERATOR_CONTROLLER_PORT = 0;
        public static final double DRIVETRAIN_DEADBAND = .05;
        public static final double DRIVE_SCALING = 0.5;
        public static final double ROTATION_SCALING = 0.5;
        public static final double TURN_THRESHOLD = 0.15;
        public static double RIGHT_OFFSET = 0.1;
        public static double EPSILON = 0.0001;
        }

    

    
}