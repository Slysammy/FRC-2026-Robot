// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
 // private final SparkMax ShooterBlueRight = new SparkMax(17, MotorType.kBrushless);
  private final SparkMax ShooterBlueLeft = new SparkMax(16, MotorType.kBrushless);
  private final SparkMax ShooterGreen = new SparkMax(18, MotorType.kBrushless);
  
  public ShooterSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setMotor(double Speed){
    ShooterBlueLeft.set(-Speed);
  
  }

  public void setGreenMotor(double Speed){
    ShooterGreen.set(-Speed);
  }

}
