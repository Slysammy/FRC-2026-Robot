// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HopperSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class HopperOutCommand extends Command {
  private final HopperSubsystem hopperSubsystem;
  public boolean RightFinished;
  public boolean LeftFinished;
  /** Creates a new HopperOutCommand. */
  public HopperOutCommand(HopperSubsystem hopperSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.hopperSubsystem = hopperSubsystem;
    addRequirements(hopperSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hopperSubsystem.setRightEncoder(0);
    hopperSubsystem.setLeftEncoder(0);
   hopperSubsystem.setLeftMotor(-.31);
    hopperSubsystem.setRightMotor(.3);
  /*   if (RobotContainer.m_hopperSubsystem.leftHopperTravel() == 15 && RobotContainer.m_hopperSubsystem.rightHopperTravel()== 0.038278) {
      hopperSubsystem.setLeftMotor(0);
      hopperSubsystem.setRightMotor(0);
      hopperSubsystem.finished = true;
      
    }
   /*  if (RobotContainer.m_hopperSubsystem.rightHopperTravel()== 0.038278) {
      hopperSubsystem.setRightMotor(0);
      hopperSubsystem.RightFinished = true;
    }
    if (RobotContainer.m_hopperSubsystem.leftHopperTravel() == 0) {
      hopperSubsystem.setLeftMotor(0);
      hopperSubsystem.LeftFinished ==true;
    }*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopperSubsystem.setRightMotor(0);
    hopperSubsystem.setLeftMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (hopperSubsystem.finished == true) {
      return true;
    }
    return false;
  }
}
