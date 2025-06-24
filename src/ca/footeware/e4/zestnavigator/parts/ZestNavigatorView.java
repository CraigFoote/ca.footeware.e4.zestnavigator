package ca.footeware.e4.zestnavigator.parts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.nebula.widgets.opal.notifier.Notifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;

public class ZestNavigatorView {

	private GraphViewer viewer;

	@PostConstruct
	public void createPartControl(Composite parent) {
		viewer = new GraphViewer(parent, SWT.NONE);
		viewer.setContentProvider(new TreePathContentProvider());
		viewer.setLabelProvider(new ZestNavigatorLabelProvider());
		viewer.setLayoutAlgorithm(new SpringLayoutAlgorithm());
		viewer.addDoubleClickListener(event -> {
			if (event.getSelection() instanceof IStructuredSelection selection
					&& selection.getFirstElement() instanceof File file && Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.OPEN)) {
					try {
						desktop.open(file);
					} catch (IOException e) {
						Notifier.notify("Error", e.getLocalizedMessage());
					}
				}
			}
		});
	}

	@Focus
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object o) {
		if (viewer != null && o instanceof ITreeSelection selection) {
			viewer.setInput(selection);
		}
	}
}
