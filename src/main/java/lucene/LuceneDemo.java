package lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author liming
 * @version [版本号, 十月 15, 2016]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LuceneDemo
{
    public void createIndex(String indexPath)
        throws IOException
    {
        Directory dir = FSDirectory.open(Paths.get(indexPath));
        Analyzer analyzer = new StandardAnalyzer();
        Document document = new Document();
        document.add(new Field("name","zhansan", Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("address","hangzhou", Field.Store.YES, Field.Index.ANALYZED));
        document.add(new Field("sex","male", Field.Store.YES, Field.Index.NOT_ANALYZED));
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(dir,conf);
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    public void query(String querStr, String[] fields, String indexPath)
        throws IOException, ParseException
    {
        Analyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        Query query = parser.parse(querStr);

        Directory dir = FSDirectory.open(Paths.get(indexPath));
        DirectoryReader ireader = DirectoryReader.open(dir);
        IndexSearcher indexSearcher = new IndexSearcher(ireader);
        org.apache.lucene.search.Filter filter = null;
        TopDocs topDocs = indexSearcher.search(query, filter, 10000);

        System.out.println(topDocs.totalHits);

        for(ScoreDoc doc : topDocs.scoreDocs)
        {
            int docNum = doc.doc;
            Document d = indexSearcher.doc(docNum);
            printDocument(d);
        }
    }

    public void printDocument(Document d)
    {
        for(IndexableField field :d.getFields()){
            System.out.println(field.name()+":"+field.stringValue());
            d.getValues(field.name());
        }
    }


    public static void main(String[] args)
        throws IOException, ParseException
    {
        LuceneDemo demo = new LuceneDemo();
        String[] strs = {"name","address"};
        demo.query("zhangsan",strs,"D://test");
    }
}
