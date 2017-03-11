package com.joshwithee.model;

import org.springframework.stereotype.Component;

@Component
public class VerseLimit {
	
	int limit;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
