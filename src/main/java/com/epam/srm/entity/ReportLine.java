package com.epam.srm.entity;

public class ReportLine {
	private String taskName;
	private String sprint;
	private String story;
	private String tag;
	private String owner;
	private String state;
	private String project;
	private double estimate;
	private double todo;
	private double actual;
	private double estimationVariance;

	public double getEstimationVariance() {
		return estimationVariance;
	}

	public void setEstimationVariance(double estimationVariance) {
		this.estimationVariance = estimationVariance;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getEstimate() {
		return estimate;
	}

	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}

	public double getTodo() {
		return todo;
	}

	public void setTodo(double todo) {
		this.todo = todo;
	}

	public double getActual() {
		return actual;
	}

	public void setActual(double actual) {
		this.actual = actual;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
