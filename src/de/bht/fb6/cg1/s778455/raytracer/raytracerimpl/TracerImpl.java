package de.bht.fb6.cg1.s778455.raytracer.raytracerimpl;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Ray;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;
import de.bht.fb6.cg1.raytracer.scene.World;
import de.bht.fb6.cg1.raytracer.util.Maybe;

/**
 * The implementation of the Tracer Interface.
 * 
 * @author Sascha Feldmann (s778455) - Beuth Hochschule für Technik
 * 
 */
public class TracerImpl implements Tracer {
  /**
   * The reference to the world instance.
   */
  private final World world;

  /**
   * Create a new tracer with a reference to a world.
   * 
   * @param world
   *          - the world instance.
   */
  public TracerImpl(final World world) {
    if (world == null) {
      throw new IllegalArgumentException("The parameter world in TracerImpl() mustn't be null.");
    }

    this.world = world;
  }

  @Override
  public RGBColor traceRay(final Ray ray) {
    if (ray == null) {
      throw new IllegalArgumentException("The parameter ray in traceRay() mustn't be null.");
    }
    if (!this.hitsAnything(ray)) {
      return world.backgroundColor;
    }
    for (int i = 0; i < this.world.size(); i++) {
      final Maybe<ShadeRecord> hit = this.world.get(i).hit(ray);
      if (hit.isJust()) {
        ShadeRecord shadeRec = hit.fromJust();
        Material hitMat = shadeRec.geometricObject.getMaterial();
        return hitMat.getColorAt(shadeRec, this);
      }
    }
    return world.backgroundColor;
  }

  @Override
  public boolean hitsAnything(final Ray ray) {
    if (ray == null) {
      throw new IllegalArgumentException("The parameter ray in hitsAnything() mustn't be null.");
    }
    for (int i = 0; i < this.world.size(); i++) {
      final Maybe<ShadeRecord> hit = this.world.get(i).hit(ray);
      if (hit.isJust()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isPointVisible(final Ray ray, final Point3D point) {
    if (ray == null) {
      throw new IllegalArgumentException("The parameter ray in hitsAnything() mustn't be null.");
    }
    if (point == null) {
      throw new IllegalArgumentException("The parameter point in isPointVisible() mustn't be null.");
    }
    if (this.hitsAnything(ray)) {

    }
    return false;
  }

  @Override
  public World getWorld() {
    return this.world;
  }

}
