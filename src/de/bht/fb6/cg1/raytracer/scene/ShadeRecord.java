package de.bht.fb6.cg1.raytracer.scene;

import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;

/**
 * A shade records contains information about the intersection between a ray and
 * an object. This information is necessary to determine the color at the hit
 * point.
 * 
 * Shade records are comparable and compares the t attribute.
 * 
 * @author Stephan Rehfeld
 */
public class ShadeRecord implements Comparable<ShadeRecord> {

  /**
   * The t of the ray, where the ray intersects the object.
   */
  public final double t;

  /**
   * The point where the ray and the object intersects.
   */
  public final Point3D localHitPoint;

  /**
   * The normal at the intersection point.
   */
  public final Normal normal;

  /**
   * The geometric object of the intersection.
   */
  public final GeometricObject geometricObject;

  /**
   * The ray of the intersection.
   */
  public final Ray ray;

  /**
   * This constructor creates a new shade record.
   * 
   * @param t
   *          The t of the ray, where the ray intersects the object.
   * @param localHitPoint
   *          The point where the ray and the object intersects. Must not be
   *          null.
   * @param normal
   *          The normal at the intersection point. Must not be null.
   * @param geometricObject
   *          The geometric object of the intersection. Must not be null.
   * @param ray
   *          The ray of the intersection. Must not be null.
   */
  public ShadeRecord(final double t, final Point3D localHitPoint, final Normal normal,
      final GeometricObject geometricObject, final Ray ray) {
    if (localHitPoint == null)
      throw new IllegalArgumentException("The parameter 'localHitPoint' must not be null!");
    if (normal == null)
      throw new IllegalArgumentException("The parameter 'normal' must not be null!");
    if (geometricObject == null)
      throw new IllegalArgumentException("The parameter 'geometricObject' must not be null!");
    if (ray == null)
      throw new IllegalArgumentException("The parameter 'ray' must not be null!");

    this.t = t;
    this.localHitPoint = localHitPoint;
    this.normal = normal;
    this.geometricObject = geometricObject;
    this.ray = ray;
  }

  @Override
  public int compareTo(final ShadeRecord compareObject) {
    if (this.t < compareObject.t)
      return -1;
    else if (this.t == compareObject.t)
      return 0;
    else
      return 1;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    ShadeRecord that = (ShadeRecord) o;

    if (Double.compare(that.t, t) != 0)
      return false;
    if (geometricObject != null ? !geometricObject.equals(that.geometricObject) : that.geometricObject != null)
      return false;
    if (localHitPoint != null ? !localHitPoint.equals(that.localHitPoint) : that.localHitPoint != null)
      return false;
    if (normal != null ? !normal.equals(that.normal) : that.normal != null)
      return false;
    if (ray != null ? !ray.equals(that.ray) : that.ray != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = t != +0.0d ? Double.doubleToLongBits(t) : 0L;
    result = (int) (temp ^ (temp >>> 32));
    result = 31 * result + (localHitPoint != null ? localHitPoint.hashCode() : 0);
    result = 31 * result + (normal != null ? normal.hashCode() : 0);
    result = 31 * result + (geometricObject != null ? geometricObject.hashCode() : 0);
    result = 31 * result + (ray != null ? ray.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ShadeRecord{" + "t=" + t + ", localHitPoint=" + localHitPoint + ", normal=" + normal + ", geometricObject="
        + geometricObject + ", ray=" + ray + '}';
  }
}
