package com.sxpohar.rei.command;

import java.util.List;
import java.util.Stack;

public class AddCommand implements Command {

	@Override
	public void execute(List<Stack<String>> stacks, List<Integer> stackIdx) {

		stacks.get(stackIdx.get(0) - 1).push("X");

	}

}
