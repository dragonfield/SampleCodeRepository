package jp.dragon.field.task;

import java.io.File;
import java.util.Date;

public class Task {
	private Date date_ = null;
	private File source_ = null;
	private File destination_ = null;
	
	public Task() {

	}

	public Task(Date date, File source, File destination) {
		date_ = date;
		source_ = source;
		destination_ = destination;
	}

	public Date getDate() {
		return date_;
	}

	public void setDate(Date date) {
		date_ = date;
	}

	public File getSource() {
		return source_;
	}

	public void setSource(File source) {
		source_ = source;
	}

	public File getDestination() {
		return destination_;
	}

	public void setDestination(File destination) {
		destination_ = destination;
	}

	@Override
	public String toString() {
		return "Task [date_=" + date_ + ", source_=" + source_
				+ ", destination_=" + destination_ + "]";
	}

}
