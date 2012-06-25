package de.bht.fb6.cg1.raytracer.ui;

import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point2D;

/**
 * Non-public class für multi threading.
 */
class PointToColor {

    private final Point2D point;
    private final RGBColor color;


    public PointToColor( final Point2D point, final RGBColor color ) {
        if( point == null ) throw new IllegalArgumentException( "The parameter 'point' must not be 'null'!" );
        if( color == null ) throw new IllegalArgumentException( "The parameter 'color' must not be 'null'!" );

        this.point = point;
        this.color = color;
    }

    public Point2D getPoint() {
        return this.point;
    }

    public RGBColor getColor() {
        return this.color;
    }

}
