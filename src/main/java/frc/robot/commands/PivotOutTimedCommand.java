// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakePivotSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class PivotOutTimedCommand extends Command {
  /** Creates a new PivotOutCommand. */
  private final IntakePivotSubsystem intakePivotSubsystem;
    public boolean finished;
  public PivotOutTimedCommand(IntakePivotSubsystem intakePivotSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intakePivotSubsystem = intakePivotSubsystem;
    addRequirements(intakePivotSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intakePivotSubsystem.setMotor(.3);
    if (RobotContainer.m_intakePivotSubsystem.PivotTravel()>= 1.516183) {
     intakePivotSubsystem.setMotor(0);
      finished = true;
}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakePivotSubsystem.setMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
   if (finished = true) {
      return true;
    }
    return false;
  }
}
