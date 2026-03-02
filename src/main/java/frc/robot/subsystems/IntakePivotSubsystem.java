// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakePivotSubsystem extends SubsystemBase {
  private final SparkMax IntakePivot = new SparkMax(14, MotorType.kBrushless);
  public final DigitalInput IntakePivotLS = new DigitalInput(0);
  private final RelativeEncoder IntakePivotEncoder = IntakePivot.getEncoder();
  public double encoder;
  
  /** Creates a new IntakePivotSubsystem. */
  public IntakePivotSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setMotor(double Speed){
    IntakePivot.set(Speed);
    SmartDashboard.putNumber("Encoder Value", IntakePivotEncoder.getPosition());
  }

public void setEncoder(double value){
  IntakePivotEncoder.setPosition(0);
}

public double PivotTravel(){
  double PivotTicks = IntakePivotEncoder.getPosition();
  return PivotTicks;
}

  public boolean getLS(){
    return IntakePivotLS.get();
  }

}
