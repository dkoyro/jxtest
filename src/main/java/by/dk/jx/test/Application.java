package by.dk.jx.test;

import by.dk.jx.test.rest.TestResource;
import com.google.inject.Binder;
import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.ConfigModule;
import io.bootique.jersey.JerseyModule;

public class Application extends ConfigModule {

	public static void main(String[] args) {
		Bootique.main(args);
	}

	@Override
	protected String defaultConfigPrefix() {
		return "jx";
	}

	@Override
	public void configure(Binder binder) {
		BQCoreModule.extend(binder).addConfig("classpath:by/dk/jx/test/config.yml");
		JerseyModule.extend(binder).addResource(TestResource.class);
	}
}