
package e2s.model.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import e2s.model.ERDDiagramModel;
import e2s.model.SpaceAwareModel;
import e2s.model.Table;

/**
 * A command to add a Shape to a ShapeDiagram. The command can be undone or
 * redone.
 * 
 * Based on GEF Shape Plugin Example.
 */
public class ERDCreateCommand extends Command {


	private Table newShape;
	
	private final ERDDiagramModel parent;

	private Rectangle bounds;

	
	public ERDCreateCommand(Table newShape, ERDDiagramModel parent,
			Rectangle bounds) {
		this.newShape = newShape;
		this.parent = parent;
		this.bounds = bounds;
		setLabel("shape creation");
	}

	
	public boolean canExecute() {
		return newShape != null && parent != null && bounds != null;
	}

	
	public void execute() {
		newShape.setLocation(bounds.getLocation());
		Dimension size = bounds.getSize();
		if (size.width > 0 && size.height > 0)
			newShape.setSize(size);
		redo();
	}

	
	public void redo() {
		parent.addChild(newShape);
	}

	
	public void undo() {
		parent.removeChild(newShape);
	}

}