package ca.footeware.e4.zestnavigator.parts;

import java.io.File;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.graphics.Image;

public class ZestNavigatorLabelProvider extends LabelProvider {

	private ResourceManager resourceManager;

	@Override
	public Image getImage(Object element) {
		if (element instanceof TreePath treePath) {
			Object lastSegment = treePath.getLastSegment();
			if (lastSegment instanceof File file) {
				return getImage(file);
			}
		} else if (element instanceof File file) {
			return getImage(file);
		} else if (element instanceof File[]) {
			return null;
		}
		return super.getImage(element);
	}

	private Image getImage(File file) {
		ImageDeviceResourceDescriptor descriptor = null;
		if (file.isDirectory()) {
			descriptor = new ImageDeviceResourceDescriptor("/icons/folder.png");
		} else {
			descriptor = new ImageDeviceResourceDescriptor("/icons/file_obj.png");
		}
		return getResourceManager().create(descriptor);
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

	private ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return resourceManager;
	}
}
