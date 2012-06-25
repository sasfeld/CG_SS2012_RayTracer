package de.bht.fb6.cg1.s778455.raytracer.raytracerimpl;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point3D;
import de.bht.fb6.cg1.raytracer.math.Point3DImpl;
import de.bht.fb6.cg1.raytracer.math.Vector3DImpl;
import de.bht.fb6.cg1.raytracer.scene.Camera;
import de.bht.fb6.cg1.raytracer.scene.World;
import de.bht.fb6.cg1.raytracer.ui.RayTracerWindow;

/**
 * This class is the central control.
 * 
 * @author Sascha Feldmann (s778455) - Beuth Hochschule für Technik
 * 
 */
public class RayTracerMain {

  public static void main(String[] args) {
    World world = new World(new RGBColor(0f, 0f, 0f));
    Point3D a = new Point3DImpl(5, 0, 60);
    // GeometricObject geometricObject = new PlainImpl(a, new NormalImpl(0f, 1f,
    // 0f), new MaterialImpl(new RGBColor(100f)));
    // world.add(geometricObject);
    Tracer tracer = new TracerImpl(world);
    Camera camera = new CameraImpl(new Point3DImpl(0, 0, 0), new Point3DImpl(0, 0, 1), new Vector3DImpl(1, 1, 0));
    RayTracerWindow window = new RayTracerWindow(tracer, world, camera);

  }
}
