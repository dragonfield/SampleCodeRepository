package jp.dragon.field.task;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class CsvTaskPersisterTest {

	//@Test
	public void testPersist() throws Exception {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task(DateUtils.parseDate("2013/03/20", CsvTaskPersister.DATE_FORMAT),
				  new File("./data/server/test1.lst"),
				  new File("./data/server/to")));
		tasks.add(new Task(DateUtils.parseDate("2013/03/22", CsvTaskPersister.DATE_FORMAT),
				  new File("./data/server/test2.lst"),
				  new File("./data/server/to")));
		tasks.add(new Task(DateUtils.parseDate("2013/03/25", CsvTaskPersister.DATE_FORMAT),
				  new File("./data/server/test3.lst"),
				  new File("./data/server/to")));
		
		CsvTaskPersister persister = new CsvTaskPersister("./data");
		persister.persist(tasks);
		
	}
	
	@Test
	public void testRetrieve() throws Exception {
		CsvTaskPersister persister = new CsvTaskPersister("./data");
		List<Task> tasks = persister.retrieve();
		
		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.get(i);
			System.out.println(task.toString());
		}
		
	}

}
