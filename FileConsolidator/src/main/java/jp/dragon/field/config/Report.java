package jp.dragon.field.config;

public abstract class Report {
	protected String dir_ = null;
	protected String prefix_ = null;

	public Report() {
	}

	public Report(String dir, String prefix) {
		dir_ = dir;
		prefix_ = prefix;
	}

	public String getDir() {
		return dir_;
	}

	public void setDir(String dir) {
		dir_ = dir;
	}

	public String getPrefix() {
		return prefix_;
	}

	public void setPrefix(String prefix) {
		prefix_ = prefix;
	}
	
}
