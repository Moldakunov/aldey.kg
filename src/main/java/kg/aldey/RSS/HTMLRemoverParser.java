package kg.aldey.RSS;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class HTMLRemoverParser {

    HTMLRemoverBean objBean;
    Vector<HTMLRemoverBean> vectParse;

    int mediaThumbnailCount;
    boolean urlflag;
    int count = 0;

    public HTMLRemoverParser() {
    }

    public HTMLRemoverParser(String RssUrl) {
        try {
            vectParse = new Vector<HTMLRemoverBean>();
            URL url = new URL(RssUrl);
            URLConnection con = url.openConnection();

            System.out.println("Connection is : " + con);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            System.out.println("Reader :" + reader);

            String inputLine;
            String fullStr = "";
            while ((inputLine = reader.readLine()) != null)
                fullStr = fullStr.concat(inputLine + "\n");

            InputStream istream = url.openStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = builder.parse(istream);

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("item");

            System.out.println();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    objBean = new HTMLRemoverBean();
                    vectParse.add(objBean);

                    objBean.title = getTagValue("title", eElement);
                    objBean.description = getTagValue("description", eElement);
                    String noHTMLString = objBean.description.replaceAll("\\<.*?\\>", "");
                    objBean.description = noHTMLString;
                    objBean.link = getTagValue("link", eElement);
                    objBean.pubdate = getTagValue("pubDate", eElement);

                }
            }

            List<HTMLRemoverBean> arrayRssNews = new ArrayList<>();
            for (int index1 = 0; index1 < vectParse.size(); index1++) {
                HTMLRemoverBean ObjNB = (HTMLRemoverBean) vectParse
                        .get(index1);
                arrayRssNews.add(objBean);
                System.out.println("Item No : " + index1);
                //System.out.println();
//
                System.out.println("Title is : " + ObjNB.title);
                //System.out.println("Description is : " + ObjNB.description);
                //System.out.println("Link is : " + ObjNB.link);
                //System.out.println("Pubdate is : " + ObjNB.pubdate);
//
                //System.out.println();
                //System.out.println("-------------------------------------------------------------------------------------------------------------");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<HTMLRemoverBean> getRssNews(String rssUrl){
        List<HTMLRemoverBean> arrayRssNews = new ArrayList<>();
        try {

            vectParse = new Vector<HTMLRemoverBean>();
            URL url = new URL(rssUrl);
            URLConnection con = url.openConnection();

            //System.out.println("Connection is : " + con);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            //System.out.println("Reader :" + reader);

            String inputLine;
            String fullStr = "";
            while ((inputLine = reader.readLine()) != null)
                fullStr = fullStr.concat(inputLine + "\n");

            InputStream istream = url.openStream();

            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = builder.parse(istream);

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("item");

            //System.out.println();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    objBean = new HTMLRemoverBean();
                    vectParse.add(objBean);

                    objBean.title = getTagValue("title", eElement);
                    objBean.description = getTagValue("description", eElement);
                    String noHTMLString = objBean.description.replaceAll("\\<.*?\\>", "");
                    objBean.description = noHTMLString;
                    objBean.link = getTagValue("link", eElement);
                    objBean.pubdate = getTagValue("pubDate", eElement);

                }
            }


            for (int index1 = 0; index1 < vectParse.size(); index1++) {
                HTMLRemoverBean ObjNB = (HTMLRemoverBean) vectParse
                        .get(index1);
                arrayRssNews.add(ObjNB);
                //System.out.println("Item No : " + index1);
                //System.out.println();
//
                //System.out.println("Title is : " + ObjNB.title);
                //System.out.println("Description is : " + ObjNB.description);
                //System.out.println("Link is : " + ObjNB.link);
                //System.out.println("Pubdate is : " + ObjNB.pubdate);
//
                //System.out.println();
                //System.out.println("-------------------------------------------------------------------------------------------------------------");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayRssNews;
    }

    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
                .getChildNodes();

        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();

    }
}
