package de.bht.fb6.cg1.raytracer.scene;

import java.util.ArrayList;
import java.util.List;

import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.scene.light.AmbientLight;
import de.bht.fb6.cg1.raytracer.scene.light.Light;

/**
 * This class represents the world that should be ray traced. It contains the
 * objects and the lights of the scene.
 * 
 * @author Stephan Rehfeld
 */
public class World {

  /**
   * The list of all geometries in the scene.
   */
  private final List<GeometricObject> objects;

  /**
   * The list of all ambient lights in the scene.
   */
  private final List<AmbientLight> ambientLights;

  /**
   * The list of all non ambient lights in the scene.
   */
  private final List<Light> lights;

  /**
   * The background color.
   */
  public final RGBColor backgroundColor;

  /**
   * This constructor creates a new world with a given background color. The
   * background color is used if a ray doesn't hit any object.
   * 
   * @param backgroundColor
   *          The background color of the world. Must not be null.
   */
  public World(final RGBColor backgroundColor) {
    if (backgroundColor == null) {
      throw new IllegalArgumentException("The parameter 'backgroundColor' must not be null!");
    }
    this.objects = new ArrayList<>();
    this.ambientLights = new ArrayList<>();
    this.lights = new ArrayList<>();
    this.backgroundColor = backgroundColor;
  }

  /**
   * This method returns the amount of objects in the scene.
   * 
   * @return The amount of objects in the scene.
   */
  public int size() {
    return objects.size();
  }

  /**
   * This method adds an object to the scene.
   * 
   * @param geometricObject
   *          The object. Must not be null.
   * @return 'true' if the object was successfully added.
   */
  public boolean add(final GeometricObject geometricObject) {
    return objects.add(geometricObject);
  }

  /**
   * This method adds an ambient light to the scene.
   * 
   * @param ambientLight
   *          The ambient light. Must not be null.
   * @return 'true' if the ambientLight was successfully added.
   */
  public boolean add(final AmbientLight ambientLight) {
    return ambientLights.add(ambientLight);
  }

  /**
   * This method adds a light to the scene.
   * 
   * @param light
   *          The light. Must not be null.
   * @return 'true' if the light was successfully added.
   */
  public boolean add(Light light) {
    return lights.add(light);
  }

  /**
   * This method returns an object at the given position.
   * 
   * @param i
   *          The position.
   * @return The object.
   */
  public GeometricObject get(final int i) {
    return objects.get(i);
  }

  /**
   * This method returns a list of all ambient lights.
   * 
   * @return A list of all ambient lights.
   */
  public List<AmbientLight> getAmbientLights() {
    return this.ambientLights;
  }

  /**
   * This method returns a list of all lights.
   * 
   * @return A list of all lights.
   */
  public List<Light> getLights() {
    return this.lights;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    World world = (World) o;

    if (ambientLights != null ? !ambientLights.equals(world.ambientLights) : world.ambientLights != null) {
      return false;
    }
    if (backgroundColor != null ? !backgroundColor.equals(world.backgroundColor) : world.backgroundColor != null) {
      return false;
    }
    if (lights != null ? !lights.equals(world.lights) : world.lights != null) {
      return false;
    }
    if (objects != null ? !objects.equals(world.objects) : world.objects != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = objects != null ? objects.hashCode() : 0;
    result = 31 * result + (ambientLights != null ? ambientLights.hashCode() : 0);
    result = 31 * result + (lights != null ? lights.hashCode() : 0);
    result = 31 * result + (backgroundColor != null ? backgroundColor.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "World{" + "objects=" + objects + ", ambientLights=" + ambientLights + ", lights=" + lights
        + ", backgroundColor=" + backgroundColor + '}';
  }
}
