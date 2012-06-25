package de.bht.fb6.cg1.raytracer.scene;

import de.bht.fb6.cg1.raytracer.math.Point2D;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

import java.util.Map;

/**
 * This interface represents a camera.
 *
 * @author Stephan Rehfeld
 *
 */
public interface Camera {

    /**
     * An implementation of this methods generates the rays for a screen with the given amount of pixels.
     * It begins at point (0/0) and ends at point (width-1/height-1). The origin and direction of the rays
     * depends on the kind of camera.
     *
     * @param width The horizontal amount pixels.
     * @param height The vertical amount of pixels.
     * @return A map that maps from the point on the screen to the ray. Never returns null.
     */
    public Map<Point2D, Ray > generateRaysFor( final int width, final int height );

    /**
     * An implementation of this method returns the position of the eye.
     *
     * @return The position of the eye. Never returns null.
     */
    public Point3D getEye();

    /**
     * An implementation of this method returns the up vector.
     *
     * @return The up vector. Never returns null.
     */
    public Vector3D getUp();

    /**
     * An implementation of this method returns the point this camera looks at.
     *
     * @return The point this camera looks at. Never returns null.
     */
    public Point3D getLookAt();

    /**
     * An implementation of this method returns the U vector of the camera coordinate system.
     *
     * @return The U vector of the camera coordinate system. Never returns null.
     */
    public Vector3D getU();

    /**
     * An implementation of this method returns the V vector of the camera coordinate system.
     *
     * @return The V vector of the camera coordinate system. Never returns null.
     */
    public Vector3D getV();

    /**
     * An implementation of this method returns the W vector of the camera coordinate system.
     *
     * @return The W vector of the camera coordinate system. Never returns null.
     */
    public Vector3D getW();
}
