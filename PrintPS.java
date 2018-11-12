import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.print.event.*;
import java.util.*;
import java.io.File;

public class PrintPS {

        public static void main(String args[]) {
				System.out.println("Antes de crear el objeto PrintPS");
                PrintPS ps = new PrintPS();
				File archivo = new File("ejemplo.pdf");
				if(archivo.delete()){
					System.err.println("archivo borrado correctamente");
				}
        }
        public PrintPS() {
                /* Construct the print request specification.
                 * The print data is Postscript which will be 
                 * supplied as a stream.  The media size 
                 * required is A4, and 2 copies are to be printed
                 */
				System.out.println("ENTRA DENTRO DE LA FUNCION PrintPS");
                DocFlavor flavor = DocFlavor.INPUT_STREAM.POSTSCRIPT;
                PrintRequestAttributeSet aset = 
                        new HashPrintRequestAttributeSet();
                aset.add(MediaSizeName.ISO_A4);
                aset.add(new Copies(1));
                aset.add(Sides.TWO_SIDED_LONG_EDGE);
                aset.add(Finishings.STAPLE);
				
                /* locate a print service that can handle it */
                //PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, aset);
				PrintService[] pservices = PrintServiceLookup.lookupPrintServices(null, null);
			

				
				//System.out.println("selected printer " + pservices[0].getName());
			
			    System.out.println("Estoy antes del IF");
                if (pservices.length > 0) {
                        System.out.println("selected printer " + pservices[0].getName());

                        /* create a print job for the chosen service */
                        DocPrintJob pj = pservices[0].createPrintJob();
                        try {
                                /* 
                                * Create a Doc object to hold the print data.
                                * Since the data is postscript located in a disk file,
                                * an input stream needs to be obtained
                                * BasicDoc is a useful implementation that will if requested
                                * close the stream when printing is completed.
                                */
                                FileInputStream fis = new FileInputStream("ejemplo.pdf");
                                Doc doc = new SimpleDoc(fis, flavor, null);

                                /* print the doc as specified */
                                pj.print(doc, aset);
					
                                /*
                                * Do not explicitly call System.exit() when print returns.
                                * Printing can be asynchronous so may be executing in a
                                * separate thread.
                                * If you want to explicitly exit the VM, use a print job 
                                * listener to be notified when it is safe to do so.
                                */

                        } catch (IOException ie) { 
                                System.err.println(ie);
                        } catch (PrintException e) { 
                                System.err.println(e);
                        }
                }
        }
}