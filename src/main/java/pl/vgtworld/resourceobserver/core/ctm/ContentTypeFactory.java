package pl.vgtworld.resourceobserver.core.ctm;

import java.util.ArrayList;
import java.util.List;

final class ContentTypeFactory {

	private ContentTypeFactory() {
	}

	static List<ResourceContentType> createContentTypes() {
		List<ResourceContentType> list = new ArrayList<>();
		list.add(new ResourceContentType(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF}, "image/jpeg", "jpg"));
		list.add(new ResourceContentType(new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A}, "image/png", "png"));
		list.add(new ResourceContentType(new byte[]{0x3C, 0x3F, 0x58, 0x4D, 0x4C}, "application/xml", "xml"));
		list.add(new ResourceContentType(new byte[]{0x3C, 0x3F, 0x78, 0x6d, 0x6c}, "application/xml", "xml"));
		return list;
	}
}
