package e2s.views;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ToolbarLayout;

/**
 * Figure used to hold the column labels
 * Based on Database Schema Diagram Editor.
 */
public class ColumnsFigure extends Figure
{

	public ColumnsFigure()
	{
		ToolbarLayout layout = new ToolbarLayout();

		layout.setSpacing(3);
		setLayoutManager(layout);

	}
}