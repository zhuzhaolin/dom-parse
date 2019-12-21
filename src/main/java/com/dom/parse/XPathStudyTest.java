package com.dom.parse;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XPathStudyTest {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        //开启验证
        builderFactory.setValidating(true);
        builderFactory.setNamespaceAware(false);
        builderFactory.setIgnoringComments(true);
        builderFactory.setIgnoringElementContentWhitespace(false);
        builderFactory.setCoalescing(false);
        builderFactory.setExpandEntityReferences(true);

        //创建 DocumentBuilder
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        //设置异常处理对象
        builder.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException exception) throws SAXException {

            }

            public void error(SAXParseException exception) throws SAXException {

            }

            public void fatalError(SAXParseException exception) throws SAXException {

            }
        });

        //将文档加载到一个Document对象中
        Document document = builder.parse("F:\\framework\\domparse\\src\\main\\resources\\school.xml");

        //创建XPathFactory
        XPathFactory factory = XPathFactory.newInstance();
        //创建XPath对象
        XPath xpath = factory.newXPath();

        //编译XPath表达式
        XPathExpression expression = xpath.compile("//student[name='Andy']/height/text()");

        //通过XPath表达式得到结果，第一个参数指定了XPath表达式进行查询的上下文节点，也就是在指定
        //节点下查找符合XPath的节点。本例中上下文节点是整个文档；第二个参数指定了XPath表达式的返回类型。

        NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
        System.out.println("查询名字为Adny学生的身高: ");

        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("查询年龄大于20的学生身高");
        nodes = (NodeList) xpath.evaluate("//student[@age>20]/height/text()", document, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }

        System.out.println("查询年龄大于20的学生的年龄和身高");
        nodes = (NodeList) xpath.evaluate("//student[@age>20]/@*|//student[@age>20]/height/text()", document, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }


    }
}
