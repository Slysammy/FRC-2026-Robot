// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterFeederSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ShooterFeederCommand extends Command {
  /** Creates a new ShooterFeederCommand. */
  private final ShooterFeederSubsystem shooterFeederSubsystem;
  public ShooterFeederCommand(ShooterFeederSubsystem shooterFeederSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooterFeederSubsystem = shooterFeederSubsystem;
    addRequirements(shooterFeederSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterFeederSubsystem.setMotor(-.7);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterFeederSubsystem.setMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
