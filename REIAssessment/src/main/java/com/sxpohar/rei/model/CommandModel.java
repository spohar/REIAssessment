package com.sxpohar.rei.model;

import java.util.List;

public class CommandModel {

	String name;
	List<Integer> argList;

	public CommandModel(String name, List<Integer> argList) {
		this.name = name;
		this.argList = argList;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getArgList() {
		return argList;
	}

}
