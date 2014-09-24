package utilities;

import com.sun.pdfview.PDFFile;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Imprimir extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Imprimir</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Imprimir at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Imprimir</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> ");
        
        PDFFile pdfFile;
        
        try 
        {
            /* Procesamos la información de la forma adecuada, según se
            trate de datos ASCII o binarios.
            Obtenemos el stream de entrada para leer la informacion que nos envie el
            server*/
            out.println("Obteniendo DataInputStream");
            FileInputStream fileIn = new FileInputStream(new File("D:\\proyectosjava\\sgautomotriz\\web\\pdf\\prueba.pdf"));
            DataInputStream dataIn = new DataInputStream(fileIn);

            // Recibo el numero de archivos que enviara el servidor
            //int numArchivosRecibir = dataIn.readInt();
            //Recibo un unico archivo
            int numArchivosRecibir = 1;
            //System.out.println(" Numero de archivos para imprimir " + numArchivosRecibir);

            // Recibe los archivos
            /*String nameFiles[] = new String[numArchivosRecibir];
            File f = null;
            File files[] = new File[numArchivosRecibir];
            FileOutputStream outputStream = null;*/
            ByteArrayOutputStream outputStream = null;

            System.out.println("lectura del archivo");
            for(int k = 0; k < numArchivosRecibir; k++)
            {
                out.println("aa");
                outputStream = new ByteArrayOutputStream();
                byte buf[] = new byte[1024];
                int len;

                while ((dataIn != null) && ((len = dataIn.read(buf)) != -1))
                {
                    outputStream.write(buf,0,len);
                    //out.println("bb");
                }

                /*
                * Si tenemos varias impresoras configuradas se hace una busqueda
                * de las impresoras
                */
                DocFlavor psInFormat = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);

                // this step is necessary because I have several printers configured
                PrintService myPrinter = null;
                for (int i = 0; i < services.length; i++)
                {
                    String svcName = services[i].toString();
                    //Cambiar el nombre de la impresora
                    if (svcName.contains("Bullzip PDF Printer"))
                    {
                        myPrinter = services[i];
                        out.println(svcName);
                        break;
                    }
                }

                ByteBuffer buff = ByteBuffer.wrap(outputStream.toByteArray());

                pdfFile = new PDFFile(buff);

                if (myPrinter != null) 
                {
                    PrinterJob job = PrinterJob.getPrinterJob ();

                    //Se le asigna la impresora configurada
                    job.setPrintService(myPrinter);
                    try
                    {
                        PageFormat pf = job.defaultPage();
                        Paper paper = new Paper();
                        paper.setSize(595, 842); //A4
                        paper.setImageableArea(10, 10, 523, 770);
                        pf.setPaper(paper);
                        pdf obj = new pdf(pdfFile);
                        job.setPrintable(obj, pf);
                        job.print();
                        out.println("dd");
                        out.println(obj.getError());
                    }
                    catch (Exception pe) 
                    {
                        out.println(pe.getMessage());
                        //response.getWriter().write(pe.getMessage());
                    }
                } 
                else 
                {
                    out.println("no printer services found");
                    //System.out.println("no printer services found");
                }

                outputStream.close();
            }

            /* Y finalmente cerramos la conexión. */

            dataIn.close();
            dataIn = null;
            outputStream = null;
        } 
        catch (PrinterException e) 
        {
            out.println(e.getMessage());
            e.printStackTrace();
        }
        catch (IOException ex) 
        {
            out.println(ex.getMessage());
            ex.printStackTrace();
        }
        out.println("</h1>");
        out.println("</body>");
        out.println("</html>");
        
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
