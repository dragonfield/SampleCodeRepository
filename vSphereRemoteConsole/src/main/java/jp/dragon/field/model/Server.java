package jp.dragon.field.model;

import java.util.ArrayList;
import java.util.List;

public class Server {
	private String host_ = null;
	private String version_ = null;
	private String user_ = null;
	private String password_ = null;
	private List<Vm> vms_ = new ArrayList<Vm>();
	
	public Server() {
		
	}
	
	public Server(String host, String version, String user, String password) {
		host_ = host;
		version_ = version;
		user_ = user;
		password_ = password;
	}

	public void addVm(Vm vm) {
		vms_.add(vm);
	}

	public String getHost() {
		return host_;
	}

	public void setHost(String host) {
		host_ = host;
	}

	public String getVersion() {
		return version_;
	}

	public void setVersion(String version) {
		version_ = version;
	}

	public String getUser() {
		return user_;
	}

	public void setUser(String user) {
		user_ = user;
	}

	public String getPassword() {
		return password_;
	}

	public void setPassword(String password) {
		password_ = password;
	}

	public List<Vm> getVms() {
		return vms_;
	}

	public void setVms(List<Vm> vms) {
		vms_ = vms;
	}
	
}
