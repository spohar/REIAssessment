package com.sxpohar.rei.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CommandTypes {
	add(1), mv(2), rm(1);

	int args;

	CommandTypes(int args) {
		this.args = args;
	}

	public int args() {
		return args;
	}

	public static boolean isValidCommand(final String command) {
		return Arrays.stream(CommandTypes.values()).map(CommandTypes::name).collect(Collectors.toSet())
				.contains(command);
	}

}
