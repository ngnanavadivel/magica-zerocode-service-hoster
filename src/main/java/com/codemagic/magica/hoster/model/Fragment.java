package com.codemagic.magica.hoster.model;

public class Fragment {

	public enum FragmentType {
		RESOURCE_DISCRIMINATOR, RESOURCE_ID
	};

	public enum GoesAsType {
		QUERY_PARAMETER, PATH_FRAGMENT
	};

	private int				index;
	private FragmentType	type;
	private GoesAsType			goesAs;
	private String			name;

	public FragmentType getType() {
		return type;
	}

	public void setType(FragmentType type) {
		this.type = type;
	}

	public GoesAsType getGoesAs() {
		return goesAs;
	}

	public void setGoesAs(GoesAsType goesAs) {
		this.goesAs = goesAs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}