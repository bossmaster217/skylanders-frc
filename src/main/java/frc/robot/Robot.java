// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private  SerialPort arduino;
  private Timer timer;
  @Override
  public void robotInit() {
    try{
      arduino = new SerialPort(9600, SerialPort.Port.kUSB);
      System.out.println("Connected on kUSB!");
    }
    catch(Exception e)
    {
      System.out.println("Failed to connect on kUSB, trying kUSB1");
      try
      {
        arduino = new SerialPort(9600, SerialPort.Port.kUSB1);
        System.out.println("Connected on kUSB1!");
      }
      catch(Exception e1)
      {
        System.out.println("Failed to connect on kUSB1, trying kUSB2");
        try
        {
          arduino = new SerialPort(9600, SerialPort.Port.kUSB2);
          System.out.println("Connected on kUSB2!");
        }
        catch(Exception e2)
        {
          System.out.println("Failed to connect on kUSB2, all connection attempts failed");
        }
    }
  }
  timer = new Timer();
  timer.start();
}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {  }

  @Override
  public void teleopPeriodic() {
    if(timer.get() > 5)
    {
      System.out.println("Wrtoe to arduino");
      arduino.write(new byte[] {0x12}, 1);
      timer.reset();
    }
    if(arduino.getBytesReceived() > 0)
    {
      System.out.print(arduino.readString());
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}