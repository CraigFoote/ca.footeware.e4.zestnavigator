package ca.footeware.e4.zestnavigator.parts;

import java.io.InputStream;

import org.eclipse.jface.resource.DeviceResourceDescriptor;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

public class ImageDeviceResourceDescriptor extends DeviceResourceDescriptor<Image> {

	private final String imagePath;

	public ImageDeviceResourceDescriptor(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public Image createResource(Device device) {
		InputStream stream = getClass().getResourceAsStream(imagePath);
        if (stream == null) {
            throw new IllegalArgumentException("Resource not found: " + imagePath);
        }
        return new Image(device, stream);
	}

	@Override
	public void destroyResource(Object previouslyCreatedObject) {
		if (previouslyCreatedObject instanceof Image image && !image.isDisposed()) {
			image.dispose();
		}
	}
}
