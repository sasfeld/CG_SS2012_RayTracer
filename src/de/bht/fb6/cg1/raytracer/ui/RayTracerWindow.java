package de.bht.fb6.cg1.raytracer.ui;

import de.bht.fb6.cg1.raytracer.Tracer;
import de.bht.fb6.cg1.raytracer.scene.Camera;
import de.bht.fb6.cg1.raytracer.scene.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * This class opens a windows that contains a ray tracer canvas.
 *
 * @author Stephan Rehfeld
 */
public class RayTracerWindow extends JFrame {

    /**
     * This constructor creates a new ray tracer window.
     *
     * @param tracer The tracer to use. Must not be null.
     * @param world  The world to render. Must not be null.
     * @param camera  The camera to use. Must not be null.
     */
    public RayTracerWindow( final Tracer tracer, final World world, final Camera camera ) {
        super("Ray Tracer");
        if( tracer == null ) throw new IllegalArgumentException( "The parameter 'tracer' must not be null." );
        if( world == null ) throw new IllegalArgumentException( "The parameter 'world' must not be null." );
        if( camera == null ) throw new IllegalArgumentException( "The parameter 'camera' must not be null." );

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 800, 600 );

        final JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);


        JMenuItem renderMenuItem = new JMenuItem( "Render" );
        JMenuItem setThreadsMenuItem = new JMenuItem( "Set Threads" );
        JMenuItem saveImageMenuItem = new JMenuItem( "Save Image" );

        menuBar.add( renderMenuItem );
        menuBar.add( setThreadsMenuItem );
        menuBar.add( saveImageMenuItem );

        this.setVisible( true );
        final RayTracerCanvas c = new RayTracerCanvas( tracer, world, camera );
        c.setSize( this.getWidth(), this.getHeight() );
        c.setLocation(0, 0);
        this.setLayout(new GridLayout(1, 1));
        this.add(c);

        renderMenuItem.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        c.renderOnNextRedraw();
                        c.repaint();
                    }
                }
        );

        setThreadsMenuItem.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String threads = JOptionPane.showInputDialog( "Number of Threads" );
                        c.setThreads( Integer.parseInt( threads ) );

                    }
                }
        );

        final JFrame me = this;
        saveImageMenuItem.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final JFileChooser fc = new JFileChooser();
                        final int returnVal = fc.showOpenDialog( me );
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            final File file = fc.getSelectedFile();
                            try {
                                ImageIO.write(c.getImage(), "png", file);
                            } catch (IOException e1) {
                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }


                    }
                }
        );
    }

}
