package com.possibletriangle.shinygear.item;

public interface IModItem {

	void onOreEvent();
	void recipe();
	String type();

	default boolean isIron() {
		return false;
	}

	static String parse(String in) {
	
		String out = in.substring(0, 1).toUpperCase();
	
		for (int i = 1; i < in.length(); i++) {
			if (in.charAt(i) == '_') {
				out += in.substring(i + 1, i + 2).toUpperCase();
				i++;
			} else
				out += in.charAt(i);
		}
	
		return out;
	
	}

    String ore();

}
