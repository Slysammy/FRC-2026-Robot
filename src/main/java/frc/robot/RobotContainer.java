// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.HopperInCommand;
import frc.robot.commands.HopperOutCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.commands.IntakeTimedCommand;
import frc.robot.commands.PivotInCommand;
import frc.robot.commands.PivotOutCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.ShootStopCommand;
import frc.robot.commands.ShootTimedCommand;
import frc.robot.commands.ShooterFeederCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakePivotSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterFeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveInputStream;

import com.fasterxml.jackson.databind.node.ShortNode;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.events.EventTrigger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final CommandXboxController m_manipulatorController = new CommandXboxController(1);
  private final SwerveSubsystem drivebase = new SwerveSubsystem();
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
    
  public final static ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  public final static IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  public final static IntakePivotSubsystem m_intakePivotSubsystem = new IntakePivotSubsystem();
  public final static HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  public final static ShooterFeederSubsystem m_shooterFeederSubystem = new ShooterFeederSubsystem();
  
  
  private SendableChooser<Command> autoChooser;
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      // Configure the trigger bindings
      DriverStation.silenceJoystickConnectionWarning(true);
      configureBindings();
      drivebase.setDefaultCommand(driveFieldOreintedAngularVelocity);
            NamedCommands.registerCommand("Test", Commands.print("I Exist"));
    }
  
  
    private double invertedGetX() {
      return 1 * m_driverController.getRightX();
    }
  
  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.GetSwerveDrive(),
                                                               ()-> m_driverController.getLeftY() * -1,
                                                               ()-> m_driverController.getLeftX() * -1)
                                                               .withControllerRotationAxis(this::invertedGetX)
                                                               .deadband(OperatorConstants.DEADBAND)
                                                               .scaleTranslation(1.0)
                                                               .allianceRelativeControl(true);
  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(m_driverController::getRightX, 
                                                                                             m_driverController::getRightY)
                                                                                             .headingWhile(true);
    
    Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);
  
    Command driveFieldOreintedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);



    
    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */

  
    private void configureBindings() {

      
   // m_driverController.rightBumper().whileTrue(new ShooterFeederCommand(m_shooterFeederSubystem));
    m_driverController.leftBumper().whileTrue(new ShootCommand(m_shooterSubsystem));
    m_manipulatorController.a().whileTrue(new IntakeCommand(m_intakeSubsystem));
    m_manipulatorController.b().whileTrue(new HopperOutCommand(m_hopperSubsystem));
    m_manipulatorController.x().whileTrue(new HopperInCommand(m_hopperSubsystem));
    m_manipulatorController.y().whileTrue(new IntakeOutCommand(m_intakeSubsystem));
    m_manipulatorController.leftBumper().whileTrue(new PivotOutCommand(m_intakePivotSubsystem));
    m_manipulatorController.rightBumper().whileTrue(new PivotInCommand(m_intakePivotSubsystem));
//    m_manipulatorController.y().onTrue(new HopperOutCommand(m_hopperSubsystem).andThen(new PivotOutCommand(m_intakePivotSubsystem) ));





       m_driverController.start().onTrue((Commands.runOnce(drivebase::zeroGyro)));
      NamedCommands.registerCommand("test", Commands.print("I EXIST"));
      NamedCommands.registerCommand("Shoot", (new ShootCommand(m_shooterSubsystem)));
      NamedCommands.registerCommand("Stop", (new ShootStopCommand(m_shooterSubsystem)));
      new EventTrigger("Shooter").onTrue(new ShootCommand(m_shooterSubsystem));
      new EventTrigger("Stopper").onTrue(new ShootStopCommand(m_shooterSubsystem));
      NamedCommands.registerCommand("STime", new ShootTimedCommand(m_shooterSubsystem));
      NamedCommands.registerCommand("ITime", new IntakeTimedCommand(m_intakeSubsystem));
      NamedCommands.registerCommand("Intake Routine", new HopperOutCommand(m_hopperSubsystem).andThen(new PivotOutCommand(m_intakePivotSubsystem).andThen(new IntakeTimedCommand(m_intakeSubsystem))));
      NamedCommands.registerCommand("Shooting Routine", new ShooterFeederCommand(m_shooterFeederSubystem).raceWith(new ShootTimedCommand(m_shooterSubsystem)));
      autoChooser = AutoBuilder.buildAutoChooser();

    //Put the autoChooser on the SmartDashboard
    SmartDashboard.putData("Choose an Auto", autoChooser);
      } 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 public Command getAutonomousCommand()
  {
    // Pass in the selected auto from the SmartDashboard as our desired autnomous commmand 
    return autoChooser.getSelected();
  }
}
