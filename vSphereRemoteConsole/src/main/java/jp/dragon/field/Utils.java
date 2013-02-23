package jp.dragon.field;

import java.io.File;
import java.io.IOException;

import jp.dragon.field.model.Server;
import jp.dragon.field.model.Setting;
import jp.dragon.field.model.Vm;


public abstract class Utils {
	
	private static final String EXE_FILENAME = "vmware-vmrc.exe";

	public static void start(String hostName, String vmName, Setting setting) {		
		Server server = setting.getServer(hostName);
		Vm vm = setting.getVm(hostName, vmName);
		
		try {
			start(setting.getHome() + File.separator + server.getVersion() + File.separator + EXE_FILENAME,
				  hostName, server.getUser(),
				  server.getPassword(),
				  vm.getDataStore(), vmName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void start(String command, String host, String user, String password, String dataStore, String vm) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(command,
				                                    "-h", host,
				                                    "-u", user,
				                                    "-p", password,
				                                    "\"[" + dataStore + "] " + vm + "/" + vm + ".vmx\"");
		Process process = builder.start();		
	}

}
