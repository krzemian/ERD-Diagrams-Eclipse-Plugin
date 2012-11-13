
package e2s.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import e2s.model.Column;
import e2s.model.ERDDiagramModel;
import e2s.model.Table;

/**
 * Factory that maps model elements to edit parts.
 * 
 * Based on GEF Shape Plugin Example.
 */
public class ERDEditPartFactory implements EditPartFactory {

	
	public EditPart createEditPart(EditPart context, Object modelElement) {

		EditPart part = getPartForElement(modelElement);

		part.setModel(modelElement);
		return part;
	}

	
	private EditPart getPartForElement(Object modelElement) {
		if (modelElement instanceof ERDDiagramModel) {
			return new ERDDiagramEditPart();
		}
		else if (modelElement instanceof Column) {
			return new ColumnPart();
		}
		else if (modelElement instanceof Table) {
			return new TablePart();
		}
		throw new RuntimeException("Can't create part for model element: "
				+ ((modelElement != null) ? modelElement.getClass().getName()
						: "null"));
	}

}