package com.qa.phonepemdp.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAction {

	private ActionData actionData;
	private Reportee  reportee;
	private Timeline timeline;
	private String status;
	

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ActionData{
		private String assignedToEmail;
		private String dueDate;
		private String actionDesc;
		
		public ActionData(String assignedToEmail, String dueDate, String actionDesc) {
			this.assignedToEmail = assignedToEmail;
			this.dueDate = dueDate;
			this.actionDesc = actionDesc;
		}
		private String actionPlanId;
		
	}
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Reportee{
		private String reporteeName;
		private String reporteeEmail;
		private String positionName;
		private boolean selected;
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Timeline{
		private String timelineText;
		private String year;
		private boolean selected;
		
	}
	
	
}
