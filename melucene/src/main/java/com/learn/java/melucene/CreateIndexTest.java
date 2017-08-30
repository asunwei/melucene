package com.learn.java.melucene;

import java.util.List;

import org.junit.Test;


public class CreateIndexTest {
	@Test
	public void testCreateIndex() {
		//采集数据
		BookDao bookDao = new BookDaoImpl();

		List<Book> bookList = bookDao.queryBookList();
		//创建Document文档
		
	}
}