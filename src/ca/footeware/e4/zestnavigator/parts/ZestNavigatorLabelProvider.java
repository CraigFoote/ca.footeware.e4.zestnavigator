package ca.footeware.e4.zestnavigator.parts;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.graphics.Image;

public class ZestNavigatorLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof TreePath treePath) {
			Object lastSegment = treePath.getLastSegment();
			if (lastSegment instanceof File file) {
				String name = file.getName();
				return name.isEmpty() ? "/" : name;
			}
		} else if (element instanceof File file) {
			return file.getName();
		} else if (element instanceof File[] files) {
			return String.valueOf(files.length);
		}
		return null;
	}
}
