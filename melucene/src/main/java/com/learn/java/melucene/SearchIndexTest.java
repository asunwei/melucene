package com.learn.java.melucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class SearchIndexTest {
	@Test
	public void testSearchIndex() throws ParseException, IOException {
		//1.创建搜索对象Query
		//创建分词器
		Analyzer analyzer = new StandardAnalyzer();
		/*
		 * 创建分词器
		 * 第一个参数：默认field域
		 * 第二参数：分词器
		 */
		QueryParser queryParser = new QueryParser("name",analyzer);
		//创建搜索对象
		Query query = queryParser.parse("name: spring");
		//2.创建Directory流对象,声明索引库位置
		Directory directory = FSDirectory.open(new File("C:/luceneIndex"));
		//3.创建索引读取对象IndexReader
		IndexReader reader = DirectoryReader.open(directory);
		//4.创建索引搜索对象
		IndexSearcher searcher = new IndexSearcher(reader);
		/*
		 * 5.使用索引搜索对象，执行索引，返回结果集TopDocs
		 * 第一个参数,搜索对象
		 * 第二个参数，返回数据条数，指定查询结果最顶部的n条数据返回。
		 * 
		 */
		TopDocs topDocs = searcher.search(query, 10);
		System.out.println("查询的总条数：" + topDocs.totalHits);
		//获取结果集
		ScoreDoc[] docs = topDocs.scoreDocs;
		for(ScoreDoc scoreDoc : docs) {
			//获取文档
			int docID = scoreDoc.doc;
			Document doc = searcher.doc(docID);
			System.out.println("================================");
			System.out.println("docID:" + docID);
			System.out.println("bookId:" + doc.get("id"));
			System.out.println("name:" + doc.get("name"));
			System.out.println("price:" + doc.get("price"));
			System.out.println("pic:" + doc.get("pic"));
			System.out.println("desc:" + doc.get("desc"));
		}
		//释放资源
		reader.close();
	}
}
