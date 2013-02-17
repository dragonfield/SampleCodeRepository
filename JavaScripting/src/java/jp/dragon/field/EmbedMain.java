package jp.dragon.field;

import org.jruby.embed.ScriptingContainer;

public class EmbedMain {

	public static void main(String[] args) {
		ScriptingContainer ruby = new ScriptingContainer();
		ruby.runScriptlet("puts \"Hello World\"");
	}

}
