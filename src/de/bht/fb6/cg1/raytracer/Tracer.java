package de.bht.fb6.cg1.raytracer;

import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.scene.World;


/**
 * A tracer implements the fundamental ray tracing algorithm. It's tracing
 * a ray within a given world.
 *
 * @author Stephan Rehfeld
 */
public interface Tracer {

    /**
     * An implementation of this method traces the ray trough a given world and returns the color for the ray.
     *
     * @param ray The ray. Must not be null.
     * @return The color for the given ray. Must not return null.
     */
    public RGBColor traceRay( final Ray ray );

    /**
     * An implementation of this method determines if the ray hits anything.
     *
     * @param ray The ray. Must not be null.
     * @return Returns 'true' if the ray hits anything.
     */
    public boolean hitsAnything( final Ray ray );


    /**
     *  An implementation of this method should determine if a ray hits a given point.
     *
     * @param ray The ray to test. Must not be null.
     * @param point  The point to test. Must not be null.
     * @return Returns 'true' if the point is visible and 'false' if it's covered by an object.
     */
    public boolean isPointVisible( final Ray ray, final Point3D point );

    /**
     * This method returns the world.
     *
     * @return  The world. Never returns 'null'.
     */
    public World getWorld();
}
