// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package B.A.C;

import B.A.A.A;
import C.H.L;
import C.J.K;
import C.J.b;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

// Referenced classes of package B.A.C:
//            E, B

public class C extends L
{
    private static class _A extends SVGGraphics2D
    {

        public Graphics create()
        {
            return new _A(this);
        }

        public void setRenderingHint(java.awt.RenderingHints.Key key, Object obj)
        {
            if(!RenderingHints.KEY_ANTIALIASING.equals(key) && !RenderingHints.KEY_TEXT_ANTIALIASING.equals(key))
                super.setRenderingHint(key, obj);
        }

        _A(Document document)
        {
            super(document);
        }

        _A(_A _pa)
        {
            super(_pa);
        }
    }


    public C()
    {
        C = true;
        G = false;
        H = A.B;
    }

    public String B()
    {
        return "SVG Format";
    }

    public String A()
    {
        return "svg";
    }

    public void A(E e)
    {
        A = e;
    }

    public E F()
    {
        if(A != null)
            A.A(this);
        return A;
    }

    SVGGraphics2D D()
    {
        return E;
    }

    public void A(b b1, InputStream inputstream)
        throws IOException
    {
        throw new UnsupportedOperationException("Can't read files in " + B());
    }

    public void A(b b1, OutputStream outputstream)
        throws IOException
    {
        E = new _A(C());
        E.getGeneratorContext().setComment("Generated by ySVG");
        E.setRenderingHint(A.K, H);
        E.setRenderingHint(B.A.B.A.C, new B.A.B.A());
        K k = (K)b1.i();
        D = k;
        if(F != null)
        {
            D = F;
            D.A(b1);
        }
        if(D == null)
            D = A(b1);
        Dimension dimension = D.j();
        if(G())
            E.setSVGCanvasSize(dimension);
        C.J.AA aa = D.o();
        if(F() != null)
            D.B(F());
        D.F(E);
        if(F() != null)
            D.B(aa);
        if(k == null || D == F)
        {
            if(k != D)
                b1.B(D);
            if(k != null)
                b1.C(k);
        }
        D = null;
        A(E.getRoot(), ((Writer) (new OutputStreamWriter(outputstream, "UTF-8"))), G);
    }

    void A(Element element, Writer writer, boolean flag)
        throws IOException
    {
        Node node = element.getParentNode();
        Node node1 = element.getNextSibling();
        try
        {
            element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "http://www.w3.org/2000/svg");
            element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink");
            DocumentFragment documentfragment = element.getOwnerDocument().createDocumentFragment();
            documentfragment.appendChild(element);
            if(flag)
                B.A.C.B.A(documentfragment, true);
            if(F() != null)
                F().A(documentfragment, writer);
            else
                A(((Node) (documentfragment)), writer);
            writer.flush();
        }
        finally
        {
            if(node != null)
                if(node1 == null)
                    node.appendChild(element);
                else
                    node.insertBefore(element, node1);
        }
    }

    void A(Node node, Writer writer)
        throws IOException
    {
        try
        {
            DOMSource domsource = new DOMSource(node);
            StreamResult streamresult = new StreamResult(writer);
            TransformerFactory transformerfactory = TransformerFactory.newInstance();
            try
            {
                transformerfactory.setAttribute("indent-number", new Integer(2));
            }
            catch(IllegalArgumentException illegalargumentexception) { }
            Transformer transformer = transformerfactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(domsource, streamresult);
        }
        catch(TransformerConfigurationException transformerconfigurationexception)
        {
            throw new IOException(transformerconfigurationexception.getMessage());
        }
        catch(TransformerException transformerexception)
        {
            throw new IOException(transformerexception.getMessage());
        }
    }

    protected Document E()
    {
        DOMImplementation domimplementation = GenericDOMImplementation.getDOMImplementation();
        return domimplementation.createDocument(null, "svg", null);
    }

    public Document C()
    {
        if(B == null)
            B = E();
        return B;
    }

    public K A(b b1)
    {
        K k = new K(b1);
        Rectangle rectangle = b1.X();
        Dimension dimension = new Dimension(rectangle.width, rectangle.height);
        k.setSize(dimension);
        k.setPreferredSize(dimension);
        k.B(rectangle.x - 10, rectangle.y - 10, rectangle.width + 20, rectangle.height + 20);
        k.C(0.0D);
        return k;
    }

    public boolean G()
    {
        return C;
    }

    private Document B;
    private SVGGraphics2D E;
    private E A;
    private K F;
    private K D;
    private boolean C;
    private boolean G;
    private Object H;
}
