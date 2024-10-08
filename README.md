# Westwood Boomerang Subsystems
A collection of basic Subsystems written for FTC, including a claw, linear slide, and mecanum drive train.
## Installation
### Using Git Submodules (Preferred method)
To import this into your project, we recommend using submodules. A *submodule* in git, is referencing another repository inside your repository. For example, you may want to import a library from GitHub, or you are using the same files again and again inside your code.

To import this repository as a submodule into your code, make sure you have a git repository already set up in your base folder (Where your `./Teamcode/` and `./FTCRobotController` folders are). Then, navigate into your source directory by running:

<details>
<summary>OSX or a Linux Distro </summary>
<br>

 ```bash
cd ./TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
```
</details>

<details open>
<summary> Windows </summary>

```bash
cd .\TeamCode\src\main\java\org\firstinspires\ftc\teamcode\
```
</details>

Double check you are in the right directory by making sure all your files and the `README.md` are there.

<details>
<summary>OSX or a Linux Distro </summary>
<br>

 ```bash
ls
```
</details>

<details open>
<summary>Windows </summary>
<br>

 ```powershell
dir
```
</details>
 
---
```
README.md
RoadrunnerAutonomous.java
SuperEpicTeleOp.java
```
now you can add the submodule:

```bash
git submodule add https://github.com/Westwood-Boomerang/subsystems
```
Now using the files should work under `org.firstinspires.ftc.teamcode.subsystems.*`.
## Manually Cloning
The less preferred method, since it does not allow you to update code, but will work on OnBotJava as well. Simply click the Green Button at the Top > Download ZIP  and extract in the right directory.
Now using the files should work under `org.firstinspires.ftc.teamcode.subsystems.*`.
## Updating
### Git Submodules
Go inside the git submodule and run

```bash
git fetch
git merge -strategy=theirs
```
This will auto resolve any merging conflicts by using the remote (GitHub) branch.
If you want, you can also manually resolve merge conflicts, but we don't recommend it.

The next time you `commit`, it will update to the new version of the subsystems.
### If cloned
Delete all of the necessary files and redownload and unzip.
## Usage
All classes have a constructor and an update method. All of the constructors have overloads that let you call them with any number of arguments in order.
### Mecanum Drive Train (Field Centric)
#### Constructor -

`hardwareMap`: pass in `com.qualcomm.robotcore.hardware.HardwareMap`

`Reversal`: Which side to reverse; pass in `Reversal.RevLeft`, `RevRight`, or `None`

`IMUPameters`: Parameters for the IMU (Gyroscope)

`Scalar`: A function that takes in and returns a `double`; used to scale how much power is given to the motors, for example if you want it to only drive with 70% of the full power, do `return 0.7*x`

#### Update -
`forward`: from 0.0 - 1.0 how much should the drivetrain move forward and backward (Note: if using the y value on a gamepad stick, make sure to reverse it.)

`strafe`: from 0.0 - 1.0 how much should the drivetrain move left and right

`turn`: from 0.0 - 1.0 how much should the drivetrain turn

`resetIMU`: when to reset the IMU
### Linear Slide
#### Constructor -
`hardwareMap`: pass in `com.qualcomm.robotcore.hardware.HardwareMap`

`points`: array of tick locations where the `Motor` should stop at

`currIndex`: what index in the points array should the linear slide currently be at

`PID`: a tuned `PIDFcontroller`
#### Update -
`up`: should the slide go to the next-up stoppage point

`down`: should the slide go to the next-down stoppage point

**NOTE: The linear slide will try going up if possible, so if both `up` and `down` are set to true, the motor will go up**
### Claw
#### Constructor -
`hardwareMap`: pass in `com.qualcomm.robotcore.hardware.HardwareMap`

`reverse`: should the servo be reversed

`rangeMin`: the minimum value the `Servo` can go to

`rangeMax` the maximum value the `Servo` can go to
#### Update -
`open`: should the claw open

`close`: should the claw close

**NOTE: The claw will try opening if possible, so if both `open` and `close` are set to true, the claw will open**
 
---
## Why use the Mozilla Public License?
We chose this license for a few reasons:
- We wanted to encourage sharing changed source code
- We did not want the license to "infect" other pieces of code, such as GNU GPL v3
- Even though it is considered a "Copyleft license", it is quite permissive.

The license will not really be monitored (so you can make it closed source), but we would really appreciate it if you made your changed version available for everyone, since maybe someone needs it, or it could be used as a feature in the next version.