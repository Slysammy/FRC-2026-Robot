// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopperSubsystem extends SubsystemBase {
  private final SparkMax HopperLeft = new SparkMax(20, MotorType.kBrushless);
  private final SparkMax HopperRight = new SparkMax(21, MotorType.kBrushless);
  private final RelativeEncoder HopperEncoderLeft = HopperLeft.getEncoder();
  private final RelativeEncoder HopperEncoderRight = HopperRight.getEncoder();
  private final DigitalInput HopperLeftLS = new DigitalInput(1);
  private final DigitalInput HopperRightLS = new DigitalInput(2);
  public boolean finished;
  /** Creates a new HopperSubsystem. */
  public HopperSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

   public void setLeftMotor(double Speed){
    HopperLeft.set(Speed);
    
  }

  

  public void setRightMotor(double Speed){
    HopperRight.set(Speed);
  }

  public void setLeftEncoder(double position){
    HopperEncoderLeft.setPosition(position);
  }

  public void setRightEncoder(double position){
    HopperEncoderRight.setPosition(position);
  }

  public boolean getLSLeft(){
    return HopperLeftLS.get();
  }
  public boolean getLSRight(){
    return HopperRightLS.get();
  }

  public double leftHopperTravel(){
    double leftHopperTicks = HopperEncoderLeft.getPosition();
    return leftHopperTicks;
  }

  public double rightHopperTravel(){
    double rightHopperTicks = HopperEncoderRight.getPosition();
    return rightHopperTicks;
  }
  

}
