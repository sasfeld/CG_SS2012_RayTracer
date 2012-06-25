package de.bht.fb6.cg1.raytracer.math;

public class Point2DImpl implements de.bht.fb6.cg1.raytracer.math.Point2D {

	private final double x;
	private final double y;

	public Point2DImpl(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(y );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass( ) != obj.getClass( ) )
			return false;
		Point2DImpl other = ( Point2DImpl ) obj;
		if ( Double.doubleToLongBits(x ) != Double.doubleToLongBits(other.x ) )
			return false;
		if ( Double.doubleToLongBits(y ) != Double.doubleToLongBits(other.y ) )
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point2DImpl [x=" + x + ", y=" + y + "]";
	}

}
