package com.qa.phonepemdp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReporteeList {

	private Timeline timeline;
	private Span span;
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Timeline{
		private String timelineText;
		private String year;
		private boolean selected;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Span{
		private String spanText;
		private boolean selected;
	}
}
