package by.dk.jx.test;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class ApplicationProvider implements BQModuleProvider {

	@Override
	public Module module() {
		return new Application();
	}
}