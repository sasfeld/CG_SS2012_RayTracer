package de.bht.fb6.cg1.raytracer.scene;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;

/**
 * An implementation of this interface represents a surface material of an object.
 *
 * @author Stephan Rehfeld
 */
public interface Material {

    /**
     * An implementation of this method should return the color for a given shade record.
     *
     * The tracer can be used if further rays are necessary to determine the color at this position.
     *
     * @param shadeRecord The shade record. Must not be null.
     * @param tracer The tracer that should be used for further rays. Must not be null.
     * @return The color at the position. Never returns null.
     */
    public RGBColor getColorAt( final ShadeRecord shadeRecord, final Tracer tracer );
    
}
