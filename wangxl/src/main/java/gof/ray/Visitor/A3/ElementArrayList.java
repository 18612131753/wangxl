package gof.ray.Visitor.A3;

import java.util.ArrayList;
import java.util.Iterator;

import gof.ray.Visitor.A2.Entry;

@SuppressWarnings("rawtypes")
public class ElementArrayList extends ArrayList implements Element {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ElementArrayList() {
    }

    @SuppressWarnings("unchecked")
    public void accept(Visitor v) {
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            e.accept(v);
        }
    }
}
