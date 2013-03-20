package jp.dragon.field.config;

import java.util.ArrayList;
import java.util.List;

public class Setting {
	private String root_ = null;
	private List<SystemGroup> systems_ = new ArrayList<SystemGroup>();

	public Setting() {
	}
	
	public Setting(String root) {
		root_ = root;
	}

	public void addSystem(SystemGroup system) {
		systems_.add(system);
	}
	
	public String getRoot() {
		return root_;
	}

	public void setRoot(String root) {
		root_ = root;
	}

	public List<SystemGroup> getSystems() {
		return systems_;
	}

	public void setSystems(List<SystemGroup> systems) {
		systems_ = systems;
	}

}
