package jp.dragon.field.model;

public class Vm {
	private String name_ = null;
	private String dataStore_ = null;
	
	public Vm() {
		
	}

	public Vm(String name, String dataStore) {
		name_ = name;
		dataStore_ = dataStore;
	}

	public String getName() {
		return name_;
	}

	public void setName(String name) {
		name_ = name;
	}

	public String getDataStore() {
		return dataStore_;
	}

	public void setDataStore(String dataStore) {
		dataStore_ = dataStore;
	}
	
	
}
