package com.sxpohar.rei;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sxpohar.rei.model.CommandModel;

public class RoboticClawTest {
	RoboticClaw classUnderTest = new RoboticClaw();
	static List<Stack<String>> stackList = null;

	@BeforeClass
	public static void init() {
		stackList = new ArrayList<Stack<String>>();
		initializeStack();
	}

	@Test
	public void testValidateCommand() {
		assertTrue(classUnderTest.validateCommand(new CommandModel("add", Arrays.asList(1))));
		assertTrue(classUnderTest.validateCommand(new CommandModel("rm", Arrays.asList(1))));
		assertTrue(classUnderTest.validateCommand(new CommandModel("mv", Arrays.asList(1, 2))));
		assertFalse(classUnderTest.validateCommand(new CommandModel("rm", Arrays.asList(1, 2))));
		assertFalse(classUnderTest.validateCommand(new CommandModel("xv", Arrays.asList(1, 2))));
	}

	@Test
	public void testValidateArguments() {
		assertTrue(classUnderTest.validateCommandArguments(new CommandModel("add", Arrays.asList(3)), 4));
		assertTrue(classUnderTest.validateCommandArguments(new CommandModel("rm", Arrays.asList(3)), 4));
		assertTrue(classUnderTest.validateCommandArguments(new CommandModel("mv", Arrays.asList(1, 2)), 4));
		assertFalse(classUnderTest.validateCommandArguments(new CommandModel("rm", Arrays.asList(1, 6)), 4));
	}

	@Test
	public void testProcessCommandForAdd() {
		classUnderTest.processCommand(stackList, new CommandModel("add", Arrays.asList(2)));
		assertEquals(1, stackList.get(1).size());
		assertEquals("X", stackList.get(1).pop());
	}

	@Test
	public void testProcessCommandForRemove() {
		stackList.get(1).push("X");
		classUnderTest.processCommand(stackList, new CommandModel("rm", Arrays.asList(2)));
		assertEquals(0, stackList.get(1).size());
	}

	@Test
	public void testProcessCommandForMove() {
		stackList.get(1).push("X");
		classUnderTest.processCommand(stackList, new CommandModel("mv", Arrays.asList(2, 3)));
		assertEquals(0, stackList.get(1).size());
		assertEquals(1, stackList.get(2).size());
		assertEquals("X", stackList.get(2).pop());
	}

	@Test
	public void testMapCommandForAdd() {
		String input = "add 2";
		CommandModel cmd = classUnderTest.mapCommand(input);
		assertEquals("add", cmd.getName());
		assertEquals(1, cmd.getArgList().size());
	}

	@Test
	public void testMapCommandForMove() {
		String input = "rm 2 4";
		CommandModel cmd = classUnderTest.mapCommand(input);
		assertEquals("rm", cmd.getName());
		assertEquals(2, cmd.getArgList().size());
	}

	private static void initializeStack() {
		for (int i = 0; i < 5; i++) {
			stackList.add(new Stack<String>());

		}
	}

}
