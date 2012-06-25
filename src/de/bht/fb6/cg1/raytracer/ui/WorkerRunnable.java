package de.bht.fb6.cg1.raytracer.ui;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.color.RGBColor;
import de.bht.fb6.cg1.raytracer.math.Point2D;
import de.bht.fb6.cg1.raytracer.math.Ray;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Non-public class für multi threading.
 */
class WorkerRunnable implements Runnable {

    private final Map<Point2D, Ray > pixelToRays;
    private final ConcurrentLinkedQueue< PointToColor > queue;
    private final Tracer tracer;

    public WorkerRunnable( final Map<Point2D, Ray > pixelToRays, final ConcurrentLinkedQueue< PointToColor > queue, final Tracer tracer ) {
        if( pixelToRays == null ) throw new IllegalArgumentException( "The parameter 'pixelToRay' must not be 'null'!" );
        if( queue == null ) throw new IllegalArgumentException( "The parameter 'queue' must not be 'null'!" );
        if( tracer == null ) throw new IllegalArgumentException( "The parameter 'tracer' must not be 'null'!" );
        this.pixelToRays = pixelToRays;
        this.queue = queue;
        this.tracer = tracer;
    }

    @Override
    public void run() {
        final Set<Point2D> points = this.pixelToRays.keySet();
        for( final Point2D point : points ) {
            final Ray ray = this.pixelToRays.get( point );
            queue.add( new PointToColor( point, this.tracer.traceRay( ray ) ) );
        }
    }
}
