package com.sxpohar.rei.command;

import java.util.List;
import java.util.Stack;

public interface Command {

	public void execute(List<Stack<String>> stacks, List<Integer> stackIdx);

}
