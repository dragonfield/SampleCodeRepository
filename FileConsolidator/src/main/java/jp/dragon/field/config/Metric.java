package jp.dragon.field.config;

public class Metric {
	public static final String DATE_PRE = "pre";
	public static final String DATE_POST = "post";
	
	private String name_ = null;
	private String dir_ = null;
	private String prefix_ = null;
	private String date_ = null;

	public Metric() {
	}
		
	public Metric(String name, String dir, String prefix, String date) {
		name_ = name;
		dir_ = dir;
		prefix_ = prefix;
		date_ = date;
	}

	public String getDir() {
		return dir_;
	}

	public void setDir(String dir) {
		dir_ = dir;
	}

	public String getName() {
		return name_;
	}

	public void setName(String name) {
		name_ = name;
	}

	public String getPrefix() {
		return prefix_;
	}

	public void setPrefix(String prefix) {
		prefix_ = prefix;
	}

	public String getDate() {
		return date_;
	}

	public void setDate(String date) {
		date_ = date;
	}
	
}
