package com.demo.cache;

	import java.util.ArrayList;  
	import java.util.Collection;  
	import java.util.LinkedHashMap;  
	import java.util.concurrent.locks.Lock;  
	import java.util.concurrent.locks.ReentrantLock;  
	import java.util.Map;  


	/** 
	 * LRU
	 * 类说明：利用LinkedHashMap实现简单的缓存， 必须实现removeEldestEntry方法，具体参见JDK文档 
	 * 
	 *  2.1. 原理
LRU-K中的K代表最近使用的次数，因此LRU可以认为是LRU-1。LRU-K的主要目的是为了解决LRU算法“缓存污染”的问题，其核心思想是将“最近使用过1次”的判断标准扩展为“最近使用过K次”。

2.2. 实现
相比LRU，LRU-K需要多维护一个队列，用于记录所有缓存数据被访问的历史。只有当数据的访问次数达到K次的时候，才将数据放入缓存。当需要淘汰数据时，LRU-K会淘汰第K次访问时间距当前时间最大的数据。



1. 数据第一次被访问，加入到访问历史列表；

2. 如果数据在访问历史列表里后没有达到K次访问，则按照一定规则（FIFO，LRU）淘汰；

3. 当访问历史队列中的数据访问次数达到K次后，将数据索引从历史队列删除，将数据移到缓存队列中，并缓存此数据，缓存队列重新按照时间排序；

4. 缓存数据队列中被再次访问后，重新排序；

5. 需要淘汰数据时，淘汰缓存队列中排在末尾的数据，即：淘汰“倒数第K次访问离现在最久”的数据。

LRU-K具有LRU的优点，同时能够避免LRU的缺点，实际应用中LRU-2是综合各种因素后最优的选择，LRU-3或者更大的K值命中率会高，但适应性差，需要大量的数据访问才能将历史访问记录清除掉。

2.3. 分析
【命中率】

LRU-K降低了“缓存污染”带来的问题，命中率比LRU要高。

【复杂度】

LRU-K队列是一个优先级队列，算法复杂度和代价比较高。

【代价】

由于LRU-K还需要记录那些被访问过、但还没有放入缓存的对象，因此内存消耗会比LRU要多；当数据量很大的时候，内存消耗会比较可观。

LRU-K需要基于时间进行排序（可以需要淘汰时再排序，也可以即时排序），CPU消耗比LRU要高。
	 * @param <K> 
	 * @param <V> 
	 */ 
	
	
	//此实现用的LRU-1
	public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {  
	    private final int maxCapacity;  //最大容量

	    private static final float DEFAULT_LOAD_FACTOR = 0.75f;  

	    private final Lock lock = new ReentrantLock();  

	    public LRULinkedHashMap(int maxCapacity) {  
	        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);  
	        this.maxCapacity = maxCapacity;  
	    }  

	    @Override 
	    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {  
	        return size() > maxCapacity;  
	    }  
	    @Override 
	    public boolean containsKey(Object key) {  
	        try {  
	            lock.lock();  
	            return super.containsKey(key);  
	        } finally {  
	            lock.unlock();  
	        }  
	    }  


	    @Override 
	    public V get(Object key) {  
	        try {  
	            lock.lock();  
	            return super.get(key);  
	        } finally {  
	            lock.unlock();  
	        }  
	    }  

	    @Override 
	    public V put(K key, V value) {  
	        try {  
	            lock.lock();  
	            return super.put(key, value);  
	        } finally {  
	            lock.unlock();  
	        }  
	    }  

	    public int size() {  
	        try {  
	            lock.lock();  
	            return super.size();  
	        } finally {  
	            lock.unlock();  
	        }  
	    }  

	    public void clear() {  
	        try {  
	            lock.lock();  
	            super.clear();  
	        } finally {  
	            lock.unlock();  
	        }  
	    }  

	    public Collection<Map.Entry<K, V>> getAll() {  
	        try {  
	            lock.lock();  
	            return new ArrayList<Map.Entry<K, V>>(super.entrySet());  
	        } finally {  
	            lock.unlock();  
	        }  
	    }  

}
