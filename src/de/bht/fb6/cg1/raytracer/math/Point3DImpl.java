package de.bht.fb6.cg1.raytracer.math;

public class Point3DImpl implements Point3D {

  private final double x;
  private final double y;
  private final double z;

  public Point3DImpl(final double newX, final double newY, final double newZ) {
    this.x = newX;
    this.y = newY;
    this.z = newZ;
  }

  @Override
  public Point3D add(Vector3D addVector) {
    if (addVector == null)
      throw new IllegalArgumentException("Illegal Argument for value addVector in the method add (Point3DImpl).");
    final double newX = this.x + addVector.getX();
    final double newY = this.y + addVector.getY();
    final double newZ = this.z + addVector.getZ();
    return new Point3DImpl(newX, newY, newZ);
  }

  @Override
  public Normal asNormal() {
    return new NormalImpl(this.x, this.y, this.z);
  }

  @Override
  public Vector3D asVector() {
    return new Vector3DImpl(this.x, this.y, this.z);
  }

  @Override
  public double dot(Normal arg0) {
    // TODO Auto-generated method stub
    return 0;
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
  public Vector3D sub(Point3D subPoint) {
    if (subPoint == null)
      throw new IllegalArgumentException("Illegal Argument for value subPoint in the method sub (Point3DImpl).");
    final double newX = this.x - subPoint.getX();
    final double newY = this.y - subPoint.getY();
    final double newZ = this.z - subPoint.getZ();
    return new Vector3DImpl(newX, newY, newZ);
  }

  @Override
  public Point3D sub(Vector3D subVector) {
    if (subVector == null)
      throw new IllegalArgumentException("Illegal Argument for value subVector in the method sub (Point3DImpl).");
    final double newX = this.x - subVector.getX();
    final double newY = this.y - subVector.getY();
    final double newZ = this.z - subVector.getZ();
    return new Point3DImpl(newX, newY, newZ);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(z);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Point3DImpl other = (Point3DImpl) obj;
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
      return false;
    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
      return false;
    if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
      return false;
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Point3DImpl [x=" + x + ", y=" + y + ", z=" + z + "]";
  }

}
