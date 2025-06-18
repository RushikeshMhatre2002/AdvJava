package com.sunbeam.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Candidate {
	private int id;
	private String name;
	private String party;
	private int votes;
}