package com.learn.java.melucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.util.Version;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.junit.Test;


public class CreateIndexTest {
	@Test
	public void testCreateIndex() throws IOException {
		//1.采集数据
		BookDao bookDao = new BookDaoImpl();

		List<Book> bookList = bookDao.queryBookList();
		System.out.println(bookList);
		//2.创建Document文档
		List<Document> documents = new ArrayList<Document>();
		for(Book book : bookList) {
			Document document = new Document();
			/*
			 * Document文档中添加field域
			 * 图书 id、name、price、pic、desc
			 * Store.YES 表示存储到文档域中
			 */
			//图书ID，不分词，不索引，存储    StoredField(FieldName, FieldValue)
			document.add(new StoredField("id", book.getId().toString()));
			//图书名称， 分词，索引，存储  TextField(FieldName, FieldValue, Store.YES/NO)
			document.add(new TextField("name",book.getName().toString(),Store.YES));
			//图书的价格， 分词，索引，存储  FloatField(FieldName, FieldValue,Store.YES)
			document.add(new FloatField("price", book.getPrice(), Store.YES));
			//图书的图片，不分词，不索引，储存  StoredField(FieldName, FieldValue)
			document.add(new StoredField("pic",book.getPic().toString()));
			//图书的描述， 分词，索引，不储存  TextField(FieldName, FieldValue, Store.YES/NO)
			document.add(new TextField("desc",book.getDesc().toString(),Store.NO));
			//把Document到list中
			documents.add(document);
		}
		//3.创建analyzer分词器，分析文档，对文档进行分词
		Analyzer analyzer = new StandardAnalyzer();
		//4.创建Directory对象，申明索引库的位置
		Directory directory = FSDirectory.open(new File("C:/luceneIndex"));
		//5.创建IndexWriterConfig对象，写入索引需要的配置
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
		//6.创建IndexWriter对象
		IndexWriter indexWriter = new IndexWriter(directory,config);
		//7.写入索引库，通过IndexWriter添加文档对象document。
		for(Document document: documents){
			indexWriter.addDocument(document);
		}
		//8.释放资源
		indexWriter.close();
	}
}