package com.learn.java.melucene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BookDaoImpl implements BookDao{

	public List<Book> queryBookList() {
		//创建数据库连接
		Connection con = null;
		//预编译statement
		PreparedStatement pst = null;
		//创建结果集
		ResultSet rs = null;
		//图书馆列表
		List<Book> list = new ArrayList<Book>();
		try {
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/testgrp", "root", "root");
			//sql语句
			String sql = "select * from book";
			//创建Preparedstatement
			pst =con.prepareStatement(sql);
			//获得结果集
			rs = pst.executeQuery();
			//处理结果集
			while (rs.next()) {

				Book book = new Book();

				book.setId(rs.getInt("id"));

				book.setName(rs.getString("name"));

				book.setPrice(rs.getFloat("price"));

				book.setPic(rs.getString("pic"));

				book.setDesc(rs.getString("desc"));

				list.add(book);

				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		return list;
	}
}
