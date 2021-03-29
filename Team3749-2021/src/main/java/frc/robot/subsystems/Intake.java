package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends SubsystemBase {
  private VictorSPX m_intake = new VictorSPX(Constants.CAN.intake_motor);
  private VictorSPX m_lift = new VictorSPX(Constants.CAN.intake_motor_lift);

  public Intake() {
    m_intake.setInverted(true);
  }

  /**
   * Intake power cells in
   */
  public void intakeIn() {
    m_intake.set(ControlMode.PercentOutput, Constants.Intake.kIntakeSpeed);
  }

  /**
   * Expel power cells out
   */
  public void intakeOut() {
    m_intake.set(ControlMode.PercentOutput, -Constants.Intake.kIntakeSpeed);
  }

  /**
   * Stop intake
   */
  public void intakeStop() {
    m_intake.set(ControlMode.PercentOutput, 0);
  }

  /**
   * Lift intake up
   */
  public void liftUp() {
    m_lift.set(ControlMode.PercentOutput, Constants.Intake.kIntakeLiftUpSpeed);
  }

  /**
   * Drop intake down
   */
  public void liftDown() {
    m_lift.set(ControlMode.PercentOutput, Constants.Intake.kIntakeLiftDownSpeed);
  }

  /**
   * Stop intake lift
   */
  public void liftStop() {
    m_lift.set(ControlMode.PercentOutput, 0);
  }
}
