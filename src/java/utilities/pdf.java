package utilities;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class pdf implements Printable
{
    PDFFile pdfFile;
    private String error="";
    
    public pdf(PDFFile pdfFile)
    {
        this.pdfFile = pdfFile;
    }
            
    @Override
    public int print (Graphics g, PageFormat format, int index) throws PrinterException
    {
        int pagenum = index+1;
        if (pagenum < 1 || pagenum > pdfFile.getNumPages ())
        return NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = g2d.getTransform ();

        PDFPage pdfPage = pdfFile.getPage (pagenum);

        Dimension dim;
        dim = pdfPage.getUnstretchedSize ((int) format.getImageableWidth (),
        (int) format.getImageableHeight (),
        pdfPage.getBBox ());

        Rectangle bounds = new Rectangle ((int) format.getImageableX (),
                (int) format.getImageableY (),
                dim.width,
                dim.height);

        PDFRenderer rend = new PDFRenderer (pdfPage, (Graphics2D) g, bounds,null, null);
        try
        {
            pdfPage.waitForFinish ();
            rend.run ();
        }
        catch (InterruptedException ie)
        {
            error = ie.getMessage();
        }

        g2d.setTransform (at);

        return PAGE_EXISTS;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }
}
