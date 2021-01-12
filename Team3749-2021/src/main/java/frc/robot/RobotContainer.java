package frc.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  private final Drivetrain m_drive = new Drivetrain();
  private final Elevator m_elevator = new Elevator();
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();

  public XboxController m_xboxController = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // shooter controller bindings
    new JoystickButton(m_xboxController, Axis.kRightTrigger.value)
        .whenPressed(new Shoot(m_shooter, () -> m_xboxController.getTriggerAxis(Hand.kRight)), true)
        .whenReleased(new Shoot(m_shooter, () -> m_xboxController.getTriggerAxis(Hand.kRight)), true);

    // elevator up controller bindings
    new JoystickButton(m_xboxController, Button.kB.value).whenPressed(new ElevatorUp(m_elevator), true)
        .whenReleased(new ElevatorStop(m_elevator), true);

    // elevator down controller bindings
    new JoystickButton(m_xboxController, Button.kX.value).whenPressed(new ElevatorDown(m_elevator), true)
        .whenReleased(new ElevatorStop(m_elevator), true);

    // intake up controller bindings
    new JoystickButton(m_xboxController, Button.kA.value).whenPressed(new IntakeUp(m_intake), true)
        .whenReleased(new IntakeStop(m_intake), true);

    // intake down controller bindings
    new JoystickButton(m_xboxController, Button.kY.value).whenPressed(new IntakeDown(m_intake), true)
        .whenReleased(new IntakeStop(m_intake), true);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * 
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // return m_autoCommand;

    TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(5.0), Units.feetToMeters(5.0));
    config.setKinematics(m_drive.getKinematics());

    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(Arrays.asList(new Pose2d(),
        new Pose2d(1.0, 0, new Rotation2d()), new Pose2d(2.3, 1.2, Rotation2d.fromDegrees(90.0))), config);

    RamseteCommand command = new RamseteCommand(trajectory, m_drive::getPose, new RamseteController(2.0, 0.7),
        m_drive.getFeedforward(), m_drive.getKinematics(), m_drive::getSpeeds, m_drive.getLeftPIDController(),
        m_drive.getRightPIDController(), m_drive::setOutputVolts, m_drive);

    return command.andThen(() -> m_drive.setOutputVolts(0, 0));
  }

  /**
   * A simple getter method for the Drivetrain system
   * 
   * @return m_drive
   */
  public Drivetrain getDrivetrain() {
    return m_drive;
  }

  /**
   * A simple getter method for the Elevator system
   * 
   * @return m_shooter
   */
  public Elevator getElevator() {
    return m_elevator;
  }

  /**
   * A simple getter method for the Shooter system
   * 
   * @return m_shooter
   */
  public Shooter getShooter() {
    return m_shooter;
  }

  /**
   * A simple getter method for the Intake system
   * 
   * @return m_intake
   */
  public Intake getIntake() {
    return m_intake;
  }

  public void reset() {
    m_drive.reset();
  }
}
