package com.sxpohar.rei;

import com.sxpohar.rei.command.AddCommand;
import com.sxpohar.rei.command.Command;
import com.sxpohar.rei.command.MoveCommand;
import com.sxpohar.rei.command.RemoveCommand;
import com.sxpohar.rei.model.CommandTypes;

public class CommandFactory {

	public static Command getCommand(CommandTypes command) {
		switch (command) {
		case add:
			return new AddCommand();
		case rm:
			return new RemoveCommand();
		case mv:
			return new MoveCommand();
		default:
			return null;
		}
	}
}
