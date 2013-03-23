package jp.dragon.field.task;

import java.util.List;

public interface TaskPersister {

	public void persist(List<Task> tasks);

	public List<Task> retrieve();

}
