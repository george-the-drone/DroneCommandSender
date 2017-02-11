package edu.mit.anekin.DroneCommandSender;

public enum Behavior {

	CIRCLE("circle"),
	IDLE("idle"),
	ROAM("roam"),
	LINE("line"),
	CUSTOM("custom"),
	NO_CHANGE("no_change");
	
	private final String name;
	
	private Behavior(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public static Behavior getBehaviorFromName(String name){
		switch(name.toLowerCase()){
			case "circle":
				return CIRCLE;
			case "idle":
				return IDLE;
			case "roam":
				return ROAM;
			case "line":
				return LINE;
			case "custom":
				return CUSTOM;
			default:
				return NO_CHANGE;
		}
	}
	
}
