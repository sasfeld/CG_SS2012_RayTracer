package de.bht.fb6.cg1.raytracer.math;
import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * My personal implementation of the Normal-interface.
 * 
 * @author Sascha Feldmann
 * 
 */
public class NormalImpl implements Normal {

	private double x;
	private double z;
	private double y;

	public NormalImpl( final double x, final double y, final double z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public Normal add( final Normal addNormal ) {
		if ( addNormal == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value addNormal in the method add (NormalImpl)." );
		final double newX = this.x + addNormal.getX( );
		final double newY = this.y + addNormal.getY( );
		final double newZ = this.z + addNormal.getZ( );
		return new NormalImpl(newX, newY, newZ );
	}

	@Override
	public Point3D asPoint() {
		return new Point3DImpl(this.x, this.y, this.z );
	}

	@Override
	public Vector3D asVector() {
		return new Vector3DImpl(this.x, this.y, this.z );
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
	public Normal mul( double scalar ) {
		final double newX = this.x * scalar;
		final double newY = this.y * scalar;
		final double newZ = this.z * scalar;

		return new NormalImpl(newX, newY, newZ );
	}

	@Override
	public Normal normalized() {
		Vector3D asVector = this.asVector( );
		Vector3D asNormalizedVector = asVector.div(asVector.getMagnitude( ) );
		return asNormalizedVector.asNormal( );
	}

	@Override
	public Vector3D reflect( Vector3D vector ) {
		final double d = vector.dot( this.normalized() );
		return new Vector3DImpl( vector.getX() - 2 * d, vector.getY() - 2 * d, vector.getZ() - 2 * d );
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
		NormalImpl other = ( NormalImpl ) obj;
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
		return "NormalImpl [x=" + x + ", z=" + z + ", y=" + y + "]";
	}

}
