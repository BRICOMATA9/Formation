// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package org.w3c.dom.svg;

import org.w3c.dom.DOMException;

// Referenced classes of package org.w3c.dom.svg:
//            SVGPathSeg

public interface SVGPathSegCurvetoCubicSmoothRel
    extends SVGPathSeg
{

    public abstract float getX();

    public abstract void setX(float f)
        throws DOMException;

    public abstract float getY();

    public abstract void setY(float f)
        throws DOMException;

    public abstract float getX2();

    public abstract void setX2(float f)
        throws DOMException;

    public abstract float getY2();

    public abstract void setY2(float f)
        throws DOMException;
}
