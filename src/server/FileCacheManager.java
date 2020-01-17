package server;

import java.io.Serializable;
import java.util.HashMap;

public class FileCacheManager implements CacheManager<String, String>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> cache;

	public HashMap<String, String> getCache() {
		return cache;
	}

	public void setCache(HashMap<String, String> cache) {
		this.cache = cache;
	}

	// constructor
	public FileCacheManager() {
		this.cache = new HashMap<String, String>();
	}

	@Override
	public boolean solutionExist(String problem) {
		return cache.containsKey(problem);
	}

	@Override
	public String getSolution(String problem) {
		return cache.get(problem);
	}

	@Override
	public void saveSolution(String problem, String solution) {
		cache.put(problem, solution);
	}
}