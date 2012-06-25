package de.bht.fb6.cg1.raytracer.scene;

import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.util.Maybe;

/**
 * This interface represents an geometric object. All visible objects implements this interface.
 *
 * @author Stephan Rehfeld
 */
public interface GeometricObject {

    /**
     * An implementation of this method returns the material of the object.
     *
     * @return The material of the object. Never returns null.
     */
    public Material getMaterial();

    /**
     * An implementation of this method checks if the ray hits the object and returns a shade record, if the ray
     * hits the object.
     *
     * @param ray The ray. Must not be null.
     * @return Returns a Just that contains the shade record, if the ray hits the object. If not, it returns Nothing. Never returns null.
     */
    public Maybe< ShadeRecord > hit( final Ray ray );
}
