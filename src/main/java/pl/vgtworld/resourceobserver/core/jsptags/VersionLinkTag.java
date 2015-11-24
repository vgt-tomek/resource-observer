package pl.vgtworld.resourceobserver.core.jsptags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.app.resources.models.details.ResourceVersion;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.awt.Color;
import java.io.IOException;

public class VersionLinkTag extends SimpleTagSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(VersionLinkTag.class);

	private ResourceVersion version;

	public void setVersion(ResourceVersion version) {
		this.version = version;
	}


	@Override
	public void doTag() throws JspException, IOException {
		PageContext context = (PageContext) getJspContext();
		String contextPath = context.getServletContext().getContextPath();
		JspWriter out = context.getOut();

		if (version != null) {
			String hrefUrl = contextPath + "/app/snapshots/download/" + version.getSnapshotId();
			String backgroundColor = colorToHex(version.getBackgroundColor());
			String style = " style=\"background-color:#" + backgroundColor + ";\" ";
			String linkClass = " class=\"btn btn-default btn-xs\" ";
			out.print(
				  "<a " + linkClass + style + " href=\"" + hrefUrl + "\">Version #" + version.getVersionId() + "</a>"
			);
			return;
		}
		LOGGER.warn("Version not provided. Unable to create output.");
	}

	private String colorToHex(Color color) {
		return nbrToHex(color.getRed()) + nbrToHex(color.getGreen()) + nbrToHex(color.getBlue());
	}

	private String nbrToHex(int number) {
		String result = Integer.toHexString(number);
		if (result.length() == 1) {
			return "0" + result;
		}
		return result;
	}

}
