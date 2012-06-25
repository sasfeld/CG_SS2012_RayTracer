/**
 * 
 */
package de.bht.fb6.cg1.s778455.raytracer.raytracerimpl;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.scene.Material;
import de.bht.fb6.cg1.raytracer.scene.ShadeRecord;

/**
 * An material implementation.
 * 
 * @author Sascha Feldmann (s778455) - Beuth Hochschule für Technik
 * 
 */
public class MaterialImpl implements Material {

  private final RGBColor basicColor;

  /**
   * Create a new material.
   * 
   * @param basicColor
   *          - the color of the (easy) material.
   */
  public MaterialImpl(final RGBColor basicColor) {
    this.basicColor = basicColor;
  }

  @Override
  public RGBColor getColorAt(final ShadeRecord shadeRecord, final Tracer tracer) {
    if (shadeRecord == null) {
      throw new IllegalArgumentException("The value for the ShadeRecord shadeRecord in MaterialImpl mustn't be null");
    }
    if (tracer == null) {
      throw new IllegalArgumentException("The value for the Tracer tracer in MaterialImpl mustn't be null");
    }
    return this.basicColor;
  }

}
