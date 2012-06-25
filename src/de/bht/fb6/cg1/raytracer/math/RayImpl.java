package de.bht.fb6.cg1.raytracer.math;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * My personal implementation of the Ray-interface.
 * 
 * @author Sascha Feldmann
 * 
 */
public class RayImpl implements Ray {

	private final Point3D origin;
	private final Vector3D direction;

	public RayImpl(Point3D origin, Vector3D direction) {
		this.origin = origin;
		this.direction = direction;
	}
	@Override
	public Point3D at( double t ) {
		Point3D newPoint = origin.add(direction.mul(t ) );
		return newPoint;
	}

	@Override
	public Vector3D getDirection() {
		return this.direction;
	}

	@Override
	public Point3D getOrigin() {
		return this.origin;
	}

	@Override
	public double tOf( Point3D point ) {
		if(point == null)
			throw new IllegalArgumentException(
					"Illegal Argument for value point in the method tOf (RayImpl)." );
		
		// Calculate ts for each coordinate
		final double xT = (point.getX( ) - this.origin.getX( )) / this.direction.getX( ); 
		final double yT = (point.getY( ) - this.origin.getY( )) / this.direction.getY( ); 
		final double zT = (point.getZ( ) - this.origin.getZ( )) / this.direction.getZ( );
		
		// Check if point lays on the ray
		if(xT != yT && yT != zT) {
			throw new IllegalArgumentException(
					"The point must lay on the ray in the method tOf (RayImpl)." );
		}
		return xT;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ( ( direction == null ) ? 0 : direction.hashCode( ) );
		result = prime * result
				+ ( ( origin == null ) ? 0 : origin.hashCode( ) );
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
		RayImpl other = ( RayImpl ) obj;
		if ( direction == null ) {
			if ( other.direction != null )
				return false;
		} else if ( !direction.equals(other.direction ) )
			return false;
		if ( origin == null ) {
			if ( other.origin != null )
				return false;
		} else if ( !origin.equals(other.origin ) )
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RayImpl [origin=" + origin + ", direction=" + direction + "]";
	}

}
