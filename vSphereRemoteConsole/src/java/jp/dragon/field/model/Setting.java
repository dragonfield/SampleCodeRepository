package jp.dragon.field.model;

import java.util.ArrayList;
import java.util.List;

public class Setting {
	private String home_ = null;
	private List<Server> servers_ = new ArrayList<Server>();

	public Setting() {
		
	}
	
	public void addServer(Server server) {
		servers_.add(server);
	}
	
	public void addVm(String name, Vm vm) {
		Server server = null;
		for (int i = 0; i < servers_.size(); i++) {
			server = servers_.get(i);
			
			if (name.equalsIgnoreCase(server.getHost())) {
				server.addVm(vm);
			}
		}
		
	}

	public String[] getHostNames() {
		String[] hostNames = new String[servers_.size()];
		
		for (int i = 0; i < servers_.size(); i++) {
			hostNames[i] = servers_.get(i).getHost();
		}
		                              
		return hostNames;
	}
	
	public String[] getVmNames(String name) {
		String[] vmNames = null;
		
		for (int i = 0; i < servers_.size(); i++) {
			Server server = servers_.get(i);
			
			if (name.equalsIgnoreCase(server.getHost())) {
				List<Vm> vms = server.getVms();
				vmNames = new String[vms.size()];
				
				for (int j =0; j < vms.size(); j++) {
					vmNames[j] = vms.get(j).getName();
				}
				
			}
		}
		
		return vmNames;
	}
	
	public Server getServer(String name) {
		Server result = null;
		
		for (int i = 0; i < servers_.size(); i++) {
			Server server = servers_.get(i);
			
			if (name.equalsIgnoreCase(server.getHost())) {
				result = server;
			}
		}
		
		return result;
	}
	
	public Vm getVm(String serverName, String vmName) {
		Vm result = null;
		
		for (int i = 0; i < servers_.size(); i++) {
			Server server = servers_.get(i);
			
			if (serverName.equalsIgnoreCase(server.getHost())) {
				List<Vm> vms = server.getVms();
				
				for (int j =0; j < vms.size(); j++) {
					Vm vm = vms.get(j);
					
					if (vmName.equalsIgnoreCase(vm.getName())) {
						result = vm;
					}
				}
				
			}
		}
		
		return result;
	}
	
	public Setting(String home) {
		home_ = home;
	}

	public String getHome() {
		return home_;
	}

	public void setHome(String home) {
		home_ = home;
	}

	public List<Server> getServers() {
		return servers_;
	}

	public void setServers(List<Server> servers) {
		servers_ = servers;
	}
}
