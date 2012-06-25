package de.bht.fb6.cg1.raytracer.math;
import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * My implementation of a Vector with 3 coordinates.
 * 
 * @author Sascha Feldmann
 * 
 */
public class Vector3DImpl implements Vector3D {
	private final double x;
	private final double y;
	private final double z;

	public Vector3DImpl( final double x, final double y, final double z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public Vector3D add( final Vector3D addVector ) {
		if ( addVector == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value addVector in the method add (Vector3DImpl)." );
		final double newX = this.x + addVector.getX( );
		final double newY = this.y + addVector.getY( );
		final double newZ = this.z + addVector.getZ( );
		return new Vector3DImpl(newX, newY, newZ );
	}

	@Override
	public Vector3D sub( final Vector3D subVector ) {
		if ( subVector == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value subVector in the method sub (Vector3DImpl)." );
		final double newX = this.x - subVector.getX( );
		final double newY = this.y - subVector.getY( );
		final double newZ = this.z - subVector.getZ( );
		return new Vector3DImpl(newX, newY, newZ );
	}

	@Override
	public Vector3D div( final double scalar ) {
		final double newX = this.x / scalar;
		final double newY = this.y / scalar;
		final double newZ = this.z / scalar;

		return new Vector3DImpl(newX, newY, newZ );
	}

	@Override
	public Vector3D mul( final double scalar ) {
		final double newX = this.x * scalar;
		final double newY = this.y * scalar;
		final double newZ = this.z * scalar;

		return new Vector3DImpl(newX, newY, newZ );
	}

	@Override
	public Vector3D add( Normal addNormal ) {
		if ( addNormal == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value addNormal in the method add (Vector3DImpl)." );
		final double newX = this.x + addNormal.getX( );
		final double newY = this.y + addNormal.getY( );
		final double newZ = this.z + addNormal.getZ( );
		return new Vector3DImpl(newX, newY, newZ );
	}

	@Override
	public Normal asNormal() {
		return new NormalImpl(this.x, this.y, this.z );
	}

	@Override
	public Point3D asPoint() {
		return new Point3DImpl(this.x, this.y, this.z );
	}

	@Override
	/**
	 * This method returns the so-called "Kreuzprodukt".
	 */
	public Vector3D cross( Vector3D crossVector ) {
		if ( crossVector == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value crossVector in the method cross (Vector3DImpl)." );

		final double newX = this.y*crossVector.getZ( ) - this.z*crossVector.getY( );
		final double newY = this.z*crossVector.getX( ) - this.x*crossVector.getZ( );
		final double newZ = this.x*crossVector.getY( ) - this.y*crossVector.getX( );
		
		return new Vector3DImpl(newX, newY, newZ );
	}

	@Override
	public double dot( Normal dotNormal ) {
		if ( dotNormal == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value dotNormal in the method dot (Vector3DImpl)." );

		final double dot = this.x * dotNormal.getX( ) + this.y
				* dotNormal.getY( ) + this.z * dotNormal.getZ( );
		return dot;
	}

	@Override
	/**
	 * This method returns the so-called "Skalarprodukt"
	 */
	public double dot( final Vector3D dotVector ) {
		if ( dotVector == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value dotVector in the method dot (Vector3DImpl)." );

		final double dot = this.x * dotVector.getX( ) + this.y
				* dotVector.getY( ) + this.z * dotVector.getZ( );
		return dot;
	}

	@Override
	public double getMagnitude() {
		final double magnitude = Math.sqrt(this.x * this.x + this.y * this.y
				+ this.z * this.z );
		return magnitude;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return this.z;
	}

	@Override
	/**
	 * This method returns the so-called "Einheitsvektor"
	 */
	public Vector3D normalized() {
		return this.div(this.getMagnitude( ) );
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
		temp = Double.doubleToLongBits(z );
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
		Vector3DImpl other = ( Vector3DImpl ) obj;
		if ( Double.doubleToLongBits(x ) != Double.doubleToLongBits(other.x ) )
			return false;
		if ( Double.doubleToLongBits(y ) != Double.doubleToLongBits(other.y ) )
			return false;
		if ( Double.doubleToLongBits(z ) != Double.doubleToLongBits(other.z ) )
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vector3DImpl [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
