package de.bht.fb6.cg1.s778455.raytracer.raytracerimpl;

import java.util.ArrayList;

import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Normal;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.scene.GeometricObject;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;
import de.bht.fb6.cg1.raytracer.util.Maybe;

/**
 * This class modelles a plain.
 * 
 * @author Sascha Feldmann
 * 
 */
public class PlainImpl implements GeometricObject {

  private final Point3D a;
  private final Normal n;
  private final Material mat;

  /**
   * Create a new immutable plain instance.
   * 
   * @param a
   *          - a known Point3D.
   * @param n
   *          - the normal.
   * @param material
   *          - the Material.
   */
  public PlainImpl(final Point3D a, final Normal n, Material material) {
    if (a == null) {
      throw new IllegalArgumentException("The value for the known point a in PlainImpl mustn't be null");
    }
    if (n == null) {
      throw new IllegalArgumentException("The value for the normal n in PlainImpl mustn't be null");
    }
    if (material == null) { // Set standard material
      material = new MaterialImpl(new RGBColor(100f));
    }
    this.a = a;
    this.n = n;
    this.mat = material;
  }

  @Override
  public Material getMaterial() {
    return this.mat;
  }

  @Override
  public Maybe<ShadeRecord> hit(Ray ray) {
    ArrayList<ShadeRecord> shadeRecords = new ArrayList<ShadeRecord>();
    final double firstTerm = this.a.sub(ray.getOrigin()).dot(this.n);
    final double secondTerm = ray.getDirection().dot(this.n.asVector());
    final double t = firstTerm / secondTerm;

    if (!Double.isNaN(t)) {
      Point3D p = ray.at(t);
      shadeRecords.add(new ShadeRecord(t, p, this.n, this, ray));
    }

    return Maybe.listToMaybe(shadeRecords);

  }

}
