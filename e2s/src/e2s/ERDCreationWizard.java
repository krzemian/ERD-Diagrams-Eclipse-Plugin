package e2s;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

import e2s.model.ERDDiagramModel;

/**
 * Create new .erd file for use with ERD2SQL Editor. 
 * Based on Elias Volanakis's GEF Shape Plugin Example.
 */
public class ERDCreationWizard extends Wizard implements INewWizard {

	private static int fileCount = 1;
	private CreationPage page1;

	public void addPages() {
		// add pages to this wizard
		addPage(page1);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// create pages for this wizard
		page1 = new CreationPage(workbench, selection);
	}

	public boolean performFinish() {
		return page1.finish();
	}
	
	private class CreationPage extends WizardNewFileCreationPage {
		private static final String DEFAULT_EXTENSION = ".erd";
		private final IWorkbench workbench;

		CreationPage(IWorkbench workbench, IStructuredSelection selection) {
			super("erdCreationPage1", selection);
			this.workbench = workbench;
			setTitle("Stwórz nowy plik " + DEFAULT_EXTENSION);
			setDescription("Stwórz nowy plik " + DEFAULT_EXTENSION);
		}

		public void createControl(Composite parent) {
			super.createControl(parent);
			setFileName("erd" + fileCount + DEFAULT_EXTENSION);
			setPageComplete(validatePage());
		}

		private Object createDefaultContent() {
			return new ERDDiagramModel();
		}
		
		boolean finish() {
			// create a new file, result != null if successful
			IFile newFile = createNewFile();
			fileCount++;

			// open newly created file in the editor
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
					.getActivePage();
			if (newFile != null && page != null) {
				try {
					IDE.openEditor(page, newFile, true);
				} catch (PartInitException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}

		protected InputStream getInitialContents() {
			ByteArrayInputStream bais = null;
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(createDefaultContent()); // argument must be
															// Serializable
				oos.flush();
				oos.close();
				bais = new ByteArrayInputStream(baos.toByteArray());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			return bais;
		}

		private boolean validateFilename() {
			if (getFileName() != null
					&& getFileName().endsWith(DEFAULT_EXTENSION)) {
				return true;
			}
			setErrorMessage("Plik musi posiadaæ rozszerzenie "
					+ DEFAULT_EXTENSION);
			return false;
		}

		protected boolean validatePage() {
			return super.validatePage() && validateFilename();
		}
	}
}
