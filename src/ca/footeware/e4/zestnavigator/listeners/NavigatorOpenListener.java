package ca.footeware.e4.zestnavigator.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;

/**
 * @author Footeware.ca
 */
public class NavigatorOpenListener implements IOpenListener {

	@Override
	public void open(OpenEvent event) {
		if (event.getSelection() instanceof IStructuredSelection selection) {
			File file = (File) selection.getFirstElement();
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.OPEN)) {
					try {
						desktop.open(file);
					} catch (IOException e) {
						// TODO notify user
					}
				}
			}
		}
	}
}