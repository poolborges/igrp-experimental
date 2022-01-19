/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cv.nosicode.igrpframework.xml;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author pauloborges
 */
public class XSLDemo {

    public final static String BASE_DIR = "/Users/pauloborges/_GIATSI/PROJECTOS_FORK/NOSCODE/NOSiCode-CV/IGRP-Java-Template-Eclipse/IGRP-Template/src/main/webapp/images/IGRP/IGRP2.3/core/formgen/util";

    //public final static String BASE_DIR = "/Users/pauloborges/_GIATSI/PROJECTOS_FORK/NOSCODE/NOSiCode-CV/IGRP-Java-Template-Eclipse/IGRP-Template/src/main/webapp/images/IGRP/IGRP2.3/core/formgen/util/java/crud";
    
    /**
     * Transform based on parameters
     *
     * @param xmlIn    XML
     * @param xslIn    XSL
     * @param result   Result
     * @param paramMap Parameter map
     * @throws javax.xml.transform.TransformerException
     *          will be thrown
     */
    public static void transform(Source xmlIn, Source xslIn, Result result, Map paramMap)
            throws TransformerException {
        try {
            TransformerFactory transformerFactory =  TransformerFactory.newInstance();//new TransformerFactoryImpl();
            Transformer transformer = transformerFactory.newTransformer(xslIn);
            if (paramMap != null) {
                Set set = paramMap.keySet();
                for (Object aSet : set) {
                    if (aSet != null) {
                        String key = (String) aSet;
                        String value = (String) paramMap.get(key);
                        transformer.setParameter(key, value);
                    }
                }
            }
            transformer.transform(xmlIn, result);
        } catch (TransformerException e) {
            //log.error(e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     *
     * @param inFilename
     * @param xslFilename
     * @param outFilename
     */
    public void xsl(String inFilename, String xslFilename, String outFilename, FileURIResolver fileURIResolver) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setURIResolver(fileURIResolver);

            Source xsl = new StreamSource(new FileInputStream(xslFilename));
            Source source = new StreamSource(new FileInputStream(inFilename));
            Result result = new StreamResult(new FileOutputStream(outFilename));

            Templates template = factory.newTemplates(xsl);
            Transformer xformer = template.newTransformer();
            xformer.transform(source, result);
        } catch (FileNotFoundException | TransformerConfigurationException ex) {
            Logger.getLogger(XSLDemo.class.getName()).log(Level.SEVERE, "Exception xslt file: ", ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XSLDemo.class.getName()).log(Level.SEVERE, "Exception xslt transformer: ", ex);
        }
    }

