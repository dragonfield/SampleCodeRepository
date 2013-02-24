package jp.dragon.field.vfs2;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;

public class VfsZipWalker {

	public static void main(String[] args) throws Exception {
		FileObject resourceFile = null;
		
		try {
			resourceFile = VFS.getManager().resolveFile("zip:file:C:///work/sample2.zip");
			fileWalk(resourceFile);
		} finally {
			if (resourceFile != null) {
				resourceFile.close();
			}
		}
	}
	
	public static void fileWalk(FileObject file) throws IOException{
		FileObject[] children = file.getChildren();

		for (int i = 0; i < children.length; i++) {
			if (FileType.FILE.equals(children[i].getType())) {
				System.out.println(children[i].getName().toString());
				try (InputStream content = children[i].getContent().getInputStream()) {
					System.out.println(IOUtils.toString(content));
				}
			} else if (FileType.FOLDER.equals(children[i].getType())) {
				fileWalk(children[i]);
			}
		}
		
		
	}

}
