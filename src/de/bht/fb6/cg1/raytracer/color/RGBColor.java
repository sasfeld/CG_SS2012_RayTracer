package de.bht.fb6.cg1.raytracer.color;

import java.awt.Color;


/**
 * This class represents a color in RGB color space.
 *
 * The components are saved as float values. Normally they have values between 0.0 and 1.0.
 * This class also supports component values larger than 1.0. That's necessary to calculate
 * the color of the surface during illumination.
 *
 * An object of this class is immutable.
 *
 * @author Stephan Rehfeld
 */
public class RGBColor {

    /**
     * This attribute contains the red component of the color.
     */
    private final float r;

    /**
     * This attribute contains the green component of the color.
     */
    private final float g;

    /**
     * This attribute contains the blue component of the color.
     */
    private final float b;

    /**
     * This constructor creates a new rgb color with the given values for each component.
     *
     * @param r The red component of the color. Must be at least 0.0.
     * @param g The green component of the color. Must be at least 0.0.
     * @param b The blue component of the color. Must be at least 0.0.
     */
    public RGBColor( final float r, final float g, final float b ) {
        if( r < 0.0 ) throw new IllegalArgumentException( "The parameter 'r' must be at least 0.0!" );
        if( g < 0.0 ) throw new IllegalArgumentException( "The parameter 'g' must be at least 0.0!" );
        if( b < 0.0 ) throw new IllegalArgumentException( "The parameter 'b' must be at least 0.0!" );
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * This constructor creates a new gray rgb color using the given value as brightness.
     *
     * @param gray The brightness of the color. Must be at least 0.0.
     */
    public RGBColor( final float gray ) {
        if( gray < 0.0 ) throw new IllegalArgumentException( "The parameter 'gray' must be at least 0.0!" );
        this.r = gray;
        this.g = gray;
        this.b = gray;
    }

    /**
     * This method converts this color to an AWT color object.
     *
     * If components are larger than 1.0 they are normalized to 1.0.
     *
     * @return This color as AWT color. Never returns null.
     */
    public Color asAwtColor() {
        return new Color( Math.max( 0.0f, Math.min( 1.0f, this.r ) ), Math.max( 0.0f, Math.min( 1.0f, this.g ) ), Math.max( 0.0f, Math.min( 1.0f, this.b ) ) );
    }


    /**
     * This method adds a color to the current color and returns the result.
     *
     * @param rhs The color that should be added to this color. Must not be null!
     * @return The sum of both color. Never returns null.
     */
    public RGBColor add( final RGBColor rhs ) {
        if( rhs == null ) throw new IllegalArgumentException( "The parameter 'rhs' must not be null." );
        return new RGBColor( this.r + rhs.r, this.g + rhs.g, this.b + rhs.b );
    }

    /**
     * This method multiplies this color with a value and returns the result.
     *
     * @param rhs The factor for the components. Must be at least 0.0.
     * @return The resulting color. Never returns null.
     */
    public RGBColor mul( final float rhs ) {
        if( rhs < 0.0 ) throw new IllegalArgumentException( "The parameter 'rhs' must be at least 0.0." );
        return new RGBColor( this.r * rhs, this.g * rhs, this.b * rhs );
    }

    /**
     * This method multiplies this color with another color and returns the result.
     *
     * @param rhs The color that should be multiplied with this color.
     * @return The resulting color. Never return null.
     */
    public RGBColor mul( final RGBColor rhs ) {
        if( rhs == null ) throw new IllegalArgumentException( "The parameter 'rhs' must not be null." );
        return new RGBColor( this.r * rhs.r, this.g * rhs.g, this.b * rhs.b );
    }


    /**
     * This method divides this color by a value and returns the result.
     *
     * @param rhs The divisor for the components. Must be larger than 0.0.
     * @return The resulting color. Never returns null.
     */
    public RGBColor div( final float rhs ) {
        if( rhs <= 0.0 ) throw new IllegalArgumentException( "The parameter 'rhs' must be larger than 0.0." );
        return this.mul( 1.0f/rhs );
    }

    /**
     * This method returns the red component of the color.
     *
     * @return The red component of the color. Return at least 0.0.
     */
    public double getR() {
        return this.r;
    }

    /**
     * This method returns the green component of the color.
     *
     * @return The green component of the color. Return at least 0.0.
     */
    public double getG() {
        return this.g;
    }

    /**
     * This method returns the blue component of the color.
     *
     * @return The blue component of the color. Return at least 0.0.
     */
    public double getB() {
        return this.b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RGBColor rgbColor = (RGBColor) o;

        if (Float.compare(rgbColor.b, b) != 0) return false;
        if (Float.compare(rgbColor.g, g) != 0) return false;
        if (Float.compare(rgbColor.r, r) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (r != +0.0f ? Float.floatToIntBits(r) : 0);
        result = 31 * result + (g != +0.0f ? Float.floatToIntBits(g) : 0);
        result = 31 * result + (b != +0.0f ? Float.floatToIntBits(b) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RGBColor{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }
}
