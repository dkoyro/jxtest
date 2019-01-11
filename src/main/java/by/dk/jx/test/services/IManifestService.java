package by.dk.jx.test.services;

public interface IManifestService {
	String IMPLEMENTATION_TITLE = "Implementation-Title";
	String IMPLEMENTATION_VERSION = "Implementation-Version";
	String IMPLEMENTATION_VENDOR_ID = "Implementation-Vendor-Id";
	String BUILT_BY = "Built-By";
	String CREATED_BY = "Created-By";

	String getImplementationTitle();

	String getImplementationVersion();

	String getImplementationVendorId();

	String getBuiltBy();

	String getCreatedBy();
}