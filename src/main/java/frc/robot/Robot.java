/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.jni.CANSparkMaxJNI;

import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.hal.sim.mockdata.DriverStationDataJNI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  CANSparkMax rF;
  CANSparkMax lF;
  CANSparkMax rB;
  CANSparkMax lB;
  PIDController drivePID;

  XboxController control;

  CANEncoder rF_enc;
  CANEncoder lF_enc;
  CANEncoder lB_enc;
  CANEncoder rB_enc;
  boolean autoForward = false;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    CANSparkMaxLowLevel.enableExternalUSBControl( true);

    rF = new CANSparkMax(1 , MotorType.kBrushless);
    lF = new CANSparkMax(2 , MotorType.kBrushless);
    lB = new CANSparkMax(3 , MotorType.kBrushless);
    rB = new CANSparkMax(4 , MotorType.kBrushless);
    
    rF_enc = rF.getEncoder();
    lF_enc = lF.getEncoder();
    lB_enc = lB.getEncoder();
    rB_enc = rB.getEncoder();

    

    //%rF.restoreFactoryDefaults(true);
    //rF.setInverted(false);
    //rF.setIdleMode(CANSparkMax.IdleMode.kBrake);
//
    //lF.restoreFactoryDefaults(true);
    //lF.setInverted(false);
    //lF.setIdleMode(CANSparkMax.IdleMode.kBrake);
//
    //lB.restoreFactoryDefaults(true);
    //lB.setInverted(false);
    //lB.setIdleMode(CANSparkMax.IdleMode.kBrake);
//
    //rB.restoreFactoryDefaults(true);
    //rB.setInverted(false);
    //rB.setIdleMode(CANSparkMax.IdleMode.kBrake);
//
    control = new XboxController(0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Right Back Encoder", rB_enc.getPosition());
    SmartDashboard.putNumber("Right Front Encoder", rF_enc.getPosition());
    SmartDashboard.putNumber("Left Back Encoder", lB_enc.getPosition());
    SmartDashboard.putNumber("Left Front Encoder", lF_enc.getPosition());

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    double valueRX = -1*control.getRawAxis(4);
    double valueLY = control.getRawAxis(1);
    double leftSide = valueLY + valueRX;
    double rightSide = valueRX - valueLY;

    rF.set(rightSide);
    rB.set(rightSide);
    lF.set(leftSide);
    lB.set(leftSide);
    
    SmartDashboard.putNumber("R", rightSide);
    SmartDashboard.putNumber("L", leftSide);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
