package com.learn.java.melucene;

import java.util.List;

public interface BookDao {
	/**
	 * 查询所有的Book数据
	 * @return
	 */
	List<Book> queryBookList();
}
