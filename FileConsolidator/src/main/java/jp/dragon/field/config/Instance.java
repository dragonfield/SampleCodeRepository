package jp.dragon.field.config;

import java.util.ArrayList;
import java.util.List;

public class Instance {
	private String name_ = null;
	private String location_ = null;
	
	private Report report_ = null;
	private List<Metric> metrics_ = new ArrayList<Metric>();

	public Instance() {
	}

	public Instance(String name, String location) {
		name_ = name;
		location_ = location;
	}
	
	public String getLocation() {
		return location_;
	}

	public void setLocation(String location) {
		location_ = location;
	}


	public void addMetric(Metric metric) {
		metrics_.add(metric);
	}
	
	public String getName() {
		return name_;
	}

	public void setName(String name) {
		name_ = name;
	}

	public Report getReport() {
		return report_;
	}

	public void setReport(Report report) {
		report_ = report;
	}

	public List<Metric> getMetrics() {
		return metrics_;
	}

	public void setMetrics(List<Metric> metrics) {
		metrics_ = metrics;
	}

}
