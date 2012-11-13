package e2s;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The plugin class (singleton). Basically not used in this pj.
 * Based on Elias Volanakis's GEF Shape Plugin Example.
 */
public class ERDPlugin extends AbstractUIPlugin {

	private static ERDPlugin singleton;

	public static ERDPlugin getDefault() {
		return singleton;
	}

	public ERDPlugin() {
		if (singleton == null) {
			singleton = this;
		}
	}

}