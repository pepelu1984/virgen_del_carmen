package com.dynamicdroides.vdc.services;

import java.io.*;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

import com.dynamicdroides.vdc.services.login.controllers.IndexController;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {

    // change this to redirect output if desired
    private static PrintStream out = System.out;

    static Logger log = Logger.getLogger(SOAPLoggingHandler.class);
	 
    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        logToSystemOut(smc);
        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        logToSystemOut(smc);
        return true;
    }

    // nothing to clean up
    public void close(MessageContext messageContext) {
    }
    public String convertStreamToString(InputStream is)
    throws IOException {
/*
 * To convert the InputStream to String we use the
 * Reader.read(char[] buffer) method. We iterate until the
 * Reader return -1 which means there's no more data to
 * read. We use the StringWriter class to produce the string.
 */
if (is != null) {
    Writer writer = new StringWriter();

    char[] buffer = new char[1024];
    try {
        Reader reader = new BufferedReader(
                new InputStreamReader(is, "UTF-8"));
        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }
    } finally {
        is.close();
    }
    return writer.toString();
} else {        
    return "";
}
}
    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context
     * to see if this is an outgoing or incoming message.
     * Write a brief message to the print stream and
     * output the message. The writeTo() method can throw
     * SOAPException or IOException
     */
    private void logToSystemOut(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean)
        smc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        log.error("Entrando en error servicio:");
        
        if (outboundProperty.booleanValue()) {
            out.println("\nOutbound message:");
            log.error("\nOutbound message:");
           
        } else {
            out.println("\nInbound message:");
            log.error("\nInbound message:");
        }
        SOAPMessage message = smc.getMessage();
        try {
            
        	
        	ByteArrayOutputStream os = new ByteArrayOutputStream();
        	PrintStream ps = new PrintStream(os);
        	
        	message.writeTo(ps);
        	String output = os.toString("UTF8");
        	log.error(output);
     
        } catch (Exception e) {
        	e.printStackTrace();
            out.println("Exception in handler: " + e);
            log.error(e);
        }
        
        
    }
    
    class StringOutputStream extends OutputStream {

	  StringBuilder mBuf;
	  
	  public String getString() {
	    return mBuf.toString();
	  }

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		mBuf.append((char) b);
	}
	}
    
    
}
