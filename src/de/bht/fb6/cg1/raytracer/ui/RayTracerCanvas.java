package de.bht.fb6.cg1.raytracer.ui;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.math.*;
import de.bht.fb6.cg1.raytracer.scene.Camera;
import de.bht.fb6.cg1.raytracer.scene.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Non-public ray tracer canvas.
 */
class RayTracerCanvas extends Canvas {

    private boolean render = true;
    private Color[][] frameBuffer = new Color[1][1];
    private BufferedImage image = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );

    private final Tracer tracer;
    private final World world;
    private final Camera camera;

    private int threads = 8;

    public RayTracerCanvas( final Tracer tracer, final World world, final Camera camera ) {
        if( tracer == null ) throw new IllegalArgumentException( "The parameter 'tracer' must not be null." );
        if( world == null ) throw new IllegalArgumentException( "The parameter 'world' must not be null." );
        if( camera == null ) throw new IllegalArgumentException( "The parameter 'camera' must not be null." );

        this.tracer = tracer;
        this.world = world;
        this.camera = camera;
    }

    private void render( final Graphics g ) {

        final long start = System.currentTimeMillis();

        frameBuffer = new Color[this.getWidth()][this.getHeight()];

        for( int i = 0; i < this.getWidth(); ++i ) {
            for( int j = 0; j < this.getHeight(); ++j ) {
                frameBuffer[i][j] = Color.white;
            }
        }

        System.out.print("Generating all rays: ");
        final Map<Point2D, Ray > pixelsToRays = camera.generateRaysFor( this.getWidth(), this.getHeight() );
        System.out.println("Done");

        final Vector< Map<Point2D, Ray > > workSets = new Vector<>( this.threads );
        final Vector< Point2D > points = new Vector( pixelsToRays.keySet() );
        for( int i = 0; i < this.threads; ++i ) {
            workSets.add( new HashMap<Point2D, Ray >() );
        }

        for( int i = 0; i < points.size(); ++i ) {
            workSets.get( i % this.threads ).put( points.get( i ), pixelsToRays.get( points.get( i ) ) );
        }

        final ConcurrentLinkedQueue< PointToColor > queue = new ConcurrentLinkedQueue< PointToColor >();

        for( int i = 0; i < this.threads; ++i ) {
            final Runnable workerRunnable = new WorkerRunnable( workSets.get( i ), queue, tracer );
            final Thread workerThread = new Thread( workerRunnable );
            workerThread.start();
        }

        final int pixel = points.size();
        int c = 0;

        while( c < pixel ) {
            final PointToColor data = queue.poll();
            if( data != null ) {
                final Color color = data.getColor().asAwtColor();
                final Point2D point = data.getPoint();
                g.setColor( color );
                g.drawLine( (int)point.getX(), this.getHeight() -1 - (int)point.getY(), (int)point.getX(), this.getHeight() -1 - (int)point.getY() );

                this.frameBuffer[(int)point.getX()][this.getHeight() -1 - (int)point.getY()] = color;
                ++c;
            } else {
                try {
                    Thread.sleep(1000);

                } catch( final InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }

        this.image = new BufferedImage( this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB );
        final WritableRaster raster = this.image.getRaster();
        final ColorModel model = this.image.getColorModel();

        for( int i = 0; i < this.getWidth(); ++i ) {
            for( int j = 0; j < this.getHeight(); ++j ) {
                raster.setDataElements( i, j, model.getDataElements( this.frameBuffer[i][j].getRGB(), null ) );
            }
        }
        g.drawImage(this.image, 0, 0, this);

        this.render = false;

        System.out.println( "Rendered image in " + ((double)(System.currentTimeMillis() - start)/1000) + "s." );

    }

    @Override
    public void paint( final Graphics g ) {
        super.paint( g );

        if( this.render ) {
            render( g );
        } else {
            g.drawImage(this.image, 0, 0, this);
        }
    }

    public void renderOnNextRedraw() {
        this.render = true;
    }

    public int getThreads() {
        return this.threads;
    }

    public void setThreads( final int threads ) {
        if( threads < 1 ) throw new IllegalArgumentException( "The parameter 'threads' must be at least 1!" );
        this.threads = threads;
    }

    public RenderedImage getImage() {
        return this.image;
    }

}
