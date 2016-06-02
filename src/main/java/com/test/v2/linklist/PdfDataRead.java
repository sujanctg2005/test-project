package com.test.v2.linklist;

import java.io.IOException;
import java.io.PrintStream;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PdfDataRead {
	 /**
     * @param args
     */
    public static void main(String[] args) {
       /* try {
             
            PdfReader reader = new PdfReader("C:/Users/logicrule/Downloads/transaction_log_IAM1.0.pdf");
            System.out.println("This PDF has "+reader.getNumberOfPages()+" pages.");
            String page = PdfTextExtractor.getTextFromPage(reader, 25);
            System.out.println("Page Content:\n\n"+page+"\n\n");
            System.out.println("Is this document tampered: "+reader.isTampered());
            System.out.println("Is this document encrypted: "+reader.isEncrypted());
 
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    	try {
			parsePdf("C:/Users/logicrule/Downloads/transaction_log_IAM1.0.pdf"  ,"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    }
    
    public static void parsePdf(String pdf, String txt) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PrintStream out = System.out;//new PrintWriter(new FileOutputStream(txt));
        Rectangle rect = new Rectangle(0,0,100, 580);
        RenderFilter filter = new RegionTextRenderFilter(rect);
        TextExtractionStrategy strategy;
        int start =25;
        int end =25;
        for (int i = start; i <=end; i++) {
            strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
            out.println(PdfTextExtractor.getTextFromPage(reader, i, strategy));
        }
        out.flush();
        out.close();
        reader.close();
    }
}
