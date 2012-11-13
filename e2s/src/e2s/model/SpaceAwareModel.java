package e2s.model;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;

import e2s.ERDPlugin;

/**
 * Abstract prototype of a model, used to locate a model's representation in space.
 * Based on Elias Volanakis's GEF Shape Plugin Example.
 */
public abstract class SpaceAwareModel extends PropertyAwareModel {

	private static final long serialVersionUID = 1;


	protected static Image createImage(String name) {
		InputStream stream = ERDPlugin.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	private Point location = new Point(0, 0);
	private Dimension size = new Dimension(50, 50);

	public abstract Image getIcon();

	public Point getLocation() {
		return location.getCopy();
	}

	public Dimension getSize() {
		return size.getCopy();
	}

	public void setLocation(Point newLocation) {
		if (newLocation == null) {
			throw new IllegalArgumentException();
		}
		location.setLocation(newLocation);
		firePropertyChange(SELF, null, location);
	}

	public void setSize(Dimension newSize) {
		if (newSize != null) {
			size.setSize(newSize);
			firePropertyChange(SELF, null, size);
		}
	}
}