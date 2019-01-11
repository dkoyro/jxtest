package by.dk.jx.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Optional;
import java.util.jar.Manifest;

public class ManifestService implements IManifestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(IManifestService.class);

	private static final String DEFAULT_MANIFEST_LOCATION = "META-INF/MANIFEST.MF";
	private static final String DEFAULT_VENDOR_ID = "by.dk.jx.test";


	private Optional<Manifest> manifest = loadManifest();

	private Optional<Manifest> loadManifest() {
		try {
			return findManifest(IManifestService.class.getClassLoader().getResources(DEFAULT_MANIFEST_LOCATION));
		}
		catch (IOException e) {
			LOGGER.info(String.format("Error reading %s: %s", DEFAULT_MANIFEST_LOCATION, e.getMessage()));
			return Optional.empty();
		}
	}

	private Optional<Manifest> findManifest(Enumeration<URL> resources) throws IOException {
		while (resources.hasMoreElements()) {
			try (InputStream is = resources.nextElement().openStream()) {
				Manifest manifest = new Manifest(is);
				if (DEFAULT_VENDOR_ID.equals(manifest.getMainAttributes().getValue(IMPLEMENTATION_VENDOR_ID))) {
					return Optional.of(manifest);
				}
			}
		}
		LOGGER.info("No manifest found at " + DEFAULT_MANIFEST_LOCATION);
		return Optional.empty();
	}

	private Manifest manifest() {
		return manifest.orElseGet(Manifest::new);
	}

	public String getImplementationTitle() {
		return manifest().getMainAttributes().getValue(IMPLEMENTATION_TITLE);
	}

	public String getImplementationVersion() {
		return manifest().getMainAttributes().getValue(IMPLEMENTATION_VERSION);
	}

	public String getImplementationVendorId() {
		return manifest().getMainAttributes().getValue(IMPLEMENTATION_VENDOR_ID);
	}

	public String getBuiltBy() {
		return manifest().getMainAttributes().getValue(BUILT_BY);
	}

	public String getCreatedBy() {
		return manifest().getMainAttributes().getValue(CREATED_BY);
	}
}
