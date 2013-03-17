package jp.dragon.field.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterManagerImpl implements FilterManager {
	private List<Filter> filters_ = new ArrayList<Filter>();
	
	public FilterManagerImpl() {
		super();
	}

	public String[] doFilter(String[] record) {
		Filter filter = null;
		String[] current = record;
		String[] result = null;
		
		for (int i = 0; i < filters_.size(); i++) {
			filter = filters_.get(i);
			result = filter.doFilter(current);
			current = record;
		}
		
		return result;
	}
	
	public void setFilter(Filter filter) {
		filters_.add(filter);
	}
	
}
