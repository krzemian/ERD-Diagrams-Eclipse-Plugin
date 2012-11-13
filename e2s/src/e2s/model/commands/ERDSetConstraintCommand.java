package e2s.model.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import e2s.model.SpaceAwareModel;

/**
 * A command to move elements.
 * Based on Elias Volanakis's GEF Shape Plugin Example.
 */
public class ERDSetConstraintCommand extends Command {
	private final Rectangle newBounds;
	private Rectangle oldBounds;
	private final ChangeBoundsRequest request;

	private final SpaceAwareModel element;

	public ERDSetConstraintCommand(SpaceAwareModel element, ChangeBoundsRequest req,
			Rectangle newBounds) {
		if (element == null || req == null || newBounds == null) {
			throw new IllegalArgumentException();
		}
		this.element = element;
		this.request = req;
		this.newBounds = newBounds.getCopy();
		setLabel("move / resize");
	}
	
	public boolean canExecute() {
		Object type = request.getType();
		return (RequestConstants.REQ_MOVE.equals(type)
				|| RequestConstants.REQ_MOVE_CHILDREN.equals(type)
				|| RequestConstants.REQ_RESIZE.equals(type) || RequestConstants.REQ_RESIZE_CHILDREN
				.equals(type));
	}

	public void execute() {
		oldBounds = new Rectangle(element.getLocation(), element.getSize());
		redo();
	}
	
	public void redo() {
		element.setSize(newBounds.getSize());
		element.setLocation(newBounds.getLocation());
	}

	public void undo() {
		element.setSize(oldBounds.getSize());
		element.setLocation(oldBounds.getLocation());
	}
}
