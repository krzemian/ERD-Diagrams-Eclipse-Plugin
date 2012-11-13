package e2s.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import e2s.handlers.SQLHandler;

/**
 * A diagram - root - model.
 * Based on Elias Volanakis's GEF Shape Plugin Example.
 **/
public class ERDDiagramModel extends PropertyAwareModel {
	
	public static final String DATA_RES = "DIAGRAM";
	/** Property ID to use when a child is added to this diagram. */
	public static final String CHILD_ADDED_PROP = "ShapesDiagram.ChildAdded";
	/** Property ID to use when a child is removed from this diagram. */
	public static final String CHILD_REMOVED_PROP = "ShapesDiagram.ChildRemoved";
	private static final long serialVersionUID = 1;
	private List<Table> shapes = new ArrayList<Table>();

	public boolean addChild(Table s) {
		if (s != null && shapes.add(s)) {
			firePropertyChange(CHILD_ADDED_PROP, null, s);
			return true;
		}
		return false;
	}

	public List<Table> getChildren() {
		return shapes;
	}

	public boolean removeChild(Table s) {
		if (s != null && shapes.remove(s)) {
			firePropertyChange(CHILD_REMOVED_PROP, null, s);
			return true;
		}
		return false;
	}
}