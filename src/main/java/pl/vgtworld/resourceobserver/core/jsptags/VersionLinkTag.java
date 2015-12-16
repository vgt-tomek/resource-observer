package pl.vgtworld.resourceobserver.core.jsptags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.resourceobserver.services.dto.ResourceVersion;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.awt.Color;
import java.io.IOException;

public class VersionLinkTag extends SimpleTagSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(VersionLinkTag.class);

	private static final double PERCEPTIVE_LUMINANCE_THRESHOLD = 0.5;
	public static final double PERCEPTIVE_LUMINANCE_RED_RATIO = 0.299;
	public static final double PERCEPTIVE_LUMINANCE_GREEN_RATIO = 0.587;
	public static final double PERCEPTIVE_LUMINANCE_BLUE_RATIO = 0.114;

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
			String fontColor = colorToHex(createFontColorForBackground(version.getBackgroundColor()));
			String style = " style=\"color:#" + fontColor + "; background-color:#" + backgroundColor + ";\" ";
			String linkClass = " class=\"btn btn-default btn-xs btn-version\" ";
			out.print(
				  "<a " + linkClass + style + " href=\"" + hrefUrl + "\">Version #" + version.getVersionId() + "</a>"
			);
			return;
		}
		LOGGER.warn("Version not provided. Unable to create output.");
	}

	private static String colorToHex(Color color) {
		return nbrToHex(color.getRed()) + nbrToHex(color.getGreen()) + nbrToHex(color.getBlue());
	}

	private static Color createFontColorForBackground(Color background) {
		double redLuminance = PERCEPTIVE_LUMINANCE_RED_RATIO * background.getRed();
		double greenLuminance = PERCEPTIVE_LUMINANCE_GREEN_RATIO * background.getGreen();
		double blueLuminance = PERCEPTIVE_LUMINANCE_BLUE_RATIO * background.getBlue();
		double perceptiveLuminance = 1 - (redLuminance + greenLuminance + blueLuminance) / 255;
		if (perceptiveLuminance < PERCEPTIVE_LUMINANCE_THRESHOLD) {
			return Color.BLACK;
		}
		return Color.WHITE;
	}

	private static String nbrToHex(int number) {
		String result = Integer.toHexString(number);
		if (result.length() == 1) {
			return "0" + result;
		}
		return result;
	}

}
