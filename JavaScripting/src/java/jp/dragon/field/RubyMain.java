package jp.dragon.field;

import java.io.File;
import java.io.FileReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class RubyMain {

	public static void main(String[] args) throws Exception {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("ruby");
		manager.put("file_name", "data/test.txt");
		engine.eval(new FileReader(new File("ruby/search.rb")));
		System.out.println(engine.get("result"));
	}

}
