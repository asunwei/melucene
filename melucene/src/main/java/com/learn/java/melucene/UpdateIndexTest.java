package com.learn.java.melucene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class UpdateIndexTest {
	@Test
	public void testIndexUpdate() throws Exception {
	// 创建分词器
	Analyzer analyzer = new StandardAnalyzer();
	// 创建Directory流对象
	Directory directory = FSDirectory.open(new File("C:/luceneIndex"));
	IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
	// 创建写入对象
	IndexWriter indexWriter = new IndexWriter(directory, config);
	// 创建Document
	Document document = new Document();
	document.add(new TextField("id", "1002", Store.YES));
	//document.add(new TextField("name", "lucene测试test 002", Store.YES));
	// 执行更新，会把所有符合条件的Document删除，再新增。Term意思：field是 name， field值是redis
	indexWriter.updateDocument(new Term("name", "redis"), document);
	// 释放资源
	indexWriter.close();

	}
}
