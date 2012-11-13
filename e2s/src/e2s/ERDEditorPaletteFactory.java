package e2s;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.SimpleFactory;
import org.eclipse.jface.resource.ImageDescriptor;

import e2s.model.Column;
import e2s.model.Table;

/**
 * A class creating tools and component palette. 
 * Based on Elias Volanakis's GEF Shape Plugin Example.
 */
final class ERDEditorPaletteFactory {

	private static PaletteContainer createERDDrawer() {
		PaletteDrawer componentsDrawer = new PaletteDrawer("Elementy");

		CombinedTemplateCreationEntry component = new CombinedTemplateCreationEntry("Tabela",
				"Stwórz now¹ tabelê", Table.class,
				new SimpleFactory(Table.class),
				ImageDescriptor.createFromFile(ERDPlugin.class,
						"icons/table_16.gif"),
				ImageDescriptor.createFromFile(ERDPlugin.class,
						"icons/table_24.gif"));
		componentsDrawer.add(component);
		
		component = new CombinedTemplateCreationEntry(
				"Kolumna", "Dodaj kolumnê do istniej¹cej tabeli", Column.class,
				new SimpleFactory(Column.class),
				ImageDescriptor.createFromFile(ERDPlugin.class,
						"icons/column_16.gif"), ImageDescriptor.createFromFile(
						ERDPlugin.class, "icons/column_24.gif"));
		componentsDrawer.add(component);
		
		return componentsDrawer;
	}

	static PaletteRoot createPalette() {
		PaletteRoot palette = new PaletteRoot();
		palette.add(createToolsGroup(palette));
		palette.add(createERDDrawer());
		return palette;
	}

	private static PaletteContainer createToolsGroup(PaletteRoot palette) {
		PaletteToolbar toolbar = new PaletteToolbar("Narzêdzia");

		// Add a selection tool to the group
		ToolEntry tool = new PanningSelectionToolEntry();
		toolbar.add(tool);
		palette.setDefaultEntry(tool);

		// Add a marquee tool to the group
		toolbar.add(new MarqueeToolEntry());

		return toolbar;
	}

	private ERDEditorPaletteFactory() {	}

}