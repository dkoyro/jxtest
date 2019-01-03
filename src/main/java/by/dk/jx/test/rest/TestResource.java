package by.dk.jx.test.rest;


import by.dk.jx.test.services.IManifestService;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.UnknownHostException;

import static by.dk.jx.test.services.IManifestService.BUILT_BY;
import static by.dk.jx.test.services.IManifestService.CREATED_BY;
import static by.dk.jx.test.services.IManifestService.IMPLEMENTATION_TITLE;
import static by.dk.jx.test.services.IManifestService.IMPLEMENTATION_VENDOR_ID;
import static by.dk.jx.test.services.IManifestService.IMPLEMENTATION_VERSION;

@Path("/")
public class TestResource {
	@Inject private IManifestService manifestService;

	@GET
	public String get() throws UnknownHostException {
//		InetAddress address = InetAddress.getLocalHost();

		return IMPLEMENTATION_TITLE + manifestService.getImplementationTitle() + "<br>" +
			   IMPLEMENTATION_VERSION + manifestService.getImplementationVersion() + "<br>" +
			   IMPLEMENTATION_VENDOR_ID + manifestService.getImplementationVendorId() + "<br>" +
			   BUILT_BY + manifestService.getBuiltBy() + "<br>" +
			   CREATED_BY + manifestService.getCreatedBy() + "<br>";
	}
}
