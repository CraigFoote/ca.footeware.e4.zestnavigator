
package ca.footeware.e4.zestnavigator.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class ShowViewHandler {

	private String partId = "ca.footeware.e4.partdescriptor.zestgraph";

	@Execute
	public void execute(MApplication app, EPartService partService) {
		MPlaceholder placeholder = partService.createSharedPart(partId);
		MPart part = (MPart) placeholder.getRef();
		partService.showPart(part, PartState.ACTIVATE);
	}

}