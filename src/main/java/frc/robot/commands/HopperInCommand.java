// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.HopperSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class HopperInCommand extends Command {
  private final HopperSubsystem hopperSubsystem;
  /** Creates a new HopperInCommand. */
  public HopperInCommand(HopperSubsystem hopperSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.hopperSubsystem = hopperSubsystem;
    addRequirements(hopperSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


     if (RobotContainer.m_hopperSubsystem.getLSLeft() == false){
      hopperSubsystem.setLeftMotor(0);
      hopperSubsystem.setLeftEncoder(0);
    }else{
       hopperSubsystem.setLeftMotor(.31);
   } 
  if ( RobotContainer.m_hopperSubsystem.getLSRight() == false) {
      hopperSubsystem.setRightMotor(0);
      hopperSubsystem.setRightEncoder(0);
    }else{
      hopperSubsystem.setRightMotor(-.3);
   }    
 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   hopperSubsystem.setLeftMotor(0);
    hopperSubsystem.setRightMotor(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (RobotContainer.m_hopperSubsystem.getLSLeft() == false && RobotContainer.m_hopperSubsystem.getLSRight() == false) {
      return true;
    }else
    {
    return false;}
  }
}
