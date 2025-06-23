package ca.footeware.e4.zestnavigator.parts;

import java.io.File;

import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

class TreePathContentProvider implements IGraphEntityContentProvider {

	@Override
	public Object[] getElements(Object input) {
		if (input instanceof ITreeSelection selection) {
			return selection.getPaths(); // TreePath[]
		}
		return new Object[0];
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if (entity instanceof TreePath treePath) {
			Object lastSegment = treePath.getLastSegment();
			if (lastSegment instanceof File file && file.isDirectory()) {
				return file.listFiles();
			}
		}
		return new Object[0];
	}
}