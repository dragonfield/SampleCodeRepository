package jp.dragon.field.config;

import java.util.ArrayList;
import java.util.List;

public class SystemGroup {
	private String name_ = null;
	private List<Instance> instances_ = new ArrayList<Instance>();

	public SystemGroup() {
	}
	
	public SystemGroup(String name) {
		name_ = name;
	}

	public void addInstance(Instance instance) {
		instances_.add(instance);
	}
	
	public String getName() {
		return name_;
	}

	public void setName(String name) {
		name_ = name;
	}

	public List<Instance> getInstances() {
		return instances_;
	}

	public void setInstances(List<Instance> instances) {
		instances_ = instances;
	}

	
}
