package com.skyworth.inputtest.xmlparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.skyworth.inputtest.R;
import com.skyworth.inputtest.utils.MyLog;
import com.skyworth.inputtest.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * @author SDT13976 
 *parse xml in xml pull way
 * @category
 */
public class XMLParseActivity extends Activity {

    private ArrayList<ClassBean> parseClassBeans;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mContext = this;
        parseClassBeans = new ArrayList<ClassBean>();
        parseXML("xml_parse.xml");
    }

    private void parseXML(String assetsXMLName) {
        InputStream xmlInputStream = Utils.getAssetsStream(mContext, assetsXMLName);
        parseClassBeans = xmlpullParse(xmlInputStream);
        printOutCome();
    }

    private void printOutCome() {
        MyLog.Out(
                " first class id name  " + parseClassBeans.get(0).getId() + "    " + parseClassBeans.get(0).getName());
        MyLog.Out(" class beans size is   " + parseClassBeans.size());

        // MyLog.Out(" end class id name " + parseClassBeans.get(0).getId()+ " "
        // + parseClassBeans.get(0).getName());

    }

    private ArrayList<ClassBean> xmlpullParse(InputStream inputStream) {
        ArrayList<ClassBean> mClassBeans = null;
        ClassBean mClassBean = null;
        ArrayList<StudentBean> mStudentBeans = null;
        StudentBean mStudentBean = null;
        XmlPullParserFactory xpf = null;
        XmlPullParser parser = null;

        try {
            xpf = XmlPullParserFactory.newInstance();
            parser = xpf.newPullParser();
            parser.setInput(inputStream, "utf-8");
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    MyLog.Out(" start document ");
                    mClassBeans = new ArrayList<ClassBean>();
                    break;
                case XmlPullParser.START_TAG:
                    if ("class".equals(parser.getName())) {
                        MyLog.Out(" start document ====1");
                        mClassBean = new ClassBean();
                        mClassBean.setId(parser.getAttributeValue(0));
                        mClassBean.setName(parser.getAttributeValue(1));
                        mStudentBeans = new ArrayList<StudentBean>();
                    } else if ("student".equals(parser.getName())) {
                        MyLog.Out(" start document ====2");
                        mStudentBean = new StudentBean();
                        mStudentBean.setId(parser.getAttributeValue(0));
                        mStudentBeans.add(mStudentBean);
                    } else if ("name".equals(parser.getName())) {
                        MyLog.Out(" start document ====3");
                        mStudentBean.setName(parser.nextText());
                    } else if ("sex".equals(parser.getName())) {
                        mStudentBean.setSex(parser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("class".equals(parser.getName())) {
                        mClassBean.setmStudentBeans(mStudentBeans);
                        mClassBeans.add(mClassBean);
                        MyLog.Out(" in parse mclass beans size is   " + mClassBeans.size());
                        mClassBean = null;
                    }
                    break;

                default:
                    break;

                }
                event = parser.next();
            }

        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mClassBeans;
    }

    // private List<ClassBean> parseFile3(InputStream is) {
    // LIST<CLASSBEAN> LIST = NULL;
    // CLASSBEAN BEAN = NULL;
    // LIST<STUDENTBEAN> SLIST = NULL;
    // STUDENTBEAN BE = NULL;
    // TRY {
    // XMLPULLPARSER PARSER = XML.NEWPULLPARSER();
    // PARSER.SETINPUT(IS, "UTF-8");
    // INT EVENT = PARSER.GETEVENTTYPE();
    // WHILE (EVENT != XMLPULLPARSER.END_DOCUMENT) {
    // SWITCH (EVENT) {
    // CASE XMLPULLPARSER.START_DOCUMENT:
    // LIST = NEW ARRAYLIST<CLASSBEAN>();
    // BREAK;
    // CASE XMLPULLPARSER.START_TAG:
    // IF ("CLASS".EQUALS(PARSER.GETNAME())) {
    // BEAN = NEW CLASSBEAN();
    // BEAN.SETID(PARSER.GETATTRIBUTEVALUE(0));
    // BEAN.SETNAME(PARSER.GETATTRIBUTEVALUE(1));
    // SLIST = NEW ARRAYLIST<STUDENTBEAN>();
    // } ELSE IF ("STUDENT".EQUALS(PARSER.GETNAME())) {
    // BE = NEW STUDENTBEAN();
    // BE.SETID(PARSER.GETATTRIBUTEVALUE(0));
    // SLIST.ADD(BE);
    // } ELSE IF ("NAME".EQUALS(PARSER.GETNAME())) {
    // BE.SETNAME(PARSER.NEXTTEXT());
    // } ELSE IF ("SEX".EQUALS(PARSER.GETNAME())) {
    // BE.SETSEX(PARSER.NEXTTEXT());
    // }
    // BREAK;
    // CASE XMLPULLPARSER.END_TAG:
    // IF ("CLASS".EQUALS(PARSER.GETNAME())) {
    // BEAN.SETLIST(SLIST);
    // LIST.ADD(BEAN);
    // BEAN = NULL;
    // }
    // BREAK;
    // }
    // EVENT = PARSER.NEXT();
    // }
    // } CATCH (EXCEPTION E) {
    // E.PRINTSTACKTRACE();
    // }
    // RETURN LIST;
    // }
    // }

}
