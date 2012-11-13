
package e2s.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import e2s.model.Column;
import e2s.model.PropertyAwareModel;
import e2s.policies.ERDComponentEditPolicy;
import e2s.views.ColumnFigure;

/**
 * EditPart used for Shape instances (more specific for EllipticalShape and
 * RectangularShape instances).
 * <p>
 * This edit part must implement the PropertyChangeListener interface, so it can
 * be notified of property changes in the corresponding model element.
 * </p>
 * 
 *Based on GEF Shape Plugin Example.
 */
public class ColumnPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {

	
	public void activate() {
		if (!isActive()) {
			super.activate();
			((PropertyAwareModel) getModel()).addPropertyChangeListener(this);
		}
	}

	
	protected void createEditPolicies() {
			
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
						new ERDComponentEditPolicy());
		
	}

	
	protected IFigure createFigure()
	{
		Column table = (Column) getModel();
		ColumnFigure tableFigure = new ColumnFigure("POCZ");

		return tableFigure;
	}	


	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((PropertyAwareModel) getModel()).removePropertyChangeListener(this);
		}
	}

	
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (Column.SELF.equals(prop)) {
			refreshVisuals();
		} 
	}

	protected void refreshVisuals() {	
		((ColumnFigure) getFigure()).setText(((Column)getModel()).toString());
	}
}