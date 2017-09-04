package com.learn.java.melucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class DeleteIndexTest {
	@Test
	public void testDeleteIndex() throws IOException {
		//1.创建Directory 对象流
		Directory directory = FSDirectory.open(new File("C:/luceneIndex"));
		//2.创建IndexWriterConfig对象，写入索引需要的配置
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3,null);
		//3.创建写入对象
		IndexWriter indexWriter = new IndexWriter(directory,config);
		//4.根据Term，删除索引库
		//indexWriter.deleteDocuments(new Term("name","java"));
		indexWriter.deleteAll();
		//5.释放资源
		indexWriter.close();
	}
}
