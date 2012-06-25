package de.bht.fb6.cg1.s778455.raytracer.raytracerimpl;

import java.util.HashMap;
import java.util.Map;

import de.bht.fb6.cg1.raytracer.math.Point2D;
import de.bht.fb6.cg1.raytracer.math.Point2DImpl;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.math.RayImpl;
import de.bht.fb6.cg1.raytracer.math.Vector3D;
import de.bht.fb6.cg1.raytracer.scene.Camera;

public class CameraImpl implements Camera {

	/**
	 * The position (a point) of the camera. It realizes movement.
	 */
	private final Point3D eye;
	/**
	 * The direction (a vector) of the camera. It realizes the eye direction. 
	 */
	private final Point3D lookAt;
	/**
	 * The up-vector. It realizes the rotation.
	 */
	private final Vector3D up;

	/**
	 * Create a new immutible Camera (within a world).
	 * @param eye - The position (a point) of the camera.
	 * @param lookAt - The direction (a vector) of the camera.
	 * @param up - The up-vector
	 */
	public CameraImpl(final Point3D eye, final Point3D lookAt, final Vector3D up) {
		this.eye = eye;
		this.lookAt = lookAt;
		this.up = up;
	}
	@Override
	public Map<Point2D, Ray> generateRaysFor(final int width, final int height ) {
		final Vector3D d = this.getW( ).mul(-1 );
		final double a = width / height;
		final double s = 1;
		final Map<Point2D, Ray> map = new HashMap<Point2D,Ray>();
		for ( int pX = 0; pX < width; pX++ ) {
			for ( int pY = 0; pY < height; pY++) {
				final Point3D firstTerm = this.getEye( );
				final Vector3D secondTerm = this.getU( ).mul(a * s * (pX - (width-1) / 2) / width -1);
				final Vector3D thirdTerm = this.getV( ).mul(s * (pY - ((height-1) / 2) ));
				Point3D o = firstTerm.add(secondTerm ).add(thirdTerm );
				map.put(new Point2DImpl(pX, pY), new RayImpl(o , d ) ); 			}
		}
		return map;
	}

	@Override
	public Point3D getEye() {
		return this.eye;
	}

	@Override
	public Vector3D getUp() {
		return this.up;
	}

	@Override
	public Point3D getLookAt() {
		return this.lookAt;
	}

	@Override
	public Vector3D getU() {
		final Vector3D crossTW = this.getUp( ).cross(this.getW( ) );
		final Vector3D u = crossTW.normalized( ); // Normalize the axis
		return u;
	}

	@Override
	public Vector3D getV() {
		// U and W are already normalized
		final Vector3D v = this.getW( ).cross(this.getU( ) );
		return v;
	}

	@Override
	public Vector3D getW() {
		final Vector3D g = this.getLookAt( ).asVector( );
		final Vector3D w = g.normalized( ).mul(-1 ); // Normalize the axis
		
		return w;
	}

}
