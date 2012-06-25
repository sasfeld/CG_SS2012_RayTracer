package de.bht.fb6.cg1.raytracer.scene.light;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;


/**
 * This interface represents a the common base interface for all kind of lights.
 *
 * @author Stephan Rehfeld
 */
public interface Light {

    /**
     * An implementation of this method returns the color of the light.
     *
     * @return The color of the light. Never returns null.
     */
    public RGBColor getColor();

    /**
     * An implementation of this method returns the direction to the light source
     * from the given point. The direction is pointing from the given point to the
     * position of the light source and is normalized.
     *
     * @param point The point from where the direction should be calculated. Must not be null.
     *
     * @return The direction to the light source. Never returns null.
     */
    public abstract Vector3D getDirectionFrom( final Point3D point );

    /**
     * An implementation of this method checks if this light source is visible from the given point.
     * If you ray tracer doesn't support shadow, it just checks if the constraints related to the angle
     * between the point and the main direction of the light source.
     *
     * If you ray tracer supports shadows it should the implementation of this method should use the tracer
     * to check if an object is between the position of the light source and the given point.
     *
     * @param point The point on the object that should bet checked. Must not be null.
     * @param tracer The tracer to determine if an object is between the light source and the point on the surface. Must not be null.
     * @return Returns 'true' if the point is visible from the light source and 'false' if not.
     */
    public abstract boolean visibleAt( final Point3D point, final Tracer tracer );
}
