package com.intehel.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * @Author 石文静
 * @Description XML转Map
 * @CreateDate 2019/11/11 16:09
 * @Version V1
*/
public class XmlToMap {
    /**
     * @Author 石文静
     * @Description XML转Map
     * @CreateDate 2019/11/11 16:17
     * @Version V1
    */
    public static Map<String, Object> xmlToMap(String xml) {
        Map<String, Object> map = new HashMap<String, Object>();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            if (doc == null){
                return map;
            }
            Element root = doc.getRootElement();
            for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
                Element e = (Element) iterator.next();
                List list = e.elements();
                if (list.size() > 0) {
                    map.put(e.getName(), dom2Map(e));
                } else{
                    map.put(e.getName(), e.getText());
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map dom2Map(Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map m = dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!"java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if ("java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else{
                        map.put(iter.getName(), m);
                    }
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!"java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if ("java.util.ArrayList".equals(obj.getClass().getName())) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else{
                        map.put(iter.getName(), iter.getText());
                    }
                }
            }
        } else{
            map.put(e.getName(), e.getText());
        }
        return map;
    }

}
