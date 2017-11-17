package com.sxpohar.rei.command;

import java.util.List;
import java.util.Stack;

public class RemoveCommand implements Command {

	@Override
	public void execute(List<Stack<String>> stacks, List<Integer> stackIdx) {
		if (!stacks.get(stackIdx.get(0) - 1).isEmpty()) {
			stacks.get(stackIdx.get(0) - 1).pop();
		}

	}

}