    public void classpathXSL(String inFilename, String xslFilename, String outFilename) {

        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();

            dFactory.setNamespaceAware(true);

            DocumentBuilder dBuilder = dFactory.newDocumentBuilder();

            //ClassLoader cl = XSLDemo.class.getClassLoader();// 
            ClassLoader cl = getClass().getClassLoader();
            // cl.getResourceAsStream("/xml/ASTestApplEvents.xml"))
            // cl.getResourceAsStream("xml/ASTestApplEvents.xml")

            //Class cl = XSLDemo.class;
            //XSLDemo.class.getResourceAsStream("/xml/ASTestApplEvents.xml")
            Document xmlDoc = dBuilder.parse(cl.getResourceAsStream("xml/ASTestApplEvents.xml"));
            Document xslDoc = dBuilder.parse(cl.getResourceAsStream("xml/ASTestApplEvents.xsl"));

            Source xmlDomSource = new DOMSource(xmlDoc);
            Source xslDomSource = new DOMSource(xslDoc);
            //Result xslDomResult = new DOMResult();
            Result xslDomResult = new StreamResult(new String());

            Transformer transformer = tFactory.newTransformer(xslDomSource);

            transformer.transform(xmlDomSource, xslDomResult);

            System.out.println("classpathXSL()" + xslDomResult.toString());

        } catch (ParserConfigurationException | TransformerConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XSLDemo.class.getName()).log(Level.SEVERE, "Exception: ", ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XSLDemo.class.getName()).log(Level.SEVERE, "Exception: ", ex);
        }
    }

    public void saxonXSLT(String inFilename, String xslFilename, String outFilename) {

        try {
            Processor processor = new Processor(false);

            XsltCompiler compiler = processor.newXsltCompiler();
            XsltExecutable stylesheet = compiler.compile(new StreamSource(new File(inFilename)));
            Serializer out = processor.newSerializer(new File(outFilename));
            out.setOutputProperty(Serializer.Property.METHOD, "html");
            out.setOutputProperty(Serializer.Property.INDENT, "yes");
            Xslt30Transformer transformer = stylesheet.load30();
            transformer.applyTemplates(new StreamSource(new File(xslFilename)), out);
        } catch (SaxonApiException ex) {
            Logger.getLogger(XSLDemo.class.getName()).log(Level.SEVERE, "SaxonAPI exception: ", ex);
        }
    }

    public static void main(String[] args) {

        System.out.println("========= XML-plsql.xml");
        new XSLDemo().xsl(
                XSLDemo.BASE_DIR + "/plsql/XML-plsql.xml",
                XSLDemo.BASE_DIR + "/plsql/XSL-plsql.xsl",
                "XSL-plsql.plsql", new FileURIResolver(BASE_DIR + "/plsql"));

        //new XSLDemo().classpathXSL(BASE_DIR, BASE_DIR, BASE_DIR);
        //new XSLDemo().saxonXSLT();
        System.out.println("========= Teste.xml");
        new XSLDemo().xsl(
                BASE_DIR + "/java/crud/Teste.xml",
                BASE_DIR + "/java/crud/XSL_CRUD_LIST_GENERATOR.xsl",
                "Teste.html", new FileURIResolver(BASE_DIR));
        
        System.out.println("========= ASTestApplEvents.xml");
        new XSLDemo().xsl(
                "src/main/resources/xml/ASTestApplEvents.xml",
                "src/main/resources/xml/ASTestApplEvents.xsl",
                "ASTestApplEvents.html", new FileURIResolver("src/main/resources/xml"));
        
        
        
        DOMSource xmlSource = null;
        DOMSource xslSource = null;
        StreamResult  result = null;
        Map<String, String> paramMap = new HashMap<String, String>();
        String contextRoot = "vendor-api";
        String proxyContextPath = "proxy";
        String serviceContextRoot = "/api/v1/";
        String multitenantPrefix = "/tenant/" + "t/" + "tenant-uuid" + "/";
        
        
        paramMap.put("js-csrf-protection", 
                proxyContextPath + multitenantPrefix + "carbon/admin/js/csrfPrevention.js");
        paramMap.put("js-global-params", 
                proxyContextPath + multitenantPrefix + "carbon/global-params.js");
        paramMap.put("proxyAddress",
                proxyContextPath + contextRoot + "carbon/admin/jsp/WSRequestXSSproxy_ajaxprocessor.jsp");
        paramMap.put("js-service-stub", proxyContextPath  + "?stub");
        paramMap.put("services-path", serviceContextRoot);
        
        try {
            XSLDemo.transform(xmlSource, xslSource, result, paramMap);
        } catch (TransformerException ex) {
            Logger.getLogger(XSLDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class ClassPathURIResolver implements URIResolver {

    DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();

    @Override
    public Source resolve(String href, String base) throws TransformerException {
        try {
            DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
            ClassLoader cl = this.getClass().getClassLoader();

            Document xslDoc = dBuilder.parse(cl.getResourceAsStream("xsl/" + href));

            Source xslDomSource = new DOMSource(xslDoc);
            xslDomSource.setSystemId("xsl/" + href);
            return xslDomSource;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ClassPathURIResolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

class FileURIResolver implements URIResolver {

    DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();

    private String baseDir = "";

    public FileURIResolver(String baseDir) {
        this.baseDir = baseDir;
    }

    @Override
    public Source resolve(String href, String base) throws TransformerException {

        Source source = null;
        System.out.println("HREF: " + href + ", BASE: " + base);
        try {

            File[] listDirectories = new File[]{
                new File(baseDir + "/" + href),
                new File(baseDir + "/java/" + href),
                new File(baseDir + "/java/crud/" + href)};

            File xslFile = null;

            for (File file : listDirectories) {

                if (file.exists()) {
                    xslFile = file;
                    break;
                }
            }

            if (xslFile != null) {

                source = new StreamSource(xslFile);
            }
        } catch (Exception ex) {
            Logger.getLogger(FileURIResolver.class.getName()).log(Level.SEVERE, "Exception: ", ex);
        }

        return source;
    }
}
