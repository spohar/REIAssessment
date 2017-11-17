package com.sxpohar.rei;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import com.sxpohar.rei.model.CommandModel;
import com.sxpohar.rei.model.CommandTypes;

public class RoboticClaw {

	public static void main(String[] args) {
		List<Stack<String>> stackList = new ArrayList<Stack<String>>();

		RoboticClaw rc = new RoboticClaw();
		// Read the file
		List<String> lines = rc.readFile("input.txt");
		int stackSize = rc.getStackSize(lines.get(0));
		if (stackSize > 0) {
			// Initialize the stack
			rc.initializeStacks(stackList, stackSize);

			// Process the commands...
			lines.stream().skip(1).map(line -> rc.mapCommand(line)).filter(cmd -> rc.validateCommand(cmd))
					.filter(cmd -> rc.validateCommandArguments(cmd, stackSize))
					.forEach(cmd -> rc.processCommand(stackList, cmd));

			// Print the output
			rc.printStacK(stackList);

		} else {
			System.out.println("Size of the stack is not valid");
		}
	}

	/**
	 * Prints stack values
	 */
	private void printStacK(List<Stack<String>> stackList) {
		int idx = 1;
		for (Stack<String> stack : stackList) {
			StringBuilder sb = new StringBuilder();
			while (!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			System.out.println(idx + ": " + sb.toString());
			idx++;
		}
	}

	private List<String> readFile(String fileName) {
		ClassLoader classLoader = new RoboticClaw().getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try {
			return Files.readAllLines(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Initialize the List with stack of Strings
	 */
	private void initializeStacks(List<Stack<String>> stackList, int size) {
		for (int i = 0; i < size; i++) {
			stackList.add(new Stack<String>());

		}
	}

	/**
	 * Parses the first line and return the size
	 */
	private int getStackSize(String sizeStrinng) {
		int size = -1;
		Scanner s = new Scanner(sizeStrinng);
		if (s.next().equals("size") && s.hasNextInt()) {
			size = s.nextInt();
		}
		s.close();
		return size;
	}

	/**
	 * Process each command and update the stack
	 */
	public void processCommand(List<Stack<String>> stackList, CommandModel cmd) {

		CommandFactory.getCommand(CommandTypes.valueOf(cmd.getName())).execute(stackList, cmd.getArgList());
	}

	/**
	 * Validates if the command name is valid and and it has valid number of
	 * arguments.
	 * 
	 * @return true if the command name is valid and and it has valid number of
	 *         arguments.
	 */
	public boolean validateCommand(CommandModel cmd) {
		if (CommandTypes.isValidCommand(cmd.getName())
				&& CommandTypes.valueOf(cmd.getName()).args() == cmd.getArgList().size()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Validates if the given arguments (stack index) are with in valid range
	 * 
	 * @return true if the value are with in the range.
	 */
	public boolean validateCommandArguments(CommandModel cmd, int size) {
		boolean isValid = true;
		for (Integer stackId : cmd.getArgList()) {
			if (stackId <= 0 || stackId > size) {
				isValid = false;
			}
		}
		return isValid;
	}

	/**
	 * Builds CommandModel object
	 * 
	 * @return CommandModel
	 */
	public CommandModel mapCommand(String commandLine) {
		Scanner scan = new Scanner(commandLine);
		String commandName = scan.next();
		List<Integer> argsList = new ArrayList<Integer>();
		while (scan.hasNextInt()) {
			argsList.add(scan.nextInt());
		}
		scan.close();
		return new CommandModel(commandName, argsList);

	}

}
