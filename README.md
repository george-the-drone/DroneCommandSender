# DroneCommandSender
A program running on a user's computer that sends commands to the DroneServer, which then communicates to the drone.

# Usage
Run the jar file as follows:

`java -jar DroneCommandSender.jar [CommandServerPort] [CommandType] [Action | Behavior] (value)`

Broken down command instructions:

`java -jar DroneCommandSender.jar 40022 behavior [behavior]`

`java -jar DroneCommandSender.jar 40022 action [action] (value)`

Examples:

`java -jar DroneCommandSender.jar 40022 behavior circle`

`java -jar DroneCommandSender.jar 40022 action forward 10`

Each usage of the run command sends a single command to DroneServer and then terminates DroneCommandSender. To send multiple commands, run the jar file multiple times. Make sure that DroneServer is running before running DroneCommandSender

### Valid Behaviors
- circle
- idle
- roam
- line
- custom

### Valid Actions
- forward
- backward
- left
- right
- up
- down
- rotate_left
- rotate_right
- switch_led
