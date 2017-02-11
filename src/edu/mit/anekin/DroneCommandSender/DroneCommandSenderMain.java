package edu.mit.anekin.DroneCommandSender;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DroneCommandSenderMain {
	
	public static String HOST = "localhost";
	public static int PORT;
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		if(hasValidArguments(args)){
			PORT = Integer.parseInt(args[0]);
			String commandType = args[1];
			if(commandType.equalsIgnoreCase("behavior")){
				Behavior behavior = Behavior.getBehaviorFromName(args[2]);
				String command = "behavior:" + behavior.getName();
				sendCommand(command);
			}else{
				Action action = Action.getActionFromName(args[2]);
				int value = Integer.parseInt(args[3]);
				String command = "action:" + action.getName() + ":" + value;
				sendCommand(command);
			}
		}
	}
	
	public static void sendCommand(String command) throws UnknownHostException, IOException{
		Socket socket = new Socket(HOST, PORT);
		PrintWriter output = new PrintWriter(socket.getOutputStream());
		System.out.println(command);
		output.println(command);
		output.flush();
	}
	
	public static boolean hasValidArguments(String[] args){
		if(args.length > 0){
			try{
				int port = Integer.parseInt(args[0]);
				if(port < 0 || port > 65535){
					System.out.println(getErrorMessage("Port must be between 0 and 65535"));
					return false;
				}
			}catch(NumberFormatException e){
				System.out.println(getErrorMessage("Incorrect usage, port must be a number..."));
				return false;
			}
		}
		if(args.length == 3){
			//Check port validity
			if(args[1].equalsIgnoreCase("behavior")){
				String behaviorName = args[2];
				Behavior behavior = Behavior.getBehaviorFromName(behaviorName);
				if(behavior == Behavior.NO_CHANGE){
					System.out.println(getErrorMessage("Invalid behavior value, see below..."));
					return false;
				}
			}else{
				System.out.println(getErrorMessage("Incorrect usage, see below..."));
				return false;
			}
		}else if(args.length == 4){
			//Check port validity
			
			if(args[1].equalsIgnoreCase("action")){
				String actionName = args[2];
				Action action = Action.getActionFromName(actionName);
				if(action == Action.NO_CHANGE){
					System.out.println(getErrorMessage("Invalid action value, see below..."));
					return false;
				}
				try{
					Integer.parseInt(args[3]);
				}catch(NumberFormatException e){
					System.out.println(getErrorMessage("Invalid value, the third argument must be an integer. See below..."));
					return false;
				}
			}else{
				System.out.println(getErrorMessage("Incorrect usage, see below..."));
				return false;
			}
		}else{
			System.out.println(getErrorMessage("Incorrect usage, see below..."));
			return false;
		}
		return true;
		
	}
	
	public static String getErrorMessage(String header){
		return header + "\n"
				+ "Correct usage:\n"
				+ "  java -jar DroneCommandSender.jar [CommandServerPort] [CommandType] [Action | Behavior] (value)\n"
				+ "For example:\n"
				+ "  java -jar DroneCommandSender.jar 40022 behavior [behavior]\n"
				+ "    java -jar DroneCommandSender.jar 40022 behavior circle\n"
				+ "  java -jar DroneCommandSender.jar 40022 action [action] (value)\n"
				+ "    java -jar DroneCommandSender.jar 40022 action forward 10\n"
				+ "Valid behaviors:\n"
				+ "  circle, idle, roam, line, custom\n"
				+ "Valid actions:\n"
				+ "  forward, backward, left, right, up, down, rotate_left, rotate_right";
	}

}
