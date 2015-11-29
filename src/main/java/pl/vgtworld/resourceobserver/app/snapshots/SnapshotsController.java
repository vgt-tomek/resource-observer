package pl.vgtworld.resourceobserver.app.snapshots;

import pl.vgtworld.resourceobserver.core.ctm.ContentTypeMapper;
import pl.vgtworld.resourceobserver.core.ctm.ResourceContentType;
import pl.vgtworld.resourceobserver.services.storage.SnapshotService;
import pl.vgtworld.resourceobserver.storage.snapshot.Snapshot;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/snapshots")
public class SnapshotsController {

	@EJB
	private SnapshotService snapshotService;

	@GET
	@Path("/download/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadSnapshot(@PathParam("id") int id) {
		Snapshot snapshot = snapshotService.findById(id);

		if (snapshot == null) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}

		ContentTypeMapper contentTypeMapper = new ContentTypeMapper();
		ResourceContentType contentType = contentTypeMapper.findContentTypeForResource(snapshot.getResource());

		return Response
			  .ok(snapshot.getResource())
			  .header("Content-Type", contentType.getName())
			  .header("Content-disposition", "filename=\"" + contentType.createFilename("snapshot-" + snapshot.getId()) + "\"")
			  .build();
	}

}
