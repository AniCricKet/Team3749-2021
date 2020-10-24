# Privi Specifications
> Hardware and software specifications for Privi

## Drivetrain
### Programmers: [REDACTED]
### Setup
- Motors: Four CIM Motors
- Motor Controllers: Two TalonSRX (front), Two VictorSPX (back)
- Encoder: CIM Encoder CIMcoder
- Other Sensors: Kaui Labs AHRS navX-MXP Gyro
### Methods
- arcadeDrive
  - Arcade drive using differential drive
- tankDrive
  - Tank drive using differential drive
- curvatureDrive
  - Curvature drive using differential drive
- driveStraight
  - Drive straight using gyro
- turnAngle
  - Turn to a specific angle
- setMotors
  - Set both sides to percent power
- speedLeftMotors
  - Set left side motors to percent power
- speedRightMotors
  - Set right side motors to percent power
- stopMotors
  - Set all motors to 0
### Commands
- ArcadeDrive
- TankDrive
- CurvatureDrive
- DriveStraight
