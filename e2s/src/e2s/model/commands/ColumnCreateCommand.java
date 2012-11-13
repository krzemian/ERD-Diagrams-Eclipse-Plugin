
package e2s.model.commands;

import org.eclipse.gef.commands.Command;

import e2s.model.Column;
import e2s.model.Table;

/**
 * Command to create a new table
 * 
 * Based on Database Schema Diagram Editor.
 */
public class ColumnCreateCommand extends Command
{

	private Column column;
	private Table table;

	public void setColumn(Column column)
	{
		this.column = column;
		this.column.setName("kolumna" + (table.getColumns().size() + 1));
		this.column.setType("VARCHAR");
	}

	public void setTable(Table table)
	{
		this.table = table;
	}

	public void execute()
	{
		table.addColumn(column);
	}

	public void undo()
	{
		table.removeColumn(column);
	}

}