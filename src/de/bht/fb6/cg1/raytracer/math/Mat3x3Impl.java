package de.bht.fb6.cg1.raytracer.math;
import de.bht.fb6.cg1.raytracer.math.Mat3x3;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Vector3D;

/**
 * My personal implementation of the 3x3 - Matrix Class.
 * 
 * @author Sascha Feldmann
 * 
 */
public class Mat3x3Impl implements Mat3x3 {

	private final double a11;
	private final double a12;
	private final double a13;
	private final double a21;
	private final double a23;
	private final double a22;
	private final double a31;
	private final double a32;
	private final double a33;

	public Mat3x3Impl( final double a11, final double a12, final double a13,
			final double a21, final double a22, final double a23,
			final double a31, final double a32, final double a33 ) {
		this.a11 = a11;
		this.a12 = a12;
		this.a13 = a13;
		this.a21 = a21;
		this.a22 = a22;
		this.a23 = a23;
		this.a31 = a31;
		this.a32 = a32;
		this.a33 = a33;
	}

	@Override
	public double getDeterminant() {
		// First row
		final double det = this.a11
				* ( this.a22 * this.a33 - this.a23 * this.a32 ) - this.a12
				* ( this.a21 * this.a33 - this.a23 * this.a31 ) + this.a13
				* ( this.a21 * this.a32 - this.a22 * this.a31 );
		return det;
	}

	@Override
	public Mat3x3 mul( final Mat3x3 lMat ) {
		// Build vectors for each row
		final Vector3DImpl j1 = new Vector3DImpl(this.a11, this.a21, this.a31 );
		final Vector3DImpl j2 = new Vector3DImpl(this.a12, this.a22, this.a32 );
		final Vector3DImpl j3 = new Vector3DImpl(this.a13, this.a23, this.a33 );
		
		// Calculate new vectors
		final Vector3D newJ1 = lMat.mul(j1 );
		final Vector3D newJ2 = lMat.mul(j2 );
		final Vector3D newJ3 = lMat.mul(j3 );
		
		// Build new matrix
		Mat3x3 newMat = new Mat3x3Impl(0, 0, 0, 0, 0, 0, 0, 0, 0 );
		newMat = newMat.replaceColumn0(newJ1 );
		newMat = newMat.replaceColumn1(newJ2 );
		newMat = newMat.replaceColumn2(newJ3 );
		
		return newMat;
	}

	@Override
	public Point3D mul( final Point3D point ) {
		if(point == null)
			throw new IllegalArgumentException(
					"Illegal Argument for value point in the method mul (Mat3x3Impl)." );
		
		final double newX = this.a11*point.getX( ) + this.a12*point.getY( ) + this.a13*point.getZ( );
		final double newY = this.a21*point.getX( ) + this.a22*point.getY( ) + this.a23*point.getZ( );
		final double newZ = this.a31*point.getX( ) + this.a32*point.getY( ) + this.a33*point.getZ( );
		
		return new Point3DImpl(newX, newY, newZ );		
	}

	@Override
	public Vector3D mul( final Vector3D vector ) {
		if(vector == null)
			throw new IllegalArgumentException(
					"Illegal Argument for value vector in the method mul (Mat3x3Impl)." );
		
		final double newX = this.a11*vector.getX( ) + this.a12*vector.getY( ) + this.a13*vector.getZ( );
		final double newY = this.a21*vector.getX( ) + this.a22*vector.getY( ) + this.a23*vector.getZ( );
		final double newZ = this.a31*vector.getX( ) + this.a32*vector.getY( ) + this.a33*vector.getZ( );
		
		return new Vector3DImpl(newX, newY, newZ );		
	}

	@Override
	public Mat3x3 replaceColumn0( final Vector3D vector ) {
		if ( vector == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value vector in the method replaceColujmn0 (Mat3x3Impl)." );

		final double newA11 = vector.getX( );
		final double newA21 = vector.getY( );
		final double newA31 = vector.getZ( );
		return new Mat3x3Impl(newA11, this.a12, this.a13, newA21, this.a22,
				this.a23, newA31, this.a32, this.a33 );
	}

	@Override
	public Mat3x3 replaceColumn1( final Vector3D vector ) {
		if ( vector == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value vector in the method replaceColujmn1 (Mat3x3Impl)." );

		final double newA12 = vector.getX( );
		final double newA22 = vector.getY( );
		final double newA32 = vector.getZ( );
		return new Mat3x3Impl(this.a11, newA12, this.a13, this.a21, newA22,
				this.a23, this.a31, newA32, this.a33 );
	}

	@Override
	public Mat3x3 replaceColumn2( final Vector3D vector ) {
		if ( vector == null )
			throw new IllegalArgumentException(
					"Illegal Argument for value vector in the method replaceColujmn0 (Mat3x3Impl)." );

		final double newA13 = vector.getX( );
		final double newA23 = vector.getY( );
		final double newA33 = vector.getZ( );
		return new Mat3x3Impl(this.a11, this.a12, newA13, this.a21, this.a22,
				newA23, this.a31, this.a32, newA33 );
	}
	
	public Vector3D getColumn0() {
		return new Vector3DImpl(this.a11, this.a21, this.a31 );
	}
	
	public Vector3D getColumn1() {
		return new Vector3DImpl(this.a12, this.a22, this.a32 );
	}
	
	public Vector3D getColumn2() {
		return new Vector3DImpl(this.a13, this.a23, this.a33 );
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(a11 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a12 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a13 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a21 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a22 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a23 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a31 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a32 );
		result = prime * result + ( int ) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits(a33 );
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
		Mat3x3Impl other = ( Mat3x3Impl ) obj;
		if ( Double.doubleToLongBits(a11 ) != Double
				.doubleToLongBits(other.a11 ) )
			return false;
		if ( Double.doubleToLongBits(a12 ) != Double
				.doubleToLongBits(other.a12 ) )
			return false;
		if ( Double.doubleToLongBits(a13 ) != Double
				.doubleToLongBits(other.a13 ) )
			return false;
		if ( Double.doubleToLongBits(a21 ) != Double
				.doubleToLongBits(other.a21 ) )
			return false;
		if ( Double.doubleToLongBits(a22 ) != Double
				.doubleToLongBits(other.a22 ) )
			return false;
		if ( Double.doubleToLongBits(a23 ) != Double
				.doubleToLongBits(other.a23 ) )
			return false;
		if ( Double.doubleToLongBits(a31 ) != Double
				.doubleToLongBits(other.a31 ) )
			return false;
		if ( Double.doubleToLongBits(a32 ) != Double
				.doubleToLongBits(other.a32 ) )
			return false;
		if ( Double.doubleToLongBits(a33 ) != Double
				.doubleToLongBits(other.a33 ) )
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mat3x3Impl [a11=" + a11 + ", a12=" + a12 + ", a13=" + a13
				+ ", a21=" + a21 + ", a23=" + a23 + ", a22=" + a22 + ", a31="
				+ a31 + ", a32=" + a32 + ", a33=" + a33 + "]";
	}

}
